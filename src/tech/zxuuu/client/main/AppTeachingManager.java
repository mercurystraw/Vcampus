package tech.zxuuu.client.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.SwingUtilities;

import tech.zxuuu.client.auth.AuthGUI;
import tech.zxuuu.client.studentManage.InManagePane;
import tech.zxuuu.client.studentManage.OutManagePane;
import tech.zxuuu.client.studentManage.ResetPasswordPane;
import tech.zxuuu.client.studentManage.StudentTablePane;
import tech.zxuuu.client.studentManage.SwitchManagePane;
import tech.zxuuu.client.teaching.managerSide.CourseListPane;
import tech.zxuuu.client.teaching.managerSide.DeleteCoursePane;
import tech.zxuuu.client.teaching.managerSide.NewCoursePane;
import tech.zxuuu.client.teaching.managerSide.NewNoticePane;
import tech.zxuuu.client.rounded.LibButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

/**
 * 教务管理员界面
 * 
 * @author z0gSh1u
 */
public class AppTeachingManager extends JFrame {

	private JPanel contentPane;

	private JPanel defaultDisplayPane;
	private JPanel currentDisplayPane;

	/* 学生管理相关Panel */
	private JPanel studentTablePane;
	private JPanel outManagePane;
	private JPanel inManagePane;
	private JPanel switchManagePane;
	private JPanel resetPasswordPane;
	/* 课程管理相关Panel */
	private JPanel newCoursePane;
	private JPanel courseListPane;
	private JPanel deleteCoursePane;
	/* 通知发布Panel */
	private JPanel newNoticePane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AppTeachingManager frame = new AppTeachingManager();
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
	public AppTeachingManager() {
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(AppTeachingManager.class.getResource("/resources/assets/icon/seu_icon.png")));
		setTitle("教务管理员 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 866, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");

		label.setIcon(new ImageIcon(AppLibraryManager.class.getResource("/resources/assets/icon/seu_icon.png")));
		label.setBounds(14, 13, 60, 60);
		contentPane.add(label);

		JLabel lblVcampus = new JLabel("教务管理系统");
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
		

		LibButton btnIn = new LibButton("学生入学",0);
		btnIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplayPane.setVisible(false);
				inManagePane.setVisible(true);
				currentDisplayPane = inManagePane;
			}
		});
		btnIn.setBounds(0, 80+60*3, 147,60);
		btnIn.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		contentPane.add(btnIn);

		LibButton btnOut = new LibButton("学生退学",0);
		btnOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplayPane.setVisible(false);
				outManagePane.setVisible(true);
				currentDisplayPane = outManagePane;
			}
		});

		btnOut.setBounds(0, 80 + 60 * 4, 147, 60);
		btnOut.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		contentPane.add(btnOut);

		LibButton btnSwitch = new LibButton("学生转系",0);
		btnSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplayPane.setVisible(false);
				switchManagePane.setVisible(true);
				currentDisplayPane = switchManagePane;
			}
		});

		btnSwitch.setBounds(0, 80 + 60 *5, 147, 60);
		btnSwitch.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		contentPane.add(btnSwitch);

		LibButton btnList = new LibButton("学生查询",0);
		btnList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplayPane.setVisible(false);
				studentTablePane.setVisible(true);
				currentDisplayPane = studentTablePane;
			}
		});

		btnList.setBounds(0, 80 + 60 * 6, 147, 60);
		btnList.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		contentPane.add(btnList);

		LibButton btnResetPW = new LibButton("学生改密",0);
		btnResetPW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentDisplayPane.setVisible(false);
				resetPasswordPane.setVisible(true);
				currentDisplayPane = resetPasswordPane;
			}
		});

		btnResetPW.setBounds(0, 80 +60*7, 147, 60);
		btnResetPW.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		contentPane.add(btnResetPW);

		LibButton ExitButton = new LibButton("登出",0);
		ExitButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		ExitButton.setBackground(new Color(0, 110, 0));
		ExitButton.setBounds(750, 0, 100, 80);
		ExitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AuthGUI authGUI = new AuthGUI();
				authGUI.setVisible(true);
				dispose();
			}
		});
		contentPane.add(ExitButton);
		ExitButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				ExitButton.setBackground(new Color(0, 120, 0));
			}

		});



		/* 初始化其他所需Panel */
		studentTablePane = new StudentTablePane();
		studentTablePane.setBounds(147, 80, 719, 580);
		contentPane.add(studentTablePane);
		studentTablePane.setVisible(false);

		outManagePane = new OutManagePane();
		outManagePane.setBounds(147, 80, 719, 580);
		contentPane.add(outManagePane);
		outManagePane.setVisible(false);

		inManagePane = new InManagePane();
		inManagePane.setBounds(147, 80, 719, 580);
		contentPane.add(inManagePane);
		inManagePane.setVisible(false);

		switchManagePane = new SwitchManagePane();
		switchManagePane.setBounds(147, 80, 719, 580);
		contentPane.add(switchManagePane);
		switchManagePane.setVisible(false);

		newCoursePane = new NewCoursePane();
		newCoursePane.setBounds(147, 80, 719, 580);
		contentPane.add(newCoursePane);
		newCoursePane.setVisible(true);
		currentDisplayPane = newCoursePane;

		courseListPane = new CourseListPane();
		courseListPane.setBounds(147, 80, 719, 580);
		contentPane.add(courseListPane);
		courseListPane.setVisible(false);

		deleteCoursePane = new DeleteCoursePane();
		deleteCoursePane.setBounds(147, 80, 719, 580);
		contentPane.add(deleteCoursePane);
		deleteCoursePane.setVisible(false);

		resetPasswordPane = new ResetPasswordPane();
		resetPasswordPane.setBounds(147, 80, 719, 580);
		contentPane.add(resetPasswordPane);
		resetPasswordPane.setVisible(false);
		
		newNoticePane = new NewNoticePane();
		newNoticePane.setBounds(147, 80, 719, 580);
		contentPane.add(newNoticePane);
		newNoticePane.setVisible(false);



		LibButton button = new LibButton("课程查询",0);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplayPane.setVisible(false);
				courseListPane.setVisible(true);
				currentDisplayPane = courseListPane;
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button.setBounds(0, 80 + 2 *60, 147, 60);
		contentPane.add(button);

		LibButton button_1 = new LibButton("课程增加",0);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplayPane.setVisible(false);
				newCoursePane.setVisible(true);
				currentDisplayPane = newCoursePane;
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_1.setBounds(0, 80, 147, 60);
		button_1.setBackground(new Color(0, 120, 0));
		contentPane.add(button_1);

		LibButton button_2 = new LibButton("课程删除",0);
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplayPane.setVisible(false);
				deleteCoursePane.setVisible(true);
				currentDisplayPane = deleteCoursePane;

			}
		});
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_2.setBounds(0, 80 + 60 * 1, 147, 60);
		contentPane.add(button_2);
		
		LibButton btnNewButton = new LibButton("发布通知",0);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentDisplayPane.setVisible(false);
				newNoticePane.setVisible(true);
				currentDisplayPane = newNoticePane;
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton.setBounds(0, 80 + 60 * 8, 147, 68);
		contentPane.add(btnNewButton);
		switchManagePane.setVisible(false);


		JLabel greenStrip = new JLabel("");
		greenStrip.setOpaque(true);
		greenStrip.setBackground(new Color(0, 100, 0)); // Green color
		greenStrip.setBounds(0, 0, 866, 80); // Adjust the height as needed
		contentPane.add(greenStrip, Integer.valueOf(-1)); // Add to the bottom layer

		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(0, 120, 0));
				button_1.setBackground(new Color(0, 100, 0));
				button_2.setBackground(new Color(0, 100, 0));
				btnNewButton.setBackground(new Color(0, 100, 0));
				btnIn.setBackground(new Color(0, 100, 0));
				btnOut.setBackground(new Color(0, 100, 0));
				btnSwitch.setBackground(new Color(0, 100, 0));
				btnList.setBackground(new Color(0, 100, 0));
				btnResetPW.setBackground(new Color(0, 100, 0));
			}

		});
		button_1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				button_1.setBackground(new Color(0, 120, 0));
				button.setBackground(new Color(0, 100, 0));
				button_2.setBackground(new Color(0, 100, 0));
				btnNewButton.setBackground(new Color(0, 100, 0));
				btnIn.setBackground(new Color(0, 100, 0));
				btnOut.setBackground(new Color(0, 100, 0));
				btnSwitch.setBackground(new Color(0, 100, 0));
				btnList.setBackground(new Color(0, 100, 0));
				btnResetPW.setBackground(new Color(0, 100, 0));
			}

		});
		button_2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				button_2.setBackground(new Color(0, 120, 0));
				button.setBackground(new Color(0, 100, 0));
				button_1.setBackground(new Color(0, 100, 0));
				btnNewButton.setBackground(new Color(0, 100, 0));
				btnIn.setBackground(new Color(0, 100, 0));
				btnOut.setBackground(new Color(0, 100, 0));
				btnSwitch.setBackground(new Color(0, 100, 0));
				btnList.setBackground(new Color(0, 100, 0));
				btnResetPW.setBackground(new Color(0, 100, 0));
			}

		});
		btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnNewButton.setBackground(new Color(0, 120, 0));
				button.setBackground(new Color(0, 100, 0));
				button_1.setBackground(new Color(0, 100, 0));
				button_2.setBackground(new Color(0, 100, 0));
				btnIn.setBackground(new Color(0, 100, 0));
				btnOut.setBackground(new Color(0, 100, 0));
				btnSwitch.setBackground(new Color(0, 100, 0));
				btnList.setBackground(new Color(0, 100, 0));
				btnResetPW.setBackground(new Color(0, 100, 0));
			}

		});
		btnIn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnIn.setBackground(new Color(0, 120, 0));
				button.setBackground(new Color(0, 100, 0));
				button_1.setBackground(new Color(0, 100, 0));
				button_2.setBackground(new Color(0, 100, 0));
				btnNewButton.setBackground(new Color(0, 100, 0));
				btnOut.setBackground(new Color(0, 100, 0));
				btnSwitch.setBackground(new Color(0, 100, 0));
				btnList.setBackground(new Color(0, 100, 0));
				btnResetPW.setBackground(new Color(0, 100, 0));
			}

		});
		btnOut.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnOut.setBackground(new Color(0, 120, 0));
				button.setBackground(new Color(0, 100, 0));
				button_1.setBackground(new Color(0, 100, 0));
				button_2.setBackground(new Color(0, 100, 0));
				btnNewButton.setBackground(new Color(0, 100, 0));
				btnIn.setBackground(new Color(0, 100, 0));
				btnSwitch.setBackground(new Color(0, 100, 0));
				btnList.setBackground(new Color(0, 100, 0));
				btnResetPW.setBackground(new Color(0, 100, 0));
			}

		});
		btnSwitch.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnSwitch.setBackground(new Color(0, 120, 0));
				button.setBackground(new Color(0, 100, 0));
				button_1.setBackground(new Color(0, 100, 0));
				button_2.setBackground(new Color(0, 100, 0));
				btnNewButton.setBackground(new Color(0, 100, 0));
				btnIn.setBackground(new Color(0, 100, 0));
				btnOut.setBackground(new Color(0, 100, 0));
				btnList.setBackground(new Color(0, 100, 0));
				btnResetPW.setBackground(new Color(0, 100, 0));
			}

		});
		btnList.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnList.setBackground(new Color(0, 120, 0));
				button.setBackground(new Color(0, 100, 0));
				button_1.setBackground(new Color(0, 100, 0));
				button_2.setBackground(new Color(0, 100, 0));
				btnNewButton.setBackground(new Color(0, 100, 0));
				btnIn.setBackground(new Color(0, 100, 0));
				btnOut.setBackground(new Color(0, 100, 0));
				btnSwitch.setBackground(new Color(0, 100, 0));
				btnResetPW.setBackground(new Color(0, 100, 0));
			}

		});
		btnResetPW.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnResetPW.setBackground(new Color(0, 120, 0));
				button.setBackground(new Color(0, 100, 0));
				button_1.setBackground(new Color(0, 100, 0));
				button_2.setBackground(new Color(0, 100, 0));
				btnNewButton.setBackground(new Color(0, 100, 0));
				btnIn.setBackground(new Color(0, 100, 0));
				btnOut.setBackground(new Color(0, 100, 0));
				btnSwitch.setBackground(new Color(0, 100, 0));
				btnList.setBackground(new Color(0, 100, 0));
			}

		});

	}
}
