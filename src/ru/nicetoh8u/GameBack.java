package ru.nicetoh8u;


import javax.swing.*;
import java.awt.*;

public class GameBack {
    Image gameBackImage,gameMenuImage, gamePauseImage;


    //constructor
    public GameBack() {

        gameBackImage = new ImageIcon("src/ru.nicetoh8u.resources.music.animation/Backgrounds/background3.png").getImage();
        gameMenuImage = new ImageIcon("src/ru.nicetoh8u.resources.music.animation/Backgrounds/background1.png").getImage();
        gamePauseImage = new ImageIcon("src/ru.nicetoh8u.resources.music.animation/Backgrounds/background2.png").getImage();
    }

    public void update() {

    }

    public void draw(Graphics2D g) {
        if(GamePanel.GameState == GamePanel.STATES.play) {
            g.drawImage(gameBackImage, 0, 0, null);
        }

            if(GamePanel.GameState == GamePanel.STATES.menu)
            {
                g.drawImage(gameMenuImage, 0, 0, null);
        }
        if(GamePanel.GameState == GamePanel.STATES.pause)
        {
            g.drawImage(gamePauseImage, 0, 0, null);
        }
        Color color = new Color(1, 1, 1);
        g.setColor(color);
        g.drawString("Player points " + GamePanel.playerPoints, 20, 20);
    }

    }

