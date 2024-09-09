package tech.mainCode.client.main;

import tech.mainCode.client.auth.AuthGUI;
import tech.mainCode.client.bbs.BBSGUI;
import tech.mainCode.client.bbs.ManagePostInfoPane;
import tech.mainCode.client.bbs.PostInfoPane;
import tech.mainCode.client.rounded.LibButton;
import tech.mainCode.client.rounded.RoundedButton;
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
    // AppPostManager.java
    private int calculatePostHeight(String content) {
        // 计算帖子高度的逻辑，可以根据内容长度动态调整
        int baseHeight = 200; // 增加基础高度
        int extraHeight = (content.length() / 50) * 20; // 每50个字符增加20像素高度
        return baseHeight + extraHeight;
    }

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
            GroupLayout layout = new GroupLayout(postPanel);
            postPanel.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);

            JPanel postInfoPanel = new ManagePostInfoPane(post.getId(), post.getContent(), post.getDate(), post.getUser_id(), post.getThumbup());

            layout.setHorizontalGroup(
                    layout.createSequentialGroup()
                            .addComponent(postInfoPanel)
            );

            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(postInfoPanel)
            );

            postPanel.setPreferredSize(new Dimension(900, calculatePostHeight(post.getContent()))); // 设置面板高度
            pnlPostList.add(postPanel);
            System.out.println(post.getId() + " " + post.getUser_id());
        }
        pnlPostList.setPreferredSize(new Dimension(spnPostList.getWidth(), ParentPostList.size() * calculatePostHeight("Sample content")));

        // 确保滚动条位置为顶部
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                spnPostList.getVerticalScrollBar().setValue(0);
            }
        });
    }
    public AppPostManager() { //类似BBSGUI界面 只不过删去了几个按钮
        setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setIconImage(Toolkit.getDefaultToolkit().getImage(BBSGUI.class.getResource("/resources/assets/icon/seu_icon.png")));
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
        spnPostList.setBounds(70, 95, 900, 600); // 调整滚动面板大小
        spnPostList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spnPostList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spnPostList.setViewportView(pnlPostList);

        pnlPostList.setLayout(new GridLayout(0, 1, 0, 0));

        contentPane.add(spnPostList);


        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(AppLibraryManager.class.getResource("/resources/assets/icon/seu_icon.png")));
        label.setBounds(14, 13, 64, 64);
        contentPane.add(label);

        JLabel lblVcampus = new JLabel("校园论坛");
        lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        lblVcampus.setForeground(Color.WHITE); // 设置字体颜色为白色
        lblVcampus.setBounds(102, 27, 239, 34);
        contentPane.add(lblVcampus);

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
        JButton btnRefresh = new RoundedButton("刷 新",10);
        btnRefresh.setBounds(450, 710, 100, 30); // 设置按钮位置和大小
        btnRefresh.setFont(new Font("微软雅黑", Font.PLAIN, 16));
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

        JLabel label_2 = new JLabel("登录卡号：");
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        label_2.setForeground(Color.WHITE); // 设置字体颜色为白色
        label_2.setBackground(new Color(0,110,0));
        label_2.setBounds(670, 23, 100, 34);
        contentPane.add(label_2);

        JLabel label_3 = new JLabel("");
        label_3.setBounds(770, 23, 100, 34);
        label_3.setForeground(Color.WHITE); // 设置字体颜色为白色
        label_3.setBackground(new Color(0,110,0));
        contentPane.add(label_3);
        label_3.setText(App.session.getManager().getCardNumber());
        label_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));

        JButton ExitButton = new LibButton("登出",0);
        ExitButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        ExitButton.setBackground(new Color(0, 110, 0));
        ExitButton.setBounds(885, 0, 100, 80);
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthGUI authGUI = new AuthGUI();
                authGUI.setVisible(true);
                dispose();
            }
        });
        ExitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ExitButton.setBackground(new Color(0, 120, 0));
            }

        });
        contentPane.add(ExitButton);
        JLabel greenStrip = new JLabel("");
        greenStrip.setOpaque(true);
        greenStrip.setBackground(new Color(0, 100, 0)); // Green color
        greenStrip.setBounds(0, 0, 1000, 80); // Adjust the height as needed
        contentPane.add(greenStrip, Integer.valueOf(-1)); // Add to the bottom layer

    }



}
