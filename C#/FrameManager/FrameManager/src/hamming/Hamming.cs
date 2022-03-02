namespace FrameManager.Hamming;

public class Hamming
{

    /*funzione per rilevare un'anomalia (errore) nel frame appena ricevuto. 
     * se presente un'anomalia, la corregge e restituisce il frame corretto.*/

    public static void Decode(string frame)
    {

        int anomalyPosition = 0;                //posizione dell'anomalia: parte da 1 
        int parityBits = GetParityBits(frame);
        for (int i = 0; i < parityBits; i++)        //per ogni bit di parità
        {
            int index = (int)Math.Pow(2, i);    //posizione dei bit di parità: parte da 1
            int numberOf1s = BitCount(index, frame);
            if (numberOf1s % 2 != 0) anomalyPosition += index;
        }

        if (anomalyPosition == 0) Console.WriteLine("\nNon è stato trovato alcun errore nel frame.");
        else if (anomalyPosition != 0 && anomalyPosition <= frame.Length)   
        {
            Console.WriteLine($"\nE' stato trovato un errore nel frame (anomalia posto {anomalyPosition}).");
            string newFrame = FlipBit(anomalyPosition, frame);
            Console.WriteLine($"\nFrame corretto:{newFrame}");
        }
        else Console.WriteLine($"\nE' stato rilevato un errore non correggibile nel frame (anomalia: {anomalyPosition}).");
    }

    //funzione per costruire il frame (dati+ridondanza): setta a 1 o 0 i bit di ridondanza.
  
    public static void Encode(string frame)
    {
        int lenght = frame.Length;                 
        int parityBits = GetParityBits(frame);
        for (int i = 0; i < parityBits; i++)
        {
            int index = (int)Math.Pow(2, i) - 1;        //indice del bit di parità: parte da 0.
            frame = frame.Substring(0, index) + '0' + frame.Substring(index);   //inserisco il bit di parità (valore 0) nel frame

        }
         
        Console.WriteLine($"Frame con parità ({lenght}+{parityBits}): {frame}");    
        for (int i = 0; i < parityBits; i++)
        {
            int BitPosition = (int)Math.Pow(2, i);
            int numberOf1s = BitCount(BitPosition, frame);
            if (numberOf1s % 2 != 0) frame = FlipBit(BitPosition, frame);   //se i bit a 1 sono dispari, flippo il bit di parità
        }
        Console.WriteLine($"Frame inviabile: {frame}");
    }

    private static int BitCount(int parityBitPosition, string frame)
    {
        int parityBitIndex = parityBitPosition - 1;     //indice del bit di parità: parte da 0
        int count = 0;
        for (int i = parityBitIndex; i < frame.Length; i += parityBitPosition * 2)
        {
            for (int j = 0; j < parityBitPosition; j++)
            {
                if ((i + j) == frame.Length) break;        //cercare di rimuovere il break
                else if (frame[i + j] == '1') count++;
            }

        }
        return count;
    }

    //funzione per calcolare i bit di parità minimi per costruire un frame.
    private static int GetParityBits(string frame)
    {
        int parityBits = 0;
        bool isCorrect = false;     //controlla se la disuguaglianza di hamming è stata soddisfatta.
        do
        {
            parityBits++;
            isCorrect = (frame.Length+1+parityBits)<=MathF.Pow(2,parityBits) ? true: false;

        } while (isCorrect==false);

        return parityBits;
    }


    //funzione per flippare un bit in un frame (ottimizzare)
    private static string FlipBit(int index, string frame)
    {
        int anomalyIndex = index - 1;
        char[] newFrame = frame.ToCharArray();

        char bitToFlip = newFrame[anomalyIndex] == '0' ? '1' : '0';
        newFrame[anomalyIndex] = bitToFlip;

        string newFrameString = "";
        foreach (char bit in newFrame)
            newFrameString += bit;

        return newFrameString;
    }
}