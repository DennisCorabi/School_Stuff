package com.company.databases;
import java.sql.*;

public class oracleDB{
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/helloSQL","sommo","Idduiddu1!");
        if (conn!=null){
            System.out.println("connessione avvenuta con successo");
        }
        assert conn != null;
        Statement statement = conn.createStatement();
        statement.execute("INSERT INTO Utenti values(\"rio\",\"cogno\",10)");
        ResultSet rs=statement.executeQuery("SELECT * FROM Utenti");

        while(rs.next()){
            System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
        }
    }
}
