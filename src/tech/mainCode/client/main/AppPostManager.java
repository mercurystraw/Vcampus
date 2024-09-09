package tech.mainCode.client.main;

import tech.mainCode.client.bbs.BBSGUI;
import tech.mainCode.client.bbs.ManagePostInfoPane;
import tech.mainCode.client.bbs.PostInfoPane;
import tech.mainCode.entity.PostInfo;
import tech.mainCode.net.Request;
import tech.mainCode.util.ResponseUtils;
import tech.mainCode.util.SwingUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppPostManager extends JFrame {
    private JPanel contentPane;
    private JPanel pnlPostList;
    private JScrollPane spnPostList;
    private void showParentPostList() {
        List<PostInfo> ParentPostList = ResponseUtils.getResponseByHash(
                        new Request(App.connectionToServer, null, "tech.mainCode.server.bbs.BBSGUI.getParentPostList", null).send())
                .getListReturn(PostInfo.class);
        Collections.sort(ParentPostList, new Comparator<PostInfo>() {
            @Override
            public int compare(PostInfo post1, PostInfo post2) {
                return Integer.compare(post2.getThumbup(), post1.getThumbup()); // 降序排序
            }
        });
        pnlPostList.removeAll(); // 清空当前的帖子列表
        for (PostInfo post : ParentPostList) {
            JPanel postPanel = new JPanel();
            postPanel.setLayout(new BorderLayout());

            postPanel.add(new ManagePostInfoPane(post.getId(), post.getContent(), post.getDate(), post.getUser_id(),post.getThumbup()), BorderLayout.CENTER);

            JButton btnDelete = new JButton("删除");
            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 弹出确认对话框
                    int confirm = JOptionPane.showConfirmDialog(null, "确定要删除此帖子吗？", "删除确认", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        Boolean result =null;
                        result = ResponseUtils.getResponseByHash(
                                new Request(App.connectionToServer, null, "tech.mainCode.server.bbs.DeletePost.deletePost", new Object[]{post.getId()}).send())
                               .getReturn(Boolean.class);
                        if (result) {
                            // 用户点击了“确定”，执行删除操作
                            SwingUtils.showMessage(null, "删除成功", "提示");
                        }
                        else{
                            SwingUtils.showMessage(null, "删除失败", "提示");
                        }
                    }
                }
            });
            postPanel.add(btnDelete, BorderLayout.EAST);

            pnlPostList.add(postPanel);
            System.out.println(post.getId() + " " + post.getUser_id());
        }
        pnlPostList.setPreferredSize(new Dimension(spnPostList.getWidth(), ParentPostList.size() * PostInfoPane.HEIGHT));
    }
    public AppPostManager() { //类似BBSGUI界面 只不过删去了几个按钮
        setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setIconImage(Toolkit.getDefaultToolkit().getImage(BBSGUI.class.getResource("/resources/assets/icon/fav.png")));
        setTitle("校园论坛 - VCampus");
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

        pnlPostList = new JPanel();
        pnlPostList.setLayout(new GridLayout(0, 1));
        spnPostList = new JScrollPane(pnlPostList);
        spnPostList.setBounds(70, 95, 800, 500); // 调整滚动面板大小
        spnPostList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spnPostList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spnPostList.setViewportView(pnlPostList);

        pnlPostList.setLayout(new GridLayout(0, 1, 0, 0));

        contentPane.add(spnPostList);

        JLabel label = new JLabel("");
//        label.setIcon(new ImageIcon(BBSGUI.class.getResource("/resources/assets/icon/forum.png")));
        label.setBounds(14, 13, 64, 64);
        contentPane.add(label);

        JLabel lblVcampus = new JLabel("校园论坛 - VCampus");
        lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        lblVcampus.setBounds(102, 32, 239, 34);
        contentPane.add(lblVcampus);

        JLabel lblNewLabel = new JLabel("选择一个帖子以查看详情...");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
        lblNewLabel.setBounds(584, 63, 168, 19);
        contentPane.add(lblNewLabel);

// 发布新帖按钮 管理员没有该功能 删除！
//        JButton btnNewPost = new JButton("发布新帖");
//        btnNewPost.setBounds(850, 670, 100, 30); // 设置按钮位置和大小
//        btnNewPost.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SwingUtilities.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        InsertNewPostGUI insertNewPostGUI = new InsertNewPostGUI();
//                        insertNewPostGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                        insertNewPostGUI.setVisible(true);
//                    }
//                });
//            }
//        });
//
//        contentPane.add(btnNewPost);

        // 刷新按钮
        JButton btnRefresh = new JButton("刷新");
        btnRefresh.setBounds(10, 670, 100, 30); // 设置按钮位置和大小
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showParentPostList(); // 点击刷新按钮时更新帖子列表
            }
        });
        contentPane.add(btnRefresh);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showParentPostList();
            }
        });
    }



}
