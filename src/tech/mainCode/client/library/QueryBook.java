package tech.mainCode.client.library;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tech.mainCode.client.main.App;

import tech.mainCode.entity.Book;
import tech.mainCode.net.Request;
import tech.mainCode.util.ResponseUtils;
import tech.mainCode.util.SwingUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Font;
import tech.mainCode.client.rounded.*;
/**
 * 书籍搜索与借阅窗口
 * 

 */
public class QueryBook extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private RoundedTextField txtTitle;
	private RoundedTextField txtAuthor;
	private JTable tblSearch;
	private DefaultTableModel model;
	private RoundedTextField txtISBN;
	private List<Book> list = null;

	/**
	 * Create the dialog.
	 */
	public QueryBook() {
		setResizable(false);
		setTitle("书籍检索与借阅 - VCampus");
		setIconImage(Toolkit.getDefaultToolkit().getImage(QueryBook.class.getResource("/resources/assets/icon/seu_icon.png")));
		setBounds(100, 100, 808, 467);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		txtTitle = new RoundedTextField(10);
		txtTitle.setBounds(65, 15, 233, 30);
		txtTitle.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		contentPanel.add(txtTitle);
		txtTitle.setColumns(10);

		JLabel lblTitle = new JLabel("书名");
		lblTitle.setBounds(21, 21, 50, 18);
		lblTitle.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		contentPanel.add(lblTitle);

		txtAuthor = new RoundedTextField(10);
		txtAuthor.setBounds(356, 15, 157, 30);
		txtAuthor.setText("");
		txtAuthor.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		contentPanel.add(txtAuthor);
		txtAuthor.setColumns(10);

		JLabel lblAuthor = new JLabel("作者");
		lblAuthor.setBounds(312, 21, 50, 18);
		lblAuthor.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		contentPanel.add(lblAuthor);

		String[] tableHeader = { "书目编号", "书名", "作者" };
		model = new DefaultTableModel(null, tableHeader);
		tblSearch = new JTable();
		tblSearch.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());

					BookDetails details = new BookDetails(list.get(row).getTitle(), list.get(row).getISBN(),
							list.get(row).getCategory(), list.get(row).getDetails());
					details.setModal(true);
					details.setVisible(true);
				}
				if (e.getClickCount() == 1) {
					int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
					txtISBN.setText(list.get(row).getISBN());
				}
			}
		});
		JScrollPane jsp = new JScrollPane(tblSearch);
		jsp.setBounds(21, 140, 740, 266);

		RoundedButton btnSearch = new RoundedButton("检 索",10);
		btnSearch.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		btnSearch.setBounds(600, 10, 121, 40);

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				list = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, null,
								"tech.mainCode.server.library.BookServer.fuzzySearchByTitleAndAuthor",
								new Object[] { txtTitle.getText().trim(), txtAuthor.getText().trim() }).send())
						.getListReturn(Book.class);
				String[][] listData = new String[list.size()][3];
				model.setRowCount(0);
				if (list == null) {
					SwingUtils.showMessage(null, "No finding", "test");
				} else {
					for (int i = 0; i < list.size(); i++) {
						listData[i][0] = list.get(i).getISBN();
						listData[i][1] = list.get(i).getTitle();
						listData[i][2] = list.get(i).getAuthor();
					}
					model = new DefaultTableModel(listData, tableHeader) {
						@Override
						public boolean isCellEditable(int a, int b) {
							return false;
						}
					};
					tblSearch.setModel(model);
					SwingUtils.showMessage(null, "查询完毕！", "提示");
				}
			}

		});

		contentPanel.add(btnSearch);

		contentPanel.add(jsp);
		tblSearch.setModel(model);
		tblSearch.setBounds(2, 2, 300, 300);

		txtISBN = new RoundedTextField(10);
		txtISBN.setBounds(271, 80, 267, 30);
		contentPanel.add(txtISBN);
		txtISBN.setColumns(10);

		JLabel lblISBN = new JLabel("需要借阅的书目编号");
		lblISBN.setBounds(50, 85, 200, 22);
		lblISBN.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPanel.add(lblISBN);

		RoundedButton btnComfirm = new RoundedButton("确 认",10);
		btnComfirm.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		btnComfirm.setBounds(600, 75, 121, 40);
		btnComfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = ResponseUtils
						.getResponseByHash(
								new Request(App.connectionToServer, App.session, "tech.mainCode.server.library.BookServer.borrowBook",
										new Object[] { App.session.getStudent().getCardNumber(), txtISBN.getText() }).send())
						.getReturn(Integer.class);
				if (result == 2) {
					SwingUtils.showMessage(null, "借阅成功！", "提示");
				}
				if (result == 1) {
					SwingUtils.showError(null, "本书已被借阅！", "错误");
				}
				if (result == 0) {
					SwingUtils.showError(null, "无效的书目编号！", "错误");
				}
			}
		});
		contentPanel.add(btnComfirm);

	}
}
