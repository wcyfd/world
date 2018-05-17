package org.aimfd.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
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
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.AttributeKey;

/**
 * 通讯
 * 
 * @author AIM
 *
 */
public abstract class Socket<T> extends SocketConfig {

	private Logger logger = LoggerFactory.getLogger(Socket.class);

	/** 通讯通道存储对象 */
	public static final AttributeKey<Integer> CLIENT_ID_KEY = AttributeKey.valueOf("CLIENT_ID_KEY");
	/** 通讯通道源 */
	public static final AttributeKey<String> SERVER_NAME = AttributeKey.valueOf("SERVER_NAME");

	/** NettyIO事件循环处理对象：boss线程 */
	private EventLoopGroup bossGroup;

	/** NettyIO事件循环处理对象：worker线程 */
	private EventLoopGroup workerGroup;

	/** Netty服务器启动对象 */
	private ServerBootstrap bootstrap;

	/** Netty启动绑定线程 */
	private Thread socketThread;

	public void start() {
		socketThread = new Thread(new Runnable() {

			@Override
			public void run() {
				bossGroup = new NioEventLoopGroup();
				workerGroup = new NioEventLoopGroup();
				try {
					bootstrap = new ServerBootstrap();

					bootstrap.option(ChannelOption.TCP_NODELAY, true);
					bootstrap.option(ChannelOption.SO_REUSEADDR, true);
					bootstrap.option(ChannelOption.SO_RCVBUF, 43690);// 43690为默认值
					bootstrap.option(ChannelOption.TCP_NODELAY, true);
					bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
					bootstrap.childOption(ChannelOption.SO_RCVBUF, 43690);// 43690为默认值
					bootstrap.childOption(ChannelOption.SO_SNDBUF, 32768);// 32k

					bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

					bootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

					bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.DEBUG)).childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();

							// if (IdentityCardCon.identity == IdentityCardCon.SERVER_DOOR) {
							// pipeline.addLast(new IdleStateHandler(myServerDoorSetting.ClientIdleRead,
							// myServerDoorSetting.ClientIdleWrite, myServerDoorSetting.AbtCheckTime));
							// }

							// Decoders
							pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
							// pipeline.addLast("bytesDecoder", new ByteArrayDecoder());
							pipeline.addLast("stringDecoder", new StringDecoder());
							// filter

							pipeline.addLast("handler", new ServerHandler());

							// Encoder
							// pipeline.addLast("frameEncoder", new LengthFieldPrepender(4, 4, false));
							pipeline.addLast("frameEncoder", new LengthFieldPrepender(4, false));
							// pipeline.addLast("bytesEncoder", new ByteArrayEncoder());
							pipeline.addLast("stringEncoder", new StringEncoder());

						}

					});

					ChannelFuture f = bootstrap.bind(getPort()).sync();

					logger.info("game tcp serve start on {}", getPort());
					f.channel().closeFuture().sync();
				} catch (InterruptedException e) {
					logger.error("建立服务器SocketS失败{}", e);
					stopServer();
				}
			}

		});
		socketThread.start();
	}

	public void stopServer() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
		logger.info("服务器已关闭");
	}

	/**
	 * 服务器协议处理类 Door,Centre
	 * 
	 * @author AIM
	 *
	 */
	class ServerHandler extends SimpleChannelInboundHandler<T> {
		/**
		 * 读取通讯数据
		 */
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {
			logger.info("{}", msg);
			serverDataReceive(ctx.channel(), msg);
		}

		/**
		 * 通讯通道建立回调处理
		 */
		@Override
		public void channelActive(ChannelHandlerContext ctx) {
			logger.info("接收到一个连接请求，地址消息:{}", ctx.channel().remoteAddress());
			newConnectIn(ctx.channel());
		}

		/**
		 * 通讯通道关闭回调处理
		 */
		@Override
		public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			Integer clientID = ctx.channel().attr(CLIENT_ID_KEY).get();
			String serverName = ctx.channel().attr(SERVER_NAME).get();
			try {
				logger.info("channelInactive 连接已经关闭，clientId:{} 地址信息:{}", clientID, ctx.channel().remoteAddress());
			} catch (Exception e) {
				logger.error("channelInactive exception {}", e);
			}

			if (clientID != null) {
				// connectDisconnectDecrementIpCount(ctx.channel());// 一般留给Door去释放IP统计的
				if (clientID == -1) {
					myServerDisconnect(ctx.channel(), serverName);
				} else {
					myServerDisconnect(clientID, serverName);
				}
			}
		}

		/**
		 * 通讯通道异常回调处理
		 */
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			try {
				logger.error("连接断开 client={}", ctx.channel().attr(CLIENT_ID_KEY).get());
				// logger.error("连接断开 channel={}", JSON.toJSONString(ctx.channel()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.error("连接异常，对方可能关闭了此链接{}", cause.getMessage());
			ctx.close();
		}

		/**
		 * 系统空闲检测程序
		 * 
		 * @param ctx
		 * @param evt
		 */
		@Override
		public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
			logger.info("玩家连接到服务器后未发送数据，被系统自动关闭，clientID={}", ctx.channel().attr(CLIENT_ID_KEY).get());
			ctx.close();
		}
	}

	/**
	 * 本身服务器监听端口接收到的数据处理
	 * 
	 * @param channel
	 * @param bytes
	 */
	protected abstract void serverDataReceive(Channel channel, T msg);

	/**
	 * 新链接进入
	 * 
	 * @param channel
	 */
	public abstract void newConnectIn(Channel channel);

	/**
	 * 连接断开
	 * 
	 * @param channel
	 * @param serverName
	 */
	public abstract void myServerDisconnect(Channel channel, String serverName);

	/**
	 * 连接断开
	 * 
	 * @param clientID
	 * @param serverName
	 */
	public abstract void myServerDisconnect(Integer clientID, String serverName);
}
