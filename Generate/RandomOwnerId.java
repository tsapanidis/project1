import java.rmi.server.UID;
import java.util.concurrent.ThreadLocalRandom;

public class RandomOwnerId {
        /**
         * Build and display some UID objects.
         */
        public int Genoid () {
            //for (int idx=0; idx<10; ++idx){
            int randomNum = ThreadLocalRandom.current().nextInt(1, 50);
                return randomNum;
           // }

    }
}
