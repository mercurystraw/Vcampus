package tech.zxuuu.client.rounded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import tech.zxuuu.client.rounded.RoundedButton;
import tech.zxuuu.client.rounded.RoundedTextField;

public class CustomOptionPane extends JOptionPane {

    @Override
    public JDialog createDialog(Component parentComponent, String title) {
        JDialog dialog = new JDialog((Frame) null, title, true);

        dialog.setContentPane(this);
        dialog.pack();
        dialog.setLocationRelativeTo(parentComponent);

        // Replace default buttons with custom rounded buttons
        for (Component comp : dialog.getContentPane().getComponents()) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                for (int i = 0; i < panel.getComponentCount(); i++) {
                    Component button = panel.getComponent(i);
                    if (button instanceof JButton) {
                        JButton oldButton = (JButton) button;
                        RoundedButton newButton = new RoundedButton(oldButton.getText(), 20);
                        newButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));

                        // Add action listener to close the dialog
                        newButton.addActionListener(e -> {
                            oldButton.doClick();
                            dialog.dispose();
                        });

                        panel.remove(oldButton);
                        panel.add(newButton, i);
                    }
                }
                panel.revalidate();
                panel.repaint();
            }
        }

        return dialog;
    }

    public static void showMessage(Component parentComponent, Object message, String title, int messageType) {
        CustomOptionPane optionPane = new CustomOptionPane();
        optionPane.setMessage(message);
        optionPane.setMessageType(messageType);
        JDialog dialog = optionPane.createDialog(parentComponent, title);
        dialog.setVisible(true);
    }

    public static void showError(Component parentComponent, Object message, String title) {
        showMessage(parentComponent, message, title, ERROR_MESSAGE);
    }

    public static void showWarning(Component parentComponent, Object message, String title) {
        showMessage(parentComponent, message, title, WARNING_MESSAGE);
    }

    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType) {
        showMessage(parentComponent, message, title, messageType);
    }

    public static String showInputDialog(Component parentComponent, Object message) {
        CustomOptionPane optionPane = new CustomOptionPane();
        optionPane.setMessage(message);
        optionPane.setMessageType(QUESTION_MESSAGE);
        optionPane.setOptionType(OK_CANCEL_OPTION);

        // Create a custom rounded text field
        RoundedTextField inputField = new RoundedTextField( 15);
        optionPane.add(inputField, 1);

        JDialog dialog = optionPane.createDialog(parentComponent, "Input");
        dialog.setVisible(true);
        Object selectedValue = optionPane.getValue();
        if (selectedValue == null || selectedValue.equals(CANCEL_OPTION)) {
            return null;
        }
        return inputField.getText();
    }
}