package fg331.com.Main;

import fg331.com.KeyManager.KeyManager;
import fg331.com.GFX.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block {
    private Font drawFont = new Font("Arial", Font.BOLD, 60);

    private final int WIDTH = 48, HEIGHT = 48;
    //    private int leftOffset = 432;
    private boolean canMoveDown = true;

    private Game game;
    private Point[] current = new Point[4];
    private KeyManager keyManager;

    private int shape;
    private BufferedImage image, image1;

    Block(Game game, int shape) {
        this.game = game;
        keyManager = game.getKeyManager();

        this.shape = shape;
        image = Assets.colorNum.get(shape);

        switch (shape) {
            case 1:
                current[0] = new Point(4, 0);

                current[1] = new Point(5, 0);

                current[2] = new Point(6, 0);

                current[3] = new Point(6, 1);
                break;
            case 2:
                current[0] = new Point(4, 0);

                current[1] = new Point(5, 0);

                current[2] = new Point(6, 0);

                current[3] = new Point(5, 1);
                break;
            case 3:
                current[0] = new Point(4, 0);

                current[1] = new Point(5, 0);

                current[2] = new Point(6, 0);

                current[3] = new Point(4, 1);
                break;
            case 4:
                current[0] = new Point(4, 1);

                current[1] = new Point(5, 0);

                current[2] = new Point(5, 1);

                current[3] = new Point(6, 0);
                break;
            case 5:
                current[0] = new Point(4, 0);

                current[1] = new Point(5, 0);

                current[2] = new Point(6, 0);

                current[3] = new Point(7, 0);
                break;
            case 6:
                current[0] = new Point(4, 0);

                current[1] = new Point(5, 0);

                current[2] = new Point(4, 1);

                current[3] = new Point(5, 1);

                break;
            case 7:
                current[0] = new Point(4, 0);

                current[1] = new Point(5, 0);

                current[2] = new Point(5, 1);

                current[3] = new Point(6, 1);
                break;
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < 4; i++) {
            g.drawImage(image, (current[i].x * WIDTH) + game.leftOffset, current[i].y * HEIGHT + game.heightOffset, WIDTH, HEIGHT, null);
        }
    }

    public void autoMove() {
        canMoveDown = true;
        for (Point point : current) {
            if ((point.y >= (game.MAP_HEIGHT - 1))) {
                canMoveDown = false;
                break;
            }
            if (game.map[point.x][point.y + 1] != 0) {
                canMoveDown = false;
                break;
            }
        }
        if (canMoveDown) {
            for (Point point : current) {
                point.y++;
            }
        } else {
            game.switching();
        }

    }

    public void move() {
        if (keyManager.down) {
            canMoveDown = true;

            while (canMoveDown) {
                for (Point point : current) {
                    if ((point.y >= (game.MAP_HEIGHT - 1))) {
                        canMoveDown = false;
                        break;
                    }
                    if (game.map[point.x][point.y + 1] != 0) {
                        canMoveDown = false;
                        break;
                    }
                }
                if (canMoveDown) {
                    for (Point point : current) {
                        point.y++;
                    }
                }
            }
            keyManager.down = false;
        }
        if (keyManager.left) {

            boolean canMoveLeft = true;
            for (Point point : current) {
                if (!(point.x > 0)) {
                    canMoveLeft = false;
                    break;
                }
                if (game.map[point.x - 1][point.y] != 0) {
                    canMoveLeft = false;
                    break;
                }
            }
            if (canMoveLeft) {
                for (Point point : current) {
                    point.x--;
                }
            }
            keyManager.left = false;
        }

        if (keyManager.right) {
            boolean canMoveRight = true;
            for (Point point : current) {
                if (!(point.x < game.MAP_WIDTH - 1)) {
                    keyManager.right = false;
                    return;
                }

                if (point.x > game.MAP_WIDTH - 1 || point.y < 0) {
                    keyManager.right = false;
                    return;
                }

                if (point.y > 0)
                    if (game.map[point.x + 1][point.y] != 0) {
                        keyManager.right = false;
                        return;
                    }
            }

            for (Point point : current) {
                point.x++;
            }
        }
        keyManager.right = false;
    }

    public void rotationLeft() {

        if (shape != 6) {
            if (keyManager.rotation0) {
                for (int i = 0; i < 4; i++) {
                    if (i != 1) {
                        int currentRotatorX = current[i].x - current[1].x;
                        int currentRotatorY = current[i].y - current[1].y;

                        int tempX = (int) Math.round(currentRotatorX * Math.cos(Math.PI / 2) - currentRotatorY * Math.sin(Math.PI / 2));
                        int tempY = (int) Math.round(currentRotatorX * Math.sin(Math.PI / 2) - currentRotatorY * Math.cos(Math.PI / 2));

                        if (tempX + current[1].x < 0 || tempX + current[1].x > game.MAP_WIDTH - 1)
                            return;
                        if (tempY + current[1].y < 0 || tempY + current[1].y > game.MAP_HEIGHT - 1)
                            return;
                        if (game.map[tempX + current[1].x][tempY + current[1].y] != 0)
                            return;
                    }
                }
                for (int i = 0; i < 4; i++) {
                    if (i != 1) {
                        int currentRotatorX = current[i].x - current[1].x;
                        int currentRotatorY = current[i].y - current[1].y;

                        int tempX = (int) Math.round(currentRotatorX * Math.cos(Math.PI / 2) - currentRotatorY * Math.sin(Math.PI / 2));
                        int tempY = (int) Math.round(currentRotatorX * Math.sin(Math.PI / 2) - currentRotatorY * Math.cos(Math.PI / 2));

                        current[i].x = tempX + current[1].x;
                        current[i].y = tempY + current[1].y;
                    }
                }
            }
        }
    }

    public void rotationRight() {
        if (shape != 6) {
            if (keyManager.rotation1) {
                for (int i = 0; i < 4; i++) {
                    if (i != 1) {
                        int currentRotatorX = current[i].x - current[1].x;
                        int currentRotatorY = current[i].y - current[1].y;

                        int tempX = (int) Math.round(currentRotatorX * Math.cos(Math.PI / 2) - currentRotatorY * Math.sin(Math.PI / 2));
                        int tempY = (int) Math.round(currentRotatorX * Math.sin(Math.PI / 2) - currentRotatorY * Math.cos(Math.PI / 2));

                        if (tempX + current[1].x < 0 || tempX + current[1].x > game.MAP_WIDTH - 1)
                            return;
                        if (tempY + current[1].y < 0 || tempY + current[1].y > game.MAP_HEIGHT - 1)
                            return;
                        if (game.map[tempX + current[1].x][tempY + current[1].y] != 0)
                            return;
                    }
                }
                for (int i = 0; i < 4; i++) {
                    if (i != 1) {
                        int currentRotatorX = current[i].x - current[1].x;
                        int currentRotatorY = current[i].y - current[1].y;

                        int tempX = (int) Math.round(currentRotatorY * Math.sin(Math.PI / 2) - currentRotatorX * Math.cos(Math.PI / 2));
                        int tempY = (int) Math.round(currentRotatorY * Math.cos(Math.PI / 2) - currentRotatorX * Math.sin(Math.PI / 2));

                        current[i].x = tempX + current[1].x;
                        current[i].y = tempY + current[1].y;
                    }
                }
            }
        }
    }

    void points() {
        int lineCounter = 0;
        int basePoint = 2000;
        int pointStacker = 0;
        int lineHolder = 0;

        for (int j = game.MAP_HEIGHT - 1; j > 0; j--) {
            for (int i = 0; i < game.MAP_WIDTH; i++) {
                if (game.map[i][j] != 0) {
                    lineCounter++;
                }
            }
            if (lineCounter == game.MAP_WIDTH) {
                pointStacker++;
                if (pointStacker == 1) {
                    lineHolder = j;
                }
            }


            lineCounter = 0;
        }
        for (int m = lineHolder; m > pointStacker; m--) {
            for (int i = 0; i < game.MAP_WIDTH; i++) {
                game.map[i][m] = game.map[i][m - pointStacker];
            }
        }

        if (pointStacker == 4) {
            game.pointCounter += basePoint * (pointStacker + 2);
        } else {
            game.pointCounter += basePoint * pointStacker;
        }
    }

    public void sideDisplay(Graphics g, int rand, int pointCounter) {
        image1 = Assets.numImage.get(rand);
        int multx = Assets.numX.get(rand);
        int multy = Assets.numY.get(rand);

        g.drawImage(image1, 200, 800, WIDTH * multx, HEIGHT * multy, null);

        g.setColor(Color.BLACK);
        g.setFont(drawFont);
        g.drawString("Menu", game.gameState.meButtonX, game.gameState.meButtonY);
        g.drawString("Score:", game.WIDTH, game.HEIGHT * 5);
        g.drawString(String.valueOf(pointCounter), game.WIDTH, game.HEIGHT * 6);
        g.drawString("High", game.WIDTH, game.HEIGHT * 8);
        g.drawString("Score:", game.WIDTH, game.HEIGHT * 9);
        if (game.highScoreGameState != null)
            g.drawString(game.highScoreGameState, game.WIDTH, game.HEIGHT * 10);
        else
            g.drawString("0", game.WIDTH, game.HEIGHT * 10);
    }

    int getX(int index) {
        return current[index].x;
    }

    int getY(int index) {
        return current[index].y;
    }

    int getShape() {
        return shape;
    }
}
