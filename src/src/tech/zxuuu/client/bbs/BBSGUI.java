package src.tech.zxuuu.client.bbs;

import tech.zxuuu.client.bbs.InsertNewPostGUI;
import tech.zxuuu.client.bbs.PostInfoPane;
import tech.zxuuu.client.teaching.studentSide.TeachingStudentMain;
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
import java.util.*;
import java.util.List;

public class BBSGUI extends JFrame {
    private JPanel contentPane;
    private JPanel pnlPostList;
    private JScrollPane spnPostList;
    private static Set<String> likedPosts = new HashSet<>(); // 用于存储已点赞的帖子ID

    private void showParentPostList() {

        List<PostInfo> ParentPostList = ResponseUtils.getResponseByHash(
                        new Request(App.connectionToServer, null, "tech.zxuuu.server.bbs.BBSGUI.getParentPostList", null).send())
                .getListReturn(PostInfo.class);
        // 按照点赞数降序排列主贴子

        Collections.sort(ParentPostList, new Comparator<PostInfo>() {
            @Override
            public int compare(PostInfo post1, PostInfo post2) {
                return Integer.compare(post2.getThumbup(), post1.getThumbup()); // 降序排序
            }
        });

        pnlPostList.removeAll(); // 清空当前的帖子列表
        for (PostInfo post : ParentPostList) {
//            int idPrefix = Integer.parseInt(post.getId().substring(0, 3));
//            String idPrefixStr = String.valueOf(idPrefix);
            pnlPostList.add(new PostInfoPane(post.getId(), post.getContent(), post.getDate(), post.getUser_id(),post.getThumbup(),likedPosts));
            System.out.println("用于测试点赞设置哎"+post.getId() +" "+ post.getUser_id()+" "+post.getThumbup());
        }
        pnlPostList.setPreferredSize(new Dimension(spnPostList.getWidth(), ParentPostList.size() * PostInfoPane.HEIGHT));
    }

    public BBSGUI() {
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

        // 发布新帖按钮
        JButton btnNewPost = new JButton("发布新帖");
        btnNewPost.setBounds(850, 670, 100, 30); // 设置按钮位置和大小
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
