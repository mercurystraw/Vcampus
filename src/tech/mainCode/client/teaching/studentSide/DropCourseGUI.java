package tech.mainCode.client.teaching.studentSide;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.mainCode.client.main.App;
import tech.mainCode.entity.Student;
import tech.mainCode.net.Request;
import tech.mainCode.util.ResponseUtils;
import tech.mainCode.client.rounded.*;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 退选课程窗口
 * 

 */
public class DropCourseGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	RoundedTextField txtClassID;
	RoundedTextField txtClassName;
	RoundedTextField txtTime;
	RoundedTextField txtTeacher;
	RoundedTextField txtClassroom;

	public Boolean takeClassInv(Student student, String dropClassId) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.mainCode.server.teaching.ClassSelectGUI.takeClassInv", new Object[] { student, dropClassId }).send())
				.getReturn(Boolean.class);
	}

	/**
	 * Create the dialog.
	 */
	public DropCourseGUI(ClassSelectPane csg, int row) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 432, 216);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lbClassID = new JLabel("ID");
		lbClassID.setBounds(49, 32, 39, 18);
		contentPanel.add(lbClassID);

		txtClassID = new RoundedTextField(10);
		txtClassID.setEditable(false);
		txtClassID.setColumns(10);
		txtClassID.setBounds(107, 29, 278, 24);
		contentPanel.add(txtClassID);

		JLabel lbClassName = new JLabel("课程名");
		lbClassName.setBounds(49, 63, 57, 18);
		contentPanel.add(lbClassName);

		txtClassName = new RoundedTextField(10);
		txtClassName.setEditable(false);
		txtClassName.setColumns(10);
		txtClassName.setBounds(107, 60, 278, 24);
		contentPanel.add(txtClassName);

		JLabel lbTime = new JLabel("时间");
		lbTime.setBounds(49, 97, 57, 18);
		contentPanel.add(lbTime);

		txtTime = new RoundedTextField(10);
		txtTime.setEditable(false);
		txtTime.setColumns(10);
		txtTime.setBounds(107, 94, 278, 24);
		contentPanel.add(txtTime);

		JLabel lbTeacher = new JLabel("教师");
		lbTeacher.setBounds(49, 128, 57, 18);
		contentPanel.add(lbTeacher);

		txtTeacher = new RoundedTextField(10);
		txtTeacher.setEditable(false);
		txtTeacher.setColumns(10);
		txtTeacher.setBounds(107, 125, 278, 24);
		contentPanel.add(txtTeacher);

		JLabel lbClassroom = new JLabel("教室");
		lbClassroom.setBounds(49, 159, 57, 18);
		contentPanel.add(lbClassroom);

		txtClassroom = new RoundedTextField(10);
		txtClassroom.setEditable(false);
		txtClassroom.setColumns(10);
		txtClassroom.setBounds(107, 156, 278, 24);
		DropCourseGUI dcg = this;
		contentPanel.add(txtClassroom);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 216, 432, 37);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton btnDrop = new RoundedButton("退选课程",10);
				btnDrop.setFont(new java.awt.Font("微软雅黑", 0, 12));
				btnDrop.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Student stu = App.session.getStudent();

						stu.setClassNumber(ResponseUtils.getResponseByHash(
								new Request(App.connectionToServer, null, "tech.mainCode.server.teaching.ClassSelectGUI.getClassSelection",
										new Object[] { App.session.getStudent() }).send())
								.getReturn(String.class));

						stu.setClassNumber(stu.getClassNumber().replace(txtClassID.getText() + ",", ""));
						takeClassInv(stu, txtClassID.getText());
						csg.dropCourse(row);
						dcg.dispose();
					}
				});
				btnDrop.setBounds(64, 5, 93, 27);
				btnDrop.setActionCommand("OK");
				buttonPane.add(btnDrop);
				getRootPane().setDefaultButton(btnDrop);
			}
			{
				JButton btnCancel = new RoundedButton("返 回",10);
				btnCancel.setFont(new java.awt.Font("微软雅黑", 0, 12));
				btnCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dcg.dispose();
					}
				});
				btnCancel.setBounds(282, 5, 93, 27);
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}
}
