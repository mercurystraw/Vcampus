package tech.zxuuu.client.main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class PostBlock extends JPanel {

    private String id;
    private String content;
    private String date;
    private String userid;
    private int thumbup;

    /**
     * Create the panel.
     */
    public PostBlock(String _id, String _content, String _date, String _userid, int _thumbup) {
        setBackground(new Color(240, 255, 240));
        this.id = _id;
        this.content = _content;
        this.date = _date;
        this.userid = _userid;
        this.thumbup = _thumbup;
        setLayout(null);

        JLabel lblDot = new JLabel("·");
        lblDot.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        lblDot.setBounds(24, 13, 4, 24);
        add(lblDot);

        JLabel lblContent = new JLabel(content);
        lblContent.setFont(new Font("宋体", Font.PLAIN, 18));
        lblContent.setBounds(42, 18, 513, 18);
        add(lblContent);

        JLabel lblDate = new JLabel(date);
        lblDate.setFont(new Font("宋体", Font.PLAIN, 18));
        lblDate.setBounds(556, 16, 108, 18);
        add(lblDate);

        JLabel lblUserid = new JLabel("用户ID: " + userid);
        lblUserid.setFont(new Font("宋体", Font.PLAIN, 14));
        lblUserid.setBounds(42, 40, 200, 14);
        add(lblUserid);

        JLabel lblThumbup = new JLabel("点赞数: " + thumbup);
        lblThumbup.setFont(new Font("宋体", Font.PLAIN, 14));
        lblThumbup.setBounds(250, 40, 200, 14);
        add(lblThumbup);


    }
}
