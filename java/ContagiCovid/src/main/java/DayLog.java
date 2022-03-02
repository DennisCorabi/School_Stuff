public class Day {
    private String Data;
    private int ricoveratiConSintomi;
    private int terapiaIntensiva;
    private int positiviTotali;
    private int positiviOggi;
    private int decedutiTotali;
    private int tamponiTotali;

    public Day(String data, int ricoveratiConSintomi, int terapiaIntensiva, int positiviTotali, int positiviOggi, int decedutiTotali, int tamponiTotali) {
        Data = data;
        this.ricoveratiConSintomi = ricoveratiConSintomi;
        this.terapiaIntensiva = terapiaIntensiva;
        this.positiviTotali = positiviTotali;
        this.positiviOggi = positiviOggi;
        this.decedutiTotali = decedutiTotali;
        this.tamponiTotali = tamponiTotali;
    }
}
