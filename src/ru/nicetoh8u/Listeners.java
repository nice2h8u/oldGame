package ru.nicetoh8u;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Listeners implements MouseListener, MouseMotionListener, KeyListener {

    private Thread delay = new Thread();

    private Point mouseClickedCoordinate;

    public static JFrame named = new JFrame(" " );
    JPanel name = new JPanel(new BorderLayout());
    JTextField textField = new JTextField("PlayerName", 1);
    JButton confirm = new JButton("Начать");
    JTextArea recordsArea = new JTextArea(5,20);


    public void mouseClicked(MouseEvent e) {
        confirm.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (textField.getText() != "") {
                    GamePanel.playerName = textField.getText();
                    named.setVisible(false);
                }
            }
        });

        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseClickedCoordinate = (e.getPoint());
            if(GamePanel.GameState == GamePanel.STATES.play) {

                //добавялем пулю
                Bullet tempBullet = new Bullet();
                tempBullet.setxEnd(mouseClickedCoordinate.getX());
                tempBullet.setyEnd(mouseClickedCoordinate.getY());
                System.out.println(mouseClickedCoordinate.getX());
                System.out.println(mouseClickedCoordinate.getY());
                GamePanel.bullets.add(tempBullet);
            }


        if(GamePanel.GameState == GamePanel.STATES.menu) {
            if ((mouseClickedCoordinate.getX() > 550) && (mouseClickedCoordinate.getX() < 650)) {
                if ((mouseClickedCoordinate.getY() > 200) && (mouseClickedCoordinate.getY() < 250)) {
                    GamePanel.settings=false;
                    GamePanel.GameState = GamePanel.STATES.play;
                }
                if ((mouseClickedCoordinate.getY() > 470) && (mouseClickedCoordinate.getY() < 520)) {
                    System.exit(0);
                }
                if ((mouseClickedCoordinate.getY() > 380) && (mouseClickedCoordinate.getY() <430)) {
                    if(!GamePanel.settings)
                        GamePanel.settings=true;
                    else GamePanel.settings=false;
                }

            }
            //records
            if ((mouseClickedCoordinate.getY() > 290) && (mouseClickedCoordinate.getY() < 340)){
                GamePanel.settings=false;
                name = new JPanel(new BorderLayout());
                name.add(new JScrollPane(recordsArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
                recordsArea.setText(null);
                recordsArea.append("Имя игрока   "+" Очки \n");
                for(String[] playerData:GamePanel.usersInfo){
                    recordsArea.append(playerData[0]+ " " + playerData[1]+"\n");

                }
                named.setContentPane(name);
                named.setLocationRelativeTo(null);
                named.setSize(150,400);
                named.setTitle("Рекорды");
                named.setVisible(true);

            }
            //settings
            if ((mouseClickedCoordinate.getX() > 700) && (mouseClickedCoordinate.getX() < 800)){
                if ((mouseClickedCoordinate.getY() > 350) && (mouseClickedCoordinate.getY() < 400)) {
                    GamePanel.coeficientOfLevel = 1;
                    GamePanel.countOfMonster=1;
                }
                if ((mouseClickedCoordinate.getY() > 420) && (mouseClickedCoordinate.getY() < 470)) {
                    GamePanel.coeficientOfLevel = 1.5;
                    GamePanel.countOfMonster=2;
                }
                if ((mouseClickedCoordinate.getY() > 490) && (mouseClickedCoordinate.getY() < 540)) {

                    name = new JPanel(new BorderLayout());
                    name.add(textField, BorderLayout.CENTER);
                    name.add(confirm, BorderLayout.SOUTH);
                    named.setContentPane(name);
                    named.setLocationRelativeTo(null);
                    named.setSize(100,100);
                    named.setTitle("Введите имя игрока");
                    named.setVisible(true);

                }
            }
        }
            if(GamePanel.GameState == GamePanel.STATES.pause) {
                if ((mouseClickedCoordinate.getX()>550)&&(mouseClickedCoordinate.getX()<650))
                //resume
                {

                    if ((mouseClickedCoordinate.getY()>110)&&(mouseClickedCoordinate.getY()<160))
                    {
                        GamePanel.settings=false;
                        GamePanel.GameState = GamePanel.STATES.play;
                    }
                    //exit
                    if ((mouseClickedCoordinate.getY()>470)&&(mouseClickedCoordinate.getY()<520))
                    { //mb "oop\\src\\records.txt"
                        try(FileWriter writer = new FileWriter("src/records.txt", true))
                        {
                            // запись всей строки
                            String text = String.valueOf(GamePanel.playerName)+":" + String.valueOf(GamePanel.playerPoints)+ System.getProperty("line.separator") ;
                            writer.write(text);
                            writer.flush();
                        }
                        catch(IOException ex){

                            System.out.println(ex.getMessage());
                        }
                        System.exit(0);
                    }
                    //records
                    if ((mouseClickedCoordinate.getY() > 290) && (mouseClickedCoordinate.getY() < 340)){
                        GamePanel.settings=false;
                        name = new JPanel(new BorderLayout());
                        name.add(new JScrollPane(recordsArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
                        recordsArea.setText(null);
                        recordsArea.append("Имя игрока   "+" Очки \n");
                        for(String[] playerData:GamePanel.usersInfo){
                            recordsArea.append(playerData[0]+ " " + playerData[1]+"\n");

                        }
                        named.setContentPane(name);
                        named.setLocationRelativeTo(null);
                        named.setSize(150,400);
                        named.setTitle("Рекорды");
                        named.setVisible(true);

                    }
                    //new game
                    if ((mouseClickedCoordinate.getY() > 200) && (mouseClickedCoordinate.getY() < 250)) {
                        GamePanel.newGame=true;
                        GamePanel.settings=false;
                        GamePanel.GameState = GamePanel.STATES.play;
                    }
                    if ((mouseClickedCoordinate.getY() > 380) && (mouseClickedCoordinate.getY() <430)) {
                        if(!GamePanel.settings)
                            GamePanel.settings=true;
                        else GamePanel.settings=false;
                    }

                }
                //settings
                if ((mouseClickedCoordinate.getX() > 700) && (mouseClickedCoordinate.getX() < 800)){
                    if ((mouseClickedCoordinate.getY() > 350) && (mouseClickedCoordinate.getY() < 400)) {
                        GamePanel.coeficientOfLevel = 1;
                        GamePanel.playerPoints=0;
                        GamePanel.countOfMonster=1;
                    }
                    if ((mouseClickedCoordinate.getY() > 420) && (mouseClickedCoordinate.getY() < 470)) {
                        GamePanel.coeficientOfLevel = 1.5;
                        GamePanel.playerPoints=0;
                        GamePanel.countOfMonster=2;
                    }
                    if ((mouseClickedCoordinate.getY() > 490) && (mouseClickedCoordinate.getY() < 540)) {

                        name = new JPanel(new BorderLayout());
                        name.add(textField, BorderLayout.CENTER);
                        name.add(confirm, BorderLayout.SOUTH);
                        named.setContentPane(name);
                        named.setLocationRelativeTo(null);
                        named.setSize(100,100);
                        named.setTitle("Введите имя игрока");
                        named.setVisible(true);

                    }
                }
            }
        }
        }

    public void keyTyped(KeyEvent e){}

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE)
            if (GamePanel.GameState == GamePanel.STATES.play) {
                GamePanel.GameState = GamePanel.STATES.pause;
            }
    }


    public void keyReleased(KeyEvent e){
}


    public void mousePressed(MouseEvent e) {

    }


    public void mouseReleased(MouseEvent e) {

    }


    public void mouseEntered(MouseEvent e) {

    }


    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        GamePanel.mouseX = e.getX();
        GamePanel.mouseY = e.getY();
    }

}


