package fg331.com.Display;
import javax.swing.*;
import java.awt.*;

public class Display {

    private Canvas canvas;
    private JFrame frame;

    private String title;
    private int width, height;

    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        Frame();
    }
    private void Frame() {
        frame = new JFrame(title);

        frame.setBounds(0, 0, width, height);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }
    public JFrame getFrame(){
        return frame;
    }
}


