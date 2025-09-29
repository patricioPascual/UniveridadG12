/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidadg12.Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author patri
 */
public class Conexion {
    private String url;
    private String usuario;
    private String password;
    private  Connection conexion= null;

    public Conexion(String url, String usuario, String password) {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }
    
    public Connection buscarConexion(){
        if(conexion==null){
            try{
                Class.forName("org.mariadb.jdbc.Driver");
                conexion=DriverManager.getConnection(url,usuario,password);
                
            }catch(SQLException |ClassNotFoundException ex){
                System.out.println("No se puede conectar o no se puede cargar el Driver");
            }
        }
        return conexion;
    }
    
}
