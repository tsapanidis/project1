import java.util.concurrent.ThreadLocalRandom;

public class RandomVehicleId {
    public int Genvid() {

// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive
        int randomNum = ThreadLocalRandom.current().nextInt(1, 150);
        return randomNum;
    }
}
