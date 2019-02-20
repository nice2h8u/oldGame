package ru.nicetoh8u.Monsters;



public class Candy extends Monster {

    public Candy(double speed, double hp)
    {
        super(speed, hp,"ru.nicetoh8u.resources.music/Candy/CandyBorn.wav");
        super.pathToImage.add("ru.nicetoh8u.resources.music.animation/Candy/Candy1.png");
        super.pathToImage.add("ru.nicetoh8u.resources.music.animation/Candy/Candy2.png");
        super.pathToImage.add("ru.nicetoh8u.resources.music.animation/Candy/CandyDie.png");
        super.pathToSoundOfDie = "ru.nicetoh8u.resources.music/Candy/CandyDie.wav";

    }

    @Override
    public void useAbility() {

    }
}
