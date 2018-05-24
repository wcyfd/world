package org.aimfd.client.socketc;

import java.net.InetSocketAddress;

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
import io.netty.util.AttributeKey;

public class Client {

	private final static AttributeKey<Integer> WRITER_IDLE_KEY = AttributeKey.<Integer>valueOf("WRITER_IDLE_KEY");

	private EventLoopGroup eventLoopGroup;
	private Bootstrap bootstrap;

	private Channel channel;

	private int reconnectCount;

	private boolean isClose;

	public void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				connect();
			}
		}).start();

		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void connect() {

		bootstrap = new Bootstrap();
		eventLoopGroup = new NioEventLoopGroup();

		try {
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

			while (!isClose) {
				// 重连达到三次，直接断开
				if (reconnectCount >= 3) {
					isClose = true;
				}

				System.out.println("开始第" + reconnectCount + "次连接");
				_connect();
			}

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			eventLoopGroup.shutdownGracefully();

			System.out.println("客户端优雅关闭");
			// 通知所有阻塞单元放开阻塞，服务器已经连接失败
			synchronized (this) {
				notifyAll();
			}
		}

	}

	private void _connect() {
		try {
			Thread.sleep(500);
			// 停顿半秒，保证外部成功阻塞
			ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost", 8081)).sync();
			if (future.isSuccess()) {
				System.out.println("客户端连接服务器成功");
				reconnectCount = 0;

				channel = future.channel();
				synchronized (this) {
					this.notifyAll();
				}
				channel.closeFuture().sync();
			}
		} catch (Exception e) {
			System.out.println("连接服务器失败");
			reconnectCount++;
		}

	}

	public void stopClient() {
		isClose = true;
		channel.close();

	}

	public Channel getChannel() {

		// 如果通道关闭则等待重连
		if (channel != null && !channel.isOpen()) {
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return channel;
	}

	final class ClientHandler extends SimpleChannelInboundHandler<String> {
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			System.out.println("客户端开启连接");
			reconnectCount = 0;
		}

		@Override
		protected void channelRead0(ChannelHandlerContext arg0, String arg1) throws Exception {
			System.out.println("客户端读取数据<异步>:" + arg1);
			receiveData(arg0, arg1);
		}

		@Override
		public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			System.out.println("客户端断开连接");
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
				ctx.channel().attr(WRITER_IDLE_KEY).set(0);
				System.out.println("write pong");
				ctx.channel().writeAndFlush("pong");
			} else if ("pong".equals(arg1)) {
				System.out.println("read pong");
			} else {
				ctx.fireChannelRead(arg1);
			}
		}

	}

	protected void receiveData(ChannelHandlerContext ctx, String arg1) throws Exception {

	}

}
