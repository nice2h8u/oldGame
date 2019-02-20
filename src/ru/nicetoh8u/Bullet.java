package ru.nicetoh8u;

import java.awt.*;

public class Bullet {

    public double x;
    public double y;
    private double xEnd;
    private double yEnd;
    private int r;
    private double dx; //исходные
    private double dy;
    private short choise;
    private double speed;
    private double disc;
    private Color color;
    private boolean flag = true;

    public Bullet() {
        x = GamePanel.player.getX();
        y = GamePanel.player.getY();
        r = 2;

        color = Color.WHITE;
        speed = 2;
    }


    public void update() {
        dx = xEnd - x;
        dy = yEnd - y;
        disc = (Math.sqrt(dx * dx + dy * dy));
        y += (dy / disc) * speed;
        x += (dx / disc) * speed;

        if (flag) {
            if (dx > 0 && dy > 0) {
                choise = 1;
                flag = false;
            }
            if (dx > 0 && dy < 0) {
                choise = 2;
                flag = false;
            }
            if (dx < 0 && dy > 0) {
                choise = 3;
                flag = false;

            }
            if (dx <= 0 && dy <= 0) {
                choise = 4;
                flag = false;
            }
        }

        switch (choise) {
            case 1:
                if ((xEnd <= x) || (yEnd <= y)) {
                    GamePanel.bullets.remove(this);
                }
                break;
            case 2:
                if ((xEnd <= x) || (yEnd >= y)) {
                    GamePanel.bullets.remove(this);
                }
                break;
            case 3:
                if ((xEnd >= x) || (yEnd <= y)) {
                    GamePanel.bullets.remove(this);
                }
                break;
            case 4:
                if ((xEnd >= x) || (yEnd >= y)) {
                    GamePanel.bullets.remove(this);
                }
                break;

        }
    }


    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) x-4, (int) y, r, r * 2);
        g.fillOval((int) x+4, (int) y, r, r * 2);
    }

    public void setxEnd(double xEnd) {
        this.xEnd = xEnd;
    }

    public void setyEnd(double yEnd) {
        this.yEnd = yEnd;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


}
