package fg331.com.State;

import fg331.com.Main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Settings extends State {
    Game game;

    public Settings(Game game) {
        this.game = game;

        seButtonX = game.MAP_WIDTH * game.WIDTH / 8;
        seButtonY = game.MAP_HEIGHT * game.WIDTH / 12 + (height + 20) * 4;
    }

    private int seButtonX, seButtonY;
    private int width = 180, height = 60;
    private int switcher = 0;
    private ArrayList<Character> speed = new ArrayList<Character>() {
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (Character c : speed) {
                stringBuilder.append(c);
            }

            return stringBuilder.toString();
        }
    };

    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getX() > seButtonX && e.getX() < seButtonX + width * 2 && e.getY() > seButtonY && e.getY() < seButtonY + height) {
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

    private KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if (speed.size() > 0)
                    speed.remove(speed.size() - 1);
            } else if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                if (Integer.parseInt(speed.toString()) > 30 && Integer.parseInt(speed.toString()) < 2) {
                    mouseRemover();
                    game.menu.mouseSetter();
                    setCurrentState(game.menu);
                }
            } else if (e.getKeyCode() > 47 && e.getKeyCode() < 58) {
                if (speed.size() < 2)
                    speed.add(e.getKeyChar());
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };

    public void updater() {

    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(seButtonX, seButtonY, width * 2, height);

        g.setColor(Color.BLACK);
        g.setFont(game.drawFont);
        System.out.println(speed.toString());

        g.drawString("Menu", seButtonX + width / 4, seButtonY + height * 2 / 3);
        g.drawString(speed.toString(), (int) (game.WIDTH * 2.5), 100);
    }

    public void mouseSetter() {
        game.display.getCanvas().addMouseListener(mouseListener);
        game.display.getCanvas().addKeyListener(keyListener);
    }

    public void mouseRemover() {
        game.display.getCanvas().removeMouseListener(mouseListener);
        game.display.getCanvas().removeKeyListener(keyListener);
    }

    public void setSwitcher(int switcher) {
        this.switcher = switcher;
    }

    public void setHolderBombs() {
        int j = speed.size();
        for (int i = 0; i < j; i++) {
            System.out.println(speed.size());
            speed.remove(0);
        }
    }
}
