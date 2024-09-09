package tech.mainCode.client.bbs;

import tech.mainCode.client.main.App;
import tech.mainCode.client.rounded.CustomOptionPane;
import tech.mainCode.client.rounded.RoundedButton;
import tech.mainCode.entity.PostInfo;
import tech.mainCode.net.Request;
import tech.mainCode.util.ResponseUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InsertReplyPostGUI extends JFrame {

    private JTextPane txtReplyContent; // 使用 JTextPane 以支持 Emoji
    private JButton btnSubmit;
    private String postId; // 当前帖子的ID

    // 提交回复的方法
    private void submitReply() {
        String content = txtReplyContent.getText().trim();

        if (content.isEmpty()) {
            CustomOptionPane.showMessageDialog(this, "回复内容不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PostInfo reply = new PostInfo();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDateTime = now.format(formatter);

        reply.setId(postId); // 便于传参进去给mapper用
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
                                "tech.mainCode.server.bbs.InsertReplyPost.insertReplyPost", new Object[]{reply}).send())
                .getReturn(Boolean.class);

        if (result) {
            CustomOptionPane.showMessageDialog(this, "回复已发布", "提示", JOptionPane.INFORMATION_MESSAGE);
        } else {
            CustomOptionPane.showMessageDialog(this, "回复发布失败，请稍后再试", "错误", JOptionPane.ERROR_MESSAGE);
        }

        // 清空输入框
        txtReplyContent.setText("");
        dispose();
    }

    // 显示 Emoji 选择面板
    private void showEmojiPicker() {
        // 创建一个 Emoji 选择面板
        JDialog emojiDialog = new JDialog(this, "选择 Emoji", true);
        emojiDialog.setLayout(new GridLayout(10, 3)); // 10 行 3 列的布局

        String[] emojis = {
                "😀", "😁", "😂", "🤣", "😃", "😄", "😅",
                "😆", "😉", "😊", "😋", "😎", "😍",
                "😘", "😗", "😙", "😚", "😜", "😝",
                "😛", "🤑", "🤗", "🤔", "🤐", "🤨",
                "😐", "😑", "😶", "😏", "😒", "😞",
                "😔", "😟", "😕", "🙁", "☹️", "😣",
                "😖", "😫", "😩", "😤", "😠", "😡",
                "😶‍🌫️", "😱", "😨", "😰", "😥", "😓",
                "😢", "😭", "😤", "😠", "😡", "😳",
                "😵", "😲", "😯", "😦", "😧", "😮",
                "😬", "😯", "😲", "🥳", "🥺", "😈",
                "👿", "👹", "👺", "💀", "👻", "👽",
                "🤖", "🎃", "😺", "😸", "😻", "😼"
        };

        for (String emoji : emojis) {
            JButton emojiButton = new JButton(emoji);
            emojiButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
            emojiButton.addActionListener(e -> {
                insertEmoji(emoji);
                emojiDialog.dispose(); // 选择后关闭对话框
            });
            emojiDialog.add(emojiButton);
        }

        emojiDialog.pack();
        emojiDialog.setLocationRelativeTo(this); // 居中显示
        emojiDialog.setVisible(true);
    }

    // 将选择的 Emoji 插入到文本框
    private void insertEmoji(String emoji) {
        StyledDocument doc = txtReplyContent.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrs, 24); // 设置 Emoji 字体大小
        StyleConstants.setFontFamily(attrs, "Segoe UI Emoji"); // 设置 Emoji 字体

        try {
            doc.insertString(doc.getLength(), emoji, attrs); // 在文本框中插入 Emoji
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        // 回复内容输入框 (使用 JTextPane)
        txtReplyContent = new JTextPane();
        txtReplyContent.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        txtReplyContent.setEditable(true); // 设置为可编辑

        JScrollPane scrollPane = new JScrollPane(txtReplyContent);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // 创建 Emoji 面板
        JPanel emojiPanel = new JPanel();
        emojiPanel.setLayout(new GridLayout(0, 1)); // 一列布局

        // Emoji 按钮
        JButton btnEmoji = new RoundedButton("Emoji",30); // 这里可以设置一个默认的 Emoji 图标
        btnEmoji.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        emojiPanel.add(btnEmoji);

        // 事件监听
        btnEmoji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEmojiPicker(); // 显示 Emoji 选择面板
            }
        });

        contentPane.add(emojiPanel, BorderLayout.EAST); // 将 Emoji 面板添加到右侧

        // 提交按钮
        btnSubmit = new RoundedButton("发布回复",30);
        btnSubmit.setFont(new Font("微软雅黑", Font.BOLD, 20));
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
