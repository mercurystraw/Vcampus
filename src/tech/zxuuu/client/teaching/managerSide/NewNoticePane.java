package tech.zxuuu.client.teaching.managerSide;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.NoticeInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.OtherUtils;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import tech.zxuuu.client.rounded.RoundedButton;
import tech.zxuuu.client.rounded.RoundedTextField;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class NewNoticePane extends JPanel {
	private RoundedTextField txtTitle;
	private RoundedTextField txtUrl;

	/**
	 * Create the panel.
	 */
	public NewNoticePane() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("发布通知");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel.setBounds(285, 30, 150, 40);
		add(lblNewLabel);

		JLabel label = new JLabel("通知标题");
		label.setBounds(120, 180, 100, 37);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		add(label);

		JLabel lblNewLabel_2 = new JLabel("跳转网页");
		lblNewLabel_2.setBounds(120, 270, 100, 37);
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		add(lblNewLabel_2);

		JButton btnNewButton = new RoundedButton("发 布",10);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (SwingUtils.isTxtEmpty(txtTitle) || SwingUtils.isTxtEmpty(txtUrl)) {
					SwingUtils.showError(null, "有字段为空！", "错误");
					return;
				}

				Long unixTimeStamp = ResponseUtils.getResponseByHash(
						new Request(App.connectionToServer, null, "tech.zxuuu.server.main.UtilsApi.getTrustedUnixTimeStamp", null)
								.send())
						.getReturn(Long.class);

				Date date = new Date(unixTimeStamp);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String dateString = simpleDateFormat.format(date);

				NoticeInfo noticeInfo = new NoticeInfo(txtTitle.getText(), dateString, txtUrl.getText());
				Boolean result = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, null,
								"tech.zxuuu.server.teaching.CourseManagerSide.addNotice", new Object[] { noticeInfo }).send())
						.getReturn(Boolean.class);

				if (result) {
					SwingUtils.showMessage(null, "添加成功！", "提示");
				} else {
					SwingUtils.showError(null, "添加失败！", "错误");
				}

			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		btnNewButton.setBounds(276, 410, 167, 50);
		add(btnNewButton);

		txtTitle = new RoundedTextField(10);
		txtTitle.setBounds(300, 180, 270, 37);
		add(txtTitle);
		txtTitle.setColumns(10);

		txtUrl = new RoundedTextField(10);
		txtUrl.setBounds(300, 270, 270, 37);
		add(txtUrl);
		txtUrl.setColumns(10);

	}
}
