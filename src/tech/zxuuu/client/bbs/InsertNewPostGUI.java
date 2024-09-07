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

public class InsertNewPostGUI extends JFrame {

    private JTextField txtPostTitle;
    private JTextArea txtPostContent;
    private JButton btnSubmit;
    // 提交帖子的方法
    private void submitPost() {
        String content = txtPostContent.getText().trim();

        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "内容不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        PostInfo post = new PostInfo();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDateTime = now.format(formatter);

        System.out.println(currentDateTime);

        post.setId("-1");
        post.setDate(currentDateTime); // 设置当前时间
        post.setContent(content);
        // 判断学生是否为空，如果为空则使用老师的cardNumber
        if (App.session.getStudent() != null) {
            post.setUser_id(App.session.getStudent().getCardNumber());
        } else if (App.session.getTeacher() != null) {
            post.setUser_id(App.session.getTeacher().getCardNumber());
        } else {
            // 如果两者都为空，可以抛出异常或设置默认值
            throw new IllegalStateException("No student or teacher found in session");
        }


        System.out.println("测试学生和老师选取一卡通号同一接口："+post.toString());

        Boolean result = ResponseUtils.getResponseByHash(
                new Request(App.connectionToServer,null,
                        "tech.zxuuu.server.bbs.InsertNewPost.insertNewPost",new Object[]{post}).send())
                .getReturn(Boolean.class);

        System.out.println(result);

        if(result) {
            // 在此处可以加入与服务器通信的代码，将帖子提交到服务器
            JOptionPane.showMessageDialog(this, "帖子已发布", "提示", JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(this, "回复发布失败，请稍后再试", "错误", JOptionPane.ERROR_MESSAGE);
        }
        // 清空输入框
        txtPostContent.setText("");
        dispose();
    }
    public InsertNewPostGUI() {
        setTitle("发布新帖 - VCampus");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null); // 窗口居中

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        // 隐藏帖子标题输入
        txtPostTitle = new JTextField("默认标题");
        txtPostTitle.setVisible(false);  // 隐藏标题栏
        txtPostTitle.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        contentPane.add(txtPostTitle, BorderLayout.NORTH);

        // 帖子内容输入框 (滚动面板)
        txtPostContent = new JTextArea();
        txtPostContent.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        txtPostContent.setLineWrap(true); // 自动换行
        txtPostContent.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(txtPostContent);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // 提交按钮
        btnSubmit = new JButton("发布");
        btnSubmit.setFont(new Font("微软雅黑", Font.BOLD, 18));
        contentPane.add(btnSubmit, BorderLayout.SOUTH);

        // 事件监听
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitPost();
            }
        });
    }



}
