import java.util.concurrent.atomic.AtomicInteger;

public class Sugoma {
    final String sugoma;
    private int su;
    protected AtomicInteger ciao = new AtomicInteger();

    public Sugoma(String sugoma, int su){
        this.sugoma = sugoma;
        this.su = su;
    }

    public void hello(){
        System.out.println("hello "+this.sugoma+"!");
    }
    public void increment(){
        ciao.incrementAndGet();
    }
}

class Dennis extends Sugoma {
    String cognome;
    public Dennis(String sugoma, int su, String cognome) {
        super(sugoma, su);
        this.cognome= cognome;
    }

    @Override
    public void hello(){
        System.out.printf("ciao %s %s!",sugoma,cognome);
    }

}
