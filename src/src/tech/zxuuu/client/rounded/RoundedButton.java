package src.tech.zxuuu.client.rounded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {
    private int radius;
    private Color pressedBackgroundColor;
    private Color normalBackgroundColor;

    public RoundedButton(String text, int radius) {
        super(text);
        this.radius = radius;
        this.normalBackgroundColor = new Color(0, 100, 0); // 正常背景颜色
        this.pressedBackgroundColor = new Color(0, 120, 0); // 按下时的背景颜色
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setBackground(normalBackgroundColor);
        setForeground(Color.WHITE); // 设置前景色为白色

        // 添加鼠标事件监听器
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(pressedBackgroundColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(normalBackgroundColor);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(pressedBackgroundColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(normalBackgroundColor);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // 不绘制边框
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width = Math.max(size.width, size.height);
        return size;
    }
}