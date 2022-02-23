using HelloAsp.Models;
namespace HelloAsp.Services;
public class PizzaService
{
    static List<Pizza> Pizzas { get; set; }
    static private int NextId; 
    static PizzaService()
    {
        Pizzas = new List<Pizza>();
        NextId = 0;
    }

    public static List<Pizza> GetPizzas() => Pizzas;
    public static Pizza? GetPizza(int id)
    {
        if (id >= Pizzas.Count)
            return null;

        return Pizzas[id];
    }
    public static void AddPizza(Pizza pizzaToAdd)
    {
        pizzaToAdd.Id = NextId++;
        Pizzas.Add(pizzaToAdd);
    }
    public static void DeletePizza(int id)
    {
        var index = Pizzas.FindIndex(p => p.Id == id);
        if (index == -1)
            return;

        Pizzas.RemoveAt(index);


    }

    public static void UpdatePizza(Pizza pizzaToUpdate)
    {
        var index = Pizzas.FindIndex(p => p.Id == pizzaToUpdate.Id);
        if (index == -1)
            return;

        Pizzas[index] = pizzaToUpdate;
    }
}

        