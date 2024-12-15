import java.util.Random;

public class BattleFields extends Locations {
    protected GameEnemies enemy;
    protected String item;
    protected int enemiesHealth;
    protected int enemiesMoney;
    protected int enemiesDamage;

    public BattleFields(Player p) {
        super(p, "Savaş Alanı");
    }

    @Override
    boolean onLocation() {
        Player player = this.getP();
        System.out.println("\n ⚔⚔⚔ SAVAŞ ALANINA HOŞGELDİN " + this.getP().getCharName() + " " + this.getP().getName());

        while (true) {
            if (player.getIsWin()) return false; // Kazanırsa oyun bitecek
            if (player.getHealth() <= 0) return false;

            System.out.println("\nİNCELEMEK İSTEDİĞİN CEPHEYİ SEÇ: ");
            System.out.println("""
                    1 - MAĞARA\
                    \t\tCanavar: ZOMBİ (1-3 Adet) \
                    \t\tEşya : Yemek (Food)\
                    \n2 - ORMAN\
                    \t\tCanavar: AYI (1-3 Adet) \
                    \t\tEşya : Odun (Firewood)\
                    \n3 - NEHİR\
                    \t\tCanavar: VAMPİR (1-3 Adet) \
                    \t\tEşya : Su (Water)\s
                    \n0 - GÜVENLİ EVE DÖN""");

            int choice = player.getManager().scanner.nextInt();

            switch (choice) {
                case 1 -> prepareFields("Mağara", "Yemek");
                case 2 -> prepareFields("Orman", "Odun");
                case 3 -> prepareFields("Nehir", "Su");
                case 0 -> {
                    System.out.println("SAVAŞ ALANINDAN ÇIKILDI.");
                    return false;
                }
                default -> System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
            }
        }
    }

    private void prepareFields(String locationName, String rewardItem) {
        Player player = this.getP();
        System.out.println(locationName + " bölgesine hoş geldiniz!");
        this.enemy = player.getManager().getEnemiesList().get(locationName);
        this.item = rewardItem;

        if (this.enemy != null) {
            calcEnemyPower(this.enemy);
            System.out.println("""
                    \nSavaş başlıyor! Hazır mısınız?
                    1 - Savaş
                    2 - Kaç""");
            int action = player.getManager().scanner.nextInt();

            if (action == 1) {
                fight();
            } else {
                System.out.println(locationName + " BÖLGESİNDEN ÇIKILDI!");
            }
        } else {
            System.out.println("Geçersiz bölge veya düşman bulunamadı!");
        }
    }

    void calcEnemyPower(GameEnemies enemy) {
        this.enemy = enemy;
        Random random = new Random();
        int enemyCount = random.nextInt(3 - 1 + 1) + 1; //max 3 min 1 adet

        this.setEnemiesHealth(enemy.getHealth() * enemyCount);
        this.setEnemiesDamage(enemy.getDamage() * enemyCount);
        this.setEnemiesMoney(enemy.getMoney() * enemyCount);

        System.out.println("Karşılaşacağınız düşman: " + enemy.getName());
        System.out.println("Düşman sayısı: " + enemyCount);
        System.out.println("Toplam düşman sağlığı: " + this.enemiesHealth);
        System.out.println("Toplam düşman hasarı: " + this.enemiesDamage);
        System.out.println("Kazanç: " + this.item + " ve " + this.enemiesMoney + "$");
        System.out.println("~Hasar gücünüz :" + this.getP().getDamage());
        System.out.println("~Sağlığınız :" + this.getP().getHealth());
        System.out.println("~Engelleme Gücünüz :" + this.getP().getBlocking());
    }

    private void fight() {
        Player player = this.getP();
        int blocking = player.getBlocking();


        System.out.println("\nSavaş başladı!\n");

        while (player.getHealth() > 0 && this.enemiesHealth > 0) {
            System.out.println("Saldırıyorsunuz...");
            this.enemiesHealth -= player.getDamage();
            System.out.println("~~Kalan düşman gücü: " + Math.max(0, this.enemiesHealth)); // Sağlık sıfırın altına düşemez

            if (this.enemiesHealth > 0) {
                if (blocking > 0) {
                    System.out.println("Düşman saldırısı zırhla engellendi! Kalan bloklama: " + (blocking - 1));
                    blocking--;
                    player.setBlocking(blocking);
                } else {
                    System.out.println("Düşman saldırıyor...");
                    player.setHealth(player.getHealth() - this.enemiesDamage);
                    System.out.println("~~Kalan sağlık: " + Math.max(0, player.getHealth())); // Sağlık sıfırın altına düşemez
                }
            }
        }
            if (player.getHealth() > 0) {
                System.out.println("Savaşı kazandınız!");
                rewardPlayer(); // Ödül yalnızca savaş bittikten sonra eklenir
            } else {
                System.out.println("Maalesef yenildiniz.");
                System.out.println("    T_T");
                System.out.println("  /(   )\\   ");
                System.out.println("    | |    ");
            }
            if (winControl()) { // Kazanma durumunu kontrol et
                player.setWin(true);
                System.out.println("\n\tTebrikler, oyunu kazandınız!");
                System.out.println("  \\(^_^)/  ");
                System.out.println("   (   )   ");
                System.out.println("    | |    ");
            }
    }

    private boolean winControl() {
        Player player = this.getP();

        boolean hasWater = player.getInventories().getItems().get("Su") > 0;
        boolean hasFood = player.getInventories().getItems().get("Yemek") > 0;
        boolean hasFirewood = player.getInventories().getItems().get("Odun") > 0;

        return hasWater && hasFood && hasFirewood;
    }

    private void rewardPlayer() {
        Player player = this.getP();
        player.setMoney(player.getMoney() + this.enemiesMoney);
        player.getInventories().setItems(this.item);

        System.out.println("Ganimetleriniz: " + this.item + " ve " + this.enemiesMoney + "$");
        System.out.println("Güncel sağlık: " + player.getHealth());
        System.out.println("Güncel varlık: " + player.getMoney());
        System.out.println("Güncel envanter: " + player.getInventories().printItems());


    }


    public void setEnemiesDamage(int enemiesDamage) {
        this.enemiesDamage = enemiesDamage;
    }

    public void setEnemiesHealth(int enemiesHealth) {
        this.enemiesHealth = enemiesHealth;
    }

    public void setEnemiesMoney(int enemiesMoney) {
        this.enemiesMoney = enemiesMoney;
    }

}
