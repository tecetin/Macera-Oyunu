public class Game {

    private final Player player;
    private GameManager manager;

    public Game() {
        this.player = new Player(); // Yeni bir oyuncu nesnesi oluşturuluyor.
        this.manager = new GameManager(player); // GameManager oyuncuyu yönetiyor.
    }

    public void play() {
        System.out.println("\nOyunun amacı, her savaş alanında bulunan eşyalardan en az bir tane envanterimize katmaktır.");
        System.out.println("Her eşyadan en az bir adet olduğunda oyun biter.");
        System.out.println("Tabi canımız ondan önce bitmezse!! ☺");
        System.out.println("!! İYİ ŞANSLAR !!");

        boolean again = true;
        while (again) {
            if (manager.selectChar()) {      // Savaşçı seçimi
                manager.selectLocation();   // Başlanacak bölge seçimi.
            }
            System.out.println("Tekrar Oynamak için 1");
            System.out.println("Çıkmak için 0");
            System.out.print("Seçiminiz: ");
            int selection = player.getManager().scanner.nextInt();

            if (selection == 1) this.manager = new GameManager(this.player);  // Yeni bir oyun başlat
            else again =false;
        }
        System.out.println("Oyunu oynadığınız için teşekkürler!");
    }
}

