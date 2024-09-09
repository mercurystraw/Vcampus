package tech.zxuuu.client.shop;

import java.awt.Desktop;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import tech.zxuuu.client.auth.AuthGUI;
import tech.zxuuu.client.main.App;
import tech.zxuuu.client.main.AppShopManager;
import tech.zxuuu.entity.Product;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import tech.zxuuu.client.rounded.RoundedTextField;
import tech.zxuuu.client.rounded.RoundedButton;

public class NewProductPane extends JPanel {
	private RoundedTextField txtName;
	private RoundedTextField txtPrice;
	private RoundedTextField txtImage;
	private RoundedTextField txtCount;
	private RoundedTextField txtInformation;

	/**
	 * 商品新增面板
	 *

	 */
	public NewProductPane() {
		this.setLayout(null);

		JLabel lbl_ProductName = new JLabel("商品名称");
		lbl_ProductName.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lbl_ProductName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ProductName.setBounds(118, 20, 115, 33);
		this.add(lbl_ProductName);

		JLabel lbl_Type = new JLabel("商品类型");
		lbl_Type.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lbl_Type.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Type.setBounds(118, 93, 115, 33);
		this.add(lbl_Type);

		JLabel lbl_Price = new JLabel("商品价格");
		lbl_Price.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lbl_Price.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Price.setBounds(118, 166, 115, 33);
		this.add(lbl_Price);

		JLabel lbl_Picture = new JLabel("商品图片");
		lbl_Picture.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lbl_Picture.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Picture.setBounds(118, 239, 115, 33);
		this.add(lbl_Picture);

		JLabel lbl_AddNumber = new JLabel("添加数量");
		lbl_AddNumber.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lbl_AddNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_AddNumber.setBounds(118, 312, 115, 33);
		this.add(lbl_AddNumber);

		txtName = new RoundedTextField(10);
		txtName.setBounds(288, 20, 388, 41);
		txtName.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		this.add(txtName);
		txtName.setColumns(10);

		txtPrice = new RoundedTextField(10);
		txtPrice.setColumns(10);
		txtPrice.setBounds(288, 166, 388, 41);
		txtPrice.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		this.add(txtPrice);

		txtImage = new RoundedTextField(10);
		txtImage.setColumns(10);
		txtImage.setBounds(288, 239, 388, 41);
		txtImage.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		this.add(txtImage);

		txtCount = new RoundedTextField(10);
		txtCount.setColumns(10);
		txtCount.setBounds(288, 312, 388, 41);
		txtCount.setFont(new Font("微软雅黑",Font.PLAIN,18));
		this.add(txtCount);

		JComboBox cbType = new JComboBox();

		RoundedButton btn_Confirm = new RoundedButton("确 认",10);
		btn_Confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!SwingUtils.isPureDigits(txtCount.getText()) || !SwingUtils.isPureDigits(txtPrice.getText())) {
					SwingUtils.showError(null, "输入不合法，请检查！", "错误");
				}

				Product product = new Product();
				product.setName(txtName.getText());
				product.setNumber(Integer.parseInt(txtCount.getText()));
				product.setPicture("<img src=\"" + txtImage.getText() + "\" />");
				product.setType((String) cbType.getSelectedItem());
				product.setPrice(Float.parseFloat(txtPrice.getText()));
				product.setInformation(txtInformation.getText());

				Boolean result = ResponseUtils
						.getResponseByHash((new Request(App.connectionToServer, null,
								"tech.zxuuu.server.shop.Addons.insertNewProduct", new Object[] { product }).send()))
						.getReturn(Boolean.class);

				if (result) {
					SwingUtils.showMessage(null, "入库成功！", "提示");
				} else {
					SwingUtils.showError(null, "入库失败！", "错误");
				}
			}
		});
		btn_Confirm.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		btn_Confirm.setBounds(328, 460, 167, 41);
		this.add(btn_Confirm);

		JLabel lbl_ProductInformation = new JLabel("详细信息");
		lbl_ProductInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ProductInformation.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lbl_ProductInformation.setBounds(118, 385, 115, 33);
		add(lbl_ProductInformation);

		txtInformation = new RoundedTextField(10);
		txtInformation.setColumns(10);
		txtInformation.setBounds(288, 385, 388, 41);
		add(txtInformation);

		cbType.setFont(new Font("宋体", Font.PLAIN, 16));
		cbType.setModel(new DefaultComboBoxModel(new String[] { "食物", "饮料", "水果", "文具", "用品" }));
		cbType.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		cbType.setBounds(288, 93, 388, 41);
		add(cbType);


	}
}
