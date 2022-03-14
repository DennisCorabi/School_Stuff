import com.company.person.Docente;
import com.company.person.Personale;
import com.company.person.Studenti;
import com.company.structures.Classi;

public class Main {
    public static void main(String[] args) throws Exception {

        //inizializzo due classi
        Classi classe1 = new Classi(5,'A',"Informatica");
        Classi classe2 = new Classi(4,'b',"Meccanica");

        //inizializzo un docente e un bidello
        Docente virginio = new Docente("Virginio","Recalcati","26/09/1967","Italiano",15);
        Personale antonio = new Personale("Antonio","Vespia","24/04/1977",10);
        virginio.addClasse(classe1);    //setto la classe del docente

        System.out.println(virginio.getMateria());
        System.out.println(antonio.getStipendio()+"€");

        //creo due studenti
        Studenti studente1 = new Studenti("Dennis","corabi","28/12/2004",classe1);
        Studenti studente2 = new Studenti("Federico","Zotti","26/01/2004",classe1);

        //per ogni studente, stampo il suo nome
        for (Studenti studente: classe1.getAlunni()){
            System.out.println(studente.getNome());
        }
        //...poi cambio la classe a tutti e due
        studente1.setClasse(classe2);
        studente2.setClasse(classe2);


        //stampo gli alunni delle due classi, per far vedere che sono cambiate
        System.out.println(classe1.getAlunni());
        System.out.println(classe2.getAlunni());
    }
}
