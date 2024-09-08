package tech.zxuuu.client.shop;

import java.awt.BorderLayout;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.main.App;
import tech.zxuuu.client.rounded.LibButton;
import tech.zxuuu.client.rounded.RoundedButton;
import tech.zxuuu.client.rounded.RoundedTextField;
import tech.zxuuu.entity.Product;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;

import tech.zxuuu.util.SwingUtils;

import javax.swing.JLabel;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * 商店学生主页
 * 
 * @author 杨鹏杰
 * @modify z0gSh1u
 */
public class ShopFirstPage extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Search;
	private DefaultTableModel model;

	CartPane cartPanel;

	public static List<Product> cart;
	public static JLabel lblCartCount;

	JPanel pnl_list;
	JScrollPane jsp_List;


	public void handleTypeButtonClick(JButton btn) {
		pnl_list.removeAll();
		System.out.println("这是类型按钮的文字："+btn.getText());
		List<Product> list = ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null,
						"tech.zxuuu.server.shop.ProductServer.listProductByType", new Object[] { btn.getText()}).send())
				.getListReturn(Product.class);
		if (list == null) {
			SwingUtils.showMessage(null, "抱歉，没有搜到这个商品，管理员正在努力备货中...", "提示");
		} else {
			pnl_list.setPreferredSize(new Dimension(jsp_List.getWidth() - 50, 280 * list.size()));
			for (int i = 0; i < list.size(); i++) {
				JPanel paneli = new Blocks(list.get(i).getPicture(), list.get(i).getInformation(), list.get(i).getName(),list.get(i).getType(),
						list.get(i).getPrice());
				paneli.setName(list.get(i).getName());
				pnl_list.add(paneli);
			}
		}
		// 滚动到顶端
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				jsp_List.getVerticalScrollBar().setValue(0);

				jsp_List.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShopFirstPage() {

		cart = new ArrayList<Product>();

		setResizable(false);
		setTitle("校园商店");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(ShopFirstPage.class.getResource("/resources/assets/icon/seu_icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 796);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		cartPanel = new CartPane();
		cartPanel.setBackground(Color.WHITE);
		cartPanel.setBounds(190, 173, 371, 490);
		cartPanel.setVisible(false);

		JLabel label = new JLabel("");
		label.setBounds(14, 13, 64, 64);
		label.setIcon(new ImageIcon(ShopFirstPage.class.getResource("/resources/assets/icon/seu_icon.png")));
		panel.add(label);
		panel.add(cartPanel);


		pnl_list = new JPanel();
		pnl_list.setLayout(new GridLayout(0, 1));

		jsp_List = new JScrollPane(pnl_list);
		jsp_List.setBounds(190, 173, 510, 531);

		panel.add(jsp_List);

		jsp_List.setVisible(false);

		jsp_List.setViewportView(pnl_list);
		pnl_list.setPreferredSize(new Dimension(jsp_List.getWidth() - 50, jsp_List.getHeight() * 2));
		panel.revalidate(); // 告诉其他部件,我的宽高变了 this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		txt_Search = new RoundedTextField(10);
		txt_Search.setBounds(79, 101, 494, 42);
		txt_Search.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		txt_Search.setColumns(10);
		panel.add(txt_Search);

		JButton btn_Search = new RoundedButton("搜 索",10);
		btn_Search.setBounds(587, 101, 113, 42);

		btn_Search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnl_list.removeAll();
				List<Product> list = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, null,
								"tech.zxuuu.server.shop.ProductServer.searchProduct", new Object[] { txt_Search.getText() }).send())
						.getListReturn(Product.class);
				if (list == null) {
					SwingUtils.showMessage(null, "抱歉，没有搜到这个商品，管理员正在努力备货中...", "提示");
				} else {
					pnl_list.setPreferredSize(new Dimension(jsp_List.getWidth() - 50, 280 * list.size()));
					for (int i = 0; i < list.size(); i++) {
						JPanel paneli = new Blocks(list.get(i).getPicture(), list.get(i).getInformation(),list.get(i).getName(), list.get(i).getType(),
								list.get(i).getPrice());
						paneli.setName(list.get(i).getName());
						pnl_list.add(paneli);
					}
				}

			}
		});

		btn_Search.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		panel.add(btn_Search);


		JLabel lblVcampus = new JLabel("校园商店");
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblVcampus.setForeground(Color.WHITE); // 设置字体颜色为白色
		lblVcampus.setBounds(102, 27, 239, 34);
		panel.add(lblVcampus);


		String[] tableHeader = { "商品名称", "类型", "价格", "数量" };
		model = new DefaultTableModel(null, tableHeader);


		JButton btn_Food = new LibButton("食物",0);
		btn_Food.setBounds(0, 170, 160, 80);
		panel.add(btn_Food);
		btn_Food.setFont(new Font("微软雅黑", Font.PLAIN, 28));

		JButton btn_Drink = new LibButton("饮料",0);
		btn_Drink.setBounds(0, 170+80, 160, 80);
		panel.add(btn_Drink);
		btn_Drink.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Drink);
			}
		});
		btn_Drink.setFont(new Font("微软雅黑", Font.PLAIN, 28));

		JButton btn_Fruit = new LibButton("水果",0);
		btn_Fruit.setBounds(0, 170+80*2, 160, 80);
		panel.add(btn_Fruit);
		btn_Fruit.setFont(new Font("微软雅黑", Font.PLAIN, 28));


		JButton btn_Tool = new LibButton("文具",0);
		btn_Tool.setBounds(0, 170+80*3, 160, 80);
		panel.add(btn_Tool);
		btn_Tool.setFont(new Font("微软雅黑", Font.PLAIN, 28));


		JButton btn_Thing = new LibButton("日用品",0);
		btn_Thing.setBounds(0, 170+80*4, 160, 80);
		panel.add(btn_Thing);
		btn_Thing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Thing);
			}
		});
		btn_Thing.setFont(new Font("微软雅黑", Font.PLAIN, 28));
/*
		lblCartCount = new JLabel("0");
		lblCartCount.setHorizontalAlignment(SwingConstants.LEFT);
		lblCartCount.setBackground(Color.WHITE);
		lblCartCount.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblCartCount.setForeground(Color.RED);
		lblCartCount.setBounds(10, 562, 55, 40);
		panel.add(lblCartCount);
*/
		JButton btnCart = new JButton("");
		btnCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						cartPanel.setVisible(false);
						cartPanel.requireReRender();
						cartPanel.setVisible(true);
					}
				});

			}
		});
		btnCart.setIcon(new ImageIcon(ShopFirstPage.class.getResource("/resources/assets/icon/cart.png")));
		btnCart.setBounds(15, 600, 161, 137);
		panel.add(btnCart);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ShopFirstPage.class.getResource("/resources/assets/icon/search01.png")));
		lblNewLabel.setBounds(42, 105, 32, 32);
		panel.add(lblNewLabel);

		btn_Tool.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Tool);
			}
		});
		btn_Fruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Fruit);
			}
		});
		btn_Food.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTypeButtonClick(btn_Food);
			}
		});
		JLabel greenStrip = new JLabel("");
		greenStrip.setOpaque(true);
		greenStrip.setBackground(new Color(0, 100, 0)); // Green color
		greenStrip.setBounds(0, 0, 866, 80); // Adjust the height as needed
		panel.add(greenStrip, Integer.valueOf(-1)); // Add to the bottom layer


		btn_Food.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btn_Food.setBackground(new Color(0, 120, 0));
				btn_Drink.setBackground(new Color(0, 100, 0));
				btn_Fruit.setBackground(new Color(0, 100, 0));
				btn_Thing.setBackground(new Color(0, 100, 0));
				btn_Tool.setBackground(new Color(0, 100, 0));
			}

		});
		btn_Drink.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btn_Drink.setBackground(new Color(0, 120, 0));
				btn_Food.setBackground(new Color(0, 100, 0));
				btn_Fruit.setBackground(new Color(0, 100, 0));
				btn_Thing.setBackground(new Color(0, 100, 0));
				btn_Tool.setBackground(new Color(0, 100, 0));
			}

		});
		btn_Fruit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btn_Fruit.setBackground(new Color(0, 120, 0));
				btn_Food.setBackground(new Color(0, 100, 0));
				btn_Drink.setBackground(new Color(0, 100, 0));
				btn_Thing.setBackground(new Color(0, 100, 0));
				btn_Tool.setBackground(new Color(0, 100, 0));
			}

		});
		btn_Tool.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btn_Tool.setBackground(new Color(0, 120, 0));
				btn_Food.setBackground(new Color(0, 100, 0));
				btn_Fruit.setBackground(new Color(0, 100, 0));
				btn_Thing.setBackground(new Color(0, 100, 0));
				btn_Drink.setBackground(new Color(0, 100, 0));
			}

		});
		btn_Thing.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				btn_Thing.setBackground(new Color(0, 120, 0));
				btn_Food.setBackground(new Color(0, 100, 0));
				btn_Fruit.setBackground(new Color(0, 100, 0));
				btn_Drink.setBackground(new Color(0, 100, 0));
				btn_Tool.setBackground(new Color(0, 100, 0));
			}

		});
	}
}
