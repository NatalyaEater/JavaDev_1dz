package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class ConnectWindow extends JFrame {
    static private final int WINDOW_HEIGHT = 640;
    static private final int WINDOW_WIDTH = 640;
    static private final int WINDOW_POS_X = 300;
    static private final int WINDOW_POS_Y = 100;
    static private final String WINDOW_NAME = "Подключение к серверу";
    private static final String LOG_FILE = "log.txt";
    private JTextArea textOutput = new JTextArea("");

    JTextField loginField = new JTextField("Введите логин: ");
    JTextField passwordField = new JPasswordField("Введите пароль: ");
    JTextField serverField = new JTextField("Введите сервера подключения: ");
    JButton buttonLogin = new JButton("login");

    private JTextField textInput = new JTextField();
    private JButton buttonConnect = new JButton("Отправить сообщение");

    JPanel grid = new JPanel(new GridLayout(4,2));




    ConnectWindow(){
        setTitle(WINDOW_NAME);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        grid.add(serverField,new GridLayout(1,1));
        grid.add(loginField,new GridLayout(1,2));
        grid.add(passwordField,new GridLayout(2,1));
        grid.add(buttonLogin,new GridLayout(2,2));
        grid.add(textInput,new GridLayout(4,1));
        grid.add(buttonConnect,new GridLayout(4,2));



        loadChatHistory();

        buttonConnect.addActionListener(e -> sendMessage());

        textInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
        add(grid);
        setVisible(true);
    }

    private void sendMessage() {
        String login = loginField.getText().trim();
        String message = login +" : " + textInput.getText() + "\n";
        textOutput.append(message);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textInput.setText("");
    }

    private void loadChatHistory() {
        File file = new File(LOG_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    textOutput.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
