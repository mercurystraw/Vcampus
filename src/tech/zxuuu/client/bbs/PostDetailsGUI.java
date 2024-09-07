package tech.zxuuu.client.bbs;

import tech.zxuuu.entity.PostInfo;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.client.main.App;
import tech.zxuuu.util.SwingUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PostDetailsGUI extends JFrame {

    private JPanel contentPane;
    private JPanel pnlReplyList;
    private JScrollPane spnReplyList;
    private String postId; // 当前帖子的ID

    public PostDetailsGUI(String postId) {
        this.postId = postId;
        setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setIconImage(Toolkit.getDefaultToolkit().getImage(PostDetailsGUI.class.getResource("/resources/assets/icon/fav.png")));
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

        JLabel lblVcampus = new JLabel("帖子详情 - VCampus");
        lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        lblVcampus.setBounds(102, 32, 239, 34);
        contentPane.add(lblVcampus);

        // 发表回复按钮
        JButton btnReply = new JButton("发表回复");
        btnReply.setBounds(750, 660, 100, 30); // 设置按钮位置和大小
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
        JButton btnRefresh = new JButton("刷新");
        btnRefresh.setBounds(10, 660, 100, 30); // 设置按钮位置和大小
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
    }

    // 修改 showPostDetail 方法以显示更多信息
    private void showPostDetail(JLabel lblPostID, JTextArea txtPostContent, JLabel lblPostDate, JLabel lblPostUserID) {
        // 从数据库获取主帖子的信息
        System.out.println("主帖ID" + postId);

        PostInfo mainPost = ResponseUtils.getResponseByHash(
                        new Request(App.connectionToServer, null,
                                "tech.zxuuu.server.bbs.BBSGUI.getPostById", new Object[]{postId}).send())
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

    // 显示回复列表
    private void showReplyList() {
        System.out.println(postId);

        List<PostInfo> replyList = ResponseUtils.getResponseByHash(
                        new Request(App.connectionToServer, null, "tech.zxuuu.server.bbs.PostDetails.getChildPostList", new Object[]{postId}).send())
                .getListReturn(PostInfo.class);

        System.out.println("replylist end!");

        SwingUtilities.invokeLater(() -> {
            pnlReplyList.removeAll(); // 清空当前的回复列表 用于刷新
            for (PostInfo reply : replyList) {

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

                // 添加间隔a
                JPanel spacer = new JPanel();
                spacer.setPreferredSize(new Dimension(1, 10)); // 设置间隔高度
                pnlReplyList.add(replyPanel);
                pnlReplyList.add(spacer);

                System.out.println(reply.getId() + " " + reply.getUser_id());
            }
            pnlReplyList.revalidate();
            pnlReplyList.repaint();
        });
    }
}
