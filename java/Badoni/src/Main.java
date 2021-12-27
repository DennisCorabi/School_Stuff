import com.company.person.Docente;
import com.company.person.Personale;
import com.company.person.Studenti;
import com.company.structures.Classi;

public class Main {
    public static void main(String[] args) throws Exception {

        Classi classe1 = new Classi(5,'A',"Informatica");
        Classi classe2 = new Classi(4,'b',"Meccanica");

        Docente virginio = new Docente("Virginio","Recalcati","26/09/1967","Italiano",15);
        Personale antonio = new Personale("Antonio","bo","24/04/1977",10);
        virginio.addClasse(classe1);

        System.out.println(virginio.getMateria());
        System.out.println(antonio.getStipendio()+"€");

        Studenti studente1 = new Studenti("dennis","corabi","28/12/2004",classe1);
        Studenti studente2 = new Studenti("Federico","Zotti","26/01/2004",classe1);

        for (Studenti studenti:classe1.getAlunni()){
            System.out.println(studenti.getEta());
        }

    }
}
