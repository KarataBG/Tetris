package fg331.com.State;

import fg331.com.Display.Display;
import fg331.com.GFX.Assets;
import fg331.com.Main.Game;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Scores extends State {
    private Game game;
    private String[] randomName;
    private String[][] HSRows;

    public Scores(Game game) {
        this.game = game;
    }

    public void screenSetter() {
        fileReader();
        final String[] columns = {"Score"};
        final JTable pane = new JTable(new DefaultTableModel(HSRows, columns));

        game.display.getFrame().setVisible(false);

        JFrame jFrame = new JFrame();
        jFrame.setSize(500, 300);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton jButton = new JButton("Menu");
        jButton.addActionListener(e -> {
            setCurrentState(game.menu);
            jFrame.setVisible(false);
            game.display = new Display(game.title, game.width, +game.height);
            game.menu.mouseSetter();
        });

        jFrame.getContentPane().setLayout(new BorderLayout());
        jFrame.getContentPane().add(new JScrollPane(pane), BorderLayout.CENTER);
        jFrame.getContentPane().add(jButton, BorderLayout.SOUTH);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    private void fileReader() {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Assets.path + "\\" + "HighScores.txt"));
//            BufferedReader bufferedReader = new BufferedReader(new FileReader("res/txt/HighScores.txt"));
            String line = bufferedReader.readLine();
            stringBuilder.append(line);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HSRows = new String[1][4]; // 50   4

//        for (int k = 0; k < tempString.length / 4; k++) {
//            System.arraycopy(tempString, k * 4, HSRows[k], 0, 4);
//        }
        HSRows[0][0] = stringBuilder.toString();
        randomName = stringBuilder.toString().split("\\s+");
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    public void mouseSetter() {
    }

    public void mouseRemover() {
    }
}
