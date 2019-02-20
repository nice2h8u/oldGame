package ru.nicetoh8u;

//main class
import ru.nicetoh8u.Monsters.*;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Timer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {
    //FIELDS
    public static int WIDTH = 1200;
    public static int HEIGHT = 700;

    private Thread thread;
    private boolean running;
    public static int playerPoints;
    public static double mouseX;
    public static double mouseY;
    private BufferedImage image;
    private Graphics2D g;
    private int sleepTime = 0;
    private long timerFps;
    private double miliSecToFps;
    private int monsterGenerator;
    public static String playerName = "Player Name";

    private Audio mainSound;
///for level of game
    private int timer = 499;
    private int f=2;
    public static int countOfMonster =1;
    private int timerForMonsterGeneration = 500;
    private int ostatok = 5;
    public static double coeficientOfLevel = 1;
    //menu
    public static enum STATES{menu,play,pause};
    public static STATES GameState;
///
    public static boolean settings=false;
    public static GameBack background;
    public static Player player;
    public static Menu menu;
    public static boolean newGame=false;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Monster> monsters;
    public static List<String[]> usersInfo;
    // CONSTRUCTOR
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        GameState = STATES.menu;
        requestFocus();
        addMouseListener(new Listeners());
        addMouseMotionListener(new Listeners());
        addKeyListener(new Listeners());
    }

    // FUNCTIONS
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void run() {

        running = true;

        miliSecToFps = 1000 / 30;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//сглаживание

        mainSound = new Audio("ru.nicetoh8u.resources.music/MainSound.wav", 0.55);
        mainSound.play();
        mainSound.setVolume();
        mainSound.repeat();

        background = new GameBack();

        player = new Player();
        menu = new Menu();
        bullets = new ArrayList<Bullet>();
        monsters = new ArrayList<Monster>();

        Scanner sc = null;
        try {
            sc = new Scanner(new File("src/records.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         usersInfo = new ArrayList<String[]>();
        while(sc.hasNext()){
            String line = sc.nextLine();
            String[] playerData = line.split(":");
            usersInfo.add(playerData);
        }





        // GAME LOOP
        while (running) {
            if (newGame)
            {
                background = new GameBack();
                player = new Player();
                menu = new Menu();
                bullets = new ArrayList<Bullet>();
                monsters = new ArrayList<Monster>();
                newGame=false;
                timer = 499;
                timerForMonsterGeneration=500;
                coeficientOfLevel = 1;
                playerPoints = 0;
                ostatok=5;


            }
            if (GameState == STATES.menu) {
                background.draw(g);
                menu.draw(g);
               gameDraw();
            }
            if (GameState == STATES.pause) {
                background.draw(g);
                menu.draw(g);

                gameDraw();
            }
            if (GameState == STATES.play){
                timerFps = System.nanoTime();
                gameUpdate();
                gameRender();
                gameDraw();

                timerFps = (System.nanoTime() - timerFps) / 1000000;
                if (miliSecToFps > timerFps)
                    sleepTime = (int) (miliSecToFps - timerFps);
                else sleepTime = 1;
                try {
                    thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timerFps = 0;
                sleepTime = 1;
            }


        }
    }

    private void gameUpdate() {
        background.update();
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
        }
        player.update();
        timer++;
        if (timer == timerForMonsterGeneration) {
            if ((playerPoints % (ostatok +f) == 0) && (playerPoints != 0)) {
                f += f;
                countOfMonster++;
            }
        }
        if ((playerPoints%ostatok == 0) && (playerPoints !=0))
        {
            if (timerForMonsterGeneration!=100)
                timerForMonsterGeneration -= 50;
            if (timer>timerForMonsterGeneration)
                timer=timerForMonsterGeneration;
            ostatok+=5;
                if (coeficientOfLevel !=3.5)
            coeficientOfLevel += 0.5;
        }

        //monster generator
        if (timer == timerForMonsterGeneration) {//chese cake snizu cadnr snicz candy
            for(int i=0;i<countOfMonster;i++) {
                monsterGenerator = (int) (Math.random() * 4);
                switch (monsterGenerator) {
                    case 0:
                        monsters.add(new Donat(0.3 * coeficientOfLevel, (int) (3 * coeficientOfLevel*1.5)));
                        break;
                    case 1:
                        monsters.add(new Candy(1.2 * coeficientOfLevel, (int) (1 * coeficientOfLevel*1.5)));
                        break;
                    case 2:
                        monsters.add(new CheeseCake(0.7 * coeficientOfLevel, (int) (2 * coeficientOfLevel*1.5)));
                        break;
                    case 3:
                        monsters.add(new Lolipop(0.5 * coeficientOfLevel, (int) (2.5 * coeficientOfLevel*1.5 )));
                        break;
                }
            }
            timer = 0;

        }

        if (monsters.size() != 0) {
            for (int i = 0; i < monsters.size(); i++)
                monsters.get(i).move();
        }

        for (int i = 0; i < monsters.size(); i++) {

            for (int j = 0; j < bullets.size(); j++) {
                double dx = monsters.get(i).getX() - bullets.get(j).getX();
                double dy = monsters.get(i).getY() - bullets.get(j).getY();
                double disc = Math.sqrt(dx * dx + dy * dy);
                if ((int) disc < 2 + 32) { //радиусы пули и монстра
                    monsters.get(i).hit();
                    bullets.remove(j);
                    break;//сомнительно
                }
            }
//
        }


    }

    private void gameRender() {
        background.draw(g);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g);
        }

        player.draw(g);



        for (int i = 0; i < monsters.size(); i++)
            monsters.get(i).draw(g);

    }

    private void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }
}

