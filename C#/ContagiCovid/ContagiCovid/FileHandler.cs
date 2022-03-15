namespace ContagiCovid;

public class FileHandler
{

    private static Uri url = new Uri("https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-andamento-nazionale/dpc-covid19-ita-andamento-nazionale.csv");
    static readonly HttpClient client = new HttpClient();
    public static string GetContent()
    {
        Console.WriteLine($"Downloading from {url}");
        HttpResponseMessage response = client.GetAsync(url).Result;
        response.EnsureSuccessStatusCode();
        Console.WriteLine($"Il file è stato scaricato con successo (Responde Code: {response.StatusCode}).");


        string fileContent = response.Content.ReadAsStringAsync().Result;
        string fileName = $"Contagi_{DateOnly.FromDateTime(DateTime.Now)}";
        fileName = fileName.Replace('/', '_');
        string fileExtension = "csv";
        string filePath = $@"{Environment.GetFolderPath(Environment.SpecialFolder.Desktop)}\{fileName}.{fileExtension}";
        File.WriteAllText(filePath, fileContent);

        Console.WriteLine($"Il file è stato salvato in {filePath}.");

        return filePath;
    }
}
