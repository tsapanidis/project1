import java.util.Random;

public class RandomNames {
    public String GenName() {
        String names[] = {"Andreas","John","Giorgos","Panagiotis","Giannis"
                        ,"Nikos","Thanasis","Dimitris","Christos","Kwstas"};
        return names[new Random().nextInt(names.length)];

    }

    public String GenLname() {
        String names[] = {"Argyropoulos","Papadopoulos","Kalampokis","Tsapanidis","Kastis"
                ,"Papaioanou","Romero","Christodoulou","Carmack","Kwstis"};
        return names[new Random().nextInt(names.length)];

    }

    public String GenAddress() {
        String names[] = {"El.benizelou","Kolokotroni","Papaflesa","Tsakalou","Skoufa"
                ,"Aitolou","Omonias","Khfhsias","Pr.Kentro","Oinopneumatos"};
        String address = names[new Random().nextInt(names.length)]+" "+(int)(Math.random()*250);
        return address;

    }

    public String GenBrands() {
        String names[] = {"Toyota","Audi","Volkswagen","Bugati","Merchendes"
                ,"Ferrari","Mixon","Amercania","UAC","Eggman"};
        return names[new Random().nextInt(names.length)];

    }

    public String GenModels() {
        String names[] = {"TFC UD4","TSI 5","FSI 7","koutsimaria","ROE D3"
                ,"MS Mach V","Alan V","Kseftila","Mobile MC7","FI-INF"};
        return names[new Random().nextInt(names.length)];

    }

}
