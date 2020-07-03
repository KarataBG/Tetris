package fg331.com.Main;

import fg331.com.GFX.Assets;
import fg331.com.KeyManager.KeyManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block {
    private final int WIDTH = 36, HEIGHT = 36;
    private final Font drawFont = new Font("Arial", Font.BOLD, 60);
    private final String[] textNamesFields = {"Menu", "Score:", "High", "Score"};
    private final Game game;
    private final Point[] current = new Point[4];
    private final KeyManager keyManager;
    private final int shape;
    private final BufferedImage image;
    //    private int leftOffset = 432;
    private boolean canMoveDown = true;
    private BufferedImage[] images = new BufferedImage[3];

    Block(Game game, int shape) {
        this.game = game;
        keyManager = game.getKeyManager();

        this.shape = shape;
        image = Assets.colorNum.get(shape);

        switch (shape) {
            case 1:
                current[0] = new Point(4, -2);

                current[1] = new Point(5, -2);

                current[2] = new Point(6, -2);

                current[3] = new Point(6, -1);
                break;
            case 2:
                current[0] = new Point(4, -2);

                current[1] = new Point(5, -2);

                current[2] = new Point(6, -2);

                current[3] = new Point(5, -1);
                break;
            case 3:
                current[0] = new Point(4, -2);

                current[1] = new Point(5, -2);

                current[2] = new Point(6, -2);

                current[3] = new Point(4, -1);
                break;
            case 4:
                current[0] = new Point(4, -1);

                current[1] = new Point(5, -2);

                current[2] = new Point(5, -1);

                current[3] = new Point(6, -2);
                break;
            case 5:
                current[0] = new Point(4, -2);

                current[1] = new Point(5, -2);

                current[2] = new Point(6, -2);

                current[3] = new Point(7, -2);
                break;
            case 6:
                current[0] = new Point(4, -2);

                current[1] = new Point(5, -2);

                current[2] = new Point(4, -1);

                current[3] = new Point(5, -1);

                break;
            case 7:
                current[0] = new Point(4, -2);

                current[1] = new Point(5, -2);

                current[2] = new Point(5, -1);

                current[3] = new Point(6, -1);
                break;
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < 4; i++) {
            if (current[i].y >= 0)
                g.drawImage(image, (current[i].x * WIDTH) + game.leftOffset, current[i].y * HEIGHT + game.heightOffset + game.topOffset, WIDTH, HEIGHT, null);
        }
    }

    public void autoMove() {
        canMoveDown = true;
        for (Point point : current) {
            if (point.y >= (game.MAP_HEIGHT - 1)) {
                canMoveDown = false;
                break;
            }
            if (point.y >= 0)
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
            for (Point p : current){
                if (p.y <= 0)
                    game.exiter();
            }
            game.switching();
        }

    }

    public void move() {
        if (keyManager.down) {
            canMoveDown = true;
            while (canMoveDown) {
                for (Point point : current) {
                    if (point.y >= (game.MAP_HEIGHT - 1)) {
                        canMoveDown = false;
                        break;
                    }
                    if (point.y >= 0)
                        if (game.map[point.x][point.y + 1] != 0) {
                            canMoveDown = false;
                            break;
                        }
                }
                if (canMoveDown) {
                    for (Point point : current) {
                        point.y++;
                    }
                    game.render();
                }
            }
            keyManager.down = false;
        } else if (keyManager.left) {

            boolean canMoveLeft = true;
            for (Point point : current) {
                if (!(point.x > 0)) {
                    canMoveLeft = false;
                    break;
                }
                if (point.y >= 0)
                    if (game.map[point.x - 1][point.y] != 0) {
                        canMoveLeft = false;
                        break;
                    }
            }

            if (canMoveLeft) {
                for (Point point : current) {
                    point.x--;
                }
                game.render();
            }
            keyManager.left = false;
        } else if (keyManager.right) {

            boolean canMoveRight = true;
            for (Point point : current) {
                if (!(point.x < game.MAP_WIDTH - 1)) {
                    canMoveRight = false;
                    break;
                }
                if (point.y >= 0)
                    if (game.map[point.x + 1][point.y] != 0) {
                        canMoveRight = false;
                        break;
                    }
            }

            if (canMoveRight) {
                for (Point point : current) {
                    point.x++;
                }
                game.render();
            }
            keyManager.right = false;
        }

    }

    public void rotationLeft() {
        if (shape != 6) {
            if (keyManager.rotation0) {
                for (int i = 0; i < 4; i++) {
                    if (i != 1) {
                        int currentRotatorX = current[i].x - current[1].x;
                        int currentRotatorY = current[i].y - current[1].y;

                        int tempX = (int) Math.round(currentRotatorX * Math.cos(1.5707963267948966) - currentRotatorY * Math.sin(1.5707963267948966));
                        int tempY = (int) Math.round(currentRotatorX * Math.sin(1.5707963267948966) - currentRotatorY * Math.cos(1.5707963267948966));

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

                        int tempX = (int) Math.round(currentRotatorX * Math.cos(1.5707963267948966) - currentRotatorY * Math.sin(1.5707963267948966));
                        int tempY = (int) Math.round(currentRotatorX * Math.sin(1.5707963267948966) - currentRotatorY * Math.cos(1.5707963267948966));

                        current[i].x = tempX + current[1].x;
                        current[i].y = tempY + current[1].y;
                    }
                }
                game.render();
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

                        int tempX = (int) Math.round(currentRotatorX * Math.cos(1.5707963267948966) - currentRotatorY * Math.sin(1.5707963267948966));
                        int tempY = (int) Math.round(currentRotatorX * Math.sin(1.5707963267948966) - currentRotatorY * Math.cos(1.5707963267948966));

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

                        int tempX = (int) Math.round(currentRotatorY * Math.sin(1.5707963267948966) - currentRotatorX * Math.cos(1.5707963267948966));
                        int tempY = (int) Math.round(currentRotatorY * Math.cos(1.5707963267948966) - currentRotatorX * Math.sin(1.5707963267948966));

                        current[i].x = tempX + current[1].x;
                        current[i].y = tempY + current[1].y;
                    }
                }
                game.render();
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

    public void sideDisplay(Graphics g, int rand, int rand1, int rand2, int pointCounter) { //TODO да се вика само при ъпдейт
        images[0] = Assets.numImage.get(rand);
        images[1] = Assets.numImage.get(rand1);
        images[2] = Assets.numImage.get(rand2);
//        int multx = Assets.numX.get(rand);
//        int multy = Assets.numY.get(rand); //от 0 до където почва сивото и от 0 до 2 * височината + y

        int[] multx = {Assets.numX.get(rand), Assets.numX.get(rand1), Assets.numX.get(rand2)};
        int[] multy = {Assets.numY.get(rand), Assets.numY.get(rand1), Assets.numY.get(rand2)};

        g.setColor(Color.BLACK);
        g.setFont(drawFont);

        FontMetrics fontMetrics = g.getFontMetrics();
        int ascent = fontMetrics.getAscent();
        int height = fontMetrics.getHeight();
        int leading = fontMetrics.getLeading();
        int y = leading + ascent;

        for (int i = 0; i < textNamesFields.length; i++) {
            g.drawString(textNamesFields[i], 0, y + 2 * height * i);
        }

        y = leading + ascent + 3 * height;
        g.drawString(String.valueOf(pointCounter), game.WIDTH / 3, y);

        y = leading + ascent + 5 * height;
        if (game.highScoreGameState != null) {
            g.drawString(game.highScoreGameState, 0, y);
        } else {
            g.drawString("0", 0, y);
        }

        int singularWidth = game.leftOffset / 4; //TODO нови координати че да изглежда на място
        for (int i = 6; i < 9; i++) {
            y += singularWidth * 2;
            g.drawImage(images[i - 6], 0, y, singularWidth * multx[i - 6], singularWidth * multy[i - 6], null);
        }
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
