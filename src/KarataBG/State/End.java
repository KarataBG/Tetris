package KarataBG.State;

import KarataBG.Main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class End extends State {
    private final String[] buttonNames = {"Score: ", "Repeat", "Menu"};
    private final int scButtonX, scButtonY;
    private final int repButtonX, repButtonY;
    private final int menButtonX, menButtonY;
    private final int width = 180, height = 60;
    private final int startX, startY, heightOffset;

    Game game;
    private final MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) { //TODO може итератор през правоъгълници(клас)
            if (e.getX() > repButtonX && e.getX() < repButtonX + width * 2 && e.getY() > repButtonY && e.getY() < repButtonY + height) {
                updater(game.gameState);
                game.render();
            } else if (e.getX() > menButtonX && e.getX() < menButtonX + width * 2 && e.getY() > menButtonY && e.getY() < menButtonY + height) {
                updater(game.menu);
                game.render();
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

    public End(Game game) {
        this.game = game;

        startX = game.MAP_WIDTH * game.WIDTH / 8;
        startY = game.MAP_HEIGHT * game.WIDTH / 12;
        heightOffset = game.heightOffsetButtons;

        scButtonX = game.MAP_WIDTH * game.WIDTH / 8;
        scButtonY = game.MAP_HEIGHT * game.WIDTH / 12;

        repButtonX = game.MAP_WIDTH * game.WIDTH / 8;
        repButtonY = game.MAP_HEIGHT * game.WIDTH / 12 + height + 20;

        menButtonX = game.MAP_WIDTH * game.WIDTH / 8;
        menButtonY = game.MAP_HEIGHT * game.WIDTH / 12 + (height + 20) * 2;
    }

    private void updater(State state) {
        game.blockReeper = 0;
        game.map = new int[game.MAP_WIDTH][game.MAP_HEIGHT];
//        game.initSpawn();
        game.pointCounter = 0;
//        game.loadHighScore();

        mouseRemover();
        state.mouseSetter();
        setCurrentState(state);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.GRAY);
//
//        g.fillRect(scButtonX, scButtonY, width * 2, height);
//        g.fillRect(repButtonX, repButtonY, width * 2, height);
//        g.fillRect(menButtonX, menButtonY, width * 2, height);
//
//        g.setColor(Color.BLACK);
//        g.setFont(game.drawFont);
//
//        g.drawString("Score: " + String.valueOf(game.pointCounter), scButtonX + width / 8, scButtonY + height * 2 / 3);
//        g.drawString("Repeat", repButtonX + width / 8, repButtonY + height * 2 / 3);
//        g.drawString("Menu", menButtonX + width / 8, menButtonY + height * 2 / 3);


        g.setFont(game.drawFont);

        for (int i = 0; i < buttonNames.length; i++) {
            g.setColor(Color.GRAY);
            g.fillRect(startX, startY + heightOffset * i, width * 2, height);
            g.setColor(Color.BLACK);

            if (i == 0) {
                g.drawString(buttonNames[i] + String.valueOf(game.pointCounter), startX + width / 8, startY + height * 2 / 3 + heightOffset * i);
            } else {
                g.drawString(buttonNames[i] , startX + width / 8, startY + height * 2 / 3 + heightOffset * i);
            }
        }

    }

    public void mouseSetter() {
        game.display.getCanvas().addMouseListener(mouseListener);
    }

    public void mouseRemover() {
        game.display.getCanvas().removeMouseListener(mouseListener);
    }
}
