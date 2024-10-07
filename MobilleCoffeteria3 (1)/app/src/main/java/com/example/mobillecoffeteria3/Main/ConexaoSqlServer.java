package com.example.mobillecoffeteria3.Main;

import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSqlServer {

    public static Connection conectar() {

        Connection conn = null;
        try {

            StrictMode.ThreadPolicy politica;
            politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);

            Class.forName("net.sourceforge.jtds.jdbc.Driver");


              conn= DriverManager.getConnection("jdbc:jtds:sqlserver://myCafeteria2f.mssql.somee.com;"+
                    "databaseName=myCafeteria2f;user=matheus;password=@ITB123456;");
            System.out.println("Conectado com sucesso ao SQL Server local!");

        } catch (SQLException e) { // SQLException
            e.getMessage();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
