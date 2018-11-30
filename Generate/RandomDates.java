import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class RandomDates {
    public Date GenDate() {
       // for (int i = 0; i < 10; i++) {
            Date randomDate = createRandomDate(2018, 2019);
            return randomDate;
       // }
    }

    public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static Date createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date=year+"-"+month+"-"+day;
        try {
            return format.parse(date);
        }catch(ParseException pe){
            pe.printStackTrace();
            return new Date();
        }
    }
}
