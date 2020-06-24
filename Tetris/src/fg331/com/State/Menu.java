package fg331.com.State;

import fg331.com.Main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu extends State {
    Game game;

    public Menu(Game game) {
        this.game = game;

        begButtonX = game.MAP_WIDTH * game.WIDTH / 8;
        begButtonY = game.MAP_HEIGHT * game.WIDTH / 12;

        intButtonX = game.MAP_WIDTH * game.WIDTH / 8;
        intButtonY = game.MAP_HEIGHT * game.WIDTH / 12 + height + 20;

        harButtonX = game.MAP_WIDTH * game.WIDTH / 8;
        harButtonY = game.MAP_HEIGHT * game.WIDTH / 12 + (height + 20) * 2;

        insButtonX = game.MAP_WIDTH * game.WIDTH / 8;
        insButtonY = game.MAP_HEIGHT * game.WIDTH / 12 + (height + 20) * 3;

        seButtonX = game.MAP_WIDTH * game.WIDTH / 8;
        seButtonY = game.MAP_HEIGHT * game.WIDTH / 12 + (height + 20) * 4;
    }

    private int scButtonX, scButtonY;
    private int begButtonX, begButtonY;
    private int intButtonX, intButtonY;
    private int harButtonX, harButtonY;
    private int insButtonX, insButtonY;
    private int seButtonX, seButtonY;
    private int width = 180, height = 60;

    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getX() > seButtonX && e.getX() < seButtonX + width * 2 && e.getY() > seButtonY && e.getY() < seButtonY + height) {
                mouseRemover();
                game.settings.mouseSetter();
                setCurrentState(game.settings);
            } else if (e.getX() > begButtonX && e.getX() < begButtonX + width * 2 && e.getY() > begButtonY && e.getY() < begButtonY + height) {
                game.speed = 20;
                updater();
            } else if (e.getX() > intButtonX && e.getX() < intButtonX + width * 2 && e.getY() > intButtonY && e.getY() < intButtonY + height) {
                game.speed = 14;
                updater();
            } else if (e.getX() > harButtonX && e.getX() < harButtonX + width * 2 && e.getY() > harButtonY && e.getY() < harButtonY + height) {
                game.speed = 8;
                updater();
            } else if (e.getX() > insButtonX && e.getX() < insButtonX + width * 2 && e.getY() > insButtonY && e.getY() < insButtonY + height) {
                game.speed = 5;
                updater();
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

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);

        g.fillRect(begButtonX, begButtonY, width * 2, height);
        g.fillRect(intButtonX, intButtonY, width * 2, height);
        g.fillRect(harButtonX, harButtonY, width * 2, height);
        g.fillRect(insButtonX, insButtonY, width * 2, height);
        g.fillRect(seButtonX, seButtonY, width * 2, height);

        g.setColor(Color.BLACK);
        g.setFont(game.drawFont);

        g.drawString("Easy Preset", begButtonX + width / 8, begButtonY + height * 2 / 3);
        g.drawString("Inter Preset", intButtonX + width / 8, intButtonY + height * 2 / 3);
        g.drawString("Hard Preset", harButtonX + width / 8, harButtonY + height * 2 / 3);
        g.drawString("Insane Preset", insButtonX + width / 8, insButtonY + height * 2 / 3);
        g.drawString("Custom Speed", seButtonX + width / 8, seButtonY + height * 2 / 3);
    }

    private void updater() {
        game.blockReeper = 0;
        game.map = new int[game.MAP_WIDTH][game.MAP_HEIGHT];
        game.initSpawn();
        game.pointCounter = 0;
        game.loadHighScore();

        mouseRemover();
        game.gameState.mouseSetter();
        setCurrentState(game.gameState);
    }

    public void mouseSetter() {
        game.display.getCanvas().addMouseListener(mouseListener);
    }

    public void mouseRemover() {
        game.display.getCanvas().removeMouseListener(mouseListener);
    }
}
