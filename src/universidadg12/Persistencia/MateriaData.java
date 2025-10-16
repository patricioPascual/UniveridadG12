package universidadg12.Persistencia;

import java.sql.Connection;
import universidadg12.Model.Alumno;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory;
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

    public  void guardarMateria(Materia m) {
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

    public void modificarMateria(Materia m, String nombre, int anio ) { // (Materia m, String nombre, String Año)
        String query = "UPDATE materia SET nombre = ?, anio = ? WHERE nombre = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, (nombre));
            ps.setInt(2, anio);
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

    public Materia buscarMateria(String nombre) {
        Materia materia = null;
        try {
            String query = "SELECT * FROM materia WHERE nombre LIKE ? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                materia = new Materia();
                materia.setId_materia(rs.getInt("id_materia"));
                int estadoInt = rs.getInt("estado");
                boolean estado;
                if (estadoInt == 1) {
                    estado = true;
                } else {
                    estado = false;
                }
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));
                materia.setEstado(estado);

                ps.close();
            } else {
                JOptionPane.showMessageDialog(null, "Materia no encontrado");
            }

        } catch (SQLException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error Al acceder a la Base de Datos");
        }
        return materia;
    }

    public void altaMateria(Materia m) {
        try {
            String query = "UPDATE materia SET estado = 1 WHERE nombre = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, m.getNombre());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Modificacion: Alta confirmada.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
        }
    }

    public void bajaMateria(Materia m) {
        try {
            String query = "UPDATE materia SET estado = 0 WHERE nombre = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, m.getNombre());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Modificacion: Baja confirmada.");
            }

        } catch (SQLException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
        }
    }

    public ArrayList mostrarMaterias() {
        ArrayList <Materia> listado = new ArrayList();
        try {
            String query = "SELECT * FROM materia";  
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              Materia materia = new Materia();  
              materia.setId_materia(rs.getInt("id_materia"));
                int estadoInt = rs.getInt("estado");
                boolean estado;
                if (estadoInt == 1) {
                    estado = true;
                } else {
                    estado = false;
                }
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));
                materia.setEstado(estado);
                listado.add(materia);
            }
                
        } catch (SQLException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
        }
        
        return listado;
    }
    
    public Materia buscarMateriaPorID(int id) {
        Materia materia = null;
        try {
            String query = "SELECT * FROM materia WHERE id_materia = ? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                materia = new Materia();
                materia.setId_materia(rs.getInt("id_materia"));
                int estadoInt = rs.getInt("estado");
                boolean estado;
                if (estadoInt == 1) {
                    estado = true;
                } else {
                    estado = false;
                }
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));
                materia.setEstado(estado);

                ps.close();
            } else {
                JOptionPane.showMessageDialog(null, "Materia no encontrado");
            }

        } catch (SQLException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error Al acceder a la Base de Datos");
        }
        return materia;
    }

}
