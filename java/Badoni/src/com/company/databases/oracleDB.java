package com.company.databases;

import java.sql.*;

public class oracleDB{
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");

        Statement statement = conn.createStatement();
        ResultSet rs=statement.executeQuery("CREATE TABLE dennis");
    }
}
