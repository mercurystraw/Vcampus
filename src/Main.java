import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JTextPane Unicode Emoji Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JTextPane textPane = new JTextPane();
            textPane.setEditable(false); // 设置为不可编辑

            // 获取文本样式
            StyledDocument doc = textPane.getStyledDocument();

            // 创建样式
            Style style = textPane.addStyle("EmojiStyle", null);
            StyleConstants.setFontSize(style, 24); // 设置字体大小
            StyleConstants.setForeground(style, Color.RED); // 设置颜色

            // 插入文本和表情
            try {
                doc.insertString(doc.getLength(), "Hello, ", null);
                doc.insertString(doc.getLength(), "\uD83D\uDE00", style); // 插入笑脸表情
                doc.insertString(doc.getLength(), " Welcome to Java!", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            frame.add(new JScrollPane(textPane));
            frame.setVisible(true);
        });
    }
}
