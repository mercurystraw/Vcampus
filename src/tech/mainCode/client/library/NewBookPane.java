package tech.mainCode.client.library;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//import com.sun.org.apache.xpath.internal.operations.Bool;

import tech.mainCode.client.main.App;
import tech.mainCode.net.Request;
import tech.mainCode.util.ResponseUtils;
import tech.mainCode.util.SwingUtils;
import tech.mainCode.client.rounded.RoundedTextField;
import tech.mainCode.client.rounded.RoundedButton;
import tech.mainCode.client.rounded.RoundedTextArea;

/**
 * 书籍添加面板

 */
public class NewBookPane extends JPanel {

	private RoundedTextField txtTitle;
	private RoundedTextField txtauthor;
	private JLabel lbl;
	private JLabel lblAuthor;
	private RoundedTextField txtSetISBN;
	private JLabel lblSetISBN;
	private RoundedButton btnComfirm;
	private JLabel lblCategory;
	private RoundedTextArea txtAreaDetails;
	private RoundedTextField txtPictureURL;
	private JLabel label;
	private JLabel lblPx;
	JComboBox<String> combCategory;
	private JButton btnNewButton;

	/**
	 * Create the panel.
	 */
	public NewBookPane() {
		this.setLayout(null);

		txtTitle = new RoundedTextField(10);
		txtTitle.setBounds(224, 20, 542, 30);
		txtTitle.setBorder(null);
		txtTitle.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		this.add(txtTitle);
		txtTitle.setColumns(10);

		txtauthor = new RoundedTextField(10);
		txtauthor.setBounds(224, 85, 542, 30);
		txtauthor.setBorder(null);
		txtauthor.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		this.add(txtauthor);
		txtauthor.setColumns(10);

		lbl = new JLabel("图书名称");
		lbl.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lbl.setBounds(46, 20, 175, 29);
		this.add(lbl);

		lblAuthor = new JLabel("图书作者");
		lblAuthor.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblAuthor.setBounds(46, 85, 175, 29);
		this.add(lblAuthor);

		txtSetISBN = new RoundedTextField(10);
		txtSetISBN.setBounds(224, 153, 175, 30);
		txtSetISBN.setBorder(null);
		txtSetISBN.setFont(new Font("微软雅黑",Font.PLAIN,18));
		this.add(txtSetISBN);
		txtSetISBN.setColumns(10);

		lblSetISBN = new JLabel("图书编号");
		lblSetISBN.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblSetISBN.setBounds(46, 153, 157, 30);
		this.add(lblSetISBN);

		btnComfirm = new RoundedButton("确 定",10);
		btnComfirm.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		btnComfirm.setBounds(381, 440, 124, 39);
		btnComfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean ret = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, null, "tech.mainCode.server.library.BookServer.addBook",
								new Object[] { txtSetISBN.getText(), txtTitle.getText(), txtauthor.getText(),
										(String) combCategory.getSelectedItem(), txtAreaDetails.getText(), txtPictureURL.getText() })
												.send())
						.getReturn(Boolean.class);
				if (ret) {
					SwingUtils.showMessage(null, "新增成功！", "提示");
				} else {
					SwingUtils.showError(null, "新增失败！", "错误");
				}
			}
		});
		this.add(btnComfirm);

		JLabel lblDetails = new JLabel("图书简介");
		lblDetails.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblDetails.setBounds(48, 230, 88, 30);
		this.add(lblDetails);

		combCategory = new JComboBox<String>();
		combCategory.setBounds(591, 156, 175, 30);
		combCategory.addItem("教育");
		combCategory.addItem("小说");
		combCategory.addItem("文艺");
		combCategory.addItem("社科");
		combCategory.addItem("经管");
		combCategory.addItem("科技");
		combCategory.addItem("励志");
		combCategory.addItem("体育");
		combCategory.setVisible(true);
		combCategory.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		add(combCategory);

		lblCategory = new JLabel("分类");
		lblCategory.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblCategory.setBounds(467, 153, 48, 29);
		this.add(lblCategory);

		txtAreaDetails = new RoundedTextArea(5,10);
		txtAreaDetails.setBounds(224, 230, 542, 123);
		txtAreaDetails.setFont(new Font("微软雅黑",Font.PLAIN,18));
		txtAreaDetails.setLineWrap(true);
		txtAreaDetails.setBorder(null);
		this.add(txtAreaDetails);

		JLabel lblurl = new JLabel("图书封面网址");
		lblurl.setFont(new Font("微软雅黑", Font.PLAIN, 22));

		lblurl.setBounds(46, 382, 157, 30);
		this.add(lblurl);

		txtPictureURL = new RoundedTextField(10);
		txtPictureURL.setBounds(224, 380, 542, 35);
		txtPictureURL.setFont(new Font("微软雅黑",Font.PLAIN,18));
		txtPictureURL.setBorder(null);
		this.add(txtPictureURL);
		txtPictureURL.setColumns(10);


		

	}

}
