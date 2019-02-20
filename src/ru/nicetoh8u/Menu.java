package ru.nicetoh8u;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
public class Menu {
    private Color color;
    private int x;
    private int y;
    private int n=4;
    private Image temp;
    ArrayList list = new ArrayList();


    public Menu(){
        x=550;
        y=200;
        color = new Color(1,1,1);

        list.add("src/ru.nicetoh8u.resources.music.animation/Menu/newGame1.png");
        list.add("src/ru.nicetoh8u.resources.music.animation/Menu/records1.png");
        list.add("src/ru.nicetoh8u.resources.music.animation/Menu/settings1.png");
        list.add("src/ru.nicetoh8u.resources.music.animation/Menu/exit1.png");
        list.add("src/ru.nicetoh8u.resources.music.animation/Menu/restartGame1.png");
        list.add("src/ru.nicetoh8u.resources.music.animation/Menu/simpleGame1.png");
        list.add("src/ru.nicetoh8u.resources.music.animation/Menu/hardGame1.png");
        list.add("src/ru.nicetoh8u.resources.music.animation/Menu/changeName1.png");
        list.add("src/ru.nicetoh8u.resources.music.animation/Menu/newGame2.png");
        list.add("src/ru.nicetoh8u.resources.music.animation/Menu/records2.png");
        list.add("src/ru.nicetoh8u.resources.music.animation/Menu/settings2.png");
        list.add("src/ru.nicetoh8u.resources.music.animation/Menu/exit2.png");
        list.add("src/ru.nicetoh8u.resources.music.animation/Menu/restartGame2.png");
    }

    public void draw(Graphics2D g){


        for (int i=0;i<n;i++ ){
            temp = new ImageIcon(list.get(i).toString()).getImage();

            g.drawImage(temp,x,y,null);
            y+=90;
        }
        y=200;
        if (GamePanel.GameState == GamePanel.STATES.pause)
        {
            temp = new ImageIcon(list.get(4).toString()).getImage();

            g.drawImage(temp,x,y-90,null);
        }
        if (GamePanel.settings){
            temp = new ImageIcon(list.get(5).toString()).getImage();
            g.drawImage(temp,x+150,y+150,null);
            temp = new ImageIcon(list.get(6).toString()).getImage();
            g.drawImage(temp,x+150,y+220,null);
            temp = new ImageIcon(list.get(7).toString()).getImage();
            g.drawImage(temp,x+150,y+290,null);

        }
        }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    }
