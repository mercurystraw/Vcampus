package tech.zxuuu.client.bbs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class PostInfoPane extends JPanel {

    public static final int HEIGHT = 130;

    private String id; // 帖子ID
    private String content; // 帖子内容
    private String date; // 发布日期
    private String userid; // 用户ID

    private JLabel lblPostId;
    private JLabel lblContent;
    private JLabel lblDate;
    private JLabel lblUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_id() {
        return userid;
    }

    public void setUser_id(String userid) {
        this.userid = userid;
    }

    /**
     * Create the panel.
     */
    public PostInfoPane() {
        setLayout(null);

        this.lblPostId = new JLabel("帖子ID");
        lblPostId.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblPostId.setBounds(10, 10, 150, 30);
        add(lblPostId);

        this.lblContent = new JLabel("帖子内容");
        lblContent.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblContent.setBounds(10, 40, 500, 20);
        add(lblContent);

        this.lblDate = new JLabel("发布日期");
        lblDate.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblDate.setBounds(10, 70, 200, 20);
        add(lblDate);

        this.lblUserId = new JLabel("用户ID");
        lblUserId.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblUserId.setBounds(10, 100, 150, 30);
        add(lblUserId);

        JButton btnViewDetails = new JButton("查看详情");
        btnViewDetails.setIcon(new ImageIcon(PostInfoPane.class.getResource("/resources/assets/icon/right-circle.png")));
        btnViewDetails.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        btnViewDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加查看详情的逻辑
                PostDetailsGUI postDetailsGUI = new PostDetailsGUI(id);
                postDetailsGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                postDetailsGUI.setVisible(true);
            }
        });
        btnViewDetails.setBounds(600, 100, 150, 70);
        add(btnViewDetails);

        setLayout(null);
        setVisible(true);
    }

    public PostInfoPane(String id, String content, String date, String userid) {
        this();
        this.id = id;
        this.content = content;
        this.date = date;
        this.userid = userid;

        this.lblPostId.setText("帖子ID: " + this.id);
        this.lblContent.setText("内容: " + this.content);
        this.lblDate.setText("日期: " + this.date);
        this.lblUserId.setText("用户ID: " + this.userid);

        this.validate();
        this.repaint();
        this.updateUI();
        this.revalidate();
    }
}

