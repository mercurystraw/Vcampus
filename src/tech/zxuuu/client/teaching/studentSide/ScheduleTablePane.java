package tech.zxuuu.client.teaching.studentSide;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

/**
 * 学生课表
 * 
 * @author 王志华
 */
public class ScheduleTablePane extends JPanel {

	private JLabel[] labels;

	public List<ClassInfo> getClassOfOneTeacher(String name) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.teaching.ClassSelectGUI.getClassOfOneTeacher", new Object[] { name }).send())
				.getListReturn(ClassInfo.class);
	}

	public String getClassSeletion(Student student) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.teaching.ClassSelectGUI.getClassSelection", new Object[] { student }).send())
				.getReturn(String.class);
	}

	public ClassInfo getOneClass(String ID) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.teaching.ClassSelectGUI.getOneClass", new Object[] { ID }).send())
				.getReturn(ClassInfo.class);
	}

	public void studentSchedule() {
		Student stu = App.session.getStudent();
		String temp = getClassSeletion(stu);
		if (temp == null || temp.equals("")) {
			return;
		}
		String[] course = temp.split(",");
//		System.out.println(temp);
//		System.out.println(course.length);


		// FIX: course.length -> course.length - 1, since `temp` is ended with `,`
		// WRONG FIX, INVERTED.
		for (int i = 0; i < course.length; i++) {
			ClassInfo cla = getOneClass(course[i]);
			labels[Integer.valueOf(course[i].charAt(6)) - 48 + (Integer.valueOf(course[i].charAt(8)) - 48) / 2 * 6]
					.setText("<html>" + cla.getClassName() + "<br>" + cla.getTeacher() + "<br>" + cla.getClassroom() + "<html>");
			labels[Integer.valueOf(course[i].charAt(9)) - 48 + (Integer.valueOf(course[i].charAt(11)) - 48) / 2 * 6]
					.setText("<html>" + cla.getClassName() + "<br>" + cla.getTeacher() + "<br>" + cla.getClassroom() + "<html>");
			System.out.println(cla.getID());
			System.out.println(cla.getClassName()+cla.getTeacher()+cla.getClassroom());
			System.out.println(Integer.valueOf(course[i].charAt(3))-48);
//			int index1 = Integer.valueOf(cla.getID().charAt(2))-48;
//			int index2 = Integer.valueOf(cla.getID().charAt(3))-48;
//			int index_final = index1 + index2 * 6;
//			if (index_final >= 0 && index_final < labels.length) {
//				labels[index_final].setText("<html>" + cla.getClassName() + "<br>" + cla.getTeacher() + "<br>"+ cla.getClassroom() + "<html>");
//			}

//			labels[Integer.valueOf(course[i].charAt(3))-48]
//					.setText("<html>" + cla.getClassName() + "<br>" + cla.getTeacher() + "<br>" + cla.getClassroom() + "<html>");
//			labels[Integer.valueOf(course[i].charAt(3))]
//					.setText("<html>" + cla.getClassName() + "<br>" + cla.getTeacher() + "<br>" + cla.getClassroom() + "<html>");
		}

	}

	public void teacherSchedule() {
		List<ClassInfo> cla = getClassOfOneTeacher(App.session.getTeacher().getCardNumber());
		if (cla == null) {
			System.out.println("Error: getClassOfOneTeacher returned null");
			return;
		}
		String[] course = new String[cla.size() * 2];
		for (int i = 0; i < cla.size(); i++) {
			course[i] = cla.get(i).getID();
		}
		for (int i = 0; i < cla.size(); i++) {
			String id = course[i];

			System.out.println("Course ID: " + id);

			if (id.length() > 0) {
				int index1 = Integer.valueOf(id.charAt(6)) - 48 + (Integer.valueOf(id.charAt(8)) - 48) / 2 * 6;
				int index2 = Integer.valueOf(id.charAt(9)) - 48 + (Integer.valueOf(id.charAt(11)) - 48) / 2 * 6;
				//设置课程表课程的位置 表格从左到右 从上到下索引
//				int index1 = Integer.valueOf(id.charAt(2))-48;
//				int index2 = Integer.valueOf(id.charAt(3))-48;
				// 通过index1和index2找到对应的label
//				int index_final = index1 + index2 * 6;

				if (index1 >= 0 && index1 < labels.length) {
					labels[index1].setText("<html>" + cla.get(i).getClassName() + "<br>" + cla.get(i).getClassroom() + "<html>");
				}
				if(index2 >= 0 && index2 < labels.length) {
					labels[index2].setText("<html>" + cla.get(i).getClassName() + "<br>" + cla.get(i).getClassroom() + "<html>");
				}

			} else {
				System.out.println("Error: Course ID is invalid: " + id);
			}
		}
	}

	/**
	 * Create the panel.
	 */
	public ScheduleTablePane() {

		this.setLayout(null);
		JPanel northFlowPanel = new JPanel();

		JPanel centerNullPanel = new JPanel(null);
		centerNullPanel.setBounds(0, 0, 948, 627);
		this.add(centerNullPanel);

		JLabel lblNewLabel = new JLabel("再次点击查看课表即可刷新课表");
		lblNewLabel.setBounds(711, 641, 226, 18);
		add(lblNewLabel);

		Color lightblue = new Color(208, 227, 234);
		Color silvergray = new Color(233, 241, 244);
		Color gemblue = new Color(85, 169, 208);
		Color darkerGray = Color.GRAY.darker();
		Color ligherBlack = darkerGray.darker().darker().darker();
		labels = new JLabel[36];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				int index = i * 6 + j;
				labels[index] = new JLabel();
				labels[index].setBounds(j * 148, i * 103, 148, 103);
				labels[index].setOpaque(true);
				labels[index].setHorizontalAlignment(JTextField.CENTER);
				if (i == 0)
					labels[index].setBackground(gemblue);
				else if (i != 0 && j == 0)
					labels[index].setBackground(lightblue);
				else
					labels[index].setBackground(i % 2 != 0 ? lightblue : silvergray);

				labels[index].setForeground(Color.BLACK);
				labels[index].setBorder(BorderFactory.createLineBorder(Color.WHITE));
				centerNullPanel.add(labels[index]);
			}
		}

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				studentSchedule();
				for (JLabel label : labels) {
					label.setVisible(false);
					label.repaint();
					label.updateUI();
					label.validate();
					label.setVisible(true);
				}
			}
		});

		labels[0].setText("<html><body><h1>课程表</h1></body></html>");
		ScheduleUtilities.setWeekLabels(labels);
		labels[6].setText("<html><body><h2>第1-2节<br /></h2>08:00-09:35</body></html>");
		labels[12].setText("<html><body><h2>第3-4节<br /></h2>09:50-11:25</body></html>");
		labels[18].setText("<html><body><h2>第5-6节<br /></h2>14:00-15:35</body></html>");
		labels[24].setText("<html><body><h2>第7-8节<br /></h2>15:50-17:25</body></html>");
		labels[30].setText("<html><body><h2>第9-10节<br /></h2>18:30-20:05</body></html>");


	}

	static class ScheduleUtilities {
		private static ArrayList<ClassInfo> infos;

		public static void setWeekLabels(JLabel[] labels) {
			labels[1].setText("<html><body><h2>星期一<br /></h2>");
			labels[2].setText("<html><body><h2>星期二<br /></h2>");
			labels[3].setText("<html><body><h2>星期三<br /></h2>");
			labels[4].setText("<html><body><h2>星期四<br /></h2>");
			labels[5].setText("<html><body><h2>星期五<br /></h2>");
		}
	}
}
