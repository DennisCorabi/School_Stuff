


void initialize()
{
    string BitSequence = "1011010";
    string Divisore = FillWithZeros(BitSequence.Length, "1001");
    Console.WriteLine($"Dividendo: {BitSequence}\t Divisore: {Divisore}");
    Division(BitSequence, Divisore);
}

void Division(string dividendo, string divisore)
{
    char[] quoziente;
    if (dividendo.Length != divisore.Length)
        return;

    for (int i=0; i<dividendo.Length; i++)
    {
        char x = (char) (dividendo[i] + divisore[i] );
        if (x == 2) x = '0';

        quoziente[i] = x;
    }

    Console.WriteLine(quoziente);
   
}

string FillWithZeros(int lenght, string bitSeq)
{
    while (bitSeq.Length != lenght) bitSeq += "0";
    return bitSeq;
}

initialize();