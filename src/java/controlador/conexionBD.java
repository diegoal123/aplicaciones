/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ARBELAEZ
 */
public class conexionBD {
    Connection cn;
    String[] datos;
    int i;

    public conexionBD () throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        cn = DriverManager.getConnection("jdbc:mysql://localhost/cursoadroid", "root", "");
        System.out.println("Conexión exitosa");
        Statement stm;
        stm = cn.createStatement();
        ResultSet res;
        res = stm.executeQuery("SELECT COUNT(Nombres) FROM usuarios");
        while(res.next()){
            i = res.getInt(1);
        }
        System.out.println("numero: " + i);
        res = stm.executeQuery("select * from usuarios");

        datos = new String[i];
        int n = 0;
        while(res.next() && n < i){
            //System.out.println("enntra al ciclo");
            System.out.println(res.getString(2));
            datos[n] = res.getString(2);
            //System.out.println("guarda info");
            n++;
        }
    }
}
