package net.hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;

public class helloWorld {

    Connection Conn;
    Statement statement;
    final String INSERT_NAME_SQL = "insert into username_holder (username) values(?)";
//    final String VIEW_ALL_DB = "select * from username_holder";
    PreparedStatement psInsertNewUser;

    public void conn(){

        String url = "jdbc:h2:./target/user_holder";
        String username = "sa";
        String password = "";


        try {
            System.out.println("Connecting database...");
            Conn = DriverManager.getConnection(url, username, password);
            psInsertNewUser = Conn.prepareStatement(INSERT_NAME_SQL);

            System.out.println("Database connected!");
        } catch (SQLException exception) {
            System.err.println("Cannot connect the database!");
            exception.printStackTrace();
        } finally {
            System.out.println("Closing the connection.");
            if (Conn != null) try { Conn.close(); } catch (SQLException ignore) {}
        }


    }
    public void insert(String usernameInput) {

        try {
            statement = Conn.createStatement();
            psInsertNewUser.setString(1, usernameInput);
            psInsertNewUser.execute();
            System.out.println("Hello" + usernameInput);

        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {

        helloWorld world = new helloWorld();
        world.conn();
        Scanner userInput = new Scanner(System.in);
        String user = userInput.toString();
        while (!"exit".equals(user)) {
            System.out.println("username=>");
            world.insert(user);
        }
    }

}
