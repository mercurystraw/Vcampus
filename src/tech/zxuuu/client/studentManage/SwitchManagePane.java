package tech.zxuuu.client.studentManage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import tech.zxuuu.client.rounded.RoundedButton;
import tech.zxuuu.client.rounded.RoundedTextField;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class SwitchManagePane extends JPanel {

	private RoundedTextField textCardNumber;
	private RoundedTextField textNewStudentNumber;
	DefaultTableModel model = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int a, int b) {
			return false;
		}
	};
	/**
	 * Create the panel.
	 */
	public SwitchManagePane() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		this.setLayout(null);

		JLabel lblNewLabel = new JLabel("学生转系");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel.setBounds(285, 30, 150, 40);
		this.add(lblNewLabel);

		textCardNumber = new RoundedTextField(10);
		textCardNumber.setBounds(300, 120, 270, 37);
		textCardNumber.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.add(textCardNumber);
		textCardNumber.setColumns(10);

		JLabel lblCardNumber = new JLabel("一卡通号");
		lblCardNumber.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblCardNumber.setBounds(120, 120, 100, 37);
		this.add(lblCardNumber);

		JLabel lblSubjectNumber = new JLabel("转系系号");
		lblSubjectNumber.setBounds(120, 200, 100, 37);
		lblSubjectNumber.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.add(lblSubjectNumber);

		JComboBox comboSubjectNumber = new JComboBox();
		comboSubjectNumber.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				validate();
				repaint();
			}
		});
		comboSubjectNumber.setBounds(300, 200, 270, 35);
		comboSubjectNumber.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.add(comboSubjectNumber);

		JButton buttonYes = new RoundedButton("确 定",10);
		buttonYes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textCardNumber.getText().length() != 9) {
					SwingUtils.showError(null, "一卡通长度错误！", "错误");
				} else if (!(textNewStudentNumber.getText().substring(0, 2)
						.equals(((String) comboSubjectNumber.getSelectedItem()).substring(0, 2)))) {
					SwingUtils.showError(null, "学号与系号不匹配！", "错误");
				} else if (textNewStudentNumber.getText().length() != 8) {
					SwingUtils.showError(null, "学号长度错误！", "错误");
				} else {
					Student student = new Student(textCardNumber.getText(), null, null, null);
					student.setStudentNumber(textNewStudentNumber.getText());
					student.setAcademy(textNewStudentNumber.getText().substring(0, 2));
					String result = ResponseUtils
							.getResponseByHash(new Request(App.connectionToServer, null,
									"tech.zxuuu.server.studentManage.StudentManage.switchStudent",
									new Object[] { student.getCardNumber(), student.getAcademy(), student.getStudentNumber() }).send())
							.getReturn(String.class);
					if (result.equals("Nocard")) {
						SwingUtils.showError(null, "无此学生！", "错误");
					} else if (result.equals("Repeat")) {
						SwingUtils.showError(null, "此学号已有人使用！", "错误");
					} else if (result.equals("Ok")) {
						SwingUtils.showMessage(null, "转系成功！", "hint");
					} else {
						SwingUtils.showError(null, "系统错误！", "错误");
					}
				}

			}
		});
		buttonYes.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		buttonYes.setBounds(276, 400, 167, 50);
		this.add(buttonYes);

		List<String> academies = new ArrayList<>(Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
				"11", "12", "13", "14", "15", "16", "17", "19", "21", "22", "24", "25", "41", "42", "43", "57", "61", "71"));
		for (String academy : academies) {
			comboSubjectNumber.addItem(academy + " - " + OtherUtils.getAcademyByNumber(academy));
		}

		JLabel lblNewStudentNumber = new JLabel("新学号");
		lblNewStudentNumber.setBounds(120, 280, 100, 37);
		lblNewStudentNumber.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.add(lblNewStudentNumber);

		textNewStudentNumber = new RoundedTextField(10);
		textNewStudentNumber.setBounds(300, 280, 270, 37);
		textNewStudentNumber.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.add(textNewStudentNumber);
		textNewStudentNumber.setColumns(10);

	}

}
