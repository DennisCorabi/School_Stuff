import com.company.databases.DBjava;
import com.company.person.Studenti;

public class Main {
    public static void main(String[] args) throws Exception {

        Studenti studente1 = new Studenti(17,"dennis","28/09/2004");
        DBjava database = new DBjava("jdbc:mysql://localhost:3306/helloSQL", "sommo", "Idduiddu1!");

        database.GETreq("*");
    }
}
