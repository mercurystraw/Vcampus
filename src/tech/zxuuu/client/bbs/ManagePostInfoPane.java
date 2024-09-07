package tech.zxuuu.client.bbs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagePostInfoPane extends JPanel {
    public static final int HEIGHT = 130;

    private String id; // 帖子ID
    private String content; // 帖子内容
    private String date; // 发布日期
    private String userid; // 用户ID

    private JLabel lblPostId;
    private JTextPane txtContent; // 使用 JTextPane 显示帖子内容
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
    public ManagePostInfoPane() {
        setLayout(null);

        this.lblPostId = new JLabel("帖子ID");
        lblPostId.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblPostId.setBounds(10, 10, 150, 30);
        add(lblPostId);

        // 使用 JTextPane 来显示帖子内容
        this.txtContent = new JTextPane();
        txtContent.setEditable(false); // 设置为不可编辑
        txtContent.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16)); // 设置支持 Emoji 的字体
        txtContent.setBounds(10, 40, 500, 40); // 设置合适的大小
        txtContent.setOpaque(false); // 使背景透明
        add(txtContent);

        this.lblDate = new JLabel("发布日期");
        lblDate.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblDate.setBounds(10, 90, 200, 20);
        add(lblDate);

        this.lblUserId = new JLabel("用户ID");
        lblUserId.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblUserId.setBounds(10, 110, 150, 30);
        add(lblUserId);

        JButton btnViewDetails = new JButton("查看详情");
        btnViewDetails.setIcon(new ImageIcon(PostInfoPane.class.getResource("/resources/assets/icon/right-circle.png")));
        btnViewDetails.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        btnViewDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加查看详情的逻辑
                ManageReplyGUI manageReplyGUI = new ManageReplyGUI(id);
                manageReplyGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                manageReplyGUI.setVisible(true);
            }
        });
        btnViewDetails.setBounds(600, 100, 150, 70);
        add(btnViewDetails);

        setVisible(true);
    }

    public ManagePostInfoPane(String id, String content, String date, String userid) {
        this();
        this.id = id;
        this.content = content;
        this.date = date;
        this.userid = userid;

        this.lblPostId.setText("帖子ID: " + this.id);
        this.txtContent.setText(this.content); // 使用 JTextPane 设置帖子内容
        this.lblDate.setText("日期: " + this.date);
        this.lblUserId.setText("用户ID: " + this.userid);

        this.validate();
        this.repaint();
        this.updateUI();
        this.revalidate();
    }
}
