package tech.zxuuu.client.studentManage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import tech.zxuuu.client.rounded.RoundedButton;
import tech.zxuuu.client.rounded.RoundedTextField;
import tech.zxuuu.client.rounded.RoundedPasswordField;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;

/**
 * 入学功能面板
 * 
 * @author 沈汉唐
 * @author z0gSh1u
 */
public class InManagePane extends JPanel {

	private RoundedTextField textCardNumber;
	private RoundedTextField textStudentNumber;
	private RoundedTextField textName;
	private RoundedPasswordField passwordField;
	private RoundedPasswordField passwordConfirm;

	/**
	 * Create the panel.
	 */
	public InManagePane() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setLayout(null);

		JLabel lblNewLabel = new JLabel("学生入学");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel.setBounds(285, 30, 150, 40);
		this.add(lblNewLabel);

		JLabel lblCardNumber = new JLabel("一卡通号");
		lblCardNumber.setBounds(120, 120, 100, 37);
		lblCardNumber.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.add(lblCardNumber);

		JLabel lbl_StudentNumber = new JLabel("学号");
		lbl_StudentNumber.setBounds(120, 180, 100, 37);
		lbl_StudentNumber.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.add(lbl_StudentNumber);

		JLabel lblName = new JLabel("姓名");
		lblName.setBounds(120, 240, 100, 37);
		lblName.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.add(lblName);

		JLabel lblPassword = new JLabel("密码");
		lblPassword.setBounds(120, 300, 100, 37);
		lblPassword.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.add(lblPassword);

		textCardNumber = new RoundedTextField(10);
		textCardNumber.setBounds(300, 120, 270, 37);
		this.add(textCardNumber);
		textCardNumber.setColumns(10);

		textStudentNumber = new RoundedTextField(10);
		textStudentNumber.setBounds(300, 180, 270, 37);
		this.add(textStudentNumber);
		textStudentNumber.setColumns(10);

		textName = new RoundedTextField(10);
		textName.setBounds(300, 240, 270, 37);
		this.add(textName);
		textName.setColumns(10);

		passwordField = new RoundedPasswordField(10);
		passwordField.setBounds(300, 300, 270, 37);
		this.add(passwordField);

		JLabel lblPasswordConfirm = new JLabel("确认密码");
		lblPasswordConfirm.setBounds(120, 360, 100, 37);
		lblPasswordConfirm.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.add(lblPasswordConfirm);

		passwordConfirm = new RoundedPasswordField(10);
		passwordConfirm.setBounds(300, 360, 270, 37);
		passwordConfirm.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.add(passwordConfirm);

		RoundedButton buttonYes = new RoundedButton("确 认",10);
		buttonYes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textCardNumber.getText().length() != 9 || !SwingUtils.isPureDigits(textCardNumber.getText())
						|| SwingUtils.containsChinese(textCardNumber.getText())) {
					SwingUtils.showError(null, "一卡通错误！", "错误");
				} else if (textStudentNumber.getText().length() != 8
						|| SwingUtils.containsChinese(textStudentNumber.getText())) {
					SwingUtils.showError(null, "学号错误！", "错误");
				} else if (passwordField.getText().length() > 16 || passwordField.getText().length() == 0) {
					SwingUtils.showError(null, "请输入1-16位密码！", "错误");
				} else if (!(passwordField.getText().equals(passwordConfirm.getText()))) {
					SwingUtils.showError(null, "两次输入密码不一致！", "错误");
				} else {
					Student student = new Student(textCardNumber.getText(), null, OtherUtils.getMD5(passwordField.getText()), null);
					student.setName(textName.getText());
					student.setStudentNumber(textStudentNumber.getText());
					student.setAcademy(textStudentNumber.getText().substring(0, 2));
					Response resp = ResponseUtils.getResponseByHash(new Request(App.connectionToServer, null,
							"tech.zxuuu.server.studentManage.StudentManage.insertStudent", new Object[] { student }).send());
					if (resp.getReturn(Boolean.class)) {
						SwingUtils.showMessage(null, "入学成功！", "提示");
					} else {
						SwingUtils.showError(null, "入学失败！", "提示");
					}
				}
			}
		});
		buttonYes.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		buttonYes.setBounds(276, 450, 167, 50);
		this.add(buttonYes);
	}

}
