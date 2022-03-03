using Newtonsoft.Json;

namespace HellodotNet;

public class FileManager
{
    class Sale
    {
        [JsonProperty ("Total")]
        public float Value { get; set; }
    }

    //crea una directory nel percorso specificato
    public string CreateDirectory(string path, string name)
    {
        string directoryPath = Path.Combine(path, name);
        bool doesDirectoryExist = Directory.Exists(directoryPath);
        if (!doesDirectoryExist)        //se non esiste, crea la directory 
            Directory.CreateDirectory(directoryPath);
        return directoryPath;
    }

    //crea un file con un nome ed una estensione specifica nel percorso specificato
    public string CreateFile(string path, string name, string? extension)
    {
        string filePath;
        if (extension != null) 
           filePath = Path.Combine(path, name+extension);
        else 
            filePath = Path.Combine(path, name);

        bool doesFileExist = File.Exists(filePath);
        if (!doesFileExist)
            File.Create(filePath);
        return filePath;
    }

    //ritorna tutti gli elementi nel percorso specificato con l'estensione .json
    public List<string> SearchForSales()
    {
        List<string> sales = new List<string>();
        var salesFiles = Directory.EnumerateFiles(@"C:\Users\nadir\OneDrive\Desktop\Dennis\school_stuff\C#\HellodotNet\HellodotNet\stores", "*.json", SearchOption.AllDirectories);
        foreach (string sale in salesFiles)
            if (sale.EndsWith("sales.json")) 
                sales.Add(sale);

        return sales;

    }
  
    //calcola il guadagno complessivo della giornata
    public float CalculateSales(List<string> sales)
    {
        var totalSale = 0f;
        foreach(string sale in sales)
        {
            var saleFile = File.ReadAllText(sale);
            totalSale += JsonConvert.DeserializeObject<Sale>(saleFile).Value;
        }
        return totalSale;
    }

    //programma che viene svolto alla fine di ogni giornata lavorativa: effettua la somma dei guadagni di tutti i negozi
    public void Routine()
    {
        string today = DateOnly.FromDateTime(DateTime.Today).ToString();
        today= today.Replace('/', '-');

        string directoryPath = CreateDirectory(Environment.GetFolderPath(Environment.SpecialFolder.Desktop), "sales");     //crea la cartella  
        string filePath = CreateFile(directoryPath, "sales "+today, ".txt");    //crea il file

        List<string> sales = SearchForSales();          //trova i singoli guadagni
        File.WriteAllText(filePath, CalculateSales(sales).ToString());   //calcola il guadagno complessivo, lo scrive poi nel file appena creato

        Console.WriteLine($"La routine del giorno {today} è stata completata con successo.");
    }
}