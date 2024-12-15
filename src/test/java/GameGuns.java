public class GameGuns {

    private final int ID;
    private final String name;
    private final int damage;
    private final int price;

    public GameGuns(int id, String name, int damage, int price) {

        this.ID = id;
        this.name = name;
        this.damage = damage;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ID: " + this.ID + ", İsim: " + this.name + ", Hasar Gücü: " + this.damage + ", Değer $: " + this.price;
    }


}
