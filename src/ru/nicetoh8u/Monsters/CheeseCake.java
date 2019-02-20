package ru.nicetoh8u.Monsters;

  public class CheeseCake extends Monster {


        public CheeseCake(double speed, double hp)
        {
            super(speed, hp,"src/ru.nicetoh8u.resources.music/CheeseCake/CheeseCakeBorn.wav");
            super.pathToImage.add("src/ru.nicetoh8u.resources.music.animation/CheeseCake/CheeseCake1.png");
            super.pathToImage.add("src/ru.nicetoh8u.resources.music.animation/CheeseCake/CheeseCake2.png");
            super.pathToImage.add("src/ru.nicetoh8u.resources.music.animation/CheeseCake/CheeseCakeDie.png");
            super.pathToSoundOfDie = "src/ru.nicetoh8u.resources.music/CheeseCake/CheeseCakeDie.wav";


        }

        @Override
        public void useAbility() {

        }
    }


