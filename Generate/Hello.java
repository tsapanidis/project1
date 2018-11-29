public class Hello {
    public String genPlates() {

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            char ch = (char) (Math.random() * 26 + 'A');
            s.append(ch);
        }
        s.append('-');
        for (int i = 0; i < 4; i++) {
            char digit1 = (char) (Math.random() * 10 + '0');
            s.append(digit1);
        }
        System.out.println("Random vehicle plate number: " + s );

        //Check if plate format is valid: (3letters) + -(dash) + 4 digits
       // if ((s.toString().matches("[A-Z]{3}[-][\\d]{4}")) & (s.toString().length() == 8) ) {
            return s.toString();
      //  }
    }
}



