package tech.zxuuu.client.teaching.studentSide;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.rounded.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import java.awt.Color;

/**
 * 教务系统学生端主界面
 * 

 */
public class TeachingStudentMain extends JFrame {

	private JPanel contentPane;

	private JPanel currentDisplay;
	private JPanel classSelectPane;
	private ScheduleTablePane scheduleTablePane;
	private JPanel defaultPanel;
	private JPanel queryScorePane;

	/**
	 * Create the frame.
	 */
	public TeachingStudentMain() {
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(TeachingStudentMain.class.getResource("/resources/assets/icon/seu_icon.png")));
		setTitle("教务平台 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 941, 795);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new LibButton("学生选课",0);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplay.setVisible(false);
				classSelectPane.setVisible(true);
				currentDisplay = classSelectPane;
			}
		});
		btnNewButton.setBounds(670, 0, 150, 80);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new LibButton("查看课表",0);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentDisplay.setVisible(false);
//				scheduleTablePane.setVisible(true);
//				currentDisplay = scheduleTablePane;

				scheduleTablePane.setVisible(false);
				contentPane.remove(scheduleTablePane);
				scheduleTablePane = new ScheduleTablePane();
				scheduleTablePane.setVisible(true);
				scheduleTablePane.setBounds(24, 90, 889, 683);
				scheduleTablePane.setBorder(new LineBorder(new Color(0, 0, 0)));
				contentPane.add(scheduleTablePane);
				currentDisplay = scheduleTablePane;
			}
		});
		btnNewButton_1.setBounds(370, 0, 150, 80);
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton_1.setBackground(new Color(0, 120, 0));
		contentPane.add(btnNewButton_1);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TeachingStudentMain.class.getResource("/resources/assets/icon/seu_icon.png")));
		label.setBounds(24, 13, 64, 64);
		contentPane.add(label);

		JLabel lblVcampus = new JLabel("教务平台");
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblVcampus.setForeground(Color.WHITE); // 设置字体颜色为白色
		lblVcampus.setBounds(102, 27, 239, 34);
		contentPane.add(lblVcampus);




		classSelectPane = new ClassSelectPane();
		classSelectPane.setBounds(24, 90, 889, 683);
		contentPane.add(classSelectPane);
		classSelectPane.setVisible(false);



		queryScorePane = new QueryScorePane();
		queryScorePane.setBounds(24, 90, 889, 683);
		contentPane.add(queryScorePane);
		queryScorePane.setVisible(false);

		scheduleTablePane = new ScheduleTablePane();
		scheduleTablePane.setBounds(24, 90, 889, 683);
		contentPane.add(scheduleTablePane);
		scheduleTablePane.setVisible(true);
		currentDisplay = scheduleTablePane;

		JButton btnQueryScore = new LibButton("成绩查询",0);
		btnQueryScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentDisplay.setVisible(false);
				queryScorePane.setVisible(true);
				currentDisplay = queryScorePane;
			}
		});
		btnQueryScore.setBounds(520, 0, 150, 80);
		btnQueryScore.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		contentPane.add(btnQueryScore);

		btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnNewButton.setBackground(new Color(0, 120, 0));
				btnNewButton_1.setBackground(new Color(0, 100, 0));
				btnQueryScore.setBackground(new Color(0, 100, 0));
			}

		});
		btnNewButton_1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnNewButton.setBackground(new Color(0, 100, 0));
				btnNewButton_1.setBackground(new Color(0, 120, 0));
				btnQueryScore.setBackground(new Color(0, 100, 0));
			}

		});
		btnQueryScore.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btnNewButton.setBackground(new Color(0, 100, 0));
				btnNewButton_1.setBackground(new Color(0, 100, 0));
				btnQueryScore.setBackground(new Color(0, 120, 0));
			}

		});
		JLabel greenStrip = new JLabel("");
		greenStrip.setOpaque(true);
		greenStrip.setBackground(new Color(0, 100, 0)); // Green color
		greenStrip.setBounds(0, 0, 946, 80); // Adjust the height as needed
		contentPane.add(greenStrip, Integer.valueOf(-1)); // Add to the bottom layer

	}
}
