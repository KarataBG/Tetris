package fg331.com.State;

import fg331.com.Main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HighScore extends State {

    private final int width = 180;
    private final int height = 60;
    private final String[] difficultyNames = {"Easy", "Inter", "Hard", "Insane", "Back"};

    Game game;
    int startX, startY, heightOffset;
    private final MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getX() > startX && e.getX() < startX + width * 2 && e.getY() > startY + heightOffset * 4 && e.getY() < startY + heightOffset * 4 + height) { //custom speed
                mouseRemover();
                game.menu.mouseSetter();
                setCurrentState(game.menu);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    public HighScore(Game game) {
        this.game = game;

        startX = game.MAP_WIDTH * game.WIDTH / 8;
        startY = game.MAP_HEIGHT * game.WIDTH / 12;
        heightOffset = game.heightOffsetButtons;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setFont(game.drawFont);

        for (int i = 0; i < difficultyNames.length; i++) {
            g.setColor(Color.GRAY);
            g.fillRect(startX, startY + heightOffset * i, width * 2, height);
            g.setColor(Color.BLACK);
            if (i <= game.maxDifficulty)
                g.drawString(difficultyNames[i] + ": " + String.valueOf(game.scores.get(i)), startX + width / 8, startY + height * 2 / 3 + heightOffset * i);
            else
                g.drawString(difficultyNames[i], startX + width / 8, startY + height * 2 / 3 + heightOffset * i);
        }
    }

    public void mouseSetter() {
        game.display.getCanvas().addMouseListener(mouseListener);
    }

    public void mouseRemover() {
        game.display.getCanvas().removeMouseListener(mouseListener);
    }
}
