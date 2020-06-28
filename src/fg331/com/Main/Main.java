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
        //TODO скед загуба да има друг екран(стадии) на който да има неща !>
        //TODO да има high score за отделните трудности, кур, и стадий за него
        //TODO да има паузиране
        //TODO открий какъв е този клас Scores и го оправи
    }

    //TODO oprawi wartene nalqwo
    //TODO warteneto warti w obratni posoki
    // danoto e izwan ekrana  dawa gre6ka ako zawarta blok4e taka 4e da bade izwan ekrana
    //TODO da ima dobawqne na heightoffset nawsqkade kydeto rendarwa6 kub4eta

    //TODO zabaweno slagane kogato go insta pu6wam nadolu
    //TODO kogato se warti da ne moje da se warti ako ste izleze ot bounds
    //TODO oprawi pri wartene wegnaga pri sazdawane

    //TODO bez prowerki kogato sa normalno izwan kartata

    //TODO kogato zawartqh dalgoto takmo kogato se spawn-na i mu dadoh dwijenie (w slu4aq na lqwo) dade .ArrayIndexOutOfBoundsException: -1

    //TODO W Menu updater() da testartira igrata
}
