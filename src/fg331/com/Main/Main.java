package fg331.com.Main;

public class Main {

    public static void main(String[] args) {

        //Main frame - partial/filling
        //size block - 30px
        //current block =/= last && secondLast

        //blocks -   x,y,width,height, Image
//        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
//        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        Game game = new Game("Tetris");
        game.start();



    }
    //TODO има 22 реда височина играта излиза когато има на горните два реда като парчетата трябва идват от извън полето което те правят проверка някъде
    //TODO оправи да работи с малък размер gridLines и защо дисплейа на парчета бърза
    //TODO показва с едно напред в sideDisplay следващите блокчета
    //TODo след като има край на едно парче веднага да се създаде другото в двета скрити реда
    //TODO ако парче свърши и има поне един y <=0 (в средата по x) да отиде exiter

    //TODO да идват от извън игралното поле да има два нови реда които няма да се рисуват !
    //TODO скед загуба да има друг екран(стадии) на който да има неща !>
    //TODO да има high score за отделните трудности, и стадий за него !
    //TODO да има паузиране !
    //TODO открий какъв е този клас Scores и го оправи !
    //TODO вкарай gridLines !
    //TODO сложи рисуването на втора нишка и викай само при нужда !
    // може би не е нужна друга нишка !
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

    //TODO направи си някаква малка симулациика
    // simulating evolution of aggresssion
}
