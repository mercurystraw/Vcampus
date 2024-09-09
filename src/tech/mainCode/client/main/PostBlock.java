package tech.mainCode.client.main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;

public class PostBlock extends JPanel {

    private String id;
    private String content;
    private String date;
    private String userid;
    private int thumbup;
    private int arcWidth = 20;
    private int arcHeight = 20;
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
        setOpaque(false);



        JLabel lblContent = new JLabel(content);
        lblContent.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        lblContent.setBounds(22, 18, 513, 20);
        add(lblContent);

        JLabel lblDate = new JLabel(date);
        lblDate.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        lblDate.setBounds(556, 16, 108, 20);
        add(lblDate);

        JLabel lblUserid = new JLabel("用户ID: " + userid);
        lblUserid.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        lblUserid.setBounds(22, 40, 200, 18);
        add(lblUserid);

        JLabel lblThumbup = new JLabel("点赞数: " + thumbup);
        lblThumbup.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        lblThumbup.setBounds(250, 40, 200, 18);
        add(lblThumbup);


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
