package tech.mainCode.client.startup;

import tech.mainCode.util.SwingUtils;

import java.awt.EventQueue;

import tech.mainCode.client.main.App;

/**
 * 客户端启动类
 * 

 */

public class Bootstrap_client {

	public static void main(String[] args) {

		System.out.println("Client started.");
		
		try {
//			javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		} catch (Exception e) {
			SwingUtils.showError(null, "主题加载失败。", "错误");
		}

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					App frame = new App();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
