package tech.zxuuu.client.bbs;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.PostInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InsertReplyPostGUI extends JFrame {

    private JTextArea txtReplyContent;
    private JButton btnSubmit;
    private String postId; // 当前帖子的ID

    // 提交回复的方法
    private void submitReply() {
        String content = txtReplyContent.getText().trim();

        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "回复内容不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PostInfo reply = new PostInfo();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDateTime = now.format(formatter);

        reply.setId(postId); //便于传参进去给mapper用
        reply.setDate(currentDateTime); // 设置当前时间
        reply.setContent(content);

        if (App.session.getStudent() != null) {
            reply.setUser_id(App.session.getStudent().getCardNumber());
        } else if (App.session.getTeacher() != null) {
            reply.setUser_id(App.session.getTeacher().getCardNumber());
        } else {
            // 如果两者都为空，可以抛出异常或设置默认值
            throw new IllegalStateException("No student or teacher found in session");
        }

        System.out.println(reply.toString());


        Boolean result = ResponseUtils.getResponseByHash(
                        new Request(App.connectionToServer, null,
                                "tech.zxuuu.server.bbs.InsertReplyPost.insertReplyPost", new Object[]{reply}).send())
                .getReturn(Boolean.class);

        if (result) {
            JOptionPane.showMessageDialog(this, "回复已发布", "提示", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "回复发布失败，请稍后再试", "错误", JOptionPane.ERROR_MESSAGE);
        }

        // 清空输入框
        txtReplyContent.setText("");
        dispose();
    }

    public InsertReplyPostGUI(String postId) {
        this.postId = postId;
        setTitle("发表回复 - VCampus");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null); // 窗口居中

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        // 回复内容输入框 (滚动面板)
        txtReplyContent = new JTextArea();
        txtReplyContent.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        txtReplyContent.setLineWrap(true); // 自动换行
        txtReplyContent.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(txtReplyContent);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // 提交按钮
        btnSubmit = new JButton("发布回复");
        btnSubmit.setFont(new Font("微软雅黑", Font.BOLD, 18));
        contentPane.add(btnSubmit, BorderLayout.SOUTH);

        // 事件监听
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitReply();
            }
        });
    }
}
