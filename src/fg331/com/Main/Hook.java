package fg331.com.Main;

import fg331.com.GFX.Assets;

import java.io.*;

public class Hook extends Thread implements Runnable {

    private Game game;

    public Hook(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
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
                out.print(String.valueOf(game.pointCounter));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (game.pointCounter > Integer.parseInt(stringBuilder.toString())) {
//            try (PrintStream out = new PrintStream(new FileOutputStream("res/txt/HighScores.txt"))) {
            try (PrintStream out = new PrintStream(new FileOutputStream(Assets.path + "\\HighScores.txt"))) {
                out.print(String.valueOf(game.pointCounter));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
