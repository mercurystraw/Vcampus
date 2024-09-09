package tech.mainCode.client.bbs;

import tech.mainCode.client.main.App;
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

public class InsertNewPostGUI extends JFrame {

    private JTextPane txtPostContent; // 使用 JTextPane 替代 JTextArea
    private JButton btnSubmit; // 提交文本按钮

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
            throw new IllegalStateException("No student or teacher found in session");
        }

        System.out.println("测试学生和老师选取一卡通号同一接口：" + post.toString());

        Boolean result = ResponseUtils.getResponseByHash(
                        new Request(App.connectionToServer, null,
                                "tech.mainCode.server.bbs.InsertNewPost.insertNewPost", new Object[]{post}).send())
                .getReturn(Boolean.class);

        System.out.println(result);

        if (result) {
            JOptionPane.showMessageDialog(this, "帖子已发布", "提示", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "帖子发布失败，请稍后再试", "错误", JOptionPane.ERROR_MESSAGE);
        }

        // 清空输入框
        txtPostContent.setText("");
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
        StyledDocument doc = txtPostContent.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrs, 24); // 设置 Emoji 字体大小
        StyleConstants.setFontFamily(attrs, "Segoe UI Emoji"); // 设置 Emoji 字体

        try {
            doc.insertString(doc.getLength(), emoji, attrs); // 在文本框中插入 Emoji
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        // 帖子内容输入框 (使用 JTextPane)
        txtPostContent = new JTextPane();
        txtPostContent.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16)); // 使用支持 Emoji 的字体
        txtPostContent.setEditable(true);

        JScrollPane scrollPane = new JScrollPane(txtPostContent);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // 提交按钮
        btnSubmit = new RoundedButton("发布",20);
        btnSubmit.setFont(new Font("微软雅黑", Font.BOLD, 20));
        contentPane.add(btnSubmit, BorderLayout.SOUTH);

        // 创建一个 JPanel 来容纳表情按钮
        JPanel emojiPanel = new JPanel();
        emojiPanel.setLayout(new GridLayout(0, 1)); // 一列布局

        // Emoji 按钮
        JButton btnEmoji = new RoundedButton("Emoji",20); // 这里可以设置一个默认的 Emoji 图标
        btnEmoji.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        emojiPanel.add(btnEmoji);

        contentPane.add(emojiPanel, BorderLayout.EAST); // 将 emojiPanel 添加到 contentPane 的右侧
        btnEmoji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEmojiPicker(); // 显示 Emoji 选择面板
            }
        });
        // 事件监听
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitPost();
            }
        });
    }
}
