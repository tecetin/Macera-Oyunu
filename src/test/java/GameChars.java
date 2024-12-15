public record GameChars(String name, int ID, int damage, int health, int money, int blocking, boolean isWin) {

    @Override
    public String toString() {
        return "ID: " + this.ID +
                ", İsim: " + this.name +
                ", Hasar Gücü: " + this.damage +
                ", Sağlık: " + this.health +
                ", Varlık: " + this.money +
                ", Engelleme Gücü: " + this.blocking;
    }
}



