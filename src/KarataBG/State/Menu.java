package KarataBG.State;

import KarataBG.Main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu extends State {
    private final int width = 180, height = 60;
    private final int startX, startY, heightOffset;
    private final String[] presetNames = {"Easy Preset", "Inter Preset", "Hard Preset", "Insane Preset", "Omega Preset", "HighScore"};
    Game game;
    private final MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getX() > startX && e.getX() < startX + width * 2 && e.getY() > startY && e.getY() < startY + height) {
                game.speed = 20;
                game.autoMove = 0;
                game.difficulty = 0;
                updater();
            } else if (e.getX() > startX && e.getX() < startX + width * 2 && e.getY() > startY + heightOffset && e.getY() < startY + heightOffset + height) {
                game.speed = 14;
                game.autoMove = 0;
                game.difficulty = 1;
                updater();
            } else if (e.getX() > startX && e.getX() < startX + width * 2 && e.getY() > startY + heightOffset * 2 && e.getY() < startY + heightOffset * 2 + height) {
                game.speed = 8;
                game.autoMove = 0;
                game.difficulty = 2;
                updater();
            } else if (e.getX() > startX && e.getX() < startX + width * 2 && e.getY() > startY + heightOffset * 3 && e.getY() < startY + heightOffset * 3 + height) {
                game.speed = 5;
                game.autoMove = 0;
                game.difficulty = 3;
                updater();
            } else if (e.getX() > startX && e.getX() < startX + width * 2 && e.getY() > startY + heightOffset * 4 && e.getY() < startY + heightOffset * 4 + height) { //custom speed
                game.speed = 2;
                game.autoMove = 0;
                game.difficulty = 4;
                updater();
            } else if (e.getX() > startX && e.getX() < startX + width * 2 && e.getY() > startY + heightOffset * 5 && e.getY() < startY + heightOffset * 5 + height) { //custom speed
                mouseRemover();
                game.highScore.mouseSetter();
                setCurrentState(game.highScore);
                game.render();
            }
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

    public Menu(Game game) {
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

        for (int i = 0; i < presetNames.length; i++) {
            g.setColor(Color.GRAY);
            g.fillRect(startX, startY + heightOffset * i, width * 2, height);
            g.setColor(Color.BLACK);
            g.drawString(presetNames[i], startX + width / 8, startY + height * 2 / 3 + heightOffset * i);
        }
    }

    private void updater() {
        game.blockReeper = 0;
        game.map = new int[game.MAP_WIDTH][game.MAP_HEIGHT];
        game.initSpawn();
        game.pointCounter = 0;
//        game.loadHighScore();
        game.getHighScore();
        game.getKeyManager().space = false;

        mouseRemover();

        game.gameState.mouseSetter();
        setCurrentState(game.gameState); //TODO смяната на стадии задържа mouseEvent (натиска да влезе в игра когато отпусне над меню бутона се връща в менюто
        game.render();
    }

    public void mouseSetter() {
        game.display.getCanvas().addMouseListener(mouseListener);
    }

    public void mouseRemover() {
        game.display.getCanvas().removeMouseListener(mouseListener);
    }
}
