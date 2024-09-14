package tech.mainCode.client.library;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tech.mainCode.client.main.App;
import tech.mainCode.entity.Book;
import tech.mainCode.net.Request;
import tech.mainCode.util.ResponseUtils;
import tech.mainCode.client.rounded.*;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.*;
import java.net.MalformedURLException;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.net.URL;
/**
 * 书籍详情窗口
 */
public class BookDetails extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTable tblRecommand;
	private DefaultTableModel model;
	public JTextField txtCategory;
	public  JTextField txtName;
	public String title;
	public String ISBN;
	private List<Book> list = null;

	JTextArea txtDetails = null;

	/**
	 * Create the dialog.
	 */
	public BookDetails(String title, String ISBN, String category, String details) {
		setTitle("图书详情 - " + title + " - VCampus");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BookDetails.class.getResource("/resources/assets/icon/fav.png")));
		setResizable(false);
		setBounds(100, 100, 680, 350);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 432, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblDetails = new JLabel("图书简介");
		lblDetails.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		lblDetails.setBounds(14, 150, 104, 32);
		getContentPane().add(lblDetails);


		JLabel lblName= new JLabel("图书标题");
		lblName.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		lblName.setBounds(14, 22, 104, 32);
		getContentPane().add(lblName);

		String[] head = { "藏书号", "书名", "作者", "被借次数" };
		model = new DefaultTableModel(null, head);

		tblRecommand = new JTable();
		tblRecommand.setModel(model);
		tblRecommand.setBounds(2, 2, 225, 105);
		JScrollPane jsp = new JScrollPane(tblRecommand);
		jsp.setBounds(14, 336, 624, 154);
		getContentPane().add(jsp);
		txtCategory = new RoundedTextField(10);
		txtCategory.setEditable(false);
		txtCategory.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		txtCategory.setBounds(140, 82, 119, 40);
		getContentPane().add(txtCategory);
		txtCategory.setColumns(10);
		this.txtCategory.setText(category);
		txtName= new RoundedTextField(10);
		txtName.setEditable(false);
		txtName.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		txtName.setBounds(140, 22, 119, 40);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		this.txtName.setText(title);

		list = ResponseUtils.getResponseByHash(new Request(App.connectionToServer, null,
				"tech.mainCode.server.library.BookServer.searchSimilarBook", new Object[] { title, txtCategory.getText() }).send())
				.getListReturn(Book.class);

		model.setRowCount(0);
		String[][] listData = null;
		if (list == null) {
			listData = new String[1][4];
			listData[0][0] = "未找到相关书籍...";
			listData[0][1] = listData[0][2] = listData[0][3] = "";
		} else {
			listData = new String[list.size()][4];
			for (int i = 0; i < list.size(); i++) {
				listData[i][0] = list.get(i).getISBN();
				listData[i][1] = list.get(i).getTitle();
				listData[i][2] = list.get(i).getAuthor();
				listData[i][3] = String.valueOf(list.get(i).getNumofborrowed());
			}
		}
		model = new DefaultTableModel(listData, head) {
			@Override
			public boolean isCellEditable(int a, int b) {
				return false;
			}
		};
		tblRecommand.setModel(model);


		JLabel lblCategory = new JLabel("分类");
		lblCategory.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		lblCategory.setBounds(14, 85, 70, 32);
		getContentPane().add(lblCategory);

		JLabel labelPicture = new JLabel();
		labelPicture.setBounds(453, 21, 185, 260);
		getContentPane().add(labelPicture);


		String result = ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.mainCode.server.library.BookServer.searchPicture", new Object[] { ISBN }).send())
				.getReturn(String.class);
		System.out.println("根据书号检索到的书本图片链接："+result);
		try {
			// 从 URL 创建 ImageIcon
			ImageIcon icon = new ImageIcon(new URL(result));
			// 获取图片并调整大小
			Image img = icon.getImage().getScaledInstance(185, 260, Image.SCALE_SMOOTH);
			// 将 ImageIcon 设置到 JLabel 中
			labelPicture.setIcon(new ImageIcon(img));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			// 处理 URL 格式错误的情况
			labelPicture.setText("图片加载失败");
		}

		txtDetails = new RoundedTextArea(10,10);
		txtDetails.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		txtDetails.setEditable(false);
		txtDetails.setBounds(150, 150, 264, 120);
		getContentPane().add(txtDetails);
		txtDetails.setLineWrap(true);
		txtDetails.setWrapStyleWord(true);
		this.txtDetails.setText(details);



	}
}
