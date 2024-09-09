package tech.mainCode.client.bbs;

import tech.mainCode.client.main.AppLibraryManager;
import tech.mainCode.client.rounded.LibButton;
import tech.mainCode.client.rounded.RoundedButton;
import tech.mainCode.entity.PostInfo;
import tech.mainCode.net.Request;
import tech.mainCode.util.ResponseUtils;
import tech.mainCode.client.main.App;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class PostDetailsGUI extends JFrame {

    private JPanel contentPane;
    private JPanel pnlReplyList;
    private JScrollPane spnReplyList;
    private String postId; // 当前帖子的ID
    private Set<String> likedReplies ;//直接传入用户点赞过的所有帖子id 源头再BBSGUI 是static的

    public PostDetailsGUI(String postId, Set<String> likedReplies) {
        this.postId = postId;
        this.likedReplies = likedReplies;

        setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setIconImage(Toolkit.getDefaultToolkit().getImage(PostDetailsGUI.class.getResource("/resources/assets/icon/seu_icon.png")));
        setTitle("帖子详情 - VCampus");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int frameWidth = 1000; // 调整界面宽度
        int frameHeight = 800; // 调整界面高度
        setBounds((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2, frameWidth, frameHeight);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 帖子详情面板
        JPanel pnlPostDetail = new JPanel();
        pnlPostDetail.setBounds(70, 95, 800, 200);
        pnlPostDetail.setLayout(new BorderLayout());

        // 添加主帖的详细信息面板
        JPanel pnlPostInfo = new JPanel();
        pnlPostInfo.setLayout(new GridLayout(4, 1)); // 设置4行布局

        // 帖子ID
        JLabel lblPostID = new JLabel("回帖ID：");
        pnlPostInfo.add(lblPostID);

        // 帖子内容
        JTextArea txtPostContent = new JTextArea("这里是帖子的内容...");
        txtPostContent.setEditable(false);
        pnlPostInfo.add(new JScrollPane(txtPostContent));

        // 发布日期
        JLabel lblPostDate = new JLabel("发布日期：");
        pnlPostInfo.add(lblPostDate);

        // 用户ID
        JLabel lblPostUserID = new JLabel("用户ID：");
        pnlPostInfo.add(lblPostUserID);

        pnlPostDetail.add(pnlPostInfo, BorderLayout.CENTER);
        contentPane.add(pnlPostDetail);

        // 回复列表面板
        pnlReplyList = new JPanel();
        pnlReplyList.setLayout(new BoxLayout(pnlReplyList, BoxLayout.Y_AXIS)); // 使用 BoxLayout
        spnReplyList = new JScrollPane(pnlReplyList);
        spnReplyList.setBounds(70, 350, 800, 300); // 调整滚动面板大小
        spnReplyList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spnReplyList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spnReplyList.setViewportView(pnlReplyList);

        contentPane.add(spnReplyList);
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(AppLibraryManager.class.getResource("/resources/assets/icon/seu_icon.png")));
        label.setBounds(14, 13, 64, 64);
        contentPane.add(label);

        JLabel lblVcampus = new JLabel("帖子详情");
        lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        lblVcampus.setForeground(Color.WHITE); // 设置字体颜色为白色
        lblVcampus.setBounds(102, 27, 239, 34);
        contentPane.add(lblVcampus);
        // 发表回复按钮
        JButton btnReply = new RoundedButton("发表回复",20);
        btnReply.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        btnReply.setBounds(550, 680, 150, 40); // 设置按钮位置和大小
        btnReply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 响应函数
                InsertReplyPostGUI insertReplyPostGUI = new InsertReplyPostGUI(postId);
                insertReplyPostGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                insertReplyPostGUI.setVisible(true);
            }
        });
        contentPane.add(btnReply);

        // 刷新按钮
        JButton btnRefresh = new RoundedButton("刷新",20);
        btnRefresh.setBounds(280, 680, 150, 40); // 设置按钮位置和大小
        btnRefresh.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReplyList(); // 点击刷新按钮时更新回复列表
            }
        });
        contentPane.add(btnRefresh);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showPostDetail(lblPostID, txtPostContent, lblPostDate, lblPostUserID);
                showReplyList();
            }
        });
        JLabel greenStrip = new JLabel("");
        greenStrip.setOpaque(true);
        greenStrip.setBackground(new Color(0, 100, 0)); // Green color
        greenStrip.setBounds(0, 0, 1000, 80); // Adjust the height as needed
        contentPane.add(greenStrip, Integer.valueOf(-1)); // Add to the bottom layer
    }

    // 修改 showPostDetail 方法以显示更多信息
    private void showPostDetail(JLabel lblPostID, JTextArea txtPostContent, JLabel lblPostDate, JLabel lblPostUserID) {
        // 从数据库获取主帖子的信息
        System.out.println("主帖ID" + postId);

        PostInfo mainPost = ResponseUtils.getResponseByHash(
                        new Request(App.connectionToServer, null,
                                "tech.mainCode.server.bbs.BBSGUI.getPostById", new Object[]{postId}).send())
                .getReturn(PostInfo.class);

        if (mainPost == null) {
            JOptionPane.showMessageDialog(null, "无法获取帖子详情，请稍后再试。", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 更新UI组件内容
        lblPostID.setText("帖子ID：" + mainPost.getId());
        txtPostContent.setText(mainPost.getContent());
        lblPostDate.setText("发布日期：" + mainPost.getDate());
        lblPostUserID.setText("发表用户：" + mainPost.getUser_id());

        System.out.println("主贴ID" + mainPost.getId() + " " + mainPost.getUser_id());
    }
    private void updateThumbInDatabase(String replyId, int thumb){
        System.out.println("回复帖子ID："+replyId+"更新后点赞数："+thumb);
        Boolean result = ResponseUtils.getResponseByHash(
                        new Request(App.connectionToServer, null,
                                "tech.mainCode.server.bbs.BBSGUI.updateThumbup", new Object[]{replyId, thumb}).send()).
                getReturn(Boolean.class);
        if (result == null) {
            System.out.println("更新点赞数失败！响应为null");
        }else if (result){
            System.out.println("更新点赞数成功！");
        }else{
            System.out.println("更新点赞数失败！");
        }
    }


    // 显示回复列表
    private void showReplyList() {
        System.out.println(postId);

        List<PostInfo> replyList = ResponseUtils.getResponseByHash(
                        new Request(App.connectionToServer, null, "tech.mainCode.server.bbs.PostDetails.getChildPostList", new Object[]{postId}).send())
                .getListReturn(PostInfo.class);

        System.out.println("replylist end!");

        SwingUtilities.invokeLater(() -> {

            Collections.sort(replyList, new Comparator<PostInfo>() { // 回复按点赞数排序
                @Override
                public int compare(PostInfo post1, PostInfo post2) {
                    return Integer.compare(post2.getThumbup(), post1.getThumbup()); // 降序排序
                }
            });

            pnlReplyList.removeAll(); // 清空当前的回复列表 用于刷新
            for (PostInfo reply : replyList) {
                System.out.println(reply.toString());

                JPanel replyPanel = new JPanel();
                replyPanel.setLayout(new BoxLayout(replyPanel, BoxLayout.Y_AXIS)); // 使用 BoxLayout 垂直布局

                // 回复ID
                JLabel lblReplyID = new JLabel("回复ID: " + reply.getId());
                lblReplyID.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); // 添加底部边距
                lblReplyID.setAlignmentX(Component.LEFT_ALIGNMENT);
                replyPanel.add(lblReplyID);

                // 回复内容
                JTextArea txtReplyContent = new JTextArea(reply.getContent());
                txtReplyContent.setEditable(false);
                replyPanel.add(new JScrollPane(txtReplyContent));

                // 发表用户和时间
                JPanel userAndDatePanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.anchor = GridBagConstraints.WEST;
                gbc.insets = new Insets(0, 0, 0, 10); // 添加间隔

                JLabel lblUserID = new JLabel("用户: " + reply.getUser_id());
                userAndDatePanel.add(lblUserID, gbc);

                JLabel lblDate = new JLabel("回复于: " + reply.getDate());
                gbc.gridx = 1; // 放在第二列
                userAndDatePanel.add(lblDate, gbc);

                replyPanel.add(userAndDatePanel);

                // 点赞数
                JLabel lblThumb = new JLabel("点赞数: " + reply.getThumbup());

                lblThumb.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); // 添加底部边距
                lblThumb.setAlignmentX(Component.LEFT_ALIGNMENT);
                replyPanel.add(lblThumb);

                // 点赞按钮
                JButton btnThumb = new LibButton("点  赞",10);
                btnThumb.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                btnThumb.setBackground(new Color(255, 255, 255));
                btnThumb.setForeground(Color.BLACK);
                if (likedReplies.contains(reply.getId())) {
                    btnThumb.setText("取消点赞");
                    btnThumb.setBackground(new Color(128, 0, 0)); // 设置点赞后的背景颜色
                    btnThumb.setForeground(Color.WHITE);
                }
                btnThumb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String replyId = reply.getId();
                        if (likedReplies.contains(replyId)) {
                            // 取消点赞
                            likedReplies.remove(replyId);
                            reply.setThumbup(reply.getThumbup() - 1);
                            btnThumb.setText("点 赞 ");
                            btnThumb.setBackground(new Color(255,255,255)); // 恢复默认背景颜色
                            btnThumb.setForeground(Color.BLACK);
                        } else {
                            // 点赞
                            likedReplies.add(replyId);
                            reply.setThumbup(reply.getThumbup() + 1);
                            btnThumb.setText("取消点赞");
                            btnThumb.setBackground(new Color(128, 0, 0)); // 设置点赞后的背景颜色
                            btnThumb.setForeground(Color.WHITE);
                        }
                        lblThumb.setText("点赞数: " + reply.getThumbup());

                        // 这里可以添加代码来更新数据库中的点赞数
                        updateThumbInDatabase(reply.getId(), reply.getThumbup());
                    }
                });
                replyPanel.add(btnThumb);

                // 添加间隔
                JPanel spacer = new JPanel();
                spacer.setPreferredSize(new Dimension(1, 10)); // 设置间隔高度
                pnlReplyList.add(replyPanel);
                pnlReplyList.add(spacer);

                System.out.println(reply.getId() + " " + reply.getUser_id()+" "+reply.getThumbup());
            }
            pnlReplyList.revalidate();
            pnlReplyList.repaint();
        });
    }
}

