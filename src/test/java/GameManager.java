import java.util.*;

public class GameManager {

    protected final Scanner scanner = new Scanner(System.in);

    private final List<GameChars> charsList = new ArrayList<>();
    private final List<GameGuns> gunList = new ArrayList<>();
    private final List<Shields> shieldList = new ArrayList<>();
    private final Map<String, GameEnemies> enemiesList = new HashMap<>();
    private final List<String> locationList = new ArrayList<>();
    private final Player p;

    int selectedPlayer;


    public GameManager(Player p) {
        this.p = p;
        // String name, int ID, int damage, int health, int earn
        charsList.add(new GameChars("Samuray", 1, 5, 21, 15, 0, false));
        charsList.add(new GameChars("Okçu", 2, 7, 18, 20, 0, false));
        charsList.add(new GameChars("Şovalye", 3, 8, 24, 5, 0, false));

        //int id, String name, int damage, int health, int earn
        enemiesList.put("Mağara", new GameEnemies(1, "Zombi", 3, 10, 4));
        enemiesList.put("Orman", new GameEnemies(2, "Vampir", 4, 14, 7));
        enemiesList.put("Nehir", new GameEnemies(3, "Ayı", 7, 20, 12));

        //int id, String name, int damage, int earn
        gunList.add(new GameGuns(1, "Tabanca", 2, 5));
        gunList.add(new GameGuns(2, "Kılıç", 3, 35));
        gunList.add(new GameGuns(3, "Tüfek", 7, 45));

        //String name, int protection, int earn
        shieldList.add(new Shields("Hafif Zırh", 1, 1, 15));
        shieldList.add(new Shields("Orta Zırh", 2, 3, 25));
        shieldList.add(new Shields("Ağır Zırh", 3, 5, 40));

        locationList.add(0, "Güvenli Ev");
        locationList.add(1, "Mağaza");
        locationList.add(2, "Savaş Alanları");

    }

    public List<GameGuns> getGunList() {
        return gunList;
    }

    public List<Shields> getShieldList() {
        return shieldList;
    }

    public Map<String, GameEnemies> getEnemiesList() {
        return enemiesList;
    }

    public Player getP() {
        return p;
    }

    boolean selectChar() {

        System.out.println("\nSenin kelimelerinle şekillenen bu karanlık dünyada kaderini seç ve destansı hikayeni başlat!");
        System.out.print("Lütfen ismini gir cesaret timsali: ");

        this.p.setName(scanner.nextLine());

        System.out.println("\nMerhaba " + this.p.getName() + "!!\n" +
                "\nBaşlamak için sana en uygun savaşçıyı seç -_-!\n");

        while (true) {
            printList(charsList);
            System.out.println("Savaştan kaçmak için 0'a bas!!");

            selectedPlayer = scanner.nextInt();

            if (selectedPlayer == 0) {
                System.out.println("Hoşçakalın...");
                return false;
            }
            if (selectedPlayer > charsList.size()) {
                System.out.println("Geçerli bir savaşçı ID'si giriniz !!\n");
            } else {
                p.initPlayer(charsList.get(selectedPlayer - 1)); //-----------------------------------------------önemli, player'a karakterin özellikleri atanıyor
                System.out.println("\nİyi şanslar " + this.p.getCharName() + " " + p.getName() + "!!\n" +
                        "İşte sahip oldukların --> \n" + this.p.toString() + "\n");
                break;
            }
        }
        return true;
    }

    void selectLocation() {
        boolean isRunning = true;
        int returnSafePlace = 0;
        int selectedLocation;

        while (isRunning) {

            if (returnSafePlace == 1) {
                selectedLocation = 1;
            } else {
                System.out.println("Bir konum seçin:");
                System.out.println("1 - Güvenli Ev - Burada düşman yoktur, çetin savaşlar sonrası gelip yaralarını sarabilirsin.");
                System.out.println("2 - Mağaza - Ekipman ve Hasar Engelleyici Zırh Alabilirsin");
                System.out.println("3 - Savaş Alanı");
                System.out.println("0 - Oyundan Çık");

                selectedLocation = this.p.getManager().scanner.nextInt();
            }

            switch (selectedLocation) {
                case 0 -> {
                    System.out.println("Oyundan çıkılıyor. Hoşçakal!");
                    isRunning = false;
                }
                case 1 -> {
                    SafePlace safePlace = new SafePlace(this.p);
                    if (!safePlace.onLocation()) returnSafePlace = 0;
                }
                case 2 -> {
                    Store store = new Store(this.p);
                    if (!store.onLocation()) returnSafePlace = 1; //storeda alanında 0'a basınca güvenli eve geçecek
                }
                case 3 -> {
                    BattleFields battleFields = new BattleFields(this.p);
                    if (!battleFields.onLocation()) returnSafePlace = 1; //savaş alanında 0'a basınca güvenli eve geçecek
                    if (this.p.getIsWin()) isRunning = false;
                    if (this.p.getHealth() <= 0) isRunning = false;
                }
                default -> System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
            }
        }
    }

    public <T> void printList(List<T> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Liste boş!");
        } else {
            for (T item : list) {
                System.out.println(item);
            }
        }
    }


}
