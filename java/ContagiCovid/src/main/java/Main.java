import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader("src/main/resources/dpc-covid19-ita-andamento-nazionale.csv"));
        String[] content;
        while ((content = reader.readNext())!=null) {

            System.out.println(Arrays.toString(content));
        }

    }
}
