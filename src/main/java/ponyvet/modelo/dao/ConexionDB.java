package ponyvet.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB { 
    //Esta conexion solo sirve para generar los reportes de una manera mas sencilla, sin usar el ORM Hibernate

    private Connection con = null;
    private String URL_DATABASE = "jdbc:mysql://localhost:3306/veterinariapony?serverTimezone=America/Mexico_City";
    private String USER = "vetpony";
    private String PASSWORD = "D1ng0D4ng0";

    public Connection conectar() {
        try {
            con = DriverManager.getConnection(URL_DATABASE, USER, PASSWORD);
            if (con != null) {
                System.out.println("Conexion Satisfactoria");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    
    public void cerrar(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
