public class Store extends Locations {

    int category;

    public Store(Player p) {
        super(p, "Mağaza");
    }

    @Override
    boolean onLocation() {

        System.out.println("\n $$$ MAĞAZAYA HOŞGELDİN " + this.getP().getCharName() + " " + this.getP().getName());

        while (true) {
            System.out.println("İNCELEMEK İSTEDİĞİN KATEGORİYİ SEÇ: ");
            System.out.println("1 - SİLAHLAR\n" +
                    "2 - ZIRHLAR\n" +
                    "0 - GÜVENLİ EVE DÖN");
            this.category = this.getP().getManager().scanner.nextInt();

            switch (this.category) {
                case 1:
                    if (!gunCategory()) {
                        System.out.println("MAĞAZADAN ÇIKILDI.");
                        return false;
                    }
                    break;
                case 2:
                    if (!shieldCategory()) {
                        System.out.println("MAĞAZADAN ÇIKILDI.");
                        return false;
                    }
                    break;
                case 0:
                    System.out.println("MAĞAZADAN ÇIKILDI.");
                    return false;
                default:
                    System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
                    break;
            }
        }
    }

    boolean gunCategory() {
        this.getP().getManager().printList(this.getP().getManager().getGunList());
        int selection = buyWeapon();
        //mağazada olmaya devam et
        return selection != 0; //oyundan çık
    }

    int buyWeapon() {
        Player player = this.getP();
        boolean reBuy = true;
        int selection = 0;

        while (reBuy) {
            System.out.println();
            System.out.println("VARLIĞINIZ $: " + player.getMoney());
            System.out.print("BİR SİLAH SEÇİNİZ : ");
            int selectedGunID = player.getManager().scanner.nextInt();
            switch (selectedGunID) {
                case 1, 2, 3 -> {
                    GameGuns selectedGun = this.getP().getManager().getGunList().get(selectedGunID - 1);
                    if (selectedGun.getPrice() > this.getP().getMoney()) {
                        System.out.println("Bu silah için yeterli paranız bulunmamaktadır.");
                    } else {
                        //Satınalma gerçekleşiyor
                        this.getP().getInventories().setGuns(selectedGun);
                        this.getP().setDamage(this.getP().getDamage() + selectedGun.getDamage());
                        this.getP().setMoney(this.getP().getMoney() - selectedGun.getPrice());

                        System.out.println("\nGÜNCEL ENVANTERİNİZ : " + this.getP().getInventories().toString());
                        System.out.println("GÜNCEL HASAR KUVVETİNİZ : " + this.getP().getDamage());
                        System.out.println("KALAN PARANIZ : " + this.getP().getMoney());
                    }
                }
                default -> {
                    System.out.println("Hatalı giriş.");
                }
            }
            System.out.println("\nYeni bir silah almak için 1'e, Mağaza'ya dönmek için 2'ye, Güvenli Eve dönmek için 0'a basın: ");

            selection = this.getP().getManager().scanner.nextInt();

            reBuy = switch (selection) {
                case 2, 0 -> //buyWeapon biter
                        false;
                default -> true;
            };
        }
        return selection;
    }

    boolean shieldCategory() {
        this.getP().getManager().printList(this.getP().getManager().getShieldList());
        int selection = buyShield();
        return selection != 0;
    }

    int buyShield() {
        Player player = this.getP();
        boolean reBuy = true;
        int selection = 0;

        while (reBuy) {
            System.out.println();
            System.out.println("VARLIĞINIZ $: " + player.getMoney());
            System.out.print("BİR ZIRH SEÇİNİZ : ");
            int selectedShieldID = this.getP().getManager().scanner.nextInt();
            switch (selectedShieldID) {
                case 1, 2, 3 -> {
                    Shields selectedShield = this.getP().getManager().getShieldList().get(selectedShieldID - 1);
                    if (selectedShield.getPrice() > this.getP().getMoney()) {
                        System.out.println("Bu zırh için yeterli paranız bulunmamaktadır.");
                    } else {
                        //Satınalma gerçekleşiyor
                        this.getP().getInventories().setShields(selectedShield);
                        this.getP().setBlocking(this.getP().getBlocking() + selectedShield.getProtection());
                        this.getP().setMoney(this.getP().getMoney() - selectedShield.getPrice());

                        System.out.println("\nGÜNCEL ENVANTERİNİZ : " + this.getP().getInventories().toString());
                        System.out.println("SALDIRI ENGELLEME SEVİYENİZ : " + this.getP().getDamage());
                        System.out.println("KALAN PARANIZ : " + this.getP().getMoney());
                    }
                }
                default -> {
                    System.out.println("\nHATALI GİRİŞ.");
                }
            }
            System.out.println("\nYeni bir zırh almak için 1'e, Mağaza'ya dönmek için 2'ye, Güvenli Eve dönmek için 0'a basın: ");

            selection = this.getP().getManager().scanner.nextInt();

            reBuy = switch (selection) {
                case 2, 0 -> //buyShield biter
                        false;
                default -> true;
            };
        }
        return selection;
    }
}
