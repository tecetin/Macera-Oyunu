public class GameEnemies {

    private final int ID;
    private final String name;
    private final int damage;
    private final int health;
    private final int money;


    GameEnemies(int id, String name, int damage, int health, int money) {
        ID = id;
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.money = money;
    }

    @Override
    public String toString() {
        return "ID: " + this.ID +
                ", Name: " + this.name +
                ", Damage: " + this.damage +
                ", Health: " + this.health +
                ", Earn: " + this.money;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public int getMoney() {
        return money;
    }
}
