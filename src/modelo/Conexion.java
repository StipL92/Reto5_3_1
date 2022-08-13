
package modelo;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

       
public class Conexion {
    Connection con = null;
    public Connection conexion(){
        
        try {
            Class.forName("org.mariadb.jdbc.Driver"); //com.mysql.jadbc.Driver org.mariadb.jdbc.Driver
            con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/proyectoconstruccion","root","123456"); //jdbc:mariadb://localhost:3306/proyectoconstruccion","root","123456
            System.out.println("Conexion establecida!! ");
        } catch (Exception e) {
            System.out.println("Error en la conexion.." + e);
        }
    return con;
    }
}
