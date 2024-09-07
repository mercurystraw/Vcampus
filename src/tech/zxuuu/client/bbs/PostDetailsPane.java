package tech.zxuuu.client.bbs;

import javax.swing.*;
import java.awt.*;

public class PostDetailsPane extends JPanel {

    private JLabel lblPostId;
    private JTextPane txtContent; // 使用 JTextPane 显示帖子内容
    private JLabel lblDate;
    private JLabel lblUserId;
    private JTextArea txtReplies;

    public PostDetailsPane(String id, String content, String date, String userid) {
        setLayout(new BorderLayout());

        // 主帖子信息面板
        JPanel postInfoPanel = new JPanel();
        postInfoPanel.setLayout(new GridLayout(4, 1));

        this.lblPostId = new JLabel("帖子ID: " + id);
        this.txtContent = new JTextPane(); // 使用 JTextPane 显示内容
        this.txtContent.setEditable(false); // 设置为不可编辑
        this.txtContent.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16)); // 设置支持 Emoji 的字体
        this.txtContent.setText(content); // 设置帖子内容

        this.lblDate = new JLabel("日期: " + date);
        this.lblUserId = new JLabel("用户ID: " + userid);

        postInfoPanel.add(lblPostId);
        postInfoPanel.add(txtContent); // 添加帖子内容到面板
        postInfoPanel.add(lblDate);
        postInfoPanel.add(lblUserId);

        add(postInfoPanel, BorderLayout.NORTH);

        // 回复信息面板
        this.txtReplies = new JTextArea(10, 30); // 设置行数和列数
        txtReplies.setEditable(false);
        txtReplies.setText("这里是回复信息"); // 设置初始文本
        JScrollPane scrollPane = new JScrollPane(txtReplies);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // 设置垂直滚动条策略
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // 设置水平滚动条策略
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
