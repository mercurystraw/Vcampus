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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class BBSGUI extends JFrame {
    private JPanel contentPane;
    private JPanel pnlPostList;
    private JScrollPane spnPostList;
    private static Set<String> likedPosts = new HashSet<>(); // 用于存储已点赞的帖子ID
    private String selectPostType;

    public enum PostType {
        STUDY,
        HEART,
        LIFE,
        SPORTS
    }//帖子类型！
    private PostType stringToPostType(String type) {
        switch (type) {
            case "STUDY":
                return PostType.STUDY;
            case "HEART":
                return PostType.HEART;
            case "LIFE":
                return PostType.LIFE;
            case "SPORTS":
                return PostType.SPORTS;
            default:
                return null;
        }
    }
    private void resetButtonColors() { //重置按钮颜色
        for (Component component : ((JPanel) getContentPane().getComponent(getContentPane().getComponentCount() - 1)).getComponents()) {
            if (component instanceof LibButton) {
                ((LibButton) component).setBackground(new Color(0, 100, 0));
            }
        }
    }

    private int calculatePostHeight(String content) {
        // 计算帖子高度的逻辑，可以根据内容长度动态调整
        int baseHeight = 200; // 增加基础高度
        int extraHeight = (content.length() / 50) * 20; // 每50个字符增加20像素高度
        return baseHeight + extraHeight;
    }
    private void showParentPostList(PostType postType) {
        List<PostInfo> ParentPostList = ResponseUtils.getResponseByHash(
                        new Request(App.connectionToServer, null, "tech.mainCode.server.bbs.BBSGUI.getParentPostList", null).send())
                .getListReturn(PostInfo.class);
        if (postType != null) {
            ParentPostList = ParentPostList.stream()
                    .filter(post -> stringToPostType(post.getType()) == postType)
                    .collect(Collectors.toList());
        }

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

            JPanel postInfoPanel = new PostInfoPane(post.getId(), post.getContent(), post.getDate(), post.getUser_id(), post.getThumbup(),post.getType(), likedPosts);

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

    public BBSGUI() {
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
        spnPostList.setBounds(70, 150, 800, 500); // 调整滚动面板大小
        spnPostList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spnPostList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spnPostList.setViewportView(pnlPostList);

        pnlPostList.setLayout(new GridLayout(0, 1, 0, 0));

        contentPane.add(spnPostList);

        JLabel lblVcampus = new JLabel("校园论坛");
        lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        lblVcampus.setForeground(Color.WHITE); // 设置字体颜色为白色
        lblVcampus.setBounds(102, 27, 239, 34);
        contentPane.add(lblVcampus);

        // 发布新帖按钮
        LibButton btnNewPost = new LibButton("发布新帖", 0);
        btnNewPost.setBounds(550, 650, 150, 60); // 设置按钮位置和大小
        btnNewPost.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        btnNewPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        InsertNewPostGUI insertNewPostGUI = new InsertNewPostGUI(selectPostType);
                        insertNewPostGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        insertNewPostGUI.setVisible(true);
                    }
                });
            }
        });
        contentPane.add(btnNewPost);

        // 刷新按钮
        LibButton btnRefresh = new LibButton("刷 新", 0);
        btnRefresh.setBounds(300, 650, 150, 60); // 设置按钮位置和大小
        btnRefresh.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showParentPostList(stringToPostType(selectPostType)); // 点击刷新按钮时更新帖子列表
            }
        });
        contentPane.add(btnRefresh);

        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(AppLibraryManager.class.getResource("/resources/assets/icon/seu_icon.png")));
        label.setBounds(14, 13, 64, 64);
        contentPane.add(label);
        JLabel greenStrip = new JLabel("");
        greenStrip.setOpaque(true);
        greenStrip.setBackground(new Color(0, 100, 0)); // Green color
        greenStrip.setBounds(0, 0, 1000, 80); // Adjust the height as needed
        contentPane.add(greenStrip, Integer.valueOf(-1)); // Add to the bottom layer

        // 添加版区按钮
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(1, 4, 0, 0)); // 紧凑无间隔布局
        btnPanel.setBounds(70, 95, 800, 40); // 设置按钮面板位置和大小
        contentPane.add(btnPanel);

        LibButton btnStudy = new LibButton("学习广角", 0);
        btnStudy.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        btnStudy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPostType = "STUDY";
                showParentPostList(PostType.STUDY); // 传递版区参数
            }
        });
        btnStudy.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectPostType = "STUDY";
                resetButtonColors();
                btnStudy.setBackground(new Color(0, 120, 0));
            }
        });
        btnPanel.add(btnStudy);

        LibButton btnHeart = new LibButton("心灵树洞", 0);
        btnHeart.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        btnHeart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPostType = "HEART";
                showParentPostList(PostType.HEART); // 传递版区参数
            }
        });
        btnHeart.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectPostType = "HEART";
                resetButtonColors();
                btnHeart.setBackground(new Color(0, 120, 0));
            }
        });
        btnPanel.add(btnHeart);

        LibButton btnLife = new LibButton("校园生活", 0);
        btnLife.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        btnLife.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPostType = "LIFE";
                showParentPostList(PostType.LIFE); // 传递版区参数
            }
        });
        btnLife.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectPostType = "LIFE";
                resetButtonColors();
                btnLife.setBackground(new Color(0, 120, 0));
            }
        });
        btnPanel.add(btnLife);

        LibButton btnSports = new LibButton("场馆运动", 0);
        btnSports.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        btnSports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPostType = "SPORTS";
                showParentPostList(PostType.SPORTS); // 传递版区参数
            }
        });
        btnSports.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectPostType = "SPORTS";
                resetButtonColors();
                btnSports.setBackground(new Color(0, 120, 0));
            }
        });
        btnPanel.add(btnSports);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showParentPostList(null);
            }
        });
    }

}
