package tech.mainCode.server.main;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import tech.mainCode.dao.IStudentMapper;
import tech.mainCode.entity.Student;
import tech.mainCode.net.RequestListener;
import tech.mainCode.server.messageQueue.RequestHandler;
import tech.mainCode.server.messageQueue.RequestQueue;
import tech.mainCode.util.ServerUtils;

/**
 * 服务器端全局App对象
 *
 * @author z0gSh1u
 */
public class App {

	private RequestListener requestListener; // 请求监听器
	public static RequestQueue requestQueue; // 服务器端全局请求消息队列
	public static RequestHandler requestHandler; // 请求处理器
	public static SqlSessionFactory sqlSessionFactory; // MyBatis连接工厂
	public static SqlSession foreverSqlSession; // 永久公有MyBatis连接会话，该会话仅一份，可能出现资源争抢，严禁关闭
	// 如非大量连续请求场景，请使用工厂自行创建SqlSession

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					App app = new App();
					app.startServer();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Server startup logic.
	 */
	public void startServer() {

		/**
		 * 新增部分
		 */
		// 初始化全局消息队列
		App.requestQueue = RequestQueue.getInstance();
		// 初始化MyBatis的SqlSession工厂
		String resource = "resources/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			System.out.println("数据库配置读取成功！");
		} catch (IOException e) {
			System.out.println("###严重错误！数据库配置读取失败！###" + e.toString());
			e.printStackTrace();
			return; // 如果数据库配置读取失败，直接退出
		}

		// 尝试连接数据库
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
			Boolean verifyResult = studentMapper.verifyStudent(new Student("0", null, "0", "0"));
			sqlSession.commit();
			System.out.println("数据库连接成功！");
		} catch (Exception e) {
			System.out.println("###严重错误！数据库连接失败！请检查有关配置！###");
			e.printStackTrace();
			return; // 如果数据库连接失败，直接退出
		}

		// 启动服务器端侦听
		requestListener = new RequestListener(Integer.parseInt(ServerUtils.getMainPort()));
		requestListener.start();

		// 启动请求处理器
		App.requestHandler = new RequestHandler();
		App.requestHandler.start();

		System.out.println("开始服务器端侦听...端口=" + ServerUtils.getMainPort());

		// 初始化永久SqlSession
		foreverSqlSession = sqlSessionFactory.openSession();
	}

	/**
	 * Utility method to print log messages.
	 */
	public static void appendLog(String msg) {
		System.out.println(msg);
	}

}

