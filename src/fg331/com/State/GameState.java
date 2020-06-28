package fg331.com.State;

import fg331.com.Main.Game;
import fg331.com.GFX.Assets;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameState extends State {
    Game game;

    public int height = 100, width = 160;
    private String[] rows;
    private Font font;
    public String highScore;
    public int meButtonX, meButtonY, meWidth, meHeight;

    public GameState(Game game) {
        this.game = game;
        font = new Font("Arial", Font.BOLD, 52);
        meButtonX = game.WIDTH;
        meButtonY = game.HEIGHT * 3;
        try {
            highScore = new BufferedReader(new FileReader(Assets.path + "\\HighScores.txt")).readLine();
//            highScore = new BufferedReader(new FileReader("res/txt/HighScores.txt")).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getX() > meButtonX - game.WIDTH / 2 && e.getX() < meButtonX + game.WIDTH * 3 && e.getY() > meButtonY - game.HEIGHT && e.getY() < meButtonY) {
                mouseRemover();
                game.menu.mouseSetter();
                setCurrentState(game.menu);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) { }

        @Override
        public void mouseReleased(MouseEvent e) { }

        @Override
        public void mouseEntered(MouseEvent e) { }

        @Override
        public void mouseExited(MouseEvent e) { }
    };

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

        if (game.autoMove == game.speed) {
            game.currentBlock.autoMove();
            game.autoMove = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        //draw
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, game.leftOffset, game.height);
        g.fillRect(game.width * 3 / 4, 0, game.width / 4, game.height);

        game.currentBlock.draw(g);
        game.currentBlock.sideDisplay(g, game.blocks[game.blockReeper + 1], game.pointCounter);

        for (int i = 0; i < game.MAP_WIDTH; i++) {
            for (int j = 0; j < game.MAP_HEIGHT; j++) {
                switch (game.map[i][j]) {
                    case 1:
                        g.drawImage(Assets.colors.get("red"), game.leftOffset + i * game.WIDTH, j * game.HEIGHT + game.heightOffset, game.WIDTH, game.HEIGHT, null);
                        break;
                    case 2:
                        g.drawImage(Assets.colors.get("yellow"), game.leftOffset + i * game.WIDTH, j * game.HEIGHT + game.heightOffset, game.WIDTH, game.HEIGHT, null);
                        break;
                    case 3:
                        g.drawImage(Assets.colors.get("green"), game.leftOffset + i * game.WIDTH, j * game.HEIGHT + game.heightOffset, game.WIDTH, game.HEIGHT, null);
                        break;
                    case 4:
                        g.drawImage(Assets.colors.get("lightGreen"), game.leftOffset + i * game.WIDTH, j * game.HEIGHT + game.heightOffset, game.WIDTH, game.HEIGHT, null);
                        break;
                    case 5:
                        g.drawImage(Assets.colors.get("lightBlue"), game.leftOffset + i * game.WIDTH, j * game.HEIGHT + game.heightOffset, game.WIDTH, game.HEIGHT, null);
                        break;
                    case 6:
                        g.drawImage(Assets.colors.get("blue"), game.leftOffset + i * game.WIDTH, j * game.HEIGHT + game.heightOffset, game.WIDTH, game.HEIGHT, null);
                        break;
                    case 7:
                        g.drawImage(Assets.colors.get("purple"), game.leftOffset + i * game.WIDTH, j * game.HEIGHT + game.heightOffset, game.WIDTH, game.HEIGHT, null);
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
