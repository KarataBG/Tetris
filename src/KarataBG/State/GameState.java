package KarataBG.State;

import KarataBG.GFX.Assets;
import KarataBG.Main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameState extends State {
    public int height = 100, width = 160;
    public String highScore;
    public int meButtonX, meButtonY, meWidth, meHeight;
    Game game;
    private final MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
//            if (e.getX() > meButtonX - game.WIDTH / 2 * 1.45 && e.getX() < meButtonX + game.WIDTH * 3 * 1.15 && e.getY() > meButtonY - game.HEIGHT * 1.45 && e.getY() < meButtonY * 1.15) {
            if (e.getX() > meButtonX - game.WIDTH / 2 * 1.45 && e.getX() < game.width/4 && e.getY() > 0 && e.getY() < meButtonY * 1.15) {
                mouseRemover();
                game.menu.mouseSetter();
                setCurrentState(game.menu);
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
    private String[] rows;
    private Font font;
    private boolean running = true;

    public GameState(Game game) {
        this.game = game;
        font = new Font("Arial", Font.BOLD, 52);
        meButtonX = game.WIDTH;
        meButtonY = game.HEIGHT * 3;
    }

    @Override
    public void tick() {
        game.autoMove++;
        // TODO wednav natisnato ima 4akane za wreme; press w rotationLeft = 0 ina4e rotationLeft winagi e 6;

        if (game.keyManager.rotation0) {
            game.currentBlock.rotationLeft();
            game.keyManager.rotation0 = false;
        }

        if (game.keyManager.rotation1) {
            game.currentBlock.rotationRight();
            game.keyManager.rotation1 = false;
        }

        game.currentBlock.move();

        if (game.autoMove == game.speed) { //TODO скоростта се шльопа
            game.currentBlock.autoMove();
            game.autoMove = 0;
            game.render();
        }
    }

    @Override
    public void render(Graphics g) {
        //draw
        g.setFont(game.drawFontHelvetica);
        g.setColor(Color.BLUE);
        g.drawString("EZOGON", game.width / 2 - game.getFontMetrics(game.drawFontHelvetica).stringWidth("EZOGON") / 2, game.topOffset / 2 + game.getFontMetrics(game.drawFontHelvetica).getHeight() / 2);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, game.leftOffset, game.height);
        g.fillRect(game.width * 3 / 4, 0, game.width / 4, game.height);
        g.drawImage(Assets.gridLine, game.leftOffset, game.topOffset, null);

        game.currentBlock.draw(g);
        game.currentBlock.sideDisplay(g, game.blocks[game.blockReeper], game.blocks[game.blockReeper + 1], game.blocks[game.blockReeper + 2], game.pointCounter); // имам ненужно увеличаване на blockReaper

        for (int i = 0; i < game.MAP_WIDTH; i++) {
            for (int j = 0; j < game.MAP_HEIGHT; j++) { //може равно на 2
                switch (game.map[i][j]) {
                    case 1:
                        g.drawImage(Assets.colors.get("red"), game.leftOffset + i * game.WIDTH, j * game.HEIGHT + game.heightOffset + game.topOffset, game.WIDTH, game.HEIGHT, null);
                        break;
                    case 2:
                        g.drawImage(Assets.colors.get("yellow"), game.leftOffset + i * game.WIDTH, j * game.HEIGHT + game.heightOffset + game.topOffset, game.WIDTH, game.HEIGHT, null);
                        break;
                    case 3:
                        g.drawImage(Assets.colors.get("green"), game.leftOffset + i * game.WIDTH, j * game.HEIGHT + game.heightOffset + game.topOffset, game.WIDTH, game.HEIGHT, null);
                        break;
                    case 4:
                        g.drawImage(Assets.colors.get("lightGreen"), game.leftOffset + i * game.WIDTH, j * game.HEIGHT + game.heightOffset + game.topOffset, game.WIDTH, game.HEIGHT, null);
                        break;
                    case 5:
                        g.drawImage(Assets.colors.get("lightBlue"), game.leftOffset + i * game.WIDTH, j * game.HEIGHT + game.heightOffset + game.topOffset, game.WIDTH, game.HEIGHT, null);
                        break;
                    case 6:
                        g.drawImage(Assets.colors.get("blue"), game.leftOffset + i * game.WIDTH, j * game.HEIGHT + game.heightOffset + game.topOffset, game.WIDTH, game.HEIGHT, null);
                        break;
                    case 7:
                        g.drawImage(Assets.colors.get("purple"), game.leftOffset + i * game.WIDTH, j * game.HEIGHT + game.heightOffset + game.topOffset, game.WIDTH, game.HEIGHT, null);
                }
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
