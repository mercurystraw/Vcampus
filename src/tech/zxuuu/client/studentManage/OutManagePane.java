package tech.zxuuu.client.studentManage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tech.zxuuu.client.main.App;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.ImageIcon;
import tech.zxuuu.client.rounded.RoundedButton;
import tech.zxuuu.client.rounded.RoundedTextField;

/**
 * 退学功能面板
 * 
 * @author 沈汉唐
 * @author z0gSh1u
 */
public class OutManagePane extends JPanel {

	private RoundedTextField textCardNumber;

	public OutManagePane() {
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		RoundedButton buttonYes = new RoundedButton("确 定",10);
		buttonYes.setBounds(276, 373, 167, 50);
		buttonYes.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		buttonYes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textCardNumber.getText().length() != 9 || !SwingUtils.isPureDigits(textCardNumber.getText())) {
					SwingUtils.showError(null, "一卡通错误！", "错误");
					return;
				}
				String cardnumber = textCardNumber.getText();
				String studentName = ResponseUtils.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.studentManage.StudentManage.getNameByCardNumber", new Object[] { cardnumber }).send())
						.getReturn(String.class);
				if (studentName == null) {
					SwingUtils.showError(null, "查无此人，退学失败！", "提示");
					return;
				}
				String reInput = SwingUtils.popInput(null,"要退学的学生姓名为 " + studentName + " ，请再次输入一卡通号确认：");
				if (!reInput.equals(cardnumber)) {
					SwingUtils.showMessage(null, "退学已取消！", "提示");
					return;
				}
				Response resp = ResponseUtils.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.studentManage.StudentManage.deleteStudent", new Object[] { cardnumber }).send());
				if (resp.getReturn(Boolean.class)) {
					SwingUtils.showMessage(null, "退学成功！", "提示");
				} else {
					SwingUtils.showError(null, "发生错误，退学失败！", "提示");
				}
			}
		});

		setLayout(null);
		this.add(buttonYes);

		JLabel lblNewLabel = new JLabel("学生退学");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel.setBounds(285, 30, 150, 34);
		add(lblNewLabel);

		JLabel lblCardNumber = new JLabel("一卡通号");
		lblCardNumber.setBounds(120, 200, 100, 37);
		lblCardNumber.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.add(lblCardNumber);

		textCardNumber = new RoundedTextField(10);
		textCardNumber.setBounds(300, 200, 270, 37);
		this.add(textCardNumber);
		textCardNumber.setColumns(10);
	}

}
