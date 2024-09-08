package src.tech.zxuuu.client.library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//import com.sun.org.apache.xpath.internal.operations.Bool;

import tech.zxuuu.client.main.App;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import tech.zxuuu.client.rounded.RoundedButton;
import java.awt.Font;
import tech.zxuuu.client.rounded.RoundedTextField;

/**
 * 书籍删除面板
 * 
 * @author 曾铖
 * @modify z0gSh1u
 */
public class DeleteBookPane extends JPanel {

	private RoundedTextField txtISBN;

	/**
	 * Create the panel.
	 */
	public DeleteBookPane() {
		this.setLayout(null);

		txtISBN = new RoundedTextField(10);
		txtISBN.setBounds(337, 223, 272, 27);
		txtISBN.setBorder(null);
		txtISBN.setFont(new Font("微软雅黑",Font.PLAIN,18));
		this.add(txtISBN);
		txtISBN.setColumns(10);

		JLabel lblISBN = new JLabel("图书编号");
		lblISBN.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		lblISBN.setBounds(164, 219, 140, 33);
		this.add(lblISBN);

		RoundedButton btnComfirm = new RoundedButton("确 定",10);
		btnComfirm.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		btnComfirm.setBounds(381, 440, 124, 39);
		btnComfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean ret = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, null,
								"tech.zxuuu.server.library.BookServer.deleteBook", new Object[] { txtISBN.getText() }).send())
						.getReturn(Boolean.class);

				if (ret) {
					SwingUtils.showMessage(null, "删除成功！", "提示");
				} else {
					SwingUtils.showError(null, "删除失败！", "错误");
				}
			}
		});
		this.add(btnComfirm);


	}

}
