//package tech.zxuuu.client.teaching.studentSide;
//
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//import tech.zxuuu.client.main.App;
//import tech.zxuuu.entity.Student;
//import tech.zxuuu.net.Request;
//import tech.zxuuu.util.ResponseUtils;
//import tech.zxuuu.util.SwingUtils;
//
//import javax.swing.JTextField;
//import javax.swing.JLabel;
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import java.awt.Toolkit;
//
///**
// * 确认选课窗口
// *
// * @author 王志华
// */
//public class AffirmClassSelectionGUI extends JDialog {
//
//	private JPanel contentPane;
//	public JTextField txtClassID;
//	public JTextField txtClassName;
//	public JTextField txtTime;
//	public JTextField txtTeacher;
//	public JTextField txtClassroom;
//	private final JPanel contentPanel = new JPanel();
//	JButton btnConfirm;
//
//	public enum ConflictStatus {
//		NO_CONFLICT,
//		CONFLICT,
//		ENOUGH
//	}
//	public Boolean takeClass(Student student, String newClassId) {
//		return ResponseUtils
//				.getResponseByHash(new Request(App.connectionToServer, null,
//						"tech.zxuuu.server.teaching.ClassSelectGUI.takeClass", new Object[] { student, newClassId }).send())
//				.getReturn(Boolean.class);
//	}
//
//	public ConflictStatus judgeConflict() {
//		String cn = ResponseUtils.getResponseByHash(
//				new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getClassSelection",
//						new Object[] { App.session.getStudent() }).send())
//				.getReturn(String.class);
//
//
//
//		if (cn == null || cn.equals("")) {//学生无已选课程 无冲突
//			return ConflictStatus.NO_CONFLICT;
//		}
//		//20为长度19的课程号+逗号的长度 用于计算已选课程的节数
//		int num = cn.length() / 20;
//
//		String courseId = txtClassID.getText(); // 获取课程ID
//		String students = ResponseUtils.getResponseByHash(
//						new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.Addons.getStudentOfOneClass",
//								new Object[] { courseId }).send())
//				.getReturn(String.class);
//
//		int studentNum = students.split(",").length ; // 获取课程学生数
//		//演示效果 如果已选学生超过0，就不允许再选择
//		if(studentNum>0)return ConflictStatus.ENOUGH; //课程已满
//
//		String[] course = new String[num * 2];
//		for (int i = 0; i < num; i++) { //提取课程的时间
//			course[i * 2] = cn.substring(i * 20 + 6, i * 20 + 9);
//			course[i * 2 + 1] = cn.substring(i * 20 + 9, i * 20 + 12);
//		}
//		for (int i = 0; i < num * 2; i++) {
//			if (txtClassID.getText().substring(6, 9).equals(course[i])
//					|| txtClassID.getText().substring(9, 12).contentEquals(course[i]))
//				return ConflictStatus.CONFLICT;
//		}
//
//		return ConflictStatus.NO_CONFLICT;
//	}
//
//	public AffirmClassSelectionGUI(ClassSelectPane csg, int row) {
//
//		setResizable(false);
//		setIconImage(Toolkit.getDefaultToolkit()
//				.getImage(AffirmClassSelectionGUI.class.getResource("/resources/assets/icon/fav.png")));
//		setTitle("确认课程选择 - VCampus");
//		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		setBounds(100, 100, 436, 292);
//		contentPane = new JPanel();
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//
//		JPanel panel = new JPanel();
//		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
//		panel.setBounds(0, 0, 404, 187);
//		contentPane.add(panel);
//		panel.setLayout(null);
//
//		txtClassID = new JTextField();
//		txtClassID.setEditable(false);
//		txtClassID.setBounds(105, 13, 278, 24);
//		panel.add(txtClassID);
//		txtClassID.setColumns(10);
//
//		JLabel lblID = new JLabel("ID");
//		lblID.setBounds(47, 16, 39, 18);
//		panel.add(lblID);
//
//		JLabel lblClassName = new JLabel("课程名");
//		lblClassName.setBounds(47, 47, 57, 18);
//		panel.add(lblClassName);
//
//		JLabel lblTime = new JLabel("时间");
//		lblTime.setBounds(47, 81, 57, 18);
//		panel.add(lblTime);
//
//		JLabel lblTeacher = new JLabel("教师");
//		lblTeacher.setBounds(47, 112, 57, 18);
//		panel.add(lblTeacher);
//
//		JLabel lblClassroom = new JLabel("教室");
//		lblClassroom.setBounds(47, 143, 57, 18);
//		panel.add(lblClassroom);
//
//		txtClassName = new JTextField();
//		txtClassName.setEditable(false);
//		txtClassName.setColumns(10);
//		txtClassName.setBounds(105, 44, 278, 24);
//		panel.add(txtClassName);
//
//		txtTime = new JTextField();
//		txtTime.setEditable(false);
//		txtTime.setColumns(10);
//		txtTime.setBounds(105, 78, 278, 24);
//		panel.add(txtTime);
//
//		txtTeacher = new JTextField();
//		txtTeacher.setEditable(false);
//		txtTeacher.setColumns(10);
//		txtTeacher.setBounds(105, 109, 278, 24);
//		panel.add(txtTeacher);
//
//		txtClassroom = new JTextField();
//		txtClassroom.setEditable(false);
//		txtClassroom.setColumns(10);
//		txtClassroom.setBounds(105, 140, 278, 24);
//		panel.add(txtClassroom);
//		AffirmClassSelectionGUI acsg = this;
//
//		btnConfirm = new JButton("确认选择");
//		btnConfirm.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				Student stu = App.session.getStudent();
//
//				stu.setClassNumber(ResponseUtils.getResponseByHash(
//						new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getClassSelection",
//								new Object[] { App.session.getStudent() }).send())
//						.getReturn(String.class));
//
//				stu.setClassNumber(stu.getClassNumber() == null ? ("" + txtClassID.getText() + ",")
//						: (stu.getClassNumber() + txtClassID.getText() + ","));
//				Boolean ret = takeClass(stu, txtClassID.getText());
//				if (!ret) {
//					SwingUtils.showError(null, "出现错误！", "错误");
//				} else {
//					csg.selectClass(row);
//				}
//				acsg.dispose();
//			}
//		});
//
//		btnConfirm.setBounds(72, 205, 107, 27);
//		contentPane.add(btnConfirm);
//
//		JButton btnReturn = new JButton("返回");
//		btnReturn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				acsg.dispose();
//			}
//		});
//		btnReturn.setBounds(261, 205, 113, 27);
//		contentPane.add(btnReturn);
//	}
//}
package tech.zxuuu.client.teaching.studentSide;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import tech.zxuuu.client.rounded.*;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * 确认选课窗口
 *

 */
public class AffirmClassSelectionGUI extends JDialog {

	private JPanel contentPane;
	public RoundedTextField txtClassID;
	public RoundedTextField txtClassName;
	public RoundedTextField txtTime;
	public RoundedTextField txtTeacher;
	public RoundedTextField txtClassroom;
	private final JPanel contentPanel = new JPanel();
	JButton btnConfirm;

	public enum ConflictStatus {
		NO_CONFLICT,
		CONFLICT,
		ENOUGH
	}

	public Boolean takeClass(Student student, String newClassId) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.teaching.ClassSelectGUI.takeClass", new Object[] { student, newClassId }).send())
				.getReturn(Boolean.class);
	}

	public ConflictStatus  judgeConflict() {
		String cn = ResponseUtils.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getClassSelection",
								new Object[] { App.session.getStudent() }).send())
				.getReturn(String.class);

		if (cn == null || cn.equals("")) {//学生无已选课程 无冲突
			return ConflictStatus.NO_CONFLICT;
		}
		//20为长度19的课程号+逗号的长度 用于计算已选课程的节数
		int num = cn.length() / 20;

		String courseId = txtClassID.getText(); // 获取课程ID
		String students = ResponseUtils.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.Addons.getStudentOfOneClass",
								new Object[] { courseId }).send())
				.getReturn(String.class);

		int studentNum = students.split(",").length ; // 获取课程学生数
		//演示效果 如果已选学生超过0，就不允许再选择
		if(studentNum>0)return ConflictStatus.ENOUGH; //课程已满

		String[] course = new String[num * 2];
		for (int i = 0; i < num; i++) { //提取课程的时间
			course[i * 2] = cn.substring(i * 20 + 6, i * 20 + 9);
			course[i * 2 + 1] = cn.substring(i * 20 + 9, i * 20 + 12);
		}
		for (int i = 0; i < num * 2; i++) {
			if (txtClassID.getText().substring(6, 9).equals(course[i])
					|| txtClassID.getText().substring(9, 12).contentEquals(course[i]))
				return ConflictStatus.CONFLICT;
		}

		return ConflictStatus.NO_CONFLICT;
	}

	public AffirmClassSelectionGUI(ClassSelectPane csg, int row) {

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AffirmClassSelectionGUI.class.getResource("/resources/assets/icon/seu_icon.png")));
		setTitle("确认选课 - VCampus");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 436, 292);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBounds(0, 0, 404, 187);
		contentPane.add(panel);
		panel.setLayout(null);

		txtClassID = new RoundedTextField(10);
		txtClassID.setEditable(false);
		txtClassID.setBounds(105, 13, 278, 24);
		panel.add(txtClassID);
		txtClassID.setColumns(10);

		JLabel lblID = new JLabel("ID");
		lblID.setBounds(47, 16, 39, 18);
		panel.add(lblID);

		JLabel lblClassName = new JLabel("课程名");
		lblClassName.setBounds(47, 47, 57, 18);
		panel.add(lblClassName);

		JLabel lblTime = new JLabel("时间");
		lblTime.setBounds(47, 81, 57, 18);
		panel.add(lblTime);

		JLabel lblTeacher = new JLabel("教师");
		lblTeacher.setBounds(47, 112, 57, 18);
		panel.add(lblTeacher);

		JLabel lblClassroom = new JLabel("教室");
		lblClassroom.setBounds(47, 143, 57, 18);
		panel.add(lblClassroom);

		txtClassName = new RoundedTextField(10);
		txtClassName.setEditable(false);
		txtClassName.setColumns(10);
		txtClassName.setBounds(105, 44, 278, 24);
		panel.add(txtClassName);

		txtTime = new RoundedTextField(10);
		txtTime.setEditable(false);
		txtTime.setColumns(10);
		txtTime.setBounds(105, 78, 278, 24);
		panel.add(txtTime);

		txtTeacher = new RoundedTextField(10);
		txtTeacher.setEditable(false);
		txtTeacher.setColumns(10);
		txtTeacher.setBounds(105, 109, 278, 24);
		panel.add(txtTeacher);

		txtClassroom = new RoundedTextField(10);
		txtClassroom.setEditable(false);
		txtClassroom.setColumns(10);
		txtClassroom.setBounds(105, 140, 278, 24);
		panel.add(txtClassroom);
		AffirmClassSelectionGUI acsg = this;

		btnConfirm = new RoundedButton("确认选择",10);
		btnConfirm.setFont(new java.awt.Font("微软雅黑", 0, 15));
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Student stu = App.session.getStudent();

				stu.setClassNumber(ResponseUtils.getResponseByHash(
								new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getClassSelection",
										new Object[] { App.session.getStudent() }).send())
						.getReturn(String.class));

				stu.setClassNumber(stu.getClassNumber() == null ? ("" + txtClassID.getText() + ",")
						: (stu.getClassNumber() + txtClassID.getText() + ","));
				Boolean ret = takeClass(stu, txtClassID.getText());
				if (!ret) {
					SwingUtils.showError(null, "出现错误！", "错误");
				} else {
					csg.selectClass(row);
				}
				acsg.dispose();
			}
		});

		btnConfirm.setBounds(72, 205, 107, 27);
		contentPane.add(btnConfirm);

		JButton btnReturn = new RoundedButton("返回",10);
		btnReturn.setFont(new java.awt.Font("微软雅黑", 0, 15));
		btnReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				acsg.dispose();
			}
		});
		btnReturn.setBounds(261, 205, 113, 27);
		contentPane.add(btnReturn);
	}
}

