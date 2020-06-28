package fg331.com.Main;

import fg331.com.Display.Display;
import fg331.com.KeyManager.KeyManager;
import fg331.com.State.*;
import fg331.com.GFX.Assets;
import fg331.com.State.*;
import fg331.com.State.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.*;

public class Game extends JPanel implements Runnable {

    public int MAP_WIDTH;
    public int MAP_HEIGHT;
    public final int WIDTH = 48, HEIGHT = 48;
    public final int heightOffset = 0;


    public Display display;
    public KeyManager keyManager;
    public Thread thread;

    public Menu menu;
    public Scores scores;
    public GameState gameState;
    public Settings settings;

    public int[][] map;

    public Block currentBlock;
    public int leftOffset;

    public int pointCounter = 0;

    public Graphics g;
    public Font drawFont = new Font("Arial", Font.BOLD, 45); // fon za pisaneto na ostawa6ti bombo i pri pe4elene
    public boolean running = false;
    public boolean clearSpawn = true;

    public String title;
    public int width, height;
    //    public int rand = (int) (Math.random() * 7) + 1, rand1;  // rand na side, rand1 =/= rand, na draw, sled towa rand = rand1 i now rand1
    public int blockReeper = 0;
    public int autoMove = 0, speed = 0;
    public int[] blocks = {1, 3, 2, 7, 5, 6, 2, 7, 1, 3, 5, 2, 4, 6, 1, 3, 4, 6, 5, 2, 7, 1, 6, 3, 4, 2, 6, 3, 7, 2, 5, 6, 7, 1, 3, 6, 7, 2,
            3, 1, 5, 2, 6, 1, 3, 7, 6, 5, 1, 3, 6, 5, 2, 7, 1, 6, 3, 5, 1, 6, 2, 5, 3, 7, 6, 5, 1, 2, 3, 4, 5, 6, 3, 4, 1, 5, 2, 4, 7, 1, 2,
            5, 4, 1, 2, 3, 7, 1, 2, 4, 7, 1, 5, 3, 4, 6, 7, 2, 5, 1, 7, 2, 3, 4, 5, 1, 3, 6, 4, 5, 2, 7, 4, 5, 2, 6, 3, 7, 4, 2, 3, 1, 5, 6,
            3, 2, 7, 5, 3, 6, 2, 7, 4, 6, 3, 2, 1, 5, 7, 4, 3, 1, 2, 6, 4, 3, 2, 7, 4, 6, 2, 7, 5, 3, 1, 6, 4, 2, 7, 1, 4, 6, 7, 2, 4, 3, 6,
            1, 4, 3, 2, 7, 5, 6, 3, 4, 5, 1, 7, 3, 5, 6, 7, 2, 3, 1, 6, 7, 4, 3, 2, 6, 7, 3, 4, 1, 7, 5, 2, 6,
    };

    Game(String title) {
        this.title = title;
        MAP_HEIGHT = 15;
        MAP_WIDTH = 10;

        height = HEIGHT * MAP_HEIGHT;
        width = MAP_WIDTH * WIDTH * 2;

        leftOffset = width / 4;

        map = new int[MAP_WIDTH][MAP_HEIGHT];
        keyManager = new KeyManager();
    }

    private void init() {
        Assets.init();
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        initSpawn();

        gameState = new GameState(this);
        menu = new Menu(this);
        scores = new Scores(this);
        settings = new Settings(this);

        Runtime.getRuntime().addShutdownHook(new Hook(this));

        menu.mouseSetter();
        State.setCurrentState(menu);
    }

    private void render() {
        BufferStrategy bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        //clear
        g.clearRect(0, 0, width, height);
        //end clear

        //draw
        if (State.getCurrentState() != null)
            State.getCurrentState().render(g);
        //ENDING
        bs.show();
        g.dispose();
    }

    private void tick() {
        if (State.getCurrentState() != null)
            State.getCurrentState().tick();
    }

    private void spawnChecker() {
        switch (blocks[blockReeper]) {
            case 1:
                if (map[4][0] != 0 || map[5][0] != 0 || map[6][0] != 0 || map[6][1] != 0) exiter();
                break;
            case 2:
                if (map[4][0] != 0 || map[5][0] != 0 || map[6][0] != 0 || map[5][1] != 0) exiter();
                break;
            case 3:
                if (map[4][0] != 0 || map[5][0] != 0 || map[6][0] != 0 || map[4][1] != 0) exiter();
                break;
            case 4:
                if (map[4][1] != 0 || map[5][1] != 0 || map[5][0] != 0 || map[6][0] != 0) exiter();
                break;
            case 5:
                if (map[4][0] != 0 || map[5][0] != 0 || map[6][0] != 0 || map[7][0] != 0) exiter();
                break;
            case 6:
                if (map[4][0] != 0 || map[5][0] != 0 || map[4][1] != 0 || map[5][1] != 0) exiter();
                break;
            case 7:
                if (map[4][0] != 0 || map[5][0] != 0 || map[5][1] != 0 || map[6][1] != 0) exiter();
                break;
        }
    }

    public void exiter() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("res/txt/HighScores.txt"));
//            reader = new BufferedReader(new FileReader(Assets.path + "\\HighScores.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            if ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (stringBuilder.toString().equals("")) {
            try (PrintStream out = new PrintStream(new FileOutputStream("res/txt/HighScores.txt"))) {
//            try (PrintStream out = new PrintStream(new FileOutputStream(Assets.path + "\\HighScores.txt"))) {
                out.print(String.valueOf(pointCounter));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (pointCounter > Integer.parseInt(stringBuilder.toString())) {
            try (PrintStream out = new PrintStream(new FileOutputStream("res/txt/HighScores.txt"))) {
//            try (PrintStream out = new PrintStream(new FileOutputStream(Assets.path + "\\HighScores.txt"))) {
                out.print(String.valueOf(pointCounter));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.exit(1);
    }

    public void loadHighScore() {
        BufferedReader reader = null;
        try {
//            reader = new BufferedReader(new FileReader("res/txt/HighScores.txt"));
            reader = new BufferedReader(new FileReader(Assets.path + "\\HighScores.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            if ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (stringBuilder.toString().equals("")) {
//            try (PrintStream out = new PrintStream(new FileOutputStream("res/txt/HighScores.txt"))) {
            try (PrintStream out = new PrintStream(new FileOutputStream(Assets.path + "\\HighScores.txt"))) {
                out.print(String.valueOf(pointCounter));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (pointCounter > Integer.parseInt(stringBuilder.toString())) {
//            try (PrintStream out = new PrintStream(new FileOutputStream("res/txt/HighScores.txt"))) {
            try (PrintStream out = new PrintStream(new FileOutputStream(Assets.path + "\\HighScores.txt"))) {
                out.print(String.valueOf(pointCounter));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    void switching() {
        for (int i = 0; i < 4; i++) {
            if (currentBlock.getX(i) < 0 || currentBlock.getX(i) > MAP_WIDTH || currentBlock.getY(i) < 0 || currentBlock.getY(i) > MAP_HEIGHT)
                exiter();
            map[currentBlock.getX(i)][currentBlock.getY(i)] = currentBlock.getShape();
        }
        currentBlock.points();

        spawnChecker();
        if (clearSpawn) {
            currentBlock = new Block(this, blocks[blockReeper]);
            blockReeper++;
        }
    }

    public void initSpawn() {
        currentBlock = new Block(this, blocks[blockReeper]);
        blockReeper++;
    }

    @Override
    public void run() {
        init();
        int fps = 20;

        while (running) {
                render();
                tick();

            try {
                Thread.sleep(1000/fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop();
    }

    KeyManager getKeyManager() {
        return keyManager;
    }

    synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}