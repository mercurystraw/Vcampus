package tech.mainCode.client.auth;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import tech.mainCode.client.main.App;
import tech.mainCode.client.rounded.RoundedButton;
import tech.mainCode.client.rounded.RoundedPasswordField;
import tech.mainCode.client.rounded.RoundedTextField;
import tech.mainCode.client.rounded.NoFocusJRadioButton;
import tech.mainCode.entity.Manager;
import tech.mainCode.entity.Student;
import tech.mainCode.entity.Teacher;
import tech.mainCode.entity.UserType;
import tech.mainCode.net.Session;
import tech.mainCode.util.SwingUtils;

import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Color;
/**
 * 登陆界面GUI
 *
 * @author z0gSh1u
 */


public class AuthGUI extends JFrame {

	public static JPanel contentPane;
	private RoundedTextField txtUsername;
	private RoundedPasswordField txtPassword;
	private RoundedButton btnLogin;
	private NoFocusJRadioButton rdoStudent;
	private NoFocusJRadioButton rdoTeacher;
	private NoFocusJRadioButton rdoManager;

	private void login() {
		// 输入合法检查
		if (SwingUtils.isTxtEmpty(txtPassword) || SwingUtils.isTxtEmpty(txtUsername)) {
			SwingUtils.showError(null, "有字段为空！", "错误");
			return;
		}
		UserType type = null;
		if (rdoStudent.isSelected()) {
			type = UserType.STUDENT;
			Student res = AuthHelper.verifyStudent(txtUsername.getText(), txtPassword.getText());
			if (res != null) {
				SwingUtils.showMessage(null, "学生登陆成功！", "信息");
				// 填充App.session
				App.hasLogon = true;
				App.session = new Session(res);
				setVisible(false);
				// 要求界面路由
				App.requireRouting();
			} else {
				SwingUtils.showError(null, "密码错误，登陆失败！", "错误");
				txtPassword.setText("");
			}
			// -------------
		} else if (rdoTeacher.isSelected()) {
			type = UserType.TEACHER;
			Teacher res = AuthHelper.verifyTeacher(txtUsername.getText(), txtPassword.getText());
			if (res != null) {
				SwingUtils.showMessage(null, "欢迎您，" + res.getName() + " 教师！", "信息");
				App.hasLogon = true;
				App.session = new Session(res);
				setVisible(false);
				App.requireRouting();
			} else {
				SwingUtils.showError(null, "密码错误，登陆失败！", "错误");
				txtPassword.setText("");
			}
			// -------------
		} else if (rdoManager.isSelected()) {
			type = UserType.MANAGER;
			Manager res = AuthHelper.verifyManager(txtUsername.getText(), txtPassword.getText());
			if (res != null) {
				SwingUtils.showMessage(null, res.getManagerType().toString() + " 管理员登陆成功！", "信息");
				App.hasLogon = true;
				App.session = new Session(res);
				setVisible(false);
				App.requireRouting();
			} else {
				SwingUtils.showError(null, "密码错误，登陆失败！", "错误");
				txtPassword.setText("");
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public AuthGUI() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AuthGUI.class.getResource("/resources/assets/icon/seu_icon.png")));
		setTitle("统一登录认证 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 866, 549);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);

		JPanel pBody = new JPanel();
		contentPane.add(pBody, BorderLayout.CENTER);
		pBody.setLayout(null);

		// Add green strip at the top
		JLabel greenStrip = new JLabel("");
		greenStrip.setOpaque(true);
		greenStrip.setBackground(new Color(0, 100, 0)); // Green color
		greenStrip.setBounds(0, 0, 866, 40); // Adjust the height as needed
		pBody.add(greenStrip);

		JLabel lblUsername = new JLabel("用户名");
		lblUsername.setBounds(531, 219, 60, 18);
		lblUsername.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		pBody.add(lblUsername);

		txtUsername = new RoundedTextField(10);
		txtUsername.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		txtUsername.setBounds(605, 214, 190, 24);
		txtUsername.setBorder(null);
		pBody.add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel(" 密码");
		lblPassword.setBounds(531, 271, 45, 18);
		lblPassword.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		pBody.add(lblPassword);

		JLabel lblType = new JLabel("用户类型");
		lblType.setBounds(531, 324, 75, 18);
		lblType.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		pBody.add(lblType);

		rdoStudent = new NoFocusJRadioButton("学生");
		rdoTeacher = new NoFocusJRadioButton("教师");
		rdoManager = new NoFocusJRadioButton("管理员");
		rdoStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdoStudent.isSelected()) {
					rdoTeacher.setSelected(false);
					rdoManager.setSelected(false);
				} else {
					rdoStudent.setSelected(true);
				}
			}
		});
		rdoTeacher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdoTeacher.isSelected()) {
					rdoStudent.setSelected(false);
					rdoManager.setSelected(false);
				} else {
					rdoTeacher.setSelected(true);
				}
			}
		});
		rdoManager.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdoManager.isSelected()) {
					rdoTeacher.setSelected(false);
					rdoStudent.setSelected(false);
				} else {
					rdoManager.setSelected(true);
				}
			}
		});
		rdoStudent.setBounds(605, 320, 59, 27);
		pBody.add(rdoStudent);
		rdoTeacher.setBounds(670, 320, 59, 27);
		pBody.add(rdoTeacher);
		rdoManager.setBounds(735, 320, 73, 27);
		pBody.add(rdoManager);
		rdoStudent.setSelected(true);

		JButton btnLogin = new RoundedButton("登 录",20);
		btnLogin.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnLogin.setBounds(571, 372, 200, 40);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		// 支持回车登陆
		KeyAdapter loginKeyAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					login();
			}
		};

		pBody.add(btnLogin);

		txtPassword = new RoundedPasswordField(10);
		txtPassword.setFont(new Font("宋体", Font.PLAIN, 18));
		txtPassword.setBounds(605, 266, 190, 24);
		txtPassword.setBorder(null);
		pBody.add(txtPassword);

		txtUsername.addKeyListener(loginKeyAdapter);
		rdoStudent.addKeyListener(loginKeyAdapter);
		rdoTeacher.addKeyListener(loginKeyAdapter);
		rdoManager.addKeyListener(loginKeyAdapter);
		txtPassword.addKeyListener(loginKeyAdapter);

		JLabel leftPicture = new JLabel("");
		leftPicture.setHorizontalAlignment(SwingConstants.CENTER);
		leftPicture.setBounds(0, 0, 516, 500);
		leftPicture.setIcon(new ImageIcon(AuthGUI.class.getResource("/resources/assets/picture/seu-1.jpg")));
		pBody.add(leftPicture);


		JLabel iconFavicon = new JLabel("");
		iconFavicon.setIcon(new ImageIcon(AuthGUI.class.getResource("/resources/assets/icon/fav.png")));
		iconFavicon.setBounds(530, 0, 420, 250);
		pBody.add(iconFavicon);


	}
}
