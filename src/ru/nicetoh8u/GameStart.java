package ru.nicetoh8u;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameStart {
    public static JFrame named;
    public static void main(String[] args) {

        named = new JFrame("Enter your name");
        JPanel name = new JPanel(new BorderLayout());
        JTextField textField = new JTextField("PlayerName", 1);
        JButton confirm = new JButton("Начать");
        named.setFocusable(true);
        name.add(textField,BorderLayout.CENTER);
        name.add(confirm,BorderLayout.SOUTH);
        named.setContentPane(name);
        named.setLocationRelativeTo(null);
        named.setSize(100,100);

        JFrame startFrame = new JFrame("Castle Defense");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setContentPane(new GamePanel());
        startFrame.pack();
        startFrame.setVisible(true);
        startFrame.setLocationRelativeTo(null);


    }
}
