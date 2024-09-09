package tech.mainCode.client.teaching.managerSide;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tech.mainCode.client.main.App;
import tech.mainCode.client.messageQueue.ResponseQueue;
import tech.mainCode.entity.ClassInfo;
import tech.mainCode.net.Request;
import tech.mainCode.net.Response;
import tech.mainCode.util.OtherUtils;
import tech.mainCode.util.ResponseUtils;
import tech.mainCode.client.rounded.RoundedButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * 教务老师院系开课查询
 * 

 */
public class CourseListPane extends JPanel {
	private JTable table;

	String[] head = { "ID", "课程", "时间", "教师", "教室" };
	public String[][] rowData;
	private DefaultTableModel model;

	public static List<ClassInfo> getClassInfo(String academy) {
		Request req = new Request(App.connectionToServer, null, "tech.mainCode.server.teaching.ClassSelectGUI.getClassInfo",
				new Object[] { academy });
		String hash = req.send();
		ResponseUtils.blockAndWaitResponse(hash);
		Response resp = ResponseQueue.getInstance().consume(hash);
		return resp.getListReturn(ClassInfo.class);
	}

	/**
	 * Create the panel.
	 */
	public CourseListPane() {
		setLayout(null);

		JLabel label = new JLabel("课程查询");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		label.setBounds(285, 30, 150, 30);
		add(label);

		JLabel label_1 = new JLabel("选择院系");
		label_1.setBounds(100, 100, 100, 37);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		add(label_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		comboBox.setBounds(280, 100, 250, 37);
		add(comboBox);

		List<String> academies = new ArrayList<>(Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
				"11", "12", "13", "14", "15", "16", "17", "19", "21", "22", "24", "25", "41", "42", "43", "57", "61", "71"));
		for (String academy : academies) {
			comboBox.addItem(academy + " - " + OtherUtils.getAcademyByNumber(academy));
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 96, 610, 441);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(60, 220, 572, 300);

		JButton btnNewButton = new RoundedButton("确 定",10);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model != null) {
					model.setRowCount(0);
				}
				List<ClassInfo> CI = getClassInfo(((String) comboBox.getSelectedItem()).substring(0, 2));
				rowData = new String[CI.size()][5];
				for (int i = 0; i < CI.size(); i++) {
					rowData[i][0] = CI.get(i).getID();
					rowData[i][1] = CI.get(i).getClassName();
					rowData[i][2] = CI.get(i).getTime();
					rowData[i][3] = CI.get(i).getTeacher();
					rowData[i][4] = CI.get(i).getClassroom();
				}
				model = new DefaultTableModel(rowData, head) {
					@Override
					public boolean isCellEditable(int a, int b) {
						return false;
					}
				};
				table.setModel(model);

			}
		});
		btnNewButton.setBounds(280, 160, 100, 36);
		add(btnNewButton);

	}
}
