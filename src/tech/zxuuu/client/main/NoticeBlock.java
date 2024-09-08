package tech.zxuuu.client.main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import tech.zxuuu.util.SwingUtils;

public class NoticeBlock extends JPanel {

	private String title;
	private String date;
	private String url;
	private int arcWidth = 20;
	private int arcHeight = 20;

	public NoticeBlock(String _title, String _date, String _url) {
		this.title = _title;
		this.date = _date;
		this.url = _url;
		setLayout(null);
		setOpaque(false);

		NoticeBlock that = this;
		JLabel lblTitle = new JLabel(title);
		lblTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI(that.url));
				} catch (IOException | URISyntaxException e1) {
					SwingUtils.showError(null, "链接打开失败！", "错误");
				}
			}
		});
		lblTitle.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblTitle.setBounds(22, 18, 513, 20);
		add(lblTitle);
		JLabel lblDate = new JLabel(date);
		lblDate.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblDate.setBounds(366, 16, 108, 18);
		add(lblDate);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
		super.paintComponent(g);
		g2.dispose();
	}
}