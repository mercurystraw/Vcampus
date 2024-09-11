package tech.mainCode.server.startup;

import java.awt.EventQueue;
import tech.mainCode.server.main.App;
import tech.mainCode.util.SwingUtils;

/**
 * 服务器端启动类
 *
 * @author z0gSh1u
 */

public class Bootstrap_server {

	public static void main(String[] args) {

		System.out.println("Server started.");

		// try {
		// 	javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		// } catch (Exception e) {
		// 	SwingUtils.showError(null, "主题加载失败。", "错误");
		// }

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					App app = new App();
					app.startServer();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
