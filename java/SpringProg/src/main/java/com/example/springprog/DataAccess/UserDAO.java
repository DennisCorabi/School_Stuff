package com.example.springprog.DataAccess;

import com.example.springprog.Model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Objects;
import java.util.UUID;
import java.util.Vector;

@Repository
public class UserDAO {
    private final Vector<User> database = new Vector<>();

    //funzione che ritorna tutti gli utenti presenti all'interno del database
    public Vector<User> getAllUsers(){
        return database;
    }

    //funzione che inserisce un nuovo utente all'interno del database
    public void insertUser(User newuser){
        newuser.setId(UUID.randomUUID());   //prima di aggiungerlo, gli assegna un ID univoco (con la classe UUID)
        database.add(newuser);
    }

    //funzione che cerca nel database un utente in base all'ID che viene passato; viene utilizzato per evitare di creare codici uguali nelle altre funzioni
    private User searchForId(UUID id){
        for (User user: database){       //per ogni utente nel database, se il suo ID combacia con quello passato alla funzione, allora ritorna l'utente
            if (user.getId().equals(id)){
                return user;
            }
        }
        return  null;   //se non c'è un utente con l'ID specificato, allora non ritornare niente
    }

    //funzione che ritorna un utente con un ID specifico
    public User getUserById(UUID id){
        return searchForId(id);
    }

    //funzione che permette di modificare il nome di un utente in base al suo ID
    public void changeUserNameById(UUID id, String name) {
        Objects.requireNonNull(searchForId(id)).setNome(name);        //si assicura che l'elemento che ritorna "searchForId" non sia nullo
    }

    //funzione che elimina un utente dal vettore in base al suo ID
    public void deleteUserById(UUID id){
        database.remove(searchForId(id));
    }
}
