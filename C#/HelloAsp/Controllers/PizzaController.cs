namespace HelloAsp.Controllers;
using Microsoft.AspNetCore.Mvc;
using HelloAsp.Models;
using HelloAsp.Services;

[ApiController]
[Route("[Controller]")]
public class PizzaController: ControllerBase
{
    public PizzaController()
    {
    }


    [HttpGet]
    public ActionResult<List<Pizza>> GetAll() => PizzaService.GetPizzas();

    [HttpGet("{id}")]
    public ActionResult<Pizza> Get(int id)
    {
        var pizza = PizzaService.GetPizza(id);
        if (pizza == null)
            return NotFound();

        return pizza;   
    }

    [HttpPost]
    public IActionResult Create(Pizza pizza)
    {
        PizzaService.AddPizza(pizza);
        return CreatedAtAction(nameof(Create), new { id = pizza.Id }, pizza);
    }

    [HttpPut]
    public IActionResult Update(int id, Pizza pizza)
    {
        if (id != pizza.Id)
            return BadRequest();

        var existingPizza = PizzaService.GetPizza(id);
        if (existingPizza is null) return NotFound();
        else PizzaService.UpdatePizza(pizza);

        return NoContent();
    }

    [HttpDelete]
    public IActionResult Delete(int id)
    {
        var pizzaToDelete = PizzaService.GetPizza(id);
        if (pizzaToDelete is null) return NotFound();

        PizzaService.DeletePizza(id);
        return NoContent();
        
    }
        
}
