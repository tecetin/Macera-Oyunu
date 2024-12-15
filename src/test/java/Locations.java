public abstract class Locations {

    private final Player p;

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public Locations(Player p, String name) {
        this.p = p;
        this.name = name;
    }

    abstract boolean onLocation();

    public Player getP() {
        return p;
    }

    public String getName() {
        return name;
    }
}
