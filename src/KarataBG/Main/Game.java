package KarataBG.Main;

import KarataBG.Display.Display;
import KarataBG.GFX.Assets;
import KarataBG.KeyManager.KeyManager;
import KarataBG.State.*;
import KarataBG.State.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Game extends JPanel implements Runnable {

    public final int WIDTH = 36, HEIGHT = 36;
    public final int heightOffset = 0;
    public final int heightOffsetButtons = 80;
    public final int topOffset;
    public int MAP_WIDTH;
    public int MAP_HEIGHT;
    public Display display;
    public KeyManager keyManager;
    public Thread thread;
    public Menu menu;
    public Scores score;
    public GameState gameState;
    public Settings settings;
    public End end;
    public HighScore highScore;
    public int[][] map;
    public ArrayList<Integer> scores = new ArrayList<>();
    public Block currentBlock;
    public int leftOffset;
    public int pointCounter = 0;
    public int difficulty;
    public int maxDifficulty = 5;
    public Graphics g;
    public BufferStrategy bs;
    public Font drawFont = new Font("Arial", Font.BOLD, 45); // fon za pisaneto na ostawa6ti bombo i pri pe4elene
    public Font drawFontHelvetica = new Font("Helvetica", Font.BOLD | Font.ITALIC, 60); // fon za pisaneto na ostawa6ti bombo i pri pe4elene
    public boolean running = false;
    public boolean clearSpawn = true;
    public String highScoreGameState;
    public String title;
    public int width, height;
    //    public int rand = (int) (Math.random() * 7) + 1, rand1;  // rand na side, rand1 =/= rand, na draw, sled towa rand = rand1 i now rand1
    public int blockReeper = 0;
    public int autoMove = 0, speed = 0;
    public int[] blocks = {1, 3, 2, 7, 5, 6, 2, 7, 1, 3, 5, 2, 4, 6, 1, 3, 4, 6, 5, 2, 7, 1, 6, 3, 4, 2, 6, 3, 7, 2, 5, 6, 7, 1, 3, 6, 7, 2,
            3, 1, 5, 2, 6, 1, 3, 7, 6, 5, 1, 3, 6, 5, 2, 7, 1, 6, 3, 5, 1, 6, 2, 5, 3, 7, 6, 5, 1, 2, 3, 4, 5, 6, 3, 4, 1, 5, 2, 4, 7, 1, 2,
            5, 4, 1, 2, 3, 7, 1, 2, 4, 7, 1, 5, 3, 4, 6, 7, 2, 5, 1, 7, 2, 3, 4, 5, 1, 3, 6, 4, 5, 2, 7, 4, 5, 2, 6, 3, 7, 4, 2, 3, 1, 5, 6,
            3, 2, 7, 5, 3, 6, 2, 7, 4, 6, 3, 2, 1, 5, 7, 4, 3, 1, 2, 6, 4, 3, 2, 7, 4, 6, 2, 7, 5, 3, 1, 6, 4, 2, 7, 1, 4, 6, 7, 2, 4, 3, 6,
            1, 4, 3, 2, 7, 5, 6, 3, 4, 5, 1, 7, 3, 5, 6, 7, 2, 3, 1, 6, 7, 4, 3, 2, 6, 7, 3, 4, 1, 7, 5, 2, 6, 1
    };

    Game(String title) {
        this.title = title;
        MAP_HEIGHT = 20;
        MAP_WIDTH = 10;

        height = HEIGHT * (MAP_HEIGHT + 3);
        width = MAP_WIDTH * WIDTH * 2;

        leftOffset = width / 4;
        topOffset = HEIGHT * 3;

        map = new int[MAP_WIDTH][MAP_HEIGHT];
        keyManager = new KeyManager();

        scoreboardCheck();
    }

    private void init() {
        Assets.init();
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);

        gameState = new GameState(this);
        menu = new Menu(this);
        score = new Scores(this);
        settings = new Settings(this);
        end = new End(this);
        highScore = new HighScore(this);

        Runtime.getRuntime().addShutdownHook(new Hook(this));

        menu.mouseSetter();
        State.setCurrentState(menu);

        for (int i = 0; i < 6; i++) {
            render();
        }
    }

    public void render() {
        bs = display.getCanvas().getBufferStrategy();
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

    public void renderPause() {
        bs = display.getCanvas().getBufferStrategy();
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

        if (keyManager.space) {
            String text = "PAUSED";
            g.setFont(drawFont);
            int width = getFontMetrics(drawFont).stringWidth(text);
            int height = getFontMetrics(drawFont).getHeight();
            g.drawString(text, this.width / 2 - width / 2, this.height / 2 - height / 2);
        }

        //ENDING
        bs.show();
        g.dispose();

    }

    private void tick() {
//        System.out.println(State.getCurrentState() != null);
//        System.out.println(!keyManager.space);
//        System.out.println();
        if (State.getCurrentState() != null && !keyManager.space) {
            State.getCurrentState().tick();
        } else renderPause();
    }

    private void spawnChecker() {
//        switch (blocks[blockReeper]) {
//            case 1:
//                if (map[4][0] != 0 || map[5][0] != 0 || map[6][0] != 0 || map[6][1] != 0) exiter();
//                break;
//            case 2:
//                if (map[4][0] != 0 || map[5][0] != 0 || map[6][0] != 0 || map[5][1] != 0) exiter();
//                break;
//            case 3:
//                if (map[4][0] != 0 || map[5][0] != 0 || map[6][0] != 0 || map[4][1] != 0) exiter();
//                break;
//            case 4:
//                if (map[4][1] != 0 || map[5][1] != 0 || map[5][0] != 0 || map[6][0] != 0) exiter();
//                break;
//            case 5:
//                if (map[4][0] != 0 || map[5][0] != 0 || map[6][0] != 0 || map[7][0] != 0) exiter();
//                break;
//            case 6:
//                if (map[4][0] != 0 || map[5][0] != 0 || map[4][1] != 0 || map[5][1] != 0) exiter();
//                break;
//            case 7:
//                if (map[4][0] != 0 || map[5][0] != 0 || map[5][1] != 0 || map[6][1] != 0) exiter();
//                break;
//        } // TODO мръсно
    }

    public void exiter() {
        //TODO взима трудността и чете съответния ред от файла
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("res/txt/HighScores.txt"));
//            reader = new BufferedReader(new FileReader(Assets.path + "\\HighScores.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        String pastScore = null;
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();

        try {
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                if (lineNum < difficulty)
                    stringBuilder.append(line).append("\n");
                else if (lineNum == difficulty) {
                    if (difficulty != 0)
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    pastScore = line;
                } else if (difficulty != maxDifficulty)
                    stringBuilder1.append(line).append("\n");

                lineNum++;
            }
            if (difficulty != maxDifficulty) {
                stringBuilder1.deleteCharAt(stringBuilder1.length() - 1);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        pointCounter = 19999;

        //не е това изчезващия проблем
        try (PrintStream out = new PrintStream(new FileOutputStream("res/txt/HighScores.txt"))) { //TODO силни проверки за кога трябва и кога не трябва да има нов ред при принтене
            //ако резултата се принти в средата трябва да има нов ред
            //ако след резултата има текст то той трябва да е без последен нов ред, но без нищо в края
//            try (PrintStream out = new PrintStream(new FileOutputStream(Assets.path + "\\HighScores.txt"))) {
            if (difficulty != 0)
                out.println(stringBuilder); //TODO тук е проблема
            if (pastScore.equals("") || pointCounter > Integer.parseInt(pastScore)) {
                if (difficulty != maxDifficulty)
                    out.println(pointCounter);
                else
                    out.print(pointCounter);
            } else if (difficulty != maxDifficulty) {
                out.println(pastScore);
            } else {
                out.print(pastScore);
            }
            out.print(stringBuilder1);

            out.flush();
            out.close();
//        } else if (pointCounter > Integer.parseInt(stringBuilder.toString())) {
//            try (PrintStream out = new PrintStream(new FileOutputStream("res/txt/HighScores.txt"))) {
////            try (PrintStream out = new PrintStream(new FileOutputStream(Assets.path + "\\HighScores.txt"))) {
//                out.print(String.valueOf(pointCounter));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        gameState.mouseRemover();
        end.mouseSetter();
        State.setCurrentState(end);
        render();
    }

    private void scoreboardCheck() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("res/txt/HighScores.txt"));
//            reader = new BufferedReader(new FileReader(Assets.path + "\\HighScores.txt"));
        } catch (FileNotFoundException e) {
            File file = new File(Assets.path + "res/txt/HighScores.txt");
            try {
                file.createNewFile();
                reader = new BufferedReader(new FileReader("res/txt/HighScores.txt"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        int count = 0;
        StringBuilder builder = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                count++;
                String[] l = line.split(" ");
//                System.out.println(count + " count - l[0] " + Integer.parseInt(l[0]));
                if (count < Integer.parseInt(l[0])){
                    int limit =  Integer.parseInt(l[0]) - count;
                    for (int i = 0; i < limit; i++) {
                        builder.append(count).append(" ").append(0).append("\n");
//                        System.out.println("builder \n" + builder.toString());
                        count++;
                    }
//                    System.out.println("AAAAAAAAAA");
                    builder.append(line).append("\n");
                }else builder.append(line);
            }
            System.out.println("MAX" + maxDifficulty + " " + count);
            int limit = maxDifficulty - count;
            for (int i = 0; i < limit; i++) {
                count++;
                builder.append(count).append(" ").append(0).append("\n");
//                System.out.println("AAAAAAAAAbuilder \n" + builder.toString());
            }
            System.out.println(builder);
            reader.close();

        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }

        count = maxDifficulty - count;
        System.out.println(maxDifficulty - count);

//        if (count != 0) {
//            StringBuilder stringBuilder = new StringBuilder();
            try (PrintStream out = new PrintStream(new FileOutputStream("res/txt/HighScores.txt", false))) {
////            try (PrintStream out = new PrintStream(new FileOutputStream(Assets.path + "\\HighScores.txt"))) {
//                for (int i = 0; i < count; i++) {
//                    System.out.println(i);
//                    stringBuilder.append("0");
//                    stringBuilder.append("\n");
//                }
//
//                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//                if (count != 4)
//                    out.println();
//                out.print(stringBuilder.toString());
                out.print(builder.toString());
//
                out.flush();
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
//        }

        try {
            // взимане на резултата //
            reader.close();

            reader = new BufferedReader(new FileReader("res/txt/HighScores.txt"));
            //            reader = new BufferedReader(new FileReader(Assets.path + "\\HighScores.txt"));
            String line1;
            while ((line1 = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line1.split(" ")[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadHighScore() { //TODO да хване трудност параметъра трябва
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

//        if (stringBuilder.toString().equals("")) {
////            try (PrintStream out = new PrintStream(new FileOutputStream("res/txt/HighScores.txt"))) {
//            try (PrintStream out = new PrintStream(new FileOutputStream(Assets.path + "\\HighScores.txt"))) {
//                out.print(String.valueOf(pointCounter));
//                out.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }

//        if (pointCounter > Integer.parseInt(stringBuilder.toString())) {
////            try (PrintStream out = new PrintStream(new FileOutputStream("res/txt/HighScores.txt"))) {
//            try (PrintStream out = new PrintStream(new FileOutputStream(Assets.path + "\\HighScores.txt"))) {
//                out.print(String.valueOf(pointCounter));
//                out.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
    }

    void switching() {
        for (int i = 0; i < 4; i++) {
            if (currentBlock.getX(i) < 0 || currentBlock.getX(i) > MAP_WIDTH || currentBlock.getY(i) < 0 || currentBlock.getY(i) > MAP_HEIGHT)
                exiter();
            else
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

    public void getHighScore() {
        try {
//            BufferedReader reader = new BufferedReader(new FileReader(Assets.path + "\\HighScores.txt"));
            BufferedReader reader = new BufferedReader(new FileReader("res/txt/HighScores.txt"));
            for (int i = 0; i < difficulty; i++) {
                reader.readLine();
            }
            highScoreGameState = reader.readLine();

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();
        int fps = 20;

        while (running) {
//            render(); //TODO ако искаш оптимизирай да се рисува на друга нишка само когато се промени нещо
            tick();

            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        stop();
    }

    public KeyManager getKeyManager() {
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
