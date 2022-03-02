using System;
using FrameManager.CRC;
using FrameManager.Hamming;

namespace FrameManager;
/*
 * IMPORTANTE: IMPLEMENTARE L'INSERIMENTO DEI DATI DA RIGA DI COMANDO.
 */
public class MainClass
{
    public static void Main()
    {
        Console.Write("Scelte:\n1:Hamming\n2:CRC\nScelta: ");
        switch (Console.Read())
        {
            case '1':
                HammingSelection.Initialize();
                break;
            case '2':
                CRCSelection.Initialize();
                break;
            default:
                Console.WriteLine("Inserisci una delle scelte, cazzo!");
                break;
                
        }

    }
}


