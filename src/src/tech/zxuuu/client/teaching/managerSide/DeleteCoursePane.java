package src.tech.zxuuu.client.teaching.managerSide;

import javax.swing.JPanel;

import javax.swing.JTextField;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import tech.zxuuu.client.rounded.RoundedTextField;
import tech.zxuuu.client.rounded.RoundedButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JLabel;

public class DeleteCoursePane extends JPanel {
	private JTextField txtAca;
	private JTextField txtCourseId;
	private JTextField txtTime;
	private JTextField txtTeacherId;
	private JTextField txtClassroom;
	private JTextField txtName;
	private JTextField dispTime;
	private JTextField dispClassroom;
	private JTextField dispTeacherId;
	private JTextField dispTeacher;
	private JTextField dispAca;
	private RoundedTextField txtDeleteNumber;

	/**
	 * Create the panel.
	 */
	public DeleteCoursePane() {
		
		setLayout(null);

		JLabel lblNewLabel = new JLabel("删除课程");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel.setBounds(285, 30, 150, 40);
		add(lblNewLabel);

		JLabel label = new JLabel("课程编号");
		label.setBounds(150, 200, 100, 37);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		add(label);


		txtDeleteNumber = new RoundedTextField(10);
		txtDeleteNumber.setBounds(300, 200, 270, 37);
		txtDeleteNumber.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		add(txtDeleteNumber);



		RoundedButton btnAutoLoad;
		RoundedButton btnNewButton = new RoundedButton("删 除",10);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String ID = txtDeleteNumber.getText();
				Boolean oci = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, null,
								"tech.zxuuu.server.teaching.CourseManagerSide.deleteCourse", new Object[] { ID }).send())
						.getReturn(Boolean.class);
				if (oci) {
					SwingUtils.showMessage(null, "删除成功！", "提示");
				} else {
					SwingUtils.showError(null, "删除失败！", "错误");
				}
			}
		});
		btnNewButton.setBounds(276, 373, 167, 50);
		add(btnNewButton);



	}

	public static String convertClassTimeToChinese(String classTimeCode) {
		String[] parts = new String[2];
		parts[0] = classTimeCode.substring(0, 3);
		parts[1] = classTimeCode.substring(3, 6);
		String result = "";
		for (String string : parts) {
			result += "周";
			result += string.charAt(0) == '1' ? "一"
					: string.charAt(0) == '2' ? "二"
							: string.charAt(0) == '3' ? "三"
									: string.charAt(0) == '4' ? "四" : string.charAt(0) == '5' ? "五" : "[ERROR]";
			result += string.charAt(1);
			result += '-';
			result += string.charAt(2);
			result += "节；";
		}
		return result;

		
	}

}
