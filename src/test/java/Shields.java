    public class Shields {

        private final int ID;
        private final String name;
        private final int protection;
        private final int price;

        public Shields(String name, int ID, int protection, int price) {
            this.name = name;
            this.ID = ID;
            this.protection = protection;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getProtection() {
            return protection;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "ID: " + this.ID +
                    ",İsim: " + this.name +
                    ", Hasar Engelleme: " + this.protection +
                    ", Değer $: " + this.price;
        }
    }
