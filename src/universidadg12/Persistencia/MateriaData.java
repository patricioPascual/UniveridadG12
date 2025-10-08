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
import universidadg12.Model.Materia;

/**
 *
 * @author Leandro
 */
public class MateriaData {

    private static Connection con;

    public MateriaData() {
    }
    
    public MateriaData(Conexion miConexion) {
        this.con = miConexion.buscarConexion();
    }

    public static void guardarMateria(Materia m) {
        String query = "INSERT INTO materia(nombre, anio, estado) VALUES (?,?,?) ";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, (m.getNombre()));
            ps.setInt(2, m.getAnio());
            ps.setBoolean(3, m.isEstado());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Materia agregada con exito");
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Materia");
        }
    }

    public static void modificarMateria(Materia m) {
        String query = "UPDATE materia SET nombre = ?, anio = ? WHERE nombre = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, (m.getNombre()));
            ps.setInt(2, m.getAnio());
            ps.setString(3, m.getNombre());
            int exito = ps.executeUpdate();
            
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Materia modificada con exito");
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Modifacar Materia o Nombre ya existente");
        }
    }

    public static Materia buscarMateria(int id_materia) {
        

        Materia materia = null;
        try {
            String query = "SELECT id_materia, nombre, anio FROM materia WHERE id_materia = ? ";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, id_materia);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    materia = new Materia();
                    materia.setId_materia(id_materia);
                    materia.setNombre(rs.getString("nombre"));
                    materia.setAnio(rs.getInt("anio"));
                    ps.close();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Materia no encontrado");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Al acceder a la Base de Datos");
        }

        return materia;
    }
    
    
}
