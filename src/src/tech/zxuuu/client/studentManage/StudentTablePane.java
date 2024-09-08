package src.tech.zxuuu.client.studentManage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.Student;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import tech.zxuuu.client.rounded.RoundedTextField;
import tech.zxuuu.client.rounded.RoundedButton;


public class StudentTablePane extends JPanel {

	private JTable infoTable;
	private RoundedTextField textGrade;
	List<Student> result = null;
	String[][] rowData = null;
	DefaultTableModel model = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int a, int b) {
			return false;
		}
	};

	/**
	 * Create the panel.
	 */
	public StudentTablePane() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setLayout(null);
		JLabel lblNewLabel = new JLabel("学生查询");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel.setBounds(285, 30, 150, 40);
		this.add(lblNewLabel);

		infoTable = new JTable();
		infoTable.setBounds(0, 108, 774, 300);
		infoTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		String[] head = { "一卡通", "学号", "院系", "姓名" };
		JScrollPane jsp = new JScrollPane(infoTable);
		jsp.setBounds(43, 220, 572, 300);
		model.setDataVector(rowData, head);
		infoTable.setModel(model);
		this.add(jsp);

		JComboBox comboAcademy = new JComboBox();
		comboAcademy.setBounds(150, 100, 200, 37);
		comboAcademy.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.add(comboAcademy);

		JLabel lblAcademy = new JLabel("院系");
		lblAcademy.setBounds(60, 100, 100, 37);
		lblAcademy.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		this.add(lblAcademy);

		JLabel lblGrade = new JLabel("年级");
		lblGrade.setBounds(400, 100, 100, 35);
		lblGrade.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		this.add(lblGrade);

		textGrade = new RoundedTextField(10);
		textGrade.setBounds(480, 100, 100, 35);
		this.add(textGrade);
		textGrade.setColumns(10);

		RoundedButton buttonSearch = new RoundedButton("查 询",10);
		buttonSearch.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		buttonSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textGrade.getText().length() != 2) {
					SwingUtils.showMessage(null, "请输入正确的两位年级代码！", "错误");
				} else {
					result = ResponseUtils.getResponseByHash(
							new Request(App.connectionToServer, null, "tech.zxuuu.server.studentManage.StudentManage.tableDisplay",
									new Object[] { ((String) comboAcademy.getSelectedItem()).substring(0, 2), textGrade.getText() })
											.send())
							.getListReturn(Student.class);
					if (result.size() == 0) {
						SwingUtils.showMessage(null, "该条件下查询结果为空。", "提示");
						return;
					}
					rowData = new String[result.size()][4];
					for (int i = 0; i < result.size(); i++) {
						rowData[i][0] = result.get(i).getCardNumber();
						rowData[i][1] = result.get(i).getStudentNumber();
						rowData[i][2] = result.get(i).getAcademy();
						rowData[i][3] = result.get(i).getName();
					}
					model.setDataVector(rowData, head);
				}
			}
		});
		buttonSearch.setBounds(280, 160, 100, 36);
		this.add(buttonSearch);
		


		List<String> academies = new ArrayList<>(
				Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
						"17", "19", "21", "22", "24", "25", "41", "42", "43", "57", "61", "71", "88"));
		for (String academy : academies) {
			comboAcademy.addItem(academy + " - " + OtherUtils.getAcademyByNumber(academy));
		}
	}

}
