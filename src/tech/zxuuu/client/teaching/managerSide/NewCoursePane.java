package tech.zxuuu.client.teaching.managerSide;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import tech.zxuuu.client.rounded.RoundedButton;
import tech.zxuuu.client.rounded.RoundedTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

/**
 * 教务老师新增课程
 * 
 * @author z0gSh1u
 */
public class NewCoursePane extends JPanel {
	private RoundedTextField txtAca;
	private RoundedTextField txtCourseId;
	private RoundedTextField txtTime;
	private RoundedTextField txtTeacherId;
	private RoundedTextField txtClassroom;
	private RoundedTextField txtName;
	private RoundedTextField dispTime;
	private RoundedTextField dispClassroom;
	private RoundedTextField dispTeacherId;
	private RoundedTextField dispTeacher;
	private RoundedTextField dispAca;
	public String[] time = new String[2];

	/**
	 * Create the panel.
	 */
	public boolean judgeConflict(String time[]) {
		List<ClassInfo> Tca = ResponseUtils.getResponseByHash(
				new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getClassOfOneTeacher",
						new Object[] { dispTeacher.getText() }).send())
				.getListReturn(ClassInfo.class);
		String[] courseTime = new String[Tca.size() * 2];
		for (int i = 0; i < Tca.size(); i++) {
			courseTime[i * 2] = Tca.get(i).getID().substring(6, 9);
			courseTime[i * 2 + 1] = Tca.get(i).getID().substring(9, 12);
		}
		for (int i = 0; i < Tca.size() * 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (courseTime[i] == time[j] || courseTime[i].equals(time[j]))
					return true;
			}
		}
		return false;
	}

	public NewCoursePane() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("新增课程");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel.setBounds(285, 30, 150, 40);
		this.add(lblNewLabel);

		JLabel label = new JLabel("课程编号");
		label.setBounds(58, 119, 72, 18);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(label);

		JLabel label_1 = new JLabel("课程名");
		label_1.setBounds(58, 165 , 72, 18);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(label_1);

		JLabel lblNewLabel_1 = new JLabel("课程时间");
		lblNewLabel_1.setBounds(58, 249, 72, 18);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(lblNewLabel_1);

		txtAca = new RoundedTextField(10);
		txtAca.setBounds(147, 116, 86, 24);
		txtAca.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(txtAca);
		txtAca.setColumns(10);

		txtCourseId = new RoundedTextField(10);
		txtCourseId.setBounds(247, 116, 86, 24);
		txtCourseId.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(txtCourseId);
		txtCourseId.setColumns(10);

		txtTime = new RoundedTextField(10);
		txtTime.setBounds(347, 116, 86, 24);
		txtTime.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(txtTime);
		txtTime.setColumns(10);

		txtTeacherId = new RoundedTextField(10);
		txtTeacherId.setBounds(448, 116, 86, 24);
		txtTeacherId.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(txtTeacherId);
		txtTeacherId.setColumns(10);

		txtClassroom = new RoundedTextField(10);
		txtClassroom.setBounds(548, 116, 86, 24);
		txtClassroom.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(txtClassroom);
		txtClassroom.setColumns(10);

		JLabel label_2 = new JLabel("教室");
		label_2.setBounds(58, 293, 72, 18);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(label_2);

		JLabel label_3 = new JLabel("教师代号");
		label_3.setBounds(58, 342, 72, 18);
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(label_3);

		JLabel label_4 = new JLabel("教师");
		label_4.setBounds(58, 389, 72, 18);
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(label_4);

		JLabel label_5 = new JLabel("开课院系");
		label_5.setBounds(147, 85, 72, 18);
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		add(label_5);

		JLabel label_6 = new JLabel("系课程号");
		label_6.setBounds(247, 85, 72, 18);
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		add(label_6);

		JLabel lblNewLabel_2 = new JLabel("课程时间");
		lblNewLabel_2.setBounds(343, 85, 72, 18);
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("教师代号");
		lblNewLabel_3.setBounds(445, 85, 72, 18);
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("教室");
		lblNewLabel_4.setBounds(548, 85, 72, 18);
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		add(lblNewLabel_4);

		txtName = new RoundedTextField(10);
		txtName.setBounds(144, 163, 490, 24);
		txtName.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(txtName);
		txtName.setColumns(10);

		dispTime = new RoundedTextField(10);
		dispTime.setEditable(false);
		dispTime.setBounds(147, 246, 286, 24);
		dispTime.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(dispTime);
		dispTime.setColumns(10);

		dispClassroom = new RoundedTextField(10);
		dispClassroom.setEditable(false);
		dispClassroom.setBounds(147, 290, 286, 24);
		dispClassroom.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(dispClassroom);
		dispClassroom.setColumns(10);

		dispTeacherId = new RoundedTextField(10);
		dispTeacherId.setEditable(false);
		dispTeacherId.setBounds(147, 339, 286, 24);
		dispTeacherId.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(dispTeacherId);
		dispTeacherId.setColumns(10);

		dispTeacher = new RoundedTextField(10);
		dispTeacher.setEditable(false);
		dispTeacher.setBounds(144, 386, 286, 24);

		dispTeacher.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(dispTeacher);
		dispTeacher.setColumns(10);

		RoundedButton btnAutoLoad;

		RoundedButton btnNewButton = new RoundedButton("添 加" ,10);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (dispTeacher.getText().equals("")) {
					SwingUtils.showError(null, "请先通过自动装填！", "错误");
					return;
				}
				if (txtName.getText().equals("")) {
					SwingUtils.showError(null, "有字段为空！", "错误");
					return;
				}

				if (judgeConflict(time)) {
					SwingUtils.showError(null, "课程时间冲突", "错误");
				} else {
					ClassInfo cla = new ClassInfo();
					// 加0是历史遗留问题
					cla.setId(txtAca.getText() + "0" + txtCourseId.getText() + txtTime.getText() + txtTeacherId.getText()
							+ txtClassroom.getText());
					cla.setClassName(txtName.getText());
					String[] time = dispTime.getText().split("；");
					cla.setTime(time[0] + " " + time[1]);
					cla.setTeacher(dispTeacher.getText());
					cla.setClassroom(dispClassroom.getText());
					String idInAca = txtTeacherId.getText();
					Boolean oci = ResponseUtils
							.getResponseByHash(new Request(App.connectionToServer, null,
									"tech.zxuuu.server.teaching.CourseManagerSide.insertNewCourse", new Object[] { cla, idInAca }).send())
							.getReturn(Boolean.class);
					if (oci) {
						SwingUtils.showMessage(null, "新增成功！", "提示");
					} else {
						SwingUtils.showError(null, "新增失败！", "错误");
					}
				}
			}
		});
		btnNewButton.setBounds(430, 460, 150, 40);
		add(btnNewButton);

		JLabel lblNewLabel_5 = new JLabel("开课院系");
		lblNewLabel_5.setBounds(58, 206, 72, 18);
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		add(lblNewLabel_5);

		dispAca = new RoundedTextField(10);
		dispAca.setEditable(false);
		dispAca.setColumns(10);
		dispAca.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		dispAca.setBounds(147, 206, 286, 24);
		add(dispAca);

		btnAutoLoad = new RoundedButton("自动装填",10);
		btnAutoLoad.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnAutoLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtAca.getText().length() != 2) {
					SwingUtils.showError(null, "院系代码长度错误！", "错误");
					return;
				} else if (!SwingUtils.isPureDigits(txtAca.getText())) {
					SwingUtils.showError(null, "院系代码含有非法字符！", "错误");
				}
				if (txtClassroom.getText().length() != 4) {
					SwingUtils.showError(null, "教室代码长度错误！", "错误");
					return;
				} else if (!SwingUtils.isPureDigits(txtClassroom.getText())) {
					SwingUtils.showError(null, "教师代码含有非法字符！", "错误");
				}
				if (txtCourseId.getText().length() != 3) {
					SwingUtils.showError(null, "院系内课程代码长度错误！", "错误");
					return;
				} else if (!SwingUtils.isPureDigits(txtCourseId.getText())) {
					SwingUtils.showError(null, "课程代码含有非法字符！", "错误");
				}
				if (txtTeacherId.getText().length() != 3) {
					SwingUtils.showError(null, "院系内教师代码长度错误！", "错误");
					return;
				} else if (!SwingUtils.isPureDigits(txtTeacherId.getText())) {
					SwingUtils.showError(null, "教师代码含有非法字符！", "错误");
				}
				if (txtTime.getText().length() != 6) {
					SwingUtils.showError(null, "课程时间长度错误！", "错误");
					return;
				} else if (!SwingUtils.isPureDigits(txtTime.getText())) {
					SwingUtils.showError(null, "课程时间含有非法字符！", "错误");
				}
				if (SwingUtils.isTxtEmpty(txtAca) || SwingUtils.isTxtEmpty(txtClassroom) || SwingUtils.isTxtEmpty(txtCourseId)
						|| SwingUtils.isTxtEmpty(txtTeacherId) || SwingUtils.isTxtEmpty(txtTime)) {
					SwingUtils.showError(null, "课程编号不完整！", "错误");
					return;
				}

				String academyName = OtherUtils.getAcademyByNumber(txtAca.getText());
				if (academyName == "") {
					SwingUtils.showError(null, "查无此院系！", "错误");
					dispAca.setText("");
					return;
				}
				dispAca.setText(txtAca.getText() + " - " + academyName);
				dispClassroom.setText("教" + txtClassroom.getText().charAt(0) + "-" + txtClassroom.getText().substring(1));
				dispTeacherId.setText(txtTeacherId.getText());

				String teacherName = ResponseUtils.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.CourseManagerSide.getTeacherNameById",
								new Object[] { txtAca.getText(), txtTeacherId.getText() }).send())
						.getReturn(String.class);
				if (teacherName == null || teacherName.equals("")) {
					SwingUtils.showError(null, "查无此教师！", "错误");
					dispTeacherId.setText("");
					dispTeacher.setText("");
					return;
				}
				dispTeacher.setText((teacherName == null || teacherName.equals("")) ? "" : teacherName);

				String converted = convertClassTimeToChinese(txtTime.getText());
				if (converted.contains("[ERROR]")) {
					SwingUtils.showError(null, "时间代码错误！", "错误");
					dispTime.setText("");
					return;
				}
				time[0] = txtTime.getText().substring(0, 3);
				time[1] = txtTime.getText().substring(3);
				if (judgeConflict(time)) {
					dispTime.setText(converted + "（冲突）");
				} else {
					dispTime.setText(converted);
				}

			}
		});
		btnAutoLoad.setBounds(130, 460, 150, 40);
		add(btnAutoLoad);

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
