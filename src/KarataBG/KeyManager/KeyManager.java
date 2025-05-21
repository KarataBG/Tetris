package KarataBG.KeyManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener, ActionListener {

    public boolean up, down, left, right, rotation0, rotation1;
    public boolean space;
    private boolean[] keys;

    public KeyManager() {
        keys = new boolean[256];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w':
                up = true;
                break;
            case 's':
                down = true;
                break;
            case 'a':
                left = true;
                break;
            case 'd':
                right = true;
                break;
            case 'o':
                rotation0 = true;
                break;
            case 'p':
                rotation1 = true;
                break;

        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            space = !space;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
     /*   switch (e.getKeyChar()) {
            case 'w':
                up = false;
                break;
            case 's':
                down = false;
                break;
            case 'a':
                left = false;
                break;
            case 'd':
                right = false;
                break;
//            case 'o':
//                rotation0 = false;
//                break;
//            case 'p':
//                rotation1 = false;
//                break;
        }*/

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent a) {
    }
}


