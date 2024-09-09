package tech.mainCode.client.bbs;

import tech.mainCode.client.main.AppLibraryManager;
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

public class BBSGUI extends JFrame {
    private JPanel contentPane;
    private JPanel pnlPostList;
    private JScrollPane spnPostList;
    private static Set<String> likedPosts = new HashSet<>(); // 用于存储已点赞的帖子ID

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

            JPanel postInfoPanel = new PostInfoPane(post.getId(), post.getContent(), post.getDate(), post.getUser_id(), post.getThumbup(), likedPosts);

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
        spnPostList.setBounds(70, 95, 800, 500); // 调整滚动面板大小
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
        JButton btnNewPost = new RoundedButton("发布新帖",30);
        btnNewPost.setBounds(550, 650, 150, 60); // 设置按钮位置和大小
        btnNewPost.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        btnNewPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        InsertNewPostGUI insertNewPostGUI = new InsertNewPostGUI();
                        insertNewPostGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        insertNewPostGUI.setVisible(true);
                    }
                });
            }
        });
        contentPane.add(btnNewPost);

        // 刷新按钮
        JButton btnRefresh = new RoundedButton("刷 新",30);
        btnRefresh.setBounds(300, 650, 150, 60); // 设置按钮位置和大小
        btnRefresh.setFont(new Font("微软雅黑", Font.PLAIN, 20));
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

        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(AppLibraryManager.class.getResource("/resources/assets/icon/seu_icon.png")));
        label.setBounds(14, 13, 64, 64);
        contentPane.add(label);
        JLabel greenStrip = new JLabel("");
        greenStrip.setOpaque(true);
        greenStrip.setBackground(new Color(0, 100, 0)); // Green color
        greenStrip.setBounds(0, 0, 1000, 80); // Adjust the height as needed
        contentPane.add(greenStrip, Integer.valueOf(-1)); // Add to the bottom layer
    }


}
