
namespace FrameManager.CRC;
public class CRCSelection{
    public static void Initialize()
    {
        Console.ReadLine();
        Console.Write("\nScelte: \n1: inserisci il frame inserendo i bit\n2: inserisci il frame sotto forma di caratteri\nScelta: ");
        switch (Console.Read())
        {
            case '1':
                DecodeFromBits();
                break;
            case '2':
                DecodeFromCharacters();
                break;
            default:
                Console.WriteLine("Inserisci una delle scelte, cazzo!");
                break;
        }
    }

    //per stringhe dei bit
    private static void DecodeFromBits()
    {
        Console.ReadLine();
        Console.Write("\nInserisci il frame: ");
        string dividendo = Console.ReadLine();
        Console.Write("Inserisci il polinomio generatore: ");
        string divisore = Console.ReadLine();

        if (dividendo != null && divisore != null)
        {
            if (CheckBit(dividendo) && CheckBit(divisore))
                Selection(dividendo, divisore);
            else Console.WriteLine("Operazione non eseguibile con queste stringhe di bit.");
        }
    }


    //per caratteri, poi convertiti in bit
    private static void DecodeFromCharacters()
    {
        string dividendo = "";
        Console.ReadLine();
        Console.Write("Inserisci una stringa: ");
        string stringa = Console.ReadLine();

        //conversione stringa-bit
        if (!string.IsNullOrEmpty(stringa))
        {
            foreach (char character in stringa)
                dividendo += Convert.ToString(character, 2);

            Console.Write("Inserisci il divisore (in codice binario): ");
            string divisore = Console.ReadLine();

            if (divisore != null && dividendo != null)
            {
                if (CheckBit(divisore) && CheckBit(dividendo))
                    Selection(dividendo, divisore);

                else Console.WriteLine("Operazione non eseguibile con queste stringhe di bit.");
            }
        }
        else Console.WriteLine("La stringa non può essere vuota!");
    }

    //dopo aver inserito i bit, chiedo cosa vuole fare l'utente con questi bits 
    private static void Selection(string dividendo, string divisore)
    {

        Console.Write($"\n\nDividendo: {dividendo}\t\tDivisore: {divisore}");
        Console.Write("\nScelte: \n1: Codifica CRC\n2: Decodifica CRC\nScelta: ");
        var scelta = Console.ReadLine();
        switch (scelta)
        {
            case "1":
                CRC.Encode(dividendo, divisore);
                break;
            case "2":
                CRC.Decode(dividendo, divisore);
                break;
            default:
                Console.WriteLine("Inserisci una delle opzioni, cazzo!");
                break;
        }
    }

    //controlla che le due stringhe di bit contengano solo 0 e 1
    private static bool CheckBit(string bitSequence)
    {
        foreach (char bit in bitSequence)
            if (bit != '1' && bit != '0') return false;

        return true;    //se il controllo ha avuto successo   
    }
}