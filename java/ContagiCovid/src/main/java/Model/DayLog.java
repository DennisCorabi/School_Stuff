public class DayLog {
    private String data;
    private int ricoveratiConSintomi;
    private int terapiaIntensivaTotali;
    private int positiviCorrenti;
    private int positiviOggi;
    private int decedutiTotali;
    private int tamponiTotali;

    public DayLog(String data, int ricoveratiConSintomi, int terapiaIntensiva, int positiviTotali, int positiviOggi, int decedutiTotali, int tamponiTotali) {
        this.data = data;
        this.ricoveratiConSintomi = ricoveratiConSintomi;
        this.terapiaIntensivaTotali = terapiaIntensiva;
        this.positiviCorrenti = positiviTotali;
        this.positiviOggi = positiviOggi;
        this.decedutiTotali = decedutiTotali;
        this.tamponiTotali = tamponiTotali;
    }

    @Override
    public String toString() {
        return "DayLog{" +
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
