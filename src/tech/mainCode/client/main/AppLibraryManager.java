package tech.mainCode.client.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import tech.mainCode.client.auth.AuthGUI;
import tech.mainCode.client.library.DeleteBookPane;
import tech.mainCode.client.library.NewBookPane;
import tech.mainCode.client.rounded.LibButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * 图书管理员主界面
 *
 */
public class AppLibraryManager extends JFrame {

	private JPanel contentPane;
	private JPanel currentDisplay;
	private JPanel newBookPane;
	private JPanel deleteBookPane;
	private JPanel defaultPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AppLibraryManager frame = new AppLibraryManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AppLibraryManager() {
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(AppLibraryManager.class.getResource("/resources/assets/icon/seu_icon.png")));
		setTitle("图书管理员 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 903, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);



		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AppLibraryManager.class.getResource("/resources/assets/icon/seu_icon.png")));
		label.setBounds(14, 13, 64, 64);
		contentPane.add(label);

		JLabel lblVcampus = new JLabel("图书管理系统");
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblVcampus.setForeground(Color.WHITE); // 设置字体颜色为白色
		lblVcampus.setBounds(102, 27, 239, 34);
		contentPane.add(lblVcampus);

		JLabel label_2 = new JLabel("登录卡号：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_2.setForeground(Color.WHITE); // 设置字体颜色为白色
		label_2.setBackground(new Color(0,110,0));
		label_2.setBounds(570, 23, 100, 34);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(670, 23, 100, 34);
		label_3.setForeground(Color.WHITE); // 设置字体颜色为白色
		label_3.setBackground(new Color(0,110,0));
		contentPane.add(label_3);
		label_3.setText(App.session.getManager().getCardNumber());
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		JButton btnNewButton_1;
		JButton btnNewButton = new LibButton("书籍入库",0);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplay.setVisible(false);
				newBookPane.setVisible(true);
				currentDisplay = newBookPane;
			}
		});

		btnNewButton.setBounds(305, 0, 130, 80);
		contentPane.add(btnNewButton);
		btnNewButton.setBackground(new Color(0, 120, 0));
		btnNewButton_1 = new LibButton("书籍出库",0);
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplay.setVisible(false);
				deleteBookPane.setVisible(true);
				currentDisplay = deleteBookPane;
			}
		});
		btnNewButton_1.setBounds(435, 0, 130, 80);
		contentPane.add(btnNewButton_1);
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
		contentPane.add(ExitButton);
		newBookPane = new NewBookPane();
		newBookPane.setBounds(24, 102, 835, 498);
		newBookPane.setVisible(false);
		contentPane.add(newBookPane);
		newBookPane.setVisible(true);
		currentDisplay = newBookPane;
		deleteBookPane = new DeleteBookPane();
		deleteBookPane.setBounds(24, 102, 835, 498);
		deleteBookPane.setVisible(false);
		contentPane.add(deleteBookPane);

		JLabel greenStrip = new JLabel("");
		greenStrip.setOpaque(true);
		greenStrip.setBackground(new Color(0, 100, 0)); // Green color
		greenStrip.setBounds(0, 0, 866, 80); // Adjust the height as needed
		contentPane.add(greenStrip, Integer.valueOf(-1)); // Add to the bottom layer


	}
}
