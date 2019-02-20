package ru.nicetoh8u.Monsters;

import ru.nicetoh8u.Audio;
import ru.nicetoh8u.GamePanel;

import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public abstract class Monster {
    protected double x;
    protected double y;
    protected double speed;
    protected double hp;
    protected double dx;
    protected int fps = 0;
    protected double angle = 0;
    protected int num = 0;
    protected double dy;
    protected double disc;
    protected double shift = 90;
    protected int shiftX;
    protected int shiftY;
    protected String pathToSoundOfBorn;
    protected String pathToSoundOfDie;
    protected Audio soundOfBOrn;
    protected Audio soundOfDie;
    protected boolean flag = true;
    protected int temp;
    protected int timeOfPicture = 0;
    protected ArrayList<String> pathToImage = new ArrayList<String>();
    protected int isKill; //поверяет убили ли монстра

    public Monster(double speed, double hp, String pathToSoundOfBorn) {
        int choise = (int) (Math.random() * 4);
        switch (choise) {
            case 0:
                this.x = Math.random() * 1000 + 100;
                this.y = 1;
                if (this.x < 500) {
                    shiftX = 50;
                    shiftY = 0;
                } else if (this.x < 800) {
                    shiftX = 20;
                    shiftY = 10;
                } else {
                    shiftX = 0;
                    shiftY = 50;
                }

                break;
            case 1:
                this.x = Math.random() * 1000 + 100;
                this.y = 699;
                if (this.x < 600) {
                    shiftX = -10;
                    shiftY = -30;
                } else {
                    shiftX = -36;
                    shiftY = 5;
                }
                break;
            case 2:
                this.x = 1;
                this.y = Math.random() * 500 + 100;
                shiftX = +25;
                shiftY = -30;

                break;
            case 3:
                this.x = 1180;
                this.y = Math.random() * 500 + 100;
                shiftX = -25;
                shiftY = 25;
                break;

        }
        this.speed = speed;
        this.hp = hp;
        this.pathToSoundOfBorn = pathToSoundOfBorn;
        this.isKill = 0;
        soundOfBOrn = new Audio(this.pathToSoundOfBorn, 0.8);
        soundOfBOrn.sound();
        soundOfBOrn.setVolume();


    }


    public void move() {
        if (!death()) {

            //useAbility();
            if (flag) {
                temp = check();
                flag = false;
                //shift = Math.random()*110+20;
            }

            switch (temp) {
                case 0:
                    dx = GamePanel.player.getX() + shift - x - 17;
                    dy = GamePanel.player.getY() - y - 25;//right top
                    break;
                case 1:
                    dx = GamePanel.player.getX() - shift - x ; //left bot
                    dy = GamePanel.player.getY() - y - 25;
                    break;
                case 2:
                    dx = GamePanel.player.getX() - x - 17;
                    dy = GamePanel.player.getY() + shift - y - 10;//right bot
                    break;
                case 3:
                    dx = GamePanel.player.getX() - x - 17;//left top
                    dy = GamePanel.player.getY() - shift - y;
                    break;
            }
            disc = (Math.sqrt(dx * dx + dy * dy));

            if (dx > 0) angle = 3.14 - Math.acos(dy / disc);
            if (dx < 0) angle = 3.14 + Math.acos(dy / disc);

            y += (dy / disc) * speed;
            x += (dx / disc) * speed;
            damageToPlayer();
        }

    }

    public void draw(Graphics2D g) {
        //fps
        if (!death()) {
            fps++;
            if (fps >= 25) {
                num++;
                fps = 0;
            }
            if (num == pathToImage.size() - 1)
                num = 0;
            String temp = pathToImage.get(num);
            //
            AffineTransform monsterOriginal = g.getTransform(); // получаем текущее значение картинки
            AffineTransform monsterClone = (AffineTransform) (monsterOriginal.clone()); //копируем текущее значение
            monsterClone.rotate(angle, x + shiftX, y + shiftY);
            g.setTransform(monsterClone);
            g.drawImage(new ImageIcon(temp).getImage(), (int) x + shiftX, (int) y + shiftY, null);
            g.setTransform(monsterOriginal);

        } else {
            String temp = pathToImage.get(2);
            g.drawImage(new ImageIcon(temp).getImage(), (int) x-25, (int) y-25, null);
            timeOfPicture++;
            if (timeOfPicture > 25)
                GamePanel.monsters.remove(this);
        }
    }

    public abstract void useAbility();

    public boolean death() {

        if (hp <= 0) {
            if (timeOfPicture == 0) {
                soundOfDie = new Audio(pathToSoundOfDie, 0.75);
                soundOfDie.sound();
                soundOfDie.setVolume();
                GamePanel.playerPoints++;
            }
            return true;
        }
        return false;
    }

    public void hit() {
        hp--;
    }

    public void damageToPlayer() {
        //left bot
        if (!death()) {
            if (((GamePanel.player.getX() - shift -1) >= x) && ((GamePanel.player.getX() - shift - 2) < x))
                if (((GamePanel.player.getY() - 70) < y) && ((GamePanel.player.getY() + 70) > y)) {
                    GamePanel.player.setHp(GamePanel.player.getHp() - 1);
                    hp = 0;
                    GamePanel.playerPoints--;
                }

            //rigth bot
            if (((GamePanel.player.getY() + shift - 12) < y) && ((GamePanel.player.getY() + shift - 8) > y))
                if (((GamePanel.player.getX() - 70) < x) && ((GamePanel.player.getX() + 70) > x)) {
                    GamePanel.player.setHp(GamePanel.player.getHp() - 1);
                    hp = 0;
                    GamePanel.playerPoints--;
                }

            //rigth top
            if (((GamePanel.player.getY() - 70) < y) && ((GamePanel.player.getY() + 70) > y))
                if (((GamePanel.player.getX() + shift - 14) > x) && ((GamePanel.player.getX() + shift - 20) < x)) {
                    GamePanel.player.setHp(GamePanel.player.getHp() - 1);
                    hp = 0;
                    GamePanel.playerPoints--;
                }

            //left top
            if (((GamePanel.player.getY() - shift - 28) < y) && ((GamePanel.player.getY() - shift - 21) > y))
                if (((GamePanel.player.getX() - 70) < x) && ((GamePanel.player.getX() + 70) > x)) {
                    GamePanel.player.setHp(GamePanel.player.getHp() - 1);
                    hp = 0;
                    GamePanel.playerPoints--;
                }
        }
    }

    public double getX() {
        return x;
    }

    public int check() {
        double temp;
        int num = 0;
        dx = GamePanel.player.getX() + shift - x - 17;
        dy = GamePanel.player.getY() - y - 25;//right top
        disc = (Math.sqrt(dx * dx + dy * dy));
        temp = disc;
        dx = GamePanel.player.getX() - shift - x ; //left bot
        dy = GamePanel.player.getY() - y - 25;
        disc = (Math.sqrt(dx * dx + dy * dy));
        if (temp > disc) {
            temp = disc;
            num = 1;
        }
        dx = GamePanel.player.getX() - x - 17;
        dy = GamePanel.player.getY() + shift - y - 10;//right bot
        disc = (Math.sqrt(dx * dx + dy * dy));
        if (temp > disc) {
            temp = disc;
            num = 2;
        }
        dx = GamePanel.player.getX() - x - 17;//left top
        dy = GamePanel.player.getY() - shift - y ;
        disc = (Math.sqrt(dx * dx + dy * dy));
        if (temp > disc) {
            temp = disc;
            num = 3;
        }
        return num;
    }

    public double getY() {
        return y;
    }
}
