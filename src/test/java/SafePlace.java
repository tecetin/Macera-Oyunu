public class SafePlace extends Locations {

    public SafePlace(Player p) {
        super(p, "Güvenli Ev");
    }

    @Override
    boolean onLocation() {
        Player player = this.getP();
        boolean isRunning = true;

        System.out.println("\nŞu an Güvenli Evdesin. Burada düşman yoktur, çetin savaşlar sonrası gelip yaralarını sarabilirsin..");

        // Sağlık yenileme kontrolü
        if (player.getHealth() < player.getInitialHealth()) {
            player.setHealth(player.getInitialHealth());
            System.out.println("Sağlığın yenilendi: " + player.getHealth());
        }

        while (isRunning) {
            System.out.println("1 - Oyuncu Bilgileri");
            System.out.println("0 - Ana Menü");

            int selectedLocation = player.getManager().scanner.nextInt();

            switch (selectedLocation) {
                case 0:
                    isRunning = false; // Döngüyü sonlandır
                    break;
                case 1:
                    System.out.println("\nOyuncu Bilgileri:");
                    System.out.println("Karakter Adı: " + this.getP().getCharName());
                    System.out.println("Oyuncu Adı: " + this.getP().getName());
                    System.out.println("Sağlık: " + this.getP().getHealth());
                    System.out.println("Saldırı Gücü: " + this.getP().getDamage());
                    System.out.println("Varlık: " + this.getP().getMoney());
                    System.out.println("Bloklama Gücü: " + this.getP().getBlocking()); // Bloklama sayısı
                    System.out.println("Envanter: " + this.getP().getInventories().printItems());
                default:
            }
        }
        return isRunning;
    }


    void returnHome() {
        this.getP().setHealth(getP().getInitialHealth());
        System.out.println("Tedaviniz tamamlandı. Sağlık değeriniz: " + this.getP().getHealth());
    }


}
