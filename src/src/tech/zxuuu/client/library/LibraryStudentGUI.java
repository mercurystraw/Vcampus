package src.tech.zxuuu.client.library;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.javassist.compiler.ast.NewExpr;

import com.sun.jna.platform.win32.WinDef.SCODE;

import tech.zxuuu.client.library.MyBorrowGUI;
import tech.zxuuu.client.library.QueryBook;
import tech.zxuuu.client.library.ReturnBook;
import tech.zxuuu.client.main.App;
import tech.zxuuu.client.messageQueue.ResponseQueue;
import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.util.ResponseUtils;
import tech.zxuuu.entity.*;
import tech.zxuuu.client.rounded.RoundedButton;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * 图书馆学生主页
 * 
 * @author 曾铖
 * @modify z0gSh1u
 */
public class LibraryStudentGUI extends JFrame {

	private JPanel contentPane;
	private Boolean click = true;

	int nowPos = 0;

	/**
	 * Create the frame.
	 */
	public LibraryStudentGUI() {
		setTitle("图书馆 - VCampus");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(LibraryStudentGUI.class.getResource("/resources/assets/icon/seu_icon.png")));
		setResizable(false);
		setVisible(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnBorrow = new RoundedButton("借  书",30);
		btnBorrow.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		btnBorrow.setBounds(55, 133, 172, 84);
		btnBorrow.setBackground(new Color(133, 82, 235));
		btnBorrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tech.zxuuu.client.library.QueryBook lq = new QueryBook();
				lq.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				lq.setModal(true);
				lq.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnBorrow);

		btnBorrow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				btnBorrow.setBackground(new Color(163, 112, 255));
			}

			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				btnBorrow.setBackground(new Color(133, 82, 235));
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnBorrow.setBackground(new Color(163, 112, 255)); // 鼠标进入时的颜色
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnBorrow.setBackground(new Color(133, 82, 235)); // 鼠标离开时的颜色
			}

		});

		JButton btnReturn = new RoundedButton("还  书",30);
		btnReturn.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		btnReturn.setBounds(172 + 2 * 55, 133, 172, 84);
		btnReturn.setBackground(new Color(235, 82, 0));
		btnReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tech.zxuuu.client.library.ReturnBook re = new ReturnBook();
				re.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				re.setModal(true);
				re.setVisible(true);
			}
		});
		contentPane.add(btnReturn);
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				btnReturn.setBackground(new Color(255, 112, 10));
			}

			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				btnReturn.setBackground(new Color(235, 82, 0));
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnReturn.setBackground(new Color(255, 112, 10)); // 鼠标进入时的颜色
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnReturn.setBackground(new Color(235, 82, 0)); // 鼠标离开时的颜色
			}

		});

		JButton btnRenew = new RoundedButton("我的借阅",30);
		btnRenew.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		btnRenew.setBounds(2*172 + 3* 55, 133, 171, 84);
		btnRenew.setBackground(new Color(0, 164, 113));
		btnRenew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tech.zxuuu.client.library.MyBorrowGUI renew = new MyBorrowGUI();
				renew.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				renew.setVisible(true);
			}
		});
		contentPane.add(btnRenew);
		btnRenew.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				btnRenew.setBackground(new Color(10, 214, 153));
			}

			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				btnRenew.setBackground(new Color(0, 164, 113));
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnRenew.setBackground(new Color(10, 214, 153)); // 鼠标进入时的颜色
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnRenew.setBackground(new Color(0, 164, 113)); // 鼠标离开时的颜色
			}

		});


/*
		JPanel HotList = new JPanel();
		HotList.setLayout(new GridLayout(0, 1));

		JScrollPane scrollPane = new JScrollPane(HotList);
		scrollPane.setBounds(21, 152, 462, 403);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(HotList);
		HotList.setPreferredSize(new Dimension(scrollPane.getWidth() - 50, 300 * 5));
*/
		JLabel lblLibraryIcon = new JLabel("");
		lblLibraryIcon.setIcon(new ImageIcon(LibraryStudentGUI.class.getResource("/resources/assets/icon/library.png")));
		lblLibraryIcon.setBounds(21, 10, 64, 64);
		contentPane.add(lblLibraryIcon);

		JLabel lblVcampus = new JLabel("图书借阅");
		lblVcampus.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblVcampus.setForeground(Color.WHITE); // 设置字体颜色为白色
		lblVcampus.setBounds(102, 27, 239, 34);
		contentPane.add(lblVcampus);
/*
		JLabel lblHotBookIcon = new JLabel("热门书籍");
		lblHotBookIcon.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblHotBookIcon.setBounds(189, 94, 148, 48);
		contentPane.add(lblHotBookIcon);


		List<Book> list = null;
		list = ResponseUtils.getResponseByHash(new Request(App.connectionToServer, App.session,
				"tech.zxuuu.server.library.BookServer.searchHotBook", new Object[] {}).send()).getListReturn(Book.class);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				JPanel hotBook = new HotBook(list.get(i).getPictureURL(), list.get(i).getTitle(), list.get(i).getAuthor(),
						list.get(i).getNumofborrowed());
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						hotBook.setBounds(0, 0, 445, 300);
						HotList.add(hotBook);
						hotBook.repaint();
						HotList.repaint();
					}
				});
			}
		}

		LibraryStudentGUI that = this;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				scrollPane.getVerticalScrollBar().setValue(0);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				that.setVisible(true);
			}
		});


		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				while (true) {
					scrollPane.getVerticalScrollBar().setValue(nowPos++);
					if (nowPos >= 1100 ) {
						nowPos = 0;
						scrollPane.getVerticalScrollBar().setValue(nowPos++);
					}
					try {
						Thread.sleep(90); // 注意这里要用Thread.sleep，而不是 thread.sleep
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
		*/
		JLabel greenStrip = new JLabel("");
		greenStrip.setOpaque(true);
		greenStrip.setBackground(new Color(0, 100, 0)); // Green color
		greenStrip.setBounds(0, 0, 866, 80); // Adjust the height as needed
		contentPane.add(greenStrip, Integer.valueOf(-1)); // Add to the bottom layer

	}
}
