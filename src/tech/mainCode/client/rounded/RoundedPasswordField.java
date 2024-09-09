package tech.mainCode.client.rounded;

import javax.swing.*;
import java.awt.*;

public class RoundedPasswordField extends JPasswordField {
    private Shape shape;

    public RoundedPasswordField(int size) {
        super(size);
        setOpaque(false); // 设置为透明，以便显示自定义边框
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15); // 绘制圆角矩形背景
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {

    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        return shape.contains(x, y);
    }
}
