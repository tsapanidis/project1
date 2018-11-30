import java.util.Random;

public class RandomNames {
    public String GenName() {
        String names[] = {"Andreas","Nektarios","Giorgos","Panagiotis","Giannis"
                        ,"Nikos","Thanasis","Dimitris","Christos","Kwstas"};
        return names[new Random().nextInt(names.length)];

    }

}
