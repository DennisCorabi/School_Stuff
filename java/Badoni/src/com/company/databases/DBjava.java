package com.company.databases;

import com.company.person.Studenti;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class DBjava{
    private final Statement statement;

    public DBjava(String URL, String name, String password) throws SQLException, ClassNotFoundException {
        Connection connection = initializeDB(URL, name, password);      //stabilisco una connsessione
        this.statement= connection.createStatement();   //creo un oggetto per impartire ordini al DB
    }

    private Connection initializeDB(String URL, String name, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");     //TODO da generalizzare, per ora funzione solo con MySQL
        return DriverManager.getConnection(URL,name,password);  //stabilisco effetivamente una connessione grazie alle API
    }

    /*funzione che esegue i comandi SELECT
    TODO dare la possibilità all'utente di inserire dei parametri facolativi
     */
    public void GETreq(String parameters) throws SQLException {
        ResultSet rs=statement.executeQuery("SELECT "+parameters+" FROM Studenti");
        assert rs!=null;        //mi "assicuro" che il risultato non sia nullo. il quel caso, termino la funzione
        while(rs.next()){           //stampo la griglia fino a quando non arrivo all'ultima riga.
            System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
        }
    }

    public void PUTreq(Studenti studenti){          //TODO Non funziona, risolvere
        ArrayList<String> parametri = new ArrayList<>();
        parametri.add(studenti.getEta().toString());
        parametri.add(studenti.getNome());
        parametri.add(studenti.getBirthdate().toString());
        parametri.add(studenti.getClasse().toString());
    }

}
