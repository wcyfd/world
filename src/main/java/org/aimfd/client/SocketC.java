package org.aimfd.client;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class SocketC {

	private static final Logger logger = LoggerFactory.getLogger(SocketC.class);
	private Bootstrap bootstrap;
	private EventLoopGroup workerGroup;
	private String host;
	private int port;
	private Channel channel;
	private boolean close;

	public void init() {

		bootstrap = new Bootstrap();
		workerGroup = new NioEventLoopGroup();

		bootstrap.option(ChannelOption.TCP_NODELAY, true);
		bootstrap.option(ChannelOption.SO_REUSEADDR, true);
		bootstrap.option(ChannelOption.SO_RCVBUF, 43690);// 43690为默认值
		bootstrap.option(ChannelOption.TCP_NODELAY, true);

		bootstrap.group(workerGroup).channel(NioSocketChannel.class).handler(new LoggingHandler(LogLevel.DEBUG)).handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();

				// if (IdentityCardCon.identity == IdentityCardCon.SERVER_DOOR) {
				// pipeline.addLast(new IdleStateHandler(myServerDoorSetting.ClientIdleRead,
				// myServerDoorSetting.ClientIdleWrite, myServerDoorSetting.AbtCheckTime));
				// }

				// Decoders
				pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, -4, 4));
				pipeline.addLast("bytesDecoder", new ByteArrayDecoder());
				// filter

				pipeline.addLast("handler", new ClientHandler());

				// Encoder
				pipeline.addLast("frameEncoder", new LengthFieldPrepender(4, 4, false));
				pipeline.addLast("bytesEncoder", new ByteArrayEncoder());

			}

		});
	}

	public void connectServer(String host, int port) {
		this.host = host;
		this.port = port;
		this.close = false;

		new Thread() {
			@Override
			public void run() {
				connect();
			}
		}.start();
	}

	private void connect() {
		try {
			logger.info("开始连接");

			ChannelFuture future = bootstrap.connect(InetSocketAddress.createUnresolved(host, port)).sync();
			if (future.isSuccess()) {
				channel = future.channel();
				System.out.println("------connect server success------");
				System.out.println(channel);
			}
			future.channel().closeFuture().sync();

			if (!close) {
				logger.info("连接同步关闭,快速重连");
				connect();
			}
		} catch (Exception e) {
			logger.info("连接服务器失败,睡眠五秒后重新连接服务器");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			connect();
		}

	}

	public void stop() {
		close = true;
		workerGroup.shutdownGracefully();
		logger.info("客户端主动关闭");
	}

	/**
	 * 获得通信通道
	 * 
	 * @return
	 */
	public Channel getChannel() {
		return channel;
	}

	class ClientHandler extends SimpleChannelInboundHandler<String> {

		@Override
		protected void channelRead0(ChannelHandlerContext arg0, String arg1) throws Exception {

		}

	}
}
