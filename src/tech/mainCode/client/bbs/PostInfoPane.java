package tech.mainCode.client.bbs;

import tech.mainCode.client.main.App;
import tech.mainCode.client.rounded.LibButton;
import tech.mainCode.client.rounded.RoundedButton;
import tech.mainCode.net.Request;
import tech.mainCode.util.ResponseUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class PostInfoPane extends JPanel {

    public static final int HEIGHT = 130;

    private String id; // 帖子ID
    private String content; // 帖子内容
    private String date; // 发布日期
    private String userid; // 用户ID
    private int thumbup; // 点赞数

    private JLabel lblPostId;
    private JTextPane txtContent; // 使用 JTextPane 显示帖子内容
    private JLabel lblDate;
    private JLabel lblUserId;
    private JLabel lblThumb; // 显示点赞数的标签
    private JButton btnThumb; // 点赞按钮
    private Set<String> likedPosts;//存储已经点赞的帖子ID



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

    public int getThumbup() {
        return thumbup;
    }

    public void setThumbup(int thumbup) {
        this.thumbup = thumbup;
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

        // 使用 JTextPane 来显示帖子内容
        this.txtContent = new JTextPane();
        txtContent.setEditable(false); // 设置为不可编辑
        txtContent.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16)); // 设置字体大小为16，样式为普通
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

        JButton btnViewDetails = new RoundedButton("查看详情",20);
        btnViewDetails.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        btnViewDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加查看详情的逻辑
                PostDetailsGUI postDetailsGUI = new PostDetailsGUI(id,likedPosts);
                postDetailsGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                postDetailsGUI.setVisible(true);
            }
        });
        btnViewDetails.setBounds(600, 100, 150, 50);
        add(btnViewDetails);

        // 点赞按钮和点赞数标签
        this.lblThumb = new JLabel("没什么用的文本反正下面重新设置");
        lblThumb.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblThumb.setBounds(600, 10, 150, 30);
        add(lblThumb);

        this.btnThumb = new LibButton("点 赞",20);
        btnThumb.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        btnThumb.setBackground(new Color(255, 255, 255));
        btnThumb.setForeground(Color.BLACK);
        btnThumb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (likedPosts.contains(getId())) {
                    // 取消点赞
                    likedPosts.remove(getId());
                    setThumbup(getThumbup() - 1);
                    btnThumb.setText("点 赞");
                    btnThumb.setBackground(new Color(255,255,255)); // 恢复默认背景颜色
                    btnThumb.setForeground(Color.BLACK);
                } else {
                    // 点赞
                    likedPosts.add(getId());
                    setThumbup(getThumbup() + 1);
                    btnThumb.setText("取消点赞");
                    btnThumb.setBackground(new Color(128, 0, 0)); // 设置点赞后的背景颜色
                    btnThumb.setForeground(Color.WHITE);
                }
                System.out.println("点击按钮后已点赞的帖子ID集合："+likedPosts );

                lblThumb.setText("点赞数: " + getThumbup());

                // 这里可以添加代码来更新数据库中的点赞数
                updateThumbInDatabase(getId(), getThumbup());
            }
        });

        btnThumb.setBounds(600, 40, 150, 30);
        add(btnThumb);

        setVisible(true);
    }

    public PostInfoPane(String id, String content, String date, String userid, int thumb,Set<String>likedPosts) {
        this();
        this.id = id;
        this.content = content;
        this.date = date;
        this.userid = userid;
        this.thumbup = thumb;

        this.likedPosts = likedPosts;


        this.lblPostId.setText("帖子ID: " + this.id);
        this.txtContent.setText(this.content); // 使用 JTextPane 设置帖子内容
        this.lblDate.setText("日期: " + this.date);
        this.lblUserId.setText("用户ID: " + this.userid);
        this.lblThumb.setText("点赞数: " + this.thumbup); // 设置初始点赞数

        // 初始化点赞按钮状态
        if (likedPosts.contains(id)) {
            btnThumb.setText("取消点赞");
            btnThumb.setBackground(new Color(128, 0, 0)); // 设置点赞后的背景颜色
            btnThumb.setForeground(Color.WHITE);
        }



        this.validate();
        this.repaint();
        this.updateUI();
        this.revalidate();
    }

    // 模拟更新数据库中的点赞数
    private void updateThumbInDatabase(String PostId, int thumb){
        System.out.println("主帖子ID："+PostId+"更新后点赞数："+thumb);
        Boolean result = ResponseUtils.getResponseByHash(
                        new Request(App.connectionToServer, null,
                                "tech.mainCode.server.bbs.BBSGUI.updateThumbup", new Object[]{PostId, thumb}).send()).
                getReturn(Boolean.class);
        if (result == null) {
            System.out.println("更新点赞数失败！响应为null");
        }else if (result){
            System.out.println("更新点赞数成功！");
        }else{
            System.out.println("更新点赞数失败！");
        }
    }
}
