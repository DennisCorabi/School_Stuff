package com.example.springprog.Controller;

import com.example.springprog.Model.User;
import com.example.springprog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.Vector;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    //Richiesta per visualizzare tutti gli utenti presenti nel database
    @GetMapping
    public Vector<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //Richiesta per visualizzare il singolo utente in base al suo ID (passato nella barra di ricer
    @GetMapping(path = "/{id}")
    public User getUserById(@PathVariable("id") UUID id){
        return userService.getUserById(id);
    }

    //Richiesta per aggiungere un utente al database.
    @PostMapping
    public void insertUser(@RequestBody User newuser){
        userService.insertUser(newuser);
    }

    //Richiesta per aggiornare il nome di un utente specifico (in base al suo ID, sempre passato nella barra di ricerca)
    @PutMapping(path = "/{id}")
    public void changeUserNameById(@PathVariable("id") UUID id, @RequestParam(value = "nome") String name){
        userService.changeUserNameById(id,name);
    }

    //Richiesta per rimuovere un utente in base all'ID specificato
    @DeleteMapping(path="/{id}")
    public void deleteUserById(@PathVariable("id") UUID id){
        userService.deleteUserById(id);
    }

}
