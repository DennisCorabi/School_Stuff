package Model;

public class DayLog {
    private final String data;
    private final int ricoveratiConSintomi;
    private final int terapiaIntensivaTotali;
    private final int positiviCorrenti;
    private final int positiviOggi;
    private final int decedutiTotali;
    private final int tamponiTotali;

    public DayLog(String data, int ricoveratiConSintomi, int terapiaIntensiva, int positiviTotali, int positiviOggi, int decedutiTotali, int tamponiTotali) {
        this.data = data;
        this.ricoveratiConSintomi = ricoveratiConSintomi;
        this.terapiaIntensivaTotali = terapiaIntensiva;
        this.positiviCorrenti = positiviTotali;
        this.positiviOggi = positiviOggi;
        this.decedutiTotali = decedutiTotali;
        this.tamponiTotali = tamponiTotali;
    }

    public String getData() {
        return data;
    }

    public int getRicoveratiConSintomi() {
        return ricoveratiConSintomi;
    }

    public int getTerapiaIntensivaTotali() {
        return terapiaIntensivaTotali;
    }

    public int getPositiviCorrenti() {
        return positiviCorrenti;
    }

    public int getPositiviOggi() {
        return positiviOggi;
    }

    public int getDecedutiTotali() {
        return decedutiTotali;
    }

    public int getTamponiTotali() {
        return tamponiTotali;
    }

    @Override
    public String toString() {
        return "Model.DayLog{" +
                "data='" + data + '\'' +
                ", ricoveratiConSintomi=" + ricoveratiConSintomi +
                ", terapiaIntensivaTotali=" + terapiaIntensivaTotali +
                ", positiviCorrenti=" + positiviCorrenti +
                ", positiviOggi=" + positiviOggi +
                ", decedutiTotali=" + decedutiTotali +
                ", tamponiTotali=" + tamponiTotali +
                '}';
    }
}
