public class Player {

    private final GameManager manager;
    private String name;
    private String charName;
    private int damage;
    private int health;
    private int money;
    private int blocking;
    private final Inventories inventories;
    private int initialHealth;
    private boolean isWin;

    public Player() {
        this.inventories = new Inventories();
        this.manager = new GameManager(this);
    }


    public void initPlayer(GameChars c) {
        this.charName = c.name();
        this.damage = c.damage();
        this.health = c.health();
        this.initialHealth = c.health();
        this.money = c.money();
        this.blocking = c.blocking();
        this.isWin = c.isWin();
    }

    @Override
    public String toString() {
//        return "Silahlar: " + (this.inventories.printGuns().isEmpty() ? "Hiç silah yok." : this.inventories.printGuns()) +
//                "\nZırhlar: " + (this.inventories.printShields().isEmpty() ? "Hiç zırh yok." : this.inventories.printShields()) +
//                "\nSaldırı gücü: " + this.damage +
//                "\nSağlık: " + this.health +
//                "\nVarlık: " + this.money;
        return String.format("""
        Oyuncu: %s (%s)
        Sağlık: %d / %d
        Hasar: %d
        Bloklama Gücü: %d
        Para: %d
        Envanter: %s
        """,
                this.name, this.charName, this.health, this.initialHealth, this.damage, this.blocking, this.money,
                this.inventories);
    }

    public boolean getIsWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }


    public int getInitialHealth() {
        return initialHealth;
    }

    public GameManager getManager() {
        return manager;
    }

    public int getBlocking() {
        return this.blocking;
    }

    public void setBlocking(int blocking) {
        this.blocking = blocking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Inventories getInventories() {
        return inventories;
    }

}
