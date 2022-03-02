namespace FrameManager.CRC;
public class CRC        //classe che gestisce il CRC
{
    //rileva eventuali errori in un frame appena ricevuto. Sono presenti errori quando il resto contiene anche solo un 1
    public static void Decode(string dividendo, string divisore)       
    {
        string result; //conterrà il valore del frame dopo ogni divisione
        if (dividendo.Length < divisore.Length)
        {
            Console.WriteLine("Il dividendo deve essere più grande del divisore.");
            return;
        }

        do
        {
            result = Division(dividendo, divisore);    
            dividendo = result;
            if (result.IndexOf('1') == -1) break;   //se il frame è composto da tutti zeri, allora esco
        }while (result.Length >= divisore.Length);      //fino a quando la grandezza del frame è >= alla grandezza del divisiore, allora divido


        if (result.IndexOf('1') == -1)     
            Console.WriteLine("\n\n\tNon sono stati rilevati errori nel frame inviato.");
        else 
            Console.WriteLine("\n\n\tE' presente qualche errore nel frame. Eh, non va bene.");
    }

    //inserisce i bit crc in un frame. fare override dove passo come parametro solo il payload, e non payload + bit a zero del crc
    public static void Encode(string dividendo, string divisore)
    {
        string InitialFrame = dividendo;    //conterrà il valore iniziale del frame
        string result = dividendo;

        if (dividendo.Length < divisore.Length)
        {
            Console.WriteLine("Il dividendo deve essere più grande del divisore.");
            return;
        }

        do
        {
            result = Division(dividendo, divisore);     
            dividendo = result;
            if (result.IndexOf('1') == -1) break; 
            
        } while (result.Length >= divisore.Length);


        while (result.Length != divisore.Length-1) result = "0" + result;
        Console.WriteLine($"\n\n\tFrame finale inviabile: {InitialFrame.Substring(0,InitialFrame.Length-divisore.Length+1)+result}");  //todo: sostituire gli ultimi bit del frame
    }

    //divisione tra due stringhe di bit.
    private static string Division(string dividendo, string divisore)       
    {
        string quoziente = "";      // conterrà il frame a seguito della divisione
        string untouched = dividendo.Substring(divisore.Length);    // parte che non viene toccata dalla divisione.
        string touched = dividendo.Substring(0, divisore.Length);   // parte di cui si effettua la divisione.

        Console.Write($"\n\t{dividendo} ({touched})/{divisore} = ");
        for (int i = 0; i < divisore.Length; i++)
        {
            char c = (char)(dividendo[i] ^ divisore[i]);        //effettuo una XOR tra i bit aventi indice uguale nel dividendo e nel divisore
            c += '0';   //conversione intero-carattere (+48)
            quoziente += c;
        }   
        quoziente += untouched; //aggiungo la parte non toccata del frame iniziale al quoziente della divisione.

        if (quoziente.IndexOf('1') == -1)           //se il frame contiene tutti zeri, allora ritorno il quoziente.
        {
            Console.WriteLine($"{quoziente}");
            return quoziente;
        }
        else    //se contiene almeno un 1
        {
            string newString = quoziente.Substring(quoziente.IndexOf('1'));     //del frame finale, prendo solo la parte dopo il primo 1
            Console.Write($"{quoziente} ({newString})");
            return newString;
        }
       
    }

    //PROBLEMI: CAPITA CHE VENGANO INSERITI UN NUMERO MINORE DI BIT RISPETTO A QUELLI RICHIESTI NEL FRAME (perchè gli zero precedenti vengono tagliati)
    //PROBLEMA DI COME VIENE VISUALIZZATO IL MESSAGGIO.
    //PROBLEMA: NON FINISCE IL PROGRAMMA QUANDO IL RISULTATO E' PARI A 0. (codifica CRC)
    //TODO: ottimizzare il programma (non credo sia ottimizzato al massimo)
}
