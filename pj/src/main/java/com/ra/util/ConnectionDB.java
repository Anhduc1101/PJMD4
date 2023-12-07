package com.ra.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private final static String DRIVER="com.mysql.cj.jdbc.Driver";
    private final static String USER="root";
    private final static String PASS="anhduc1101";
    private final static String URL="jdbc:mysql://localhost:3306/project_module_04";

    public static Connection openCon(){
        Connection connection=null;
        try {
            Class.forName(DRIVER);
            connection= DriverManager.getConnection(URL,USER,PASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void closeCon(Connection con){
        if (con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
