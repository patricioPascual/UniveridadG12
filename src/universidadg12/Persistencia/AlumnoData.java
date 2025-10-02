/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidadg12.Persistencia;

import java.sql.Connection;
import universidadg12.Model.Alumno;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author patri
 */
public class AlumnoData {

    private static Connection con;

    public AlumnoData(Conexion miConexion) {
        con = miConexion.buscarConexion();
    }

    public static void guardarAlumno(Alumno a) {
        String query = "INSERT INTO alumno(dni,apellido,nombre,fechaNacimiento,estado) VALUES (?,?,?,?,?) ";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, String.valueOf(a.getDni()));
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getNombre());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            ps.setBoolean(5, a.isEstado());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Alumno agregado con exito");
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Alumno");
        }

    }

    public static Alumno buscarAlumnos(int dni) {

        Alumno alumno = null;
        try {
            String sql = "SELECT * FROM alumno WHERE dni=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setId_alumno(rs.getInt("id_alumno"));

                alumno.setDni(dni);
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                int estadoInt = rs.getInt("estado");
                boolean estado;
                if (estadoInt == 1) {
                    estado = true;
                } else {
                    estado = false;
                }
                alumno.setEstado(estado);
                ps.close();

            } else {
                JOptionPane.showMessageDialog(null, "Alumno no encontrado");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Al acceder a la Base de Datos");
        }

        return alumno;

    }

    public static void modificarAlumno(Alumno a) {

        String query = "UPDATE  alumno SET  dni=?, apellido=?,nombre=?,fechaNacimiento=?  WHERE dni=?  ";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getNombre());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            ps.setInt(5, a.getDni());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Alumno modificado con exito");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Accer a la Base de datos");
        }

    }
    
    //Baja logica establece el estado en falso o 0
    public static void darBajaAlumno(int dni) {
    
        try {
            String sql= "UPDATE  alumno SET  estado = 0 WHERE dni = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Baja realizada");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Accer a la Base de datos");
        }
    }
    
    public static void darAltaAlumno(int dni) {
    
        try {
            String sql= "UPDATE  alumno SET  estado = 1 WHERE dni = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Alta realizada");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Accer a la Base de datos");
        }
    }
}
