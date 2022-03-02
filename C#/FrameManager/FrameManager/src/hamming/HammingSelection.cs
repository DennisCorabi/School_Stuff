namespace FrameManager.Hamming;
public class HammingSelection
{
    public static void Initialize()
    {
        Console.ReadLine();
        Console.WriteLine("\nInserisci il frame: ");
        string frame = Console.ReadLine();

        Console.Write("\nScelte:\n1: Codifica il frame con i codici di Hamming\n2: Decodifica un frame con i bit di parita'\nScelta: ");
        if (frame != null && CheckBit(frame))
        {
            switch (Console.Read())
            {
                case '1':
                    Hamming.Encode(frame);
                    break;
                case '2':
                    Hamming.Decode(frame);
                    break;
                default:
                    Console.WriteLine("Inserisci un opzione valida!");
                    break;
            }
        }
        else Console.WriteLine("\nInserisci una sequenza di bit valida e non nulla!");

    }
    private static bool CheckBit(string bitSequence)
    {
        foreach (char bit in bitSequence)
            if (bit != '1' && bit != '0') return false;

        return true;    //se il controllo ha avuto successo   
    }

}
