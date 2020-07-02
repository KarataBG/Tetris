package fg331.com.Main;

public class Main {

    public static void main(String[] args){

        //Main frame - partial/filling
        //size block - 30px
        //current block =/= last && secondLast

        //blocks -   x,y,width,height, Image
//        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
//        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        Game game = new Game("Tetris");
        game.start();
    }
}
