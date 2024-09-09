package tech.mainCode.client.rounded;


import javax.swing.JRadioButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class NoFocusJRadioButton extends JRadioButton {
    public NoFocusJRadioButton(String text) {
        super(text);
        setFocusPainted(false); // 去掉焦点框
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
        g2.dispose();
    }
}