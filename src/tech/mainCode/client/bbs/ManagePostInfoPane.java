package tech.mainCode.client.bbs;

import tech.mainCode.client.main.App;
import tech.mainCode.client.rounded.RoundedButton;
import tech.mainCode.net.Request;
import tech.mainCode.util.ResponseUtils;
import tech.mainCode.util.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import tech.mainCode.client.rounded.*;
public class ManagePostInfoPane extends JPanel {
    public static final int HEIGHT = 130;

    private String id; // 帖子ID
    private String content; // 帖子内容
    private String date; // 发布日期
    private String userid; // 用户ID
    private Integer thumbup; // 点赞数

    private JLabel lblPostId;
    private JTextPane txtContent; // 使用 JTextPane 显示帖子内容
    private JLabel lblDate;
    private JLabel lblUserId;
    private JLabel lblThumb; // 显示点赞数的标签

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

        JButton btnViewDetails = new RoundedButton("详 情",10);
        btnViewDetails.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        btnViewDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加查看详情的逻辑
                ManageReplyGUI manageReplyGUI = new ManageReplyGUI(id);
                manageReplyGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                manageReplyGUI.setVisible(true);
            }
        });
        btnViewDetails.setBounds(550, 100, 100, 40);
        add(btnViewDetails);
        JButton btnDelete = new RoundedButton("删 除", 10);
        btnDelete.setBackground(new Color(128, 0, 0)); // 设置按钮背景色为橙红色
        btnDelete.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        btnDelete.setBounds(700, 100, 100, 40);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 弹出确认对话框
                int confirm = JOptionPane.showConfirmDialog(null, "确定要删除此帖子吗？", "删除确认", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Boolean result = null;
                    result = ResponseUtils.getResponseByHash(
                                    new Request(App.connectionToServer, null, "tech.mainCode.server.bbs.DeletePost.deletePost", new Object[]{id}).send())
                            .getReturn(Boolean.class);
                    if (result) {
                        // 用户点击了“确定”，执行删除操作
                        SwingUtils.showMessage(null, "删除成功", "提示");
                    } else {
                        SwingUtils.showMessage(null, "删除失败", "提示");
                    }
                }
            }
        });
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDelete.setBackground(new Color(158, 0, 0));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnDelete.setBackground(new Color(158, 0, 0));
            }
            public void mouseExited(MouseEvent e) {
                btnDelete.setBackground(new Color(128, 0, 0));
            }
            public void mouseReleased(MouseEvent e) {
                btnDelete.setBackground(new Color(128, 0, 0));
            }
        });
        add(btnDelete);
        this.lblThumb = new JLabel("没什么用的文本反正下面重新设置");
        lblThumb.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblThumb.setBounds(600, 10, 150, 30);
        add(lblThumb);

        setVisible(true);
    }

    public ManagePostInfoPane(String id, String content, String date, String userid,Integer thumbup) {
        this();
        this.id = id;
        this.content = content;
        this.date = date;
        this.userid = userid;
        this.thumbup = thumbup;

        this.lblPostId.setText("帖子ID: " + this.id);
        this.txtContent.setText(this.content); // 使用 JTextPane 设置帖子内容
        this.lblDate.setText("日期: " + this.date);
        this.lblUserId.setText("用户ID: " + this.userid);
        this.lblThumb.setText("点赞数: " + this.thumbup); // 设置初始点赞数

        this.validate();
        this.repaint();
        this.updateUI();
        this.revalidate();
    }
}
