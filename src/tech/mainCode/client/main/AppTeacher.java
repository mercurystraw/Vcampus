package tech.mainCode.client.main;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import tech.mainCode.client.auth.AuthGUI;
import tech.mainCode.client.rounded.LibButton;
import tech.mainCode.client.teaching.teacherSide.ScheduleTablePane;
import tech.mainCode.client.teaching.teacherSide.StudentScoreManage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import tech.mainCode.client.bbs.BBSGUI;
public class AppTeacher extends JFrame {

	private JPanel contentPane;
	private JPanel currentDisplay;
	private JPanel coursePanel;

	private JPanel newOpencoursePanel;
	private StudentScoreManage studentScoreManage;

	/**
	 * Create the frame.
	 */
	public AppTeacher() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AppTeacher.class.getResource("/resources/assets/icon/seu_icon.png")));
		setTitle("教师主页 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 967, 801);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AppTeacher.class.getResource("/resources/assets/icon/seu_icon.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(14, 13, 64, 64);
		contentPane.add(label);

		JLabel lblVcampus = new JLabel("教师主页");
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblVcampus.setForeground(Color.WHITE); // 设置字体颜色为白色
		lblVcampus.setBounds(102, 27, 239, 34);
		contentPane.add(lblVcampus);

		coursePanel = new ScheduleTablePane();
		coursePanel.setBounds(14, 100, 932, 650);
		contentPane.add(coursePanel);

		studentScoreManage = new StudentScoreManage();
		studentScoreManage.setBounds(14, 100, 932, 650);
		contentPane.add(studentScoreManage);
		studentScoreManage.setVisible(false);

		currentDisplay = coursePanel;
		coursePanel.setVisible(true);

		JPanel pnlOpencourse = new JPanel();
		newOpencoursePanel = pnlOpencourse;
		pnlOpencourse.setBounds(14, 100, 932, 650);
		contentPane.add(pnlOpencourse);
		pnlOpencourse.setLayout(null);
		pnlOpencourse.setVisible(false);


		JButton btnCourseList = new LibButton("查看课表",0);
		btnCourseList.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnCourseList.setBackground(new Color(0, 120, 0));
		btnCourseList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentDisplay.setVisible(false);
				coursePanel.setVisible(true);
				currentDisplay = coursePanel;
			}
		});
		btnCourseList.setBounds(260, 0, 130, 80);
		contentPane.add(btnCourseList);


		JButton btnManageScore = new LibButton("成绩管理",0);
		btnManageScore.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnManageScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentDisplay.setVisible(false);
				studentScoreManage.setVisible(true);
				currentDisplay = studentScoreManage;
			}
		});
		btnManageScore.setBounds(390, 0, 130, 80);
		contentPane.add(btnManageScore);

		JButton btnForum = new LibButton("校园论坛",0);
		btnForum.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnForum.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						BBSGUI bbsGUI = new BBSGUI();
						bbsGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						bbsGUI.setVisible(true);
					}
				});
			}
		});
		btnForum.setBounds(510, 0, 130, 80);
		contentPane.add(btnForum);
		btnCourseList.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnCourseList.setBackground(new Color(0, 120, 0));
				btnManageScore.setBackground(new Color(0, 100, 0));
				btnForum.setBackground(new Color(0, 100, 0));
			}

		});
		btnManageScore.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnManageScore.setBackground(new Color(0, 120, 0));
				btnCourseList.setBackground(new Color(0, 100, 0));
				btnForum.setBackground(new Color(0, 100, 0));
			}

		});
		btnForum.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnForum.setBackground(new Color(0, 120, 0));
				btnCourseList.setBackground(new Color(0, 100, 0));
				btnManageScore.setBackground(new Color(0, 100, 0));
			}

		});



		JButton ExitButton = new LibButton("登出",0);
		ExitButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		ExitButton.setBackground(new Color(0, 110, 0));
		ExitButton.setBounds(820, 0, 130, 80);
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

		JLabel label_2 = new JLabel("登录卡号：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_2.setForeground(Color.WHITE); // 设置字体颜色为白色
		label_2.setBackground(new Color(0,110,0));
		label_2.setBounds(650, 23, 100, 34);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(750, 23, 100, 34);
		label_3.setForeground(Color.WHITE); // 设置字体颜色为白色
		label_3.setBackground(new Color(0,110,0));
		contentPane.add(label_3);
		label_3.setText(App.session.getTeacher().getCardNumber());
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));

		JLabel greenStrip = new JLabel("");
		greenStrip.setOpaque(true);
		greenStrip.setBackground(new Color(0, 100, 0)); // Green color
		greenStrip.setBounds(0, 0, 967, 80); // Adjust the height as needed
		contentPane.add(greenStrip, Integer.valueOf(-1)); // Add to the bottom layer
		//添加校园论坛按钮

	}
}
