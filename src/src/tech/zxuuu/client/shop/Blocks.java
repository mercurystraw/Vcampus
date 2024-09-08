package src.tech.zxuuu.client.shop;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import tech.zxuuu.client.shop.ShopFirstPage;
import tech.zxuuu.entity.Product;
import java.net.URL;

/**
 * 商品Block
 * 
 * @author 杨鹏杰
 * @modify z0gSh1u
 */
public class Blocks extends JPanel {
	JTextField jtxt_Price;
	private JTextField jtxt_Type;

	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Create the panel.
	 */
	public Blocks(String picture, String information,String name, String type, float price) {
		setLayout(null);
		setBackground(new Color(111,111,111));

		JButton btn_AddProduct = new JButton("加入购物车");
		btn_AddProduct.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btn_AddProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product product = new Product();
				product.setName(name);
				product.setNumber(1);
				product.setPrice(price);
				
				boolean found = false;
				for (Product pd : ShopFirstPage.cart) {
					if (pd.getName().equals(product.getName())) {
						pd.setNumber(pd.getNumber() + 1);
						found = true;
					}
				}
				if (!found) {
					ShopFirstPage.cart.add(product);
				}
				ShopFirstPage.lblCartCount.setText(String.valueOf(ShopFirstPage.cart.size()));
			}
		});
		btn_AddProduct.setBounds(264, 221, 195, 49);
		add(btn_AddProduct);



		// 使用 JLabel 和 ImageIcon 显示图片
		JLabel imageLabel = new JLabel();
		imageLabel.setBounds(14, 10, 236, 261); // 设置位置和大小
		add(imageLabel);

		// 从网址加载图片
		try {
			URL url = new URL(picture);
			ImageIcon icon = new ImageIcon(url);

			// 调整图片大小以适应 JLabel
			Image image = icon.getImage();
			Image scaledImage = image.getScaledInstance(236, 261, Image.SCALE_SMOOTH);
			imageLabel.setIcon(new ImageIcon(scaledImage));
		} catch (Exception e) {
			e.printStackTrace();
			imageLabel.setText("无法加载图片");
		}

		JTextArea txtA_Name = new JTextArea();
		txtA_Name.setFont(new Font("宋体", Font.BOLD, 18));
		txtA_Name.setEditable(false);
		txtA_Name.setBounds(264, 10, 195, 30); // 调整位置
		txtA_Name.setText(name);
		add(txtA_Name);

		JTextArea txtA_Information = new JTextArea();
		txtA_Information.setFont(new Font("宋体", Font.BOLD, 18));
		txtA_Information.setEditable(false);
		txtA_Information.setBounds(264, 50, 195, 78); // 调整位置
		txtA_Information.setText(information);
		add(txtA_Information);

		JLabel lbl_Type = new JLabel("类型");
		lbl_Type.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Type.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_Type.setBounds(264, 140, 74, 30); // 调整位置
		add(lbl_Type);

		JTextField jtxt_Type = new JTextField();
		jtxt_Type.setFont(new Font("幼圆", Font.BOLD, 20));
		jtxt_Type.setHorizontalAlignment(SwingConstants.CENTER);
		jtxt_Type.setEditable(false);
		jtxt_Type.setBounds(340, 140, 120, 30); // 调整位置
		jtxt_Type.setText(type);
		add(jtxt_Type);
		jtxt_Type.setColumns(10);

		JLabel lbl_Price = new JLabel("价格￥");
		lbl_Price.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_Price.setBounds(264, 180, 70, 30); // 调整位置
		add(lbl_Price);

		JTextField jtxt_Price = new JTextField();
		jtxt_Price.setFont(new Font("幼圆", Font.BOLD, 20));
		jtxt_Price.setHorizontalAlignment(SwingConstants.CENTER);
		jtxt_Price.setEditable(false);
		jtxt_Price.setBounds(340, 180, 120, 30); // 调整位置
		jtxt_Price.setText(jtxt_Price.getText() + price);
		add(jtxt_Price);
		jtxt_Price.setColumns(10);

	}
}
