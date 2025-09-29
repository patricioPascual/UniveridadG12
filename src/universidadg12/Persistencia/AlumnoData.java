/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidadg12.Persistencia;

import java.sql.Connection;
import universidadg12.Model.Alumno;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Date;
/**
 *
 * @author patri
 */
public class AlumnoData {
    private static Connection con;

    public AlumnoData(Conexion miConexion) {
        con = miConexion.buscarConexion();
    }
 
    public static void guardarAlumno(Alumno a){
        String query="INSERT INTO alumno(dni,apellido,nombre,fechaNacimiento,estado) VALUES (?,?,?,?,?) ";
         try{
             PreparedStatement ps= con.prepareStatement(query);
             ps.setString(1, String.valueOf(a.getDni()));
             ps.setString(2, a.getApellido());
             ps.setString(3, a.getNombre());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            ps.setBoolean(5, a.isEstado());
            ps.executeUpdate();
         }catch(SQLException ex){
             System.out.println("Error al Guardar Alumno ");
         }
                
                
    }
}
