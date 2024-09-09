package tech.zxuuu.client.shop;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import tech.zxuuu.client.main.App;
import tech.zxuuu.entity.Product;
import tech.zxuuu.net.Request;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.util.SwingUtils;
import tech.zxuuu.client.rounded.RoundedTextField;
import tech.zxuuu.client.rounded.RoundedButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JComboBox;

/**
 * 商品删除面板
 *
 */
public class DeleteProductPane extends JPanel {
	private JTextField txtName;
	private JTable tblSearchList;
	private DefaultTableModel model;
	private List<Product> list = null;
	JComboBox cbType;

	/**
	 * Create the panel.
	 */
	public DeleteProductPane() {
		this.setLayout(null);
		{
			JLabel lbl_ProductName = new JLabel("商品名称");
			lbl_ProductName.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			lbl_ProductName.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_ProductName.setBounds(100, 50, 115, 33);
			this.add(lbl_ProductName);
		}
		{
			JLabel lbl_Type = new JLabel("类型");
			lbl_Type.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			lbl_Type.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Type.setBounds(450, 50, 115, 33);
			this.add(lbl_Type);
		}
		{
			txtName = new RoundedTextField(10);
			txtName.setBounds(250, 50, 160, 33);
			this.add(txtName);
			txtName.setColumns(10);
		}

		String[] tableHeader = { "商品名称", "类型", "价格", "库存量" };
		model = new DefaultTableModel(null, tableHeader);
		tblSearchList = new JTable();
		tblSearchList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
					Product product = new Product();
					product.setName(list.get(row).getName());
					product.setType(list.get(row).getType());
					product.setPrice(list.get(row).getPrice());
					product.setNumber(list.get(row).getNumber());
					Boolean result = ResponseUtils
							.getResponseByHash((new Request(App.connectionToServer, null,
									"tech.zxuuu.server.shop.Addons.deleteProduct", new Object[] { product }).send()))
							.getReturn(Boolean.class);
					if (result) {
						SwingUtils.showMessage(null, "删除成功！", "提示");
						model.removeRow(row);
					} else {
						SwingUtils.showError(null, "删除失败！", "错误");
					}
					model.fireTableStructureChanged();

				}
			}
		});
		tblSearchList.setBounds(2, 2, 300, 300);
		model.fireTableStructureChanged();
		tblSearchList.setModel(model);
		JScrollPane jsp = new JScrollPane(tblSearchList);

		jsp.setBounds(100, 260, 700, 220);
		this.add(jsp);

		JLabel lbl_ProductList = new JLabel("商品列表(双击删除商品)");
		lbl_ProductList.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lbl_ProductList.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ProductList.setBounds(250, 200, 397, 41);
		this.add(lbl_ProductList);

		JButton btn_Search = new RoundedButton("搜 索",10);
		btn_Search.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		btn_Search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product product = new Product();
				product.setName(txtName.getText());
				product.setType((String) cbType.getSelectedItem());

				list = ResponseUtils
						.getResponseByHash(new Request(App.connectionToServer, null,
								"tech.zxuuu.server.shop.ProductServer.manageListProduct", new Object[] { product }).send())
						.getListReturn(Product.class);
				String[][] listData = new String[list.size()][4];
				model.setRowCount(0);
				if (list == null || list.size() == 0) {
					SwingUtils.showMessage(null, "抱歉，暂时没有库存", "test");
				} else {
					for (int i = 0; i < list.size(); i++) {
						listData[i][0] = list.get(i).getName();
						listData[i][1] = list.get(i).getType();
						listData[i][2] = String.valueOf(list.get(i).getPrice());
						listData[i][3] = String.valueOf(list.get(i).getNumber());
					}
					model = new DefaultTableModel(listData, tableHeader) {
						@Override
						public boolean isCellEditable(int a, int b) {
							return false;
						}
					};
					tblSearchList.setModel(model);
				}
			}

		});
		btn_Search.setBounds(375, 125, 167, 40);
		this.add(btn_Search);


		cbType = new JComboBox();
		cbType.setFont(new Font("宋体", Font.PLAIN, 16));
		cbType.setModel(new DefaultComboBoxModel(new String[] { "食物", "饮料", "水果", "文具", "用品" }));
		cbType.setBounds(600, 50, 193, 33);
		add(cbType);
	}
}
