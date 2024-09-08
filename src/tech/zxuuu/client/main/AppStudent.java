//package tech.zxuuu.client.main;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.JLabel;
//import java.awt.Font;
//import javax.swing.JButton;
//import javax.swing.SwingConstants;
//import javax.swing.SwingUtilities;
//import javax.swing.ImageIcon;
//import javax.swing.border.LineBorder;
//
//import tech.zxuuu.client.bbs.BBSGUI;
//import tech.zxuuu.client.library.LibraryStudentGUI;
//import tech.zxuuu.client.library.MyBorrowGUI;
//import tech.zxuuu.client.shop.ShopFirstPage;
//import tech.zxuuu.client.teaching.studentSide.TeachingStudentMain;
//import tech.zxuuu.entity.Book;
//import tech.zxuuu.entity.ClassInfo;
//import tech.zxuuu.entity.NoticeInfo;
//import tech.zxuuu.entity.PostInfo;
//import tech.zxuuu.net.Request;
//import tech.zxuuu.util.OtherUtils;
//import tech.zxuuu.util.ResponseUtils;
//import tech.zxuuu.util.SwingUtils;
//
//import java.awt.Color;
//import java.awt.Toolkit;
//import java.awt.event.ActionListener;
//import java.util.List;
//import java.awt.event.ActionEvent;
//
//public class AppStudent extends JFrame {
//
//	private JPanel contentPane;
//	public static JLabel lblBalance;
//
//	/**
//	 * Create the frame.
//	 */
//	public AppStudent() {
//		setResizable(false);
//		setIconImage(Toolkit.getDefaultToolkit().getImage(AppStudent.class.getResource("/resources/assets/icon/fav.png")));
//		setTitle("学生主页 - VCampus");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 1151, 800);
//		contentPane = new JPanel();
//		contentPane.setBackground(new Color(240, 255, 240));
//		contentPane.setLocation(-871, -176);
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//
//		JLabel lblVcampus = new JLabel("学生主页 - VCampus");
//		lblVcampus.setHorizontalAlignment(SwingConstants.CENTER);
//		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 32));
//		lblVcampus.setBounds(103, 25, 306, 43);
//		contentPane.add(lblVcampus);
//
//		JButton btnTeaching = new JButton(" 教务平台");
//		btnTeaching.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				SwingUtilities.invokeLater(new Runnable() {
//
//					@Override
//					public void run() {
//						TeachingStudentMain teachingStudentMain = new TeachingStudentMain();
//						teachingStudentMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//						teachingStudentMain.setVisible(true);
//					}
//				});
//			}
//		});
//		btnTeaching.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		btnTeaching.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/teaching.png")));
//		btnTeaching.setBounds(374, 600, 220, 80);
//		contentPane.add(btnTeaching);
//
//		JButton btnLibrary = new JButton(" 李文正图书馆");
//		btnLibrary.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				LibraryStudentGUI libraryStudentGUI = new LibraryStudentGUI();
//				libraryStudentGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				libraryStudentGUI.setVisible(true);
//			}
//		});
//		btnLibrary.setBackground(new Color(255, 255, 240));
//		btnLibrary.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		btnLibrary.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/library.png")));
//		btnLibrary.setBounds(120, 491, 220, 80);
//		contentPane.add(btnLibrary);
//
//		JButton btnShop = new JButton("天平超市");
//		btnShop.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ShopFirstPage shopFirstPage = new ShopFirstPage();
//				shopFirstPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				shopFirstPage.setVisible(true);
//			}
//		});
//		btnShop.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		btnShop.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/shop.png")));
//		btnShop.setBounds(120, 600, 220, 80);
//		contentPane.add(btnShop);
//
////		JButton btnOpencourse = new JButton("在线课堂");
////		btnOpencourse.addActionListener(new ActionListener() {
////			@Override
////			public void actionPerformed(ActionEvent e) {
////				SwingUtilities.invokeLater(new Runnable() {
////
////					@Override
////					public void run() {
////						StuMenuGUI stuMenuGUI = new StuMenuGUI();
////						stuMenuGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
////						stuMenuGUI.setVisible(true);
////					}
////				});
////			}
////		});
////		btnOpencourse.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/opencourse.png")));
////		btnOpencourse.setFont(new Font("微软雅黑", Font.PLAIN, 18));
////		btnOpencourse.setBounds(374, 491, 220, 80);
////		contentPane.add(btnOpencourse);
//
//		JButton btnOpencourse = new JButton("校园论坛");
//		btnOpencourse.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				SwingUtilities.invokeLater(new Runnable() {
//
//					@Override
//					public void run() {
//						BBSGUI bbsGUI = new BBSGUI();
//						bbsGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//						bbsGUI.setVisible(true);
//					}
//				});
//			}
//		});
////		btnOpencourse.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/opencourse.png")));
//		btnOpencourse.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		btnOpencourse.setBounds(374, 450, 220, 80);
//		contentPane.add(btnOpencourse);
//
//
//		JLabel lblNewLabel = new JLabel("");
//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/app.png")));
//		lblNewLabel.setBounds(25, 12, 64, 64);
//		contentPane.add(lblNewLabel);
//
//		JLabel lblNewLabel_1 = new JLabel("关于我");
//		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/aboutme.png")));
//		lblNewLabel_1.setBounds(806, 143, 122, 64);
//		contentPane.add(lblNewLabel_1);
//
//		JPanel panel = new JPanel();
//		panel.setBorder(new LineBorder(new Color(135, 206, 250), 1, true));
//		panel.setBounds(806, 221, 280, 216);
//		contentPane.add(panel);
//		panel.setLayout(null);
//
//		JLabel lblName = new JLabel("...");
//		lblName.setBounds(104, 23, 149, 18);
//		panel.add(lblName);
//
//		JLabel lblCardNumber = new JLabel("...");
//		lblCardNumber.setBounds(104, 54, 72, 18);
//		panel.add(lblCardNumber);
//
//		JLabel lblStudentNumber = new JLabel("...");
//		lblStudentNumber.setBounds(104, 85, 149, 18);
//		panel.add(lblStudentNumber);
//
//		JLabel lblAcademy = new JLabel("...");
//		lblAcademy.setBounds(104, 116, 149, 18);
//		panel.add(lblAcademy);
//
//		lblBalance = new JLabel("...");
//		lblBalance.setBounds(104, 147, 97, 18);
//		panel.add(lblBalance);
//
//		JLabel lblBookLend = new JLabel("...");
//		lblBookLend.setBounds(104, 178, 72, 18);
//		panel.add(lblBookLend);
//
//		JLabel label = new JLabel("姓名：");
//		label.setBounds(18, 23, 72, 18);
//		panel.add(label);
//
//		JLabel label_1 = new JLabel("一卡通号：");
//		label_1.setBounds(18, 54, 84, 18);
//		panel.add(label_1);
//
//		JLabel label_2 = new JLabel("学号：");
//		label_2.setBounds(18, 85, 72, 18);
//		panel.add(label_2);
//
//		JLabel label_3 = new JLabel("院系：");
//		label_3.setBounds(18, 116, 72, 18);
//		panel.add(label_3);
//
//		JLabel label_4 = new JLabel("一卡通余额：");
//		label_4.setBounds(18, 147, 104, 18);
//		panel.add(label_4);
//
//		JLabel label_5 = new JLabel("图书馆借书：");
//		label_5.setBounds(18, 178, 104, 18);
//		panel.add(label_5);
//
//		JLabel lblNewLabel_2 = new JLabel("");
//		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
//		lblNewLabel_2.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/test.png")));
//		lblNewLabel_2.setBounds(806, 450, 64, 64);
//		contentPane.add(lblNewLabel_2);
//
//		JLabel lblToday = new JLabel("今天是星期");
//		lblToday.setFont(new Font("微软雅黑", Font.PLAIN, 17));
//		lblToday.setBounds(884, 460, 198, 18);
//		contentPane.add(lblToday);
//
//		/**
//		 * 新增，初始化控件内容
//		 */
//		lblName.setText(App.session.getStudent().getName());
//		lblAcademy.setText(App.session.getStudent().getAcademy() + " - "
//				+ OtherUtils.getAcademyByNumber(App.session.getStudent().getAcademy()));
//		lblBalance.setText(String.format("%.2f", App.session.getStudent().getBalance()));
//		lblStudentNumber.setText(App.session.getStudent().getStudentNumber());
//		lblCardNumber.setText(App.session.getStudent().getCardNumber());
//		List<Book> tempList = ResponseUtils.getResponseByHash(
//				new Request(App.connectionToServer, null, "tech.zxuuu.server.library.Addons.getBorrowedBookList",
//						new Object[] { App.session.getStudent().getCardNumber() }).send())
//				.getListReturn(Book.class);
//		if (tempList == null || tempList.size() == 0) {
//			lblBookLend.setText("0 本");
//		} else {
//			lblBookLend.setText(tempList.size() + " 本");
//		}
//		JButton btnRecharge = new JButton("充值");
//		btnRecharge.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				String money = SwingUtils.popInput(null,"请输入充值金额：");
//				if (!SwingUtils.isPureDigits(money)) {
//					SwingUtils.showError(null, "输入有误！", "错误");
//					return;
//				}
//
//				Boolean result = ResponseUtils
//						.getResponseByHash(
//								(new Request(App.connectionToServer, null, "tech.zxuuu.server.main.UtilsApi.chargeCardBalance",
//										new Object[] { App.session.getStudent().getCardNumber(), Integer.parseInt(money) }).send()))
//						.getReturn(Boolean.class);
//
//				if (result) {
//					SwingUtils.showMessage(null, "充值成功！", "提示");
//					lblBalance
//							.setText(String.format("%.2f", Double.parseDouble(lblBalance.getText()) + Double.parseDouble(money)));
//				} else {
//					SwingUtils.showError(null, "充值失败！", "错误");
//				}
//
//			}
//		});
//		btnRecharge.setBounds(203, 147, 63, 27);
//		panel.add(btnRecharge);
//
//		JButton btnMyBorrow = new JButton("查看");
//		btnMyBorrow.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				MyBorrowGUI myBorrowGUI = new MyBorrowGUI();
//				myBorrowGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				myBorrowGUI.setVisible(true);
//			}
//		});
//		btnMyBorrow.setBounds(203, 174, 63, 27);
//		panel.add(btnMyBorrow);
//
//		Integer today = OtherUtils.getDay(ResponseUtils.getResponseByHash(
//				new Request(App.connectionToServer, null, "tech.zxuuu.server.main.UtilsApi.getTrustedUnixTimeStamp", null)
//						.send())
//				.getReturn(Long.class));
//		// Integer today = 2; // Test
//		lblToday.setText("今天是星期" + (today == 1 ? "一"
//				: today == 2 ? "二"
//						: today == 3 ? "三" : today == 4 ? "四" : today == 5 ? "五" : today == 6 ? "六" : today == 7 ? "日" : "..."));
//
//		/* 计算今日课程 */
//		JLabel lblTodayCourses = new JLabel("今日课程：");
//		String selectedClass = ResponseUtils.getResponseByHash(
//				new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getClassSelection",
//						new Object[] { App.session.getStudent() }).send())
//				.getReturn(String.class);
//
//		ClassInfo[] todayClass = new ClassInfo[5];
//
//		System.out.println(selectedClass);
//
//		if (selectedClass != null && !selectedClass.equals("")) {
//			for (int i = 0; i < 5; i++) {
//				todayClass[i] = null;
//			}
//			String[] course = selectedClass.split(",");
//			for (int i = 0; i < course.length; i++) {
////				if (Integer.valueOf(course[i].charAt(2))-48 == today) {
////					ClassInfo cla = ResponseUtils
////							.getResponseByHash(new Request(App.connectionToServer, null,
////									"tech.zxuuu.server.teaching.ClassSelectGUI.getOneClass", new Object[] { course[i] }).send())
////							.getReturn(ClassInfo.class);
////					todayClass[Integer.valueOf(course[i].charAt(3))-48] = cla;
////				}
//				if (Integer.valueOf(course[i].charAt(6) - 48) == today) {
//					ClassInfo cla = ResponseUtils
//							.getResponseByHash(new Request(App.connectionToServer, null,
//									"tech.zxuuu.server.teaching.ClassSelectGUI.getOneClass", new Object[] { course[i] }).send())
//							.getReturn(ClassInfo.class);
//					todayClass[(Integer.valueOf(course[i].charAt(11)) - 48) / 2 - 1] = cla;
//				}
//				if (Integer.valueOf(course[i].charAt(9) - 48) == today) {
//					ClassInfo cla = ResponseUtils
//							.getResponseByHash(new Request(App.connectionToServer, null,
//									"tech.zxuuu.server.teaching.ClassSelectGUI.getOneClass", new Object[] { course[i] }).send())
//							.getReturn(ClassInfo.class);
//					todayClass[(Integer.valueOf(course[i].charAt(11)) - 48) / 2 - 1] = cla;
//				}
//				//6101081234230327102
//
//			}
//		}
//
//		JLabel lblCI1 = new JLabel("无课程");
//		lblCI1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
//		JLabel lblCI2 = new JLabel("无课程");
//		lblCI2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
//		JLabel lblCI3 = new JLabel("无课程");
//		lblCI3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
//		JLabel lblCI4 = new JLabel("无课程");
//		lblCI4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
//		JLabel lblCI5 = new JLabel("无课程");
//		lblCI5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
//		JLabel[] lblCourses = new JLabel[] { lblCI1, lblCI2, lblCI3, lblCI4, lblCI5 };
//
//		for (int i = 0; i < 5; i++) {
//			if (todayClass[i] != null) {
//				lblCourses[i].setText(todayClass[i].getClassName() + " @ " + todayClass[i].getClassroom());
//			}
//		}
//
//		lblTodayCourses.setFont(new Font("微软雅黑", Font.PLAIN, 17));
//		lblTodayCourses.setBounds(884, 491, 115, 18);
//		contentPane.add(lblTodayCourses);
//
//		JLabel lblNewLabel_3 = new JLabel("1~2节");
//		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		lblNewLabel_3.setBounds(806, 541, 64, 24);
//		contentPane.add(lblNewLabel_3);
//
//		JLabel label_6 = new JLabel("3~4节");
//		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		label_6.setBounds(806, 578, 64, 24);
//		contentPane.add(label_6);
//
//		JLabel label_7 = new JLabel("5~6节");
//		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		label_7.setBounds(806, 615, 64, 24);
//		contentPane.add(label_7);
//
//		JLabel label_8 = new JLabel("7~8节");
//		label_8.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		label_8.setBounds(806, 652, 64, 24);
//		contentPane.add(label_8);
//
//		JLabel label_9 = new JLabel("9~10节");
//		label_9.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		label_9.setBounds(806, 689, 64, 24);
//		contentPane.add(label_9);
//
//		lblCI1.setBounds(884, 546, 222, 18);
//		contentPane.add(lblCI1);
//
//		lblCI2.setBounds(884, 583, 222, 18);
//		contentPane.add(lblCI2);
//
//		lblCI3.setBounds(884, 620, 222, 18);
//		contentPane.add(lblCI3);
//
//		lblCI4.setBounds(884, 657, 222, 18);
//		contentPane.add(lblCI4);
//
//		lblCI5.setBounds(884, 694, 222, 18);
//		contentPane.add(lblCI5);
//
//		JLabel lblNewLabel_4 = new JLabel("");
//		lblNewLabel_4.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/picture/mainbanner.png")));
//		lblNewLabel_4.setBounds(418, 0, 730, 130);
//		contentPane.add(lblNewLabel_4);
//
//
//		List<NoticeInfo> noticeInfos = ResponseUtils
//				.getResponseByHash(
//						new Request(App.connectionToServer, null, "tech.zxuuu.server.main.UtilsApi.getTop4Notice", null).send())
//				.getListReturn(NoticeInfo.class);
//
//		if(noticeInfos.size() > 0) {
//			NoticeBlock noticeBlock1 = new NoticeBlock(noticeInfos.get(0).getTitle(),
//					quickFormatDate(noticeInfos.get(0).getDate()), noticeInfos.get(0).getUrl());
//			noticeBlock1.setBounds(57, 170, 660, 50);
//			contentPane.add(noticeBlock1);
//		}
//
//		if(noticeInfos.size() > 1) {
//			NoticeBlock noticeBlock2 = new NoticeBlock(noticeInfos.get(1).getTitle(),
//					quickFormatDate(noticeInfos.get(1).getDate()), noticeInfos.get(1).getUrl());
//			noticeBlock2.setBounds(57, 233, 660, 50);
//			contentPane.add(noticeBlock2);
//		}
//
//		if(noticeInfos.size() > 2) {
//			NoticeBlock noticeBlock3 = new NoticeBlock(noticeInfos.get(2).getTitle(),
//					quickFormatDate(noticeInfos.get(2).getDate()), noticeInfos.get(2).getUrl());
//			noticeBlock3.setBounds(57, 296, 660, 50);
//			contentPane.add(noticeBlock3);
//		}
//
//		if(noticeInfos.size() > 3) {
//			NoticeBlock noticeBlock4 = new NoticeBlock(noticeInfos.get(3).getTitle(),
//					quickFormatDate(noticeInfos.get(3).getDate()), noticeInfos.get(3).getUrl());
//			noticeBlock4.setBounds(57, 359, 660, 50);
//			contentPane.add(noticeBlock4);
//		}
//
//		//考虑加一个icon“热门帖子”
//
//		List<PostInfo> postInfos = ResponseUtils.getResponseByHash(
//						new Request(App.connectionToServer, null, "tech.zxuuu.server.main.UtilsApi.getTop3Post", null).send())
//				.getListReturn(PostInfo.class);
//
//		if (postInfos.size() > 0) {
//			PostBlock postBlock1 = new PostBlock(postInfos.get(0).getId(), postInfos.get(0).getContent(),
//					quickFormatDate(postInfos.get(0).getDate()), postInfos.get(0).getUser_id(), postInfos.get(0).getThumbup());
//			postBlock1.setBounds(57, 422, 660, 80); // 调整位置
//			contentPane.add(postBlock1);
//		}
//
//		if (postInfos.size() > 1) {
//			PostBlock postBlock2 = new PostBlock(postInfos.get(1).getId(), postInfos.get(1).getContent(),
//					quickFormatDate(postInfos.get(1).getDate()), postInfos.get(1).getUser_id(), postInfos.get(1).getThumbup());
//			postBlock2.setBounds(57, 512, 660, 80); // 调整位置
//			contentPane.add(postBlock2);
//		}
//
//		if (postInfos.size() > 2) {
//			PostBlock postBlock3 = new PostBlock(postInfos.get(2).getId(), postInfos.get(2).getContent(),
//					quickFormatDate(postInfos.get(2).getDate()), postInfos.get(2).getUser_id(), postInfos.get(2).getThumbup());
//			postBlock3.setBounds(57, 602, 660, 80); // 调整位置
//			contentPane.add(postBlock3);
//		}
//
//
//
//
//
//
//	}
//
//	protected String quickFormatDate(String date) {
//		return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
//	}
//}

package tech.zxuuu.client.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import tech.zxuuu.client.auth.AuthGUI;
import tech.zxuuu.client.library.LibraryStudentGUI;
import tech.zxuuu.client.library.MyBorrowGUI;
import tech.zxuuu.client.opencourse.StuMenuGUI;
import tech.zxuuu.client.rounded.LibButton;
import tech.zxuuu.client.shop.ShopFirstPage;
import tech.zxuuu.client.teaching.studentSide.TeachingStudentMain;
import tech.zxuuu.entity.Book;
import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.entity.NoticeInfo;
import tech.zxuuu.entity.PostInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import tech.zxuuu.client.rounded.RoundedPanel;
import tech.zxuuu.client.rounded.RoundedLabel;
import tech.zxuuu.client.rounded.RoundedButton;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import tech.zxuuu.client.bbs.BBSGUI;

public class AppStudent extends JFrame {

	private JPanel contentPane;
	public static JLabel lblBalance;

	/**
	 * Create the frame.
	 */
	public AppStudent() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AppStudent.class.getResource("/resources/assets/icon/seu_icon.png")));
		setTitle("学生主页 - VCampus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1151, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setLocation(-871, -176);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblVcampus = new JLabel("学生主页");
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		lblVcampus.setForeground(Color.WHITE); // 设置字体颜色为白色
		lblVcampus.setBounds(120, 23, 239, 34);
		contentPane.add(lblVcampus);

		JButton btnTeaching = new RoundedButton("教务平台",30);
		btnTeaching.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						TeachingStudentMain teachingStudentMain = new TeachingStudentMain();
						teachingStudentMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						teachingStudentMain.setVisible(true);
					}
				});
			}
		});
		btnTeaching.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		btnTeaching.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/teaching.png")));
		btnTeaching.setBounds(794, 620, 220, 80);
		btnTeaching.setBackground(new Color(0, 204, 153));
		contentPane.add(btnTeaching);

		btnTeaching.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnTeaching.setBackground(new Color(0, 214, 163));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnTeaching.setBackground(new Color(0, 204, 153));
			}

			@Override
			public void mouseEntered(MouseEvent evt) {
				btnTeaching.setBackground(new Color(0, 214, 163)); // 鼠标进入时的颜色
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				btnTeaching.setBackground(new Color(0, 204, 153)); // 鼠标离开时的颜色
			}

		});


		JButton btnLibrary = new RoundedButton(" 图书借阅",30);
		btnLibrary.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LibraryStudentGUI libraryStudentGUI = new LibraryStudentGUI();
				libraryStudentGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				libraryStudentGUI.setVisible(true);
			}
		});
		btnLibrary.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		btnLibrary.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/library.png")));
		btnLibrary.setBounds(540, 511, 220, 80);
		contentPane.add(btnLibrary);
		btnLibrary.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnLibrary.setBackground(new Color(0,120,0));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnLibrary.setBackground(new Color(0,100,0));
			}

			@Override
			public void mouseEntered(MouseEvent evt) {
				btnLibrary.setBackground(new Color(0, 120, 0)); // 鼠标进入时的颜色
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				btnLibrary.setBackground(new Color(0, 100, 0)); // 鼠标离开时的颜色
			}

		});



		JButton btnShop = new RoundedButton("校园超市",30);
		btnShop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShopFirstPage shopFirstPage = new ShopFirstPage();
				shopFirstPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				shopFirstPage.setVisible(true);
			}
		});
		btnShop.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		btnShop.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/shop.png")));
		btnShop.setBounds(540, 620, 220, 80);
		btnShop.setBackground(new Color(51, 153, 255));
		contentPane.add(btnShop);
		btnShop.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnShop.setBackground(new Color(61, 163, 255));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnShop.setBackground(new Color(51, 153, 255));
			}

			@Override
			public void mouseEntered(MouseEvent evt) {
				btnShop.setBackground(new Color(61, 163, 255)); // 鼠标进入时的颜色
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				btnShop.setBackground(new Color(51, 153, 255)); // 鼠标离开时的颜色
			}

		});



		JButton btnOpencourse = new RoundedButton("校园论坛",30);
		btnOpencourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						BBSGUI stuMenuGUI = new BBSGUI();
						stuMenuGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						stuMenuGUI.setVisible(true);
					}
				});
			}
		});
		btnOpencourse.setBackground(new Color(204, 153, 0));
		btnOpencourse.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/opencourse.png")));
		btnOpencourse.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		btnOpencourse.setBounds(794, 511, 220, 80);
		contentPane.add(btnOpencourse);
		btnOpencourse.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnOpencourse.setBackground(new Color(214, 163, 0));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnOpencourse.setBackground(new Color(204, 153, 0));
			}

			@Override
			public void mouseEntered(MouseEvent evt) {
				btnOpencourse.setBackground(new Color(214, 163, 0)); // 鼠标进入时的颜色
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				btnOpencourse.setBackground(new Color(204, 153, 0)); // 鼠标离开时的颜色
			}

		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/seu_icon.png")));
		lblNewLabel.setBounds(25, 12, 64, 64);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("个人信息");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/aboutme.png")));
		lblNewLabel_1.setBounds(20, 85, 250, 64);
		contentPane.add(lblNewLabel_1);

		RoundedPanel panel = new RoundedPanel(40, 40);
		panel.setBounds(50, 150, 350, 280);
		panel.setBackground(new Color(204, 255, 153));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblName = new JLabel("...");
		lblName.setBounds(160, 23, 149, 25);
		lblName.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel.add(lblName);

		JLabel lblCardNumber = new JLabel("...");
		lblCardNumber.setBounds(160, 23+43, 149, 25);
		lblCardNumber.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel.add(lblCardNumber);

		JLabel lblStudentNumber = new JLabel("...");
		lblStudentNumber.setBounds(160, 23+43*2, 149, 25);
		lblStudentNumber.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel.add(lblStudentNumber);

		JLabel lblAcademy = new JLabel("...");
		lblAcademy.setBounds(160, 23+43*3, 149, 25);
		lblAcademy.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel.add(lblAcademy);

		lblBalance = new JLabel("...");
		lblBalance.setBounds(160, 23+43*4, 97, 25);
		lblBalance.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel.add(lblBalance);

		JLabel lblBookLend = new JLabel("...");
		lblBookLend.setBounds(160, 23+43*5, 72, 25);
		lblBookLend.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel.add(lblBookLend);

		JLabel label = new JLabel("姓名：");
		label.setBounds(18, 23, 72, 25);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel.add(label);

		JLabel label_1 = new JLabel("一卡通号：");
		label_1.setBounds(18, 23+43, 160, 25);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel.add(label_1);

		JLabel label_2 = new JLabel("学号：");
		label_2.setBounds(18, 23+43*2, 100, 25);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel.add(label_2);

		JLabel label_3 = new JLabel("院系：");
		label_3.setBounds(18, 23+43*3, 100, 25);
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel.add(label_3);

		JLabel label_4 = new JLabel("一卡通余额：");
		label_4.setBounds(18, 23+43*4, 180, 25);
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel.add(label_4);

		JLabel label_5 = new JLabel("图书馆借书：");
		label_5.setBounds(18, 23+43*5, 180, 25);
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel.add(label_5);


		/*
		JLabel lblToday = new JLabel("今天是星期");
		lblToday.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		lblToday.setBounds(884, 460, 198, 18);
		contentPane.add(lblToday);
		lblToday.setText("今天是星期" + (today == 1 ? "一"
				: today == 2 ? "二"
						: today == 3 ? "三" : today == 4 ? "四" : today == 5 ? "五" : today == 6 ? "六" : today == 7 ? "日" : "..."));
		/**
		 * 新增，初始化控件内容
		 */
		lblName.setText(App.session.getStudent().getName());
		lblAcademy.setText(App.session.getStudent().getAcademy() + " - "
				+ OtherUtils.getAcademyByNumber(App.session.getStudent().getAcademy()));
		lblBalance.setText(String.format("%.2f", App.session.getStudent().getBalance()));
		lblStudentNumber.setText(App.session.getStudent().getStudentNumber());
		lblCardNumber.setText(App.session.getStudent().getCardNumber());
		List<Book> tempList = ResponseUtils.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.library.Addons.getBorrowedBookList",
								new Object[] { App.session.getStudent().getCardNumber() }).send())
				.getListReturn(Book.class);
		if (tempList == null || tempList.size() == 0) {
			lblBookLend.setText("0 本");
		} else {
			lblBookLend.setText(tempList.size() + " 本");
		}
		JButton btnRecharge = new RoundedButton("充值",10);
		btnRecharge.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnRecharge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String money = SwingUtils.popInput(null,"请输入充值金额：");
				if (!SwingUtils.isPureDigits(money)) {
					SwingUtils.showError(null, "输入有误！", "错误");
					return;
				}

				Boolean result = ResponseUtils
						.getResponseByHash(
								(new Request(App.connectionToServer, null, "tech.zxuuu.server.main.UtilsApi.chargeCardBalance",
										new Object[] { App.session.getStudent().getCardNumber(), Integer.parseInt(money) }).send()))
						.getReturn(Boolean.class);

				if (result) {
					SwingUtils.showMessage(null, "充值成功！", "提示");
					lblBalance
							.setText(String.format("%.2f", Double.parseDouble(lblBalance.getText()) + Double.parseDouble(money)));
				} else {
					SwingUtils.showError(null, "充值失败！", "错误");
				}

			}
		});
		btnRecharge.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				btnRecharge.setBackground(new Color(0, 120, 0)); // 鼠标进入时的颜色
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				btnRecharge.setBackground(new Color(0, 100, 0)); // 鼠标离开时的颜色
			}
		});
		btnRecharge.setBounds(260, 23+43*4, 70, 30);
		panel.add(btnRecharge);

		JButton btnMyBorrow = new RoundedButton("查看",10);
		btnMyBorrow.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnMyBorrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MyBorrowGUI myBorrowGUI = new MyBorrowGUI();
				myBorrowGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myBorrowGUI.setVisible(true);
			}
		});
		btnMyBorrow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				btnMyBorrow.setBackground(new Color(0, 120, 0)); // 鼠标进入时的颜色
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				btnMyBorrow.setBackground(new Color(0, 100, 0)); // 鼠标离开时的颜色
			}
		});
		btnMyBorrow.setBounds(260, 23+43*5, 70, 30);
		panel.add(btnMyBorrow);

		Integer today = OtherUtils.getDay(ResponseUtils.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.main.UtilsApi.getTrustedUnixTimeStamp", null)
								.send())
				.getReturn(Long.class));


		/* 计算今日课程 */
		JLabel lblTodayCourses = new JLabel("今日课表");
		String selectedClass = ResponseUtils.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.teaching.ClassSelectGUI.getClassSelection",
								new Object[] { App.session.getStudent() }).send())
				.getReturn(String.class);

		ClassInfo[] todayClass = new ClassInfo[5];

		if (selectedClass != null && !selectedClass.equals("")) {
			for (int i = 0; i < 5; i++) {
				todayClass[i] = null;
			}
			String[] course = selectedClass.split(",");
			for (int i = 0; i < course.length; i++) {
				if (Integer.valueOf(course[i].charAt(6) - 48) == today) {
					ClassInfo cla = ResponseUtils
							.getResponseByHash(new Request(App.connectionToServer, null,
									"tech.zxuuu.server.teaching.ClassSelectGUI.getOneClass", new Object[] { course[i] }).send())
							.getReturn(ClassInfo.class);
					todayClass[(Integer.valueOf(course[i].charAt(8)) - 48) / 2 - 1] = cla;
				}
				if (Integer.valueOf(course[i].charAt(9) - 48) == today) {
					ClassInfo cla = ResponseUtils
							.getResponseByHash(new Request(App.connectionToServer, null,
									"tech.zxuuu.server.teaching.ClassSelectGUI.getOneClass", new Object[] { course[i] }).send())
							.getReturn(ClassInfo.class);
					todayClass[(Integer.valueOf(course[i].charAt(11)) - 48) / 2 - 1] = cla;
				}

			}
		}

		JLabel lblCI1 = new JLabel("无课程");
		lblCI1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		JLabel lblCI2 = new JLabel("无课程");
		lblCI2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		JLabel lblCI3 = new JLabel("无课程");
		lblCI3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		JLabel lblCI4 = new JLabel("无课程");
		lblCI4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		JLabel lblCI5 = new JLabel("无课程");
		lblCI5.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		JLabel[] lblCourses = new JLabel[] { lblCI1, lblCI2, lblCI3, lblCI4, lblCI5 };

		for (int i = 0; i < 5; i++) {
			if (todayClass[i] != null) {
				lblCourses[i].setText(todayClass[i].getClassName() + " @ " + todayClass[i].getClassroom());
			}
		}

		RoundedPanel classpanel = new RoundedPanel(40,40);
		classpanel.setBounds(50, 520, 350, 210);
		classpanel.setBackground(new Color(255, 255, 153));
		contentPane.add(classpanel);
		classpanel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setIcon(new ImageIcon(AppStudent.class.getResource("/resources/assets/icon/test.png")));
		lblNewLabel_2.setBounds(50, 440, 64, 64);
		contentPane.add(lblNewLabel_2);

		lblTodayCourses.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblTodayCourses.setBounds(150, 460, 115, 25);
		contentPane.add(lblTodayCourses);

		JLabel lblNewLabel_3 = new JLabel("1~2节");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(18, 17, 64, 24);
		classpanel.add(lblNewLabel_3);

		JLabel label_6 = new JLabel("3~4节");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_6.setBounds(18, 17+35, 64, 24);
		classpanel.add(label_6);

		JLabel label_7 = new JLabel("5~6节");
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_7.setBounds(18, 17+35*2, 64, 24);
		classpanel.add(label_7);

		JLabel label_8 = new JLabel("7~8节");
		label_8.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_8.setBounds(18, 17+35*3, 64, 24);
		classpanel.add(label_8);

		JLabel label_9 = new JLabel("9~10节");
		label_9.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_9.setBounds(18, 17+35*4, 64, 24);
		classpanel.add(label_9);

		lblCI1.setBounds(160, 20, 222, 20);
		classpanel.add(lblCI1);

		lblCI2.setBounds(160, 20+35, 222, 20);
		classpanel.add(lblCI2);

		lblCI3.setBounds(160, 20+35*2, 222, 20);
		classpanel.add(lblCI3);

		lblCI4.setBounds(160, 20+35*3, 222, 20);
		classpanel.add(lblCI4);

		lblCI5.setBounds(160, 20+35*4, 222, 20);
		classpanel.add(lblCI5);
		JLabel cardlabel = new JLabel("登录卡号：");
		cardlabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		cardlabel.setForeground(Color.WHITE); // 设置字体颜色为白色
		cardlabel.setBounds(720, 23, 100, 34);
		contentPane.add(cardlabel);

		JLabel cardnumber = new JLabel("");
		cardnumber.setBounds(820, 23, 150, 34);
		cardnumber.setForeground(Color.WHITE); // 设置字体颜色为白色
		contentPane.add(cardnumber);
		cardnumber.setText(App.session.getStudent().getCardNumber());
		cardnumber.setFont(new Font("微软雅黑", Font.PLAIN, 20));

		JButton ExitButton = new LibButton("登出",0);
		ExitButton.setFont(new Font("微软雅黑", Font.PLAIN, 23));
		ExitButton.setBackground(new Color(0, 110, 0));
		ExitButton.setBounds(1000, 0, 135, 80);
		ExitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AuthGUI authGUI = new AuthGUI();
				authGUI.setVisible(true);
				dispose();
			}
		});
		ExitButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				ExitButton.setBackground(new Color(0, 120, 0));
			}

		});
		contentPane.add(ExitButton);

		JLabel greenStrip = new JLabel("");
		greenStrip.setOpaque(true);
		greenStrip.setBackground(new Color(0, 100, 0)); // Green color
		greenStrip.setBounds(0, 0, 1150, 80); // Adjust the height as needed
		contentPane.add(greenStrip, Integer.valueOf(-1)); // Add to the bottom layer

		List<NoticeInfo> noticeInfos = ResponseUtils
				.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.main.UtilsApi.getTop4Notice", null).send())
				.getListReturn(NoticeInfo.class);

		if (noticeInfos.size() > 0) {
			NoticeBlock noticeBlock1 = new NoticeBlock(noticeInfos.get(0).getTitle(),
					quickFormatDate(noticeInfos.get(0).getDate()), noticeInfos.get(0).getUrl());
			noticeBlock1.setBounds(540, 170, 474, 50);
			noticeBlock1.setBackground(new Color(255, 224, 204));
			contentPane.add(noticeBlock1);
		}

		if (noticeInfos.size() > 1) {
			NoticeBlock noticeBlock2 = new NoticeBlock(noticeInfos.get(1).getTitle(),
					quickFormatDate(noticeInfos.get(1).getDate()), noticeInfos.get(1).getUrl());
			noticeBlock2.setBounds(540, 233, 474, 50);
			noticeBlock2.setBackground(new Color(204, 255, 204));
			contentPane.add(noticeBlock2);
		}

		if (noticeInfos.size() > 2) {
			NoticeBlock noticeBlock3 = new NoticeBlock(noticeInfos.get(2).getTitle(),
					quickFormatDate(noticeInfos.get(2).getDate()), noticeInfos.get(2).getUrl());
			noticeBlock3.setBounds(540, 296, 474, 50);
			noticeBlock3.setBackground(new Color(255, 224, 255));
			contentPane.add(noticeBlock3);
		}

		if (noticeInfos.size() > 3) {
			NoticeBlock noticeBlock4 = new NoticeBlock(noticeInfos.get(3).getTitle(),
					quickFormatDate(noticeInfos.get(3).getDate()), noticeInfos.get(3).getUrl());
			noticeBlock4.setBounds(540, 359, 474, 50);
			noticeBlock4.setBackground(new Color(204, 255, 255));
			contentPane.add(noticeBlock4);
		}

				//考虑加一个icon“热门帖子”

		List<PostInfo> postInfos = ResponseUtils.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.main.UtilsApi.getTop3Post", null).send())
				.getListReturn(PostInfo.class);

		if (postInfos.size() > 0) {
			PostBlock postBlock1 = new PostBlock(postInfos.get(0).getId(), postInfos.get(0).getContent(),
					quickFormatDate(postInfos.get(0).getDate()), postInfos.get(0).getUser_id(), postInfos.get(0).getThumbup());
			postBlock1.setBounds(57, 422, 660, 80); // 调整位置
			contentPane.add(postBlock1);
		}

		if (postInfos.size() > 1) {
			PostBlock postBlock2 = new PostBlock(postInfos.get(1).getId(), postInfos.get(1).getContent(),
					quickFormatDate(postInfos.get(1).getDate()), postInfos.get(1).getUser_id(), postInfos.get(1).getThumbup());
			postBlock2.setBounds(57, 512, 660, 80); // 调整位置
			contentPane.add(postBlock2);
		}

		if (postInfos.size() > 2) {
			PostBlock postBlock3 = new PostBlock(postInfos.get(2).getId(), postInfos.get(2).getContent(),
					quickFormatDate(postInfos.get(2).getDate()), postInfos.get(2).getUser_id(), postInfos.get(2).getThumbup());
			postBlock3.setBounds(57, 602, 660, 80); // 调整位置
			contentPane.add(postBlock3);
		}


	}

	protected String quickFormatDate(String date) {
		return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
	}
}

