package org.example;

import javax.swing.*;
import java.awt.*;

public class ConnectServer extends JFrame {
    static private final int WINDOW_HEIGHT = 640;
    static private final int WINDOW_WIDTH = 640;
    static private final int WINDOW_POS_X = 300;
    static private final int WINDOW_POS_Y = 100;
    static private final String WINDOW_NAME_SERVER = "Чат с сервером";

    JButton buttonStop = new JButton("Stop");
    JButton buttonStart = new JButton("Start");

    JPanel server = new JPanel(new GridLayout(1,2));


    ConnectServer(){
        setTitle(WINDOW_NAME_SERVER);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        server.add(buttonStop);
        server.add(buttonStart);
        add(server, BorderLayout.SOUTH);



        buttonStart.addActionListener(e -> {
            System.out.println("server start");
            new ConnectWindow();
        });
        buttonStop.addActionListener(e -> {
            System.out.println("server stop");
        });

        setVisible(true);
    }


}
