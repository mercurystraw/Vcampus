package tech.mainCode.client.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

//
import tech.mainCode.client.rounded.LibButton;
import tech.mainCode.client.shop.DeleteProductPane;
import tech.mainCode.client.shop.NewProductPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import tech.mainCode.client.auth.AuthGUI;

public class AppShopManager extends JFrame {

	private JPanel btnSwitchToOut;

	private JPanel defaultPane, currentPane, newProductPane, deleteProductPane;

	/**
	 * Create the frame.
	 */
	public AppShopManager() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AppShopManager.class.getResource("/resources/assets/icon/seu_icon.png")));
		setTitle("商店管理 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 903, 660);
		btnSwitchToOut = new JPanel();
		btnSwitchToOut.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(btnSwitchToOut);
		btnSwitchToOut.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AppShopManager.class.getResource("/resources/assets/icon/seu_icon.png")));
		label.setBounds(14, 13, 64, 64);
		btnSwitchToOut.add(label);

		JLabel lblVcampus = new JLabel("商店管理系统");
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblVcampus.setForeground(Color.WHITE); // 设置字体颜色为白色
		lblVcampus.setBounds(102, 27, 239, 34);
		btnSwitchToOut.add(lblVcampus);

		JLabel label_2 = new JLabel("登录卡号：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_2.setForeground(Color.WHITE); // 设置字体颜色为白色
		label_2.setBounds(570, 23, 100, 34);
		btnSwitchToOut.add(label_2);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(670, 23, 100, 34);
		label_3.setForeground(Color.WHITE); // 设置字体颜色为白色
		btnSwitchToOut.add(label_3);
		label_3.setText(App.session.getManager().getCardNumber());
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));

		JButton btnNewButton_1;
		JButton btnNewButton = new LibButton("商品入库",0);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentPane.setVisible(false);
				newProductPane.setVisible(true);
				currentPane= newProductPane;
			}
		});

		btnNewButton.setBounds(305, 0, 130, 80);
		btnSwitchToOut.add(btnNewButton);
		btnNewButton.setBackground(new Color(0, 120, 0));
		btnNewButton_1 = new LibButton("商品出库",0);
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentPane.setVisible(false);
				deleteProductPane.setVisible(true);
				currentPane= deleteProductPane;
			}
		});
		btnNewButton_1.setBounds(435, 0, 130, 80);
		btnSwitchToOut.add(btnNewButton_1);
		btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnNewButton.setBackground(new Color(0, 120, 0));
				btnNewButton_1.setBackground(new Color(0, 100, 0));
			}

		});
		btnNewButton_1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnNewButton_1.setBackground(new Color(0, 120, 0));
				btnNewButton.setBackground(new Color(0, 100, 0));
			}

		});
		JButton ExitButton = new LibButton("登出",0);
		ExitButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		ExitButton.setBackground(new Color(0, 110, 0));
		ExitButton.setBounds(770, 0, 100, 80);
		ExitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AuthGUI authGUI = new AuthGUI();
				authGUI.setVisible(true);
				dispose();
			}
		});
		ExitButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				ExitButton.setBackground(new Color(0, 120, 0));
			}

		});
		btnSwitchToOut.add(ExitButton);
		newProductPane = new NewProductPane();
		newProductPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		newProductPane.setBounds(0, 80, 903, 580);
		newProductPane.setVisible(true);
		btnSwitchToOut.add(newProductPane);
		currentPane = newProductPane;
		deleteProductPane = new DeleteProductPane();
		deleteProductPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		deleteProductPane.setBounds(0, 80, 903, 580);
		deleteProductPane.setVisible(false);
		btnSwitchToOut.add(deleteProductPane);

		JLabel greenStrip = new JLabel("");
		greenStrip.setOpaque(true);
		greenStrip.setBackground(new Color(0, 100, 0)); // Green color
		greenStrip.setBounds(0, 0, 866, 80); // Adjust the height as needed
		btnSwitchToOut.add(greenStrip, Integer.valueOf(-1)); // Add to the bottom layer

	}

}
