package tech.zxuuu.client.studentManage;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import tech.zxuuu.net.Request;
import tech.zxuuu.client.main.App;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import tech.zxuuu.client.rounded.RoundedButton;
import tech.zxuuu.client.rounded.RoundedTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class ResetPasswordPane extends JPanel {
	private RoundedTextField txtNameDisp;
	private RoundedTextField txtPassword;
	private RoundedTextField txtPasswordAgain;
	private RoundedTextField txtCardNumber;

	/**
	 * Create the panel.
	 */
	public ResetPasswordPane() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("学生改密");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel.setBounds(285, 30, 150, 40);
		this.add(lblNewLabel);


		JLabel lblCardNumber = new JLabel("一卡通号");
		lblCardNumber.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblCardNumber.setBounds(120, 120, 100, 37);
		this.add(lblCardNumber);



		JLabel lblNewPassword = new JLabel("新密码");
		lblNewPassword.setBounds(120, 200, 100, 37);
		lblNewPassword.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		add(lblNewPassword);

		txtPassword = new RoundedTextField(10);
		txtPassword.setBounds(300, 200, 270, 35);
		add(txtPassword);
		txtPassword.setColumns(10);

		JLabel lblNewPasswordAgain = new JLabel("确认密码");
		lblNewPasswordAgain.setBounds(120, 280, 100, 37);
		lblNewPasswordAgain.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		add(lblNewPasswordAgain);

		txtPasswordAgain = new RoundedTextField(10);
		txtPasswordAgain.setBounds(300, 280, 270, 37);
		add(txtPasswordAgain);
		txtPasswordAgain.setColumns(10);

		JButton btnSubmit = new RoundedButton("提 交",10);
		btnSubmit.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SwingUtils.isTxtEmpty(txtPassword) || (!txtPassword.getText().equals(txtPasswordAgain.getText()))) {
					SwingUtils.showError(null, "请检查密码输入！", "错误");
					return;
				}
				Boolean ret = ResponseUtils
						.getResponseByHash(
								new Request(App.connectionToServer, null, "tech.zxuuu.server.studentManage.StudentManage.resetPassword",
										new Object[] { txtCardNumber.getText(), OtherUtils.getMD5(txtPassword.getText()) }).send())
						.getReturn(Boolean.class);
				if (ret) {
					SwingUtils.showMessage(null, "改密成功！", "提示");
					return;
				} else {
					SwingUtils.showError(null, "改密失败！", "提示");
					return;
				}
			}
		});
		btnSubmit.setBounds(276, 400, 167, 50);
		add(btnSubmit);

		txtCardNumber = new RoundedTextField(10);
		txtCardNumber.setBounds(300, 120, 270, 37);
		add(txtCardNumber);
		txtCardNumber.setColumns(10);

	}
}
