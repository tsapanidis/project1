public class Main implements Comparable<Main>{

        private final String plates;

        public Main(String firstName) {
            if (firstName == null)
                throw new NullPointerException();
            this.plates = firstName;

        }

        public String firstName() { return plates; }


        public boolean equals(Object o) {
            if (!(o instanceof Main))
                return false;
            Main n = (Main) o;
            return n.plates.equals(plates);
        }

        /* public int hashCode() {
             return 31*firstName.hashCode();
        }*/

        public String toString() {
            return plates;
        }

        public int compareTo(Main n) {
            int lastCmp = plates.compareTo(n.plates);
            return (lastCmp != 0 ? lastCmp : plates.compareTo(n.plates));
        }
    }


