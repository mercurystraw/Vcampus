package tech.zxuuu.client.rounded;

import javax.swing.*;
import java.awt.*;

public class RoundedLabel extends JLabel {

    private int arcWidth;
    private int arcHeight;

    public RoundedLabel(String text, int arcWidth, int arcHeight) {
        super(text);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setFont(new Font("微软雅黑", Font.PLAIN, 16));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        g2.setColor(getForeground());
        super.paintComponent(g2);
        g2.dispose();
    }
}