public class Main implements Comparable<Main>{

        private final String firstName;

        public Main(String firstName) {
            if (firstName == null)
                throw new NullPointerException();
            this.firstName = firstName;

        }

        public String firstName() { return firstName; }


        public boolean equals(Object o) {
            if (!(o instanceof Main))
                return false;
            Main n = (Main) o;
            return n.firstName.equals(firstName);
        }

         public int hashCode() {
             return 31*firstName.hashCode();
        }

        public String toString() {
            return firstName;
        }

        public int compareTo(Main n) {
            int lastCmp = firstName.compareTo(n.firstName);
            return (lastCmp != 0 ? lastCmp : firstName.compareTo(n.firstName));
        }
    }


