


void initialize()
{
    string BitSeq1 = "01011010011010010000";
    string BitSeq2 = "11011";
    //BitSeq2 = FillWithZeros(BitSeq1.Length, BitSeq2);

    Division(BitSeq1, BitSeq2);
}

void Division(string dividendo, string divisore)
{
    string quoziente = "";
    if (divisore.Length > dividendo.Length)
        return;


  
    string untouched = dividendo.Substring(divisore.Length);
    string touched = dividendo.Substring(0, divisore.Length);

    Console.Write($"\nDividendo: {touched}\t Divisore: {divisore}\t\t");
    for (int i = 0; i < divisore.Length; i++)
    {

        char c = (char)(dividendo[i] ^ divisore[i]);
        c += '0';
        quoziente += c;
    }

    quoziente += untouched;


    var newstring = quoziente.Substring(quoziente.IndexOf('1'));
    Console.WriteLine($"Risultato: {quoziente} ({newstring})");

    Division(quoziente.Substring(quoziente.IndexOf('1')), divisore);

}
initialize();



/*
 * Programma che per ora funziona solo con certi frame.
 * I problemi di questo programma per ora sono: 
 * - problema con gli zeri alla fine dell'esercizio (non trova un uno e perciò crea un'eccezione) 
 */