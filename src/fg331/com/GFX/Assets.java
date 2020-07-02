package fg331.com.GFX;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Assets {
    private static final int width = 24, height = 24;

    public static Map<String, BufferedImage> colors = new HashMap<>();
    public static Map<String, Integer> colorNames = new HashMap<>();
    public static Map<Integer, String> colorIndexes = new HashMap<>();

    public static Map<Integer,BufferedImage> colorNum = new HashMap<>();
    public static Map<Integer,BufferedImage> numImage = new HashMap<>();
    public static Map<Integer,Integer> numX = new HashMap<>();
    public static Map<Integer,Integer> numY = new HashMap<>();
    public static BufferedImage gridLine = null;

    public static String path = null;

    public static void init() {

        SpriteSheet sheet = new SpriteSheet( Image.imageLoader("/images/Tetris1.png"));
        SpriteSheet sheet1 = new SpriteSheet(Image.imageLoader("/images/Tetris2.png"));

        try {
            path = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gridLine = Image.imageLoader("/images/TetrisGridLines.png");

        colors.put("red",           sheet.crop(0, 0, width, height));
        colorNames.put("red",       1);
        colorIndexes.put(1,         "red");
        colorNum.put(1,             sheet.crop(0, 0, width, height));

        colors.put("yellow",        sheet.crop(width, 0, width, height));
        colorNames.put("yellow",    2);
        colorIndexes.put(2,         "yellow");
        colorNum.put(2,             sheet.crop(width , 0, width, height));

        colors.put("green",         sheet.crop(width * 2, 0, width, height));
        colorNames.put("green",     3);
        colorIndexes.put(3,         "green");
        colorNum.put(3,             sheet.crop(width * 2, 0, width, height));

        colors.put("lightGreen",    sheet.crop(width * 3, 0, width, height));
        colorNames.put("lightGreen",4);
        colorIndexes.put(4,         "lightGreen");
        colorNum.put(4,             sheet.crop(width*3, 0, width, height));

        colors.put("lightBlue",     sheet.crop(width * 4, 0, width, height));
        colorNames.put("lightBlue", 5);
        colorIndexes.put(5,         "lightBlue");
        colorNum.put(5,             sheet.crop(width * 4, 0, width, height));

        colors.put("blue",          sheet.crop(width * 5, 0, width, height));
        colorNames.put("blue",      6);
        colorIndexes.put(6,         "blue");
        colorNum.put(6,             sheet.crop(width * 5, 0, width, height));

        colors.put("purple",        sheet.crop(width * 6,0,width,height));
        colorNames.put("purple",    7);
        colorIndexes.put(7,         "purple");
        colorNum.put(7,             sheet.crop(width * 6,0,width,height));


        numImage.put(1,             sheet1.crop(0, 0, width*3, height*2));
        numImage.put(2,             sheet1.crop(width*3, 0, width*3, height*2));
        numImage.put(3,             sheet1.crop(width*6, 0, width*3, height*2));
        numImage.put(4,             sheet1.crop(width*9, 0, width*3, height*2));
        numImage.put(5,             sheet1.crop(width*12, 0, width*4, height*1));
        numImage.put(6,             sheet1.crop(width*16, 0, width*2, height*2));
        numImage.put(7,             sheet1.crop(width*18, 0, width*3, height*2));

        numX.put(1,                 3);
        numX.put(2,                 3);
        numX.put(3,                 3);
        numX.put(4,                 3);
        numX.put(5,                 4);
        numX.put(6,                 2);
        numX.put(7,                 3);

        numY.put(1,                 2);
        numY.put(2,                 2);
        numY.put(3,                 2);
        numY.put(4,                 2);
        numY.put(5,                 1);
        numY.put(6,                 2);
        numY.put(7,                 2);

    }
}
