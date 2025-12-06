package com.work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class DBHandler extends Config{


    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String connString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(connString, dbUser, dbPassword);
    }

    public void registerUser(String firstName, String lastName, String username,
                             String password, String country, String gender) {

        if (Objects.equals(firstName, "")) {
            System.out.println("First name is null");
            return;
        }
        if (Objects.equals(lastName, "")) {
            System.out.println("Last name is null");
            return;
        }
        if (Objects.equals(username, "")) {
            System.out.println("Username is null");
            return;
        }
        if (Objects.equals(password, "")) {
            System.out.println("Password is null");
            return;
        }
        if (Objects.equals(country, "")) {
            System.out.println("Country is null");
            return;
        }


        String insert = ("INSERT INTO " + Const.USER_TABLE + "("
                + Const.USER_FIRSTNAME + "," + Const.USER_LASTNAME + "," + Const.USER_USERNAME + ","
                + Const.USER_PASSWORD + "," + Const.USER_COUNTRY + "," + Const.USER_GENDER + ")"
                + "VALUES (?, ?, ?, ?, ?, ?)");

        //PreparedStatement psSt;

        try {
            PreparedStatement psSt = getConnection().prepareStatement(insert);

            psSt.setString(1, firstName);
            psSt.setString(2, lastName);
            psSt.setString(3, username);
            psSt.setString(4, password);
            psSt.setString(5, country);
            psSt.setString(6, gender);

            psSt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Some info is null");
        }
    }

}
