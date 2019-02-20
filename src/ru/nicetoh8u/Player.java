package ru.nicetoh8u;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player {

    //field
    private double x, y;
    private int hp;
    private Image playerImage;
    private Image downFlorImage;
    private Image middleFLorImage;
    private double angle;
    private double middleAngle;
    private double downAngle;
    private double dx, dy, disc;
    public Player() {
        x = GamePanel.WIDTH / 2+6;
        y = GamePanel.HEIGHT / 2+4;
        hp = 20;
        downFlorImage = new ImageIcon("src/ru.nicetoh8u.resources.music.animation/player/down.png").getImage();
        middleFLorImage = new ImageIcon("src/ru.nicetoh8u.resources.music.animation/player/middle.png").getImage();
        playerImage = new ImageIcon("src/ru.nicetoh8u.resources.music.animation/player/pushka.png").getImage();
    }

    //functions

    public void update() {
        if (hp <= 0) {
            JOptionPane.showMessageDialog(null, "Game Over ;(");
            System.exit(1);
        }
        dx = GamePanel.mouseX - x;
        dy = y - GamePanel.mouseY;
        disc = Math.sqrt(dx * dx + dy * dy);
        if (dx > 0) angle = Math.acos(dy / disc);
        if (dx < 0) angle = -Math.acos(dy / disc);

    }

    public void hit() {
        hp--;
    }

    public void die() {

    }


    public void draw(Graphics2D g) {
        //down
        if (downAngle >= -Math.toRadians(720))
            downAngle -= 0.05;
        else downAngle = 0;
        AffineTransform downOriginal = g.getTransform(); // получаем текущее значение картинки
        AffineTransform downClone = (AffineTransform) (downOriginal.clone()); //копируем текущее значение
        downClone.rotate(downAngle, x, y);
        g.setTransform(downClone);
        g.drawImage(downFlorImage, (int) this.getX()-42 , (int) this.getY()-46 , null);
        g.setTransform(downOriginal);
        //middle
        if (middleAngle >= -Math.toRadians(720))
            middleAngle += 0.05;
        else middleAngle = 0;
        AffineTransform middleOriginal = g.getTransform(); // получаем текущее значение картинки
        AffineTransform middleClone = (AffineTransform) (middleOriginal.clone()); //копируем текущее значение
        middleClone.rotate(middleAngle, x, y);
        g.setTransform(middleClone);
        g.drawImage(middleFLorImage, (int) this.getX()-32 , (int) this.getY()-32 , null);
        g.setTransform(middleOriginal);
        //pushka
        AffineTransform monsterOriginal = g.getTransform(); // получаем текущее значение картинки
        AffineTransform monsterClone = (AffineTransform) (monsterOriginal.clone()); //копируем текущее значение
        monsterClone.rotate(angle, x, y);
        g.setTransform(monsterClone);
        g.drawImage(playerImage, (int) this.getX()-14 , (int) this.getY() - 52, null);
        g.setTransform(monsterOriginal);
        Color color = new Color(1, 1, 1);
        g.setColor(color);
        g.drawString(GamePanel.playerName, GamePanel.WIDTH / 2 -25, GamePanel.HEIGHT / 2 -40);
        g.drawString(getHp() + "/20", GamePanel.WIDTH / 2 -10, GamePanel.HEIGHT / 2 + 55);


    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

}

