package tech.zxuuu.client.chat;

import tech.zxuuu.client.chat.Chat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChatGUI extends JFrame
{
    private JFrame frame;
    private JPanel panelLeft;
    private JTextArea textAreaResponse;
    private JTextField textFieldInput;
    private JButton btnSend;
    private ArrayList<ChatWindow> chatWindows;
    private ChatWindow currentChatWindow;

    public ChatGUI ()
    {
        chatWindows = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("VChat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // 设置分裂面板
        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.167); // 左侧占比
        splitPane.setContinuousLayout(true);

        // 左侧面板
        panelLeft = new JPanel();
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));

        // 新建聊天按钮
        JButton btnNewChat = new JButton("新建聊天");
        btnNewChat.addActionListener(e -> addNewChatWindow());
        panelLeft.add(btnNewChat);

        splitPane.setLeftComponent(new JScrollPane(panelLeft));

        // 右侧聊天界面
        textAreaResponse = new JTextArea();
        textAreaResponse.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaResponse);

        splitPane.setRightComponent(scrollPane);

        frame.getContentPane().add(splitPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        textFieldInput = new JTextField("来说点什么吧…（Shift + Enter = 换行）");
        textFieldInput.setForeground(Color.LIGHT_GRAY);

        // 输入框焦点监听
        textFieldInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textFieldInput.getText().equals("来说点什么吧…（Shift + Enter = 换行）")) {
                    textFieldInput.setForeground(Color.BLACK);
                    textFieldInput.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textFieldInput.getText().isEmpty()) {
                    textFieldInput.setForeground(Color.LIGHT_GRAY);
                    textFieldInput.setText("来说点什么吧…（Shift + Enter = 换行）");
                }
            }
        });

        btnSend = new JButton("发送");
        btnSend.setEnabled(false);
        btnSend.addActionListener(e -> sendMessage());

        textFieldInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                btnSend.setEnabled(!textFieldInput.getText().trim().isEmpty());
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !e.isShiftDown()) {
                    sendMessage();
                }
            }
        });

        inputPanel.add(textFieldInput, BorderLayout.CENTER);
        inputPanel.add(btnSend, BorderLayout.EAST);

        frame.getContentPane().add(inputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        // 确保在组件初始化之后再调用
        addNewChatWindow();
    }


    public void switchToChatWindow(ChatWindow chatWindow)
    {
        currentChatWindow = chatWindow;
        textAreaResponse.setText(chatWindow.getChatRecords());
    }

    private void addNewChatWindow()
    {
        ChatWindow newChatWindow = new ChatWindow(this);
        chatWindows.add(newChatWindow);
        panelLeft.add(newChatWindow);
        panelLeft.revalidate();
        panelLeft.repaint();
        switchToChatWindow(newChatWindow); // 切换到新聊天窗口
    }


    private void sendMessage()
    {
        String question = textFieldInput.getText();
        if (!question.trim().isEmpty() && currentChatWindow != null)
        {
            //输出提问文本
            textAreaResponse.append("Q: " + question + "\n");

            // 调用API得到回答，并输出
            String answer = Chat.chat(question);
            textAreaResponse.append("A: " + answer + "\n");

            //输出每个问答流程的分隔符号
            int totalLength = textAreaResponse.getWidth() / textAreaResponse.getFontMetrics(textAreaResponse.getFont()).charWidth('=');
            StringBuilder separator = new StringBuilder();
            for (int i = 0; i < totalLength; i++) {
                separator.append('=');
            }
            textAreaResponse.append(separator.toString() + "\n");

            //保存聊天记录
            currentChatWindow.addChatRecord("Q: " + question);
            currentChatWindow.addChatRecord("A: " + answer);
            currentChatWindow.addChatRecord(separator.toString());

            // 清空输入框
            textFieldInput.setText("来说点什么吧…（Shift + Enter = 换行）");
            textFieldInput.setForeground(Color.LIGHT_GRAY);
            textFieldInput.requestFocus();
        }
    }

    class ChatWindow extends JButton
    {
        private ArrayList<String> chatRecords;
        private String title;

        public ChatWindow(ChatGUI app)
        {
            super("新聊天 " + (app.chatWindows.size() + 1)); // 修改这里
            this.title = "新聊天";
            this.chatRecords = new ArrayList<>();

            this.addActionListener(e -> app.switchToChatWindow(this));
        }


        public void addChatRecord(String record)
        {
            chatRecords.add(record);
        }

        public String getChatRecords()
        {
            StringBuilder records = new StringBuilder();
            for (String record : chatRecords)
            {
                records.append(record).append("\n");
            }
            return records.toString();
        }
    }
}
