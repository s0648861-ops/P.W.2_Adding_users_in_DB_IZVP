package com.work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DBHandler extends Config{


    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String connString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(connString, dbUser, dbPassword);
    }

    public void registerUser(User user) {

        String insert = ("INSERT INTO " + Const.USER_TABLE + "("
                + Const.USER_FIRSTNAME + "," + Const.USER_LASTNAME + "," + Const.USER_USERNAME + ","
                + Const.USER_PASSWORD + "," + Const.USER_COUNTRY + "," + Const.USER_GENDER + ")"
                + "VALUES (?, ?, ?, ?, ?, ?)");

        //PreparedStatement psSt;

        try {
            PreparedStatement psSt = getConnection().prepareStatement(insert);

            psSt.setString(1, user.getFirstName());
            psSt.setString(2, user.getLastName());
            psSt.setString(3, user.getUsername());
            psSt.setString(4, user.getPassword());
            psSt.setString(5, user.getCountry());
            psSt.setString(6, user.getGender());

            psSt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Some info is null");
        }
    }

    public ResultSet getUser(User user){
        ResultSet rs = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_USERNAME + " =? AND "
                + Const.USER_PASSWORD + " =?";

        try {
            PreparedStatement psSt = getConnection().prepareStatement(select);

            psSt.setString(1, user.getUsername());
            psSt.setString(2, user.getPassword());

            rs = psSt.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Some info is null");
        }

        return rs;

    }

}
