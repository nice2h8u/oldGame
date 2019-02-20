package ru.nicetoh8u.Monsters;




import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Lolipop extends Monster {

    private double angle;

    public Lolipop(double speed, double hp) {
        super(speed, hp, "src/ru.nicetoh8u.resources.music/Lolipop/LolipopBorn.wav");
        super.pathToImage.add("src/ru.nicetoh8u.resources.music.animation/Lolipop/Lolipop1.png");
        super.pathToImage.add("src/ru.nicetoh8u.resources.music.animation/Lolipop/Lolipop2.png");
        super.pathToImage.add("src/ru.nicetoh8u.resources.music.animation/Lolipop/LolipopDie.png");
        super.pathToSoundOfDie = "src/ru.nicetoh8u.resources.music/Lolipop/LolipopDie.wav";

    }

    public void draw(Graphics2D g) {
        if (!death()) {
            if (angle >= -Math.toRadians(720))
                angle -= 0.05;
            else angle = 0;
            if (num == pathToImage.size())
                num = 0;
            String temp = pathToImage.get(num);
            //
            AffineTransform monsterOriginal = g.getTransform(); // получаем текущее значение картинки
            AffineTransform monsterClone = (AffineTransform) (monsterOriginal.clone()); //копируем текущее значение
            monsterClone.rotate(angle, x, y);
            g.setTransform(monsterClone);
            g.drawImage(new ImageIcon(temp).getImage(), (int) x - 25, (int) y - 25, null);
            g.setTransform(monsterOriginal);
           // g.setColor(Color.red);
            //g.fillOval((int) x, (int) y, 4, 4);
        } else {
            String temp = pathToImage.get(2);
            g.drawImage(new ImageIcon(temp).getImage(), (int) x-25, (int) y-25, null);
                 timeOfPicture++;
            if (timeOfPicture > 25)
               ru.nicetoh8u.GamePanel.monsters.remove(this);
        }
    }

    @Override
    public void useAbility() {

    }
}
