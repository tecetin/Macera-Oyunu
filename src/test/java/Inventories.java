import java.util.HashMap;
import java.util.Map;

public class Inventories {

    private final Map<String, Integer> items = new HashMap<>();
    private final Map<GameGuns, Integer> guns = new HashMap<>();
    private final Map<Shields, Integer> shields = new HashMap<>();

    public Inventories() {
        this.items.put("Su", 0);
        this.items.put("Yemek", 0);
        this.items.put("Odun", 0);
        this.guns.put(new GameGuns(0, "Yumruk", 0, 0), 1);
        this.shields.put(new Shields("Hırka", 0, 0, 0), 1);
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(String item) {
        items.put(item, items.getOrDefault(item, 0) + 1);
        System.out.println("\n" + item + " envantere eklendi!");    }

    public Map<Shields, Integer> getShields() {
        return shields;
    }

    public Map<GameGuns, Integer> getGuns() {
        return guns;
    }

    public void setShields(Shields shield) {
        shields.put(shield, shields.getOrDefault(shield, 0) + 1);
        System.out.println("\n" + shield.getName() + " envantere eklendi!");
    }

    public void setGuns(GameGuns gun) {
        guns.put(gun, guns.getOrDefault(gun, 0) + 1);
        System.out.println(gun.getName() + " envantere eklendi!");
    }

    public String printGuns() {
        StringBuilder result = new StringBuilder();
        guns.forEach((gun, count) -> result.append(gun.getName()).append(" x").append(count).append(" "));
        return result.toString();
    }

    public String printShields() {
        StringBuilder result = new StringBuilder();
        shields.forEach((gun, count) -> result.append(gun.getName()).append(" x").append(count).append(" "));
        return result.toString();
    }

    public String printItems() {
        StringBuilder result = new StringBuilder();
        items.forEach((item, count) -> result.append(item).append(" x").append(count).append(" "));
        return result.toString();
    }

    @Override
    public String toString() {
        return "--> İhtiyaçlar: " + printItems() +
                " Silahlar: " + printGuns() +
                " Zırhlar: " + printShields();

    }
}
