package tech.zxuuu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tech.zxuuu.client.rounded.CustomOptionPane;
import javax.swing.*;

/**
 * Swing GUI设计实用工具
 *
 * 作者 z0gSh1u
 */
public final class SwingUtils {

	/**
	 * 检查文本框是否为空
	 */
	static public boolean isTxtEmpty(JTextField txt) {
		return txt.getText().trim().equals("");
	}

	/**
	 * 弹出信息提示框
	 */
	static public void showMessage(JPanel parent, String msg, String title) {
		CustomOptionPane.showMessageDialog(parent, msg, title, CustomOptionPane.WARNING_MESSAGE);
	}

	/**
	 * 弹出错误提示框
	 */
	static public void showError(JPanel parent, String msg, String title) {
		CustomOptionPane.showMessageDialog(parent, msg, title, CustomOptionPane.ERROR_MESSAGE);
	}

	/**
	 * 弹出小型输入框，返回输入内容
	 */
	static public String popInput( JPanel parent, String msg) {
		return CustomOptionPane.showInputDialog(parent, msg);
	}

	/**
	 * 检查字符串是否为纯整数
	 */
	static public boolean isPureDigits(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查字符串是否含汉字
	 */
	public static boolean containsChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

}