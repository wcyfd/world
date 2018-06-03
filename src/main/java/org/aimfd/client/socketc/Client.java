package org.aimfd.client.socketc;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public abstract class Client {

	private EventLoopGroup eventLoopGroup;
	private Bootstrap bootstrap;

	private Channel channel;
	private ExecutorService connectThread;

	private String ip;
	private int port;
	private int maxReconnectCount;
	private boolean firstConnect;

	private volatile boolean connected = false;
	private volatile boolean close = false;

	public Client(String ip, int port, int reconnectCount) {
		this.ip = ip;
		this.port = port;
		this.maxReconnectCount = reconnectCount;
		this.firstConnect = true;
		connectThread = Executors.newSingleThreadExecutor();

		bootstrap = new Bootstrap();
		eventLoopGroup = new NioEventLoopGroup();

		bootstrap.option(ChannelOption.TCP_NODELAY, true);
		bootstrap.option(ChannelOption.SO_REUSEADDR, true);
		bootstrap.option(ChannelOption.SO_RCVBUF, 43690);// 43690为默认值
		bootstrap.option(ChannelOption.TCP_NODELAY, true);

		bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel channel) throws Exception {
				ChannelPipeline pipeline = channel.pipeline();
				pipeline.addLast("decoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
				pipeline.addLast("stringdecoder", new StringDecoder());
				// pipeline.addLast("idle", new IdleStateHandler(0, 6, 0));

				// pipeline.addLast("heart-handler", new HeartHandler());

				pipeline.addLast("client-handler", new ClientHandler());

				pipeline.addLast("encoder", new LengthFieldPrepender(4, false));
				pipeline.addLast("stringencoder", new StringEncoder());

			}
		}).option(ChannelOption.SO_KEEPALIVE, true);

	}

	public boolean isConnected() {
		return connected;
	}

	protected void connect() {
		close = false;
		startConnectThread();
	}

	private void startConnectThread() {
		connectThread.execute(new Runnable() {

			@Override
			public void run() {
				int connectCount = 0;
				while (firstConnect || (!connected && connectCount < maxReconnectCount)) {
					firstConnect = false;
					if (connectCount != 0) {
						System.out.println("第" + connectCount + "次重连");
					}
					try {
						ChannelFuture future = bootstrap.connect(new InetSocketAddress(ip, port)).sync();
						if (future.isSuccess()) {
							System.out.println("服务器连接成功");
							channel = future.channel();
							connected = true;
						}
					} catch (Exception e) {
						connectCount++;
					}
				}
				if (!connected) {
					reconnectFailed();
				}
			}

		});

	}

	protected Channel getChannel() {
		return channel;
	}

	public void stopClient() {
		close = true;
		eventLoopGroup.shutdownGracefully();
	}

	final class ClientHandler extends SimpleChannelInboundHandler<String> {
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			System.out.println("客户端开启连接");
		}

		@Override
		protected void channelRead0(ChannelHandlerContext arg0, String arg1) throws Exception {
			System.out.println("客户端读取数据<异步>:" + arg1);
			receiveData(arg0, arg1);
		}

		@Override
		public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			System.out.println("客户端断开连接");
			// 如果不是主动关闭，则触发重连
			connected = false;
			if (!close) {
				startConnectThread();
			}

		}

		@Override
		public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
			if (evt instanceof IdleStateEvent) {
				IdleState state = ((IdleStateEvent) evt).state();
				if (state == IdleState.WRITER_IDLE) {
					System.out.println("write ping");
					ctx.channel().writeAndFlush("ping");
				}
			}
		}

	}

	final class HeartHandler extends SimpleChannelInboundHandler<String> {

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, String arg1) throws Exception {
			if ("ping".equals(arg1)) {
				// System.out.println("write pong");
				ctx.channel().writeAndFlush("pong");
			} else if ("pong".equals(arg1)) {
				// System.out.println("read pong");
			} else {
				ctx.fireChannelRead(arg1);
			}
		}

	}

	protected abstract void receiveData(ChannelHandlerContext ctx, String arg1) throws Exception;

	protected abstract void reconnectFailed();
}
