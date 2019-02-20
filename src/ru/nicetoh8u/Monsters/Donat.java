package ru.nicetoh8u.Monsters;



public class Donat extends Monster   {
    //


    //constructor
    public Donat(double speed,double hp) {
        super(speed, hp,"src/ru.nicetoh8u.resources.music/Donat/DonatBorn.wav");
        super.pathToImage.add("src/ru.nicetoh8u.resources.music.animation/Donat/donat1.png");
        super.pathToImage.add("src/ru.nicetoh8u.resources.music.animation/Donat/donat2.png");
        super.pathToImage.add("src/ru.nicetoh8u.resources.music.animation/Donat/donatDie.png");
        super.pathToSoundOfDie = "src/ru.nicetoh8u.resources.music/Donat/DonatDie.wav";

    }

    //methods



    public void useAbility(){

    }
}