/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidadg12.Persistencia;


import universidadg12.Model.Alumno;
import universidadg12.Model.Inscripcion;
import universidadg12.Model.Materia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionData {

    private static Connection con;

    public InscripcionData(Conexion miConexion) {
        this.con = miConexion.buscarConexion();
    }
    public InscripcionData(){
}
    

    public void guardarInscripcion(Inscripcion inscripcion) {
        String sql = "INSERT INTO inscripcion (nota, id_alumno, id_materia) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, inscripcion.getNota());
            ps.setInt(2, inscripcion.getAlumno().getId_alumno());
            ps.setInt(3, inscripcion.getMateria().getId_materia());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                inscripcion.setId_inscripto(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripcion agregada con exito");
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar inscripcion: " + e.getMessage());
        }
    }

    public List<Inscripcion> obtenerInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();

        String sql = "SELECT * FROM inscripcion";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                AlumnoData ad = new AlumnoData();
                MateriaData md = new MateriaData();

                insc.setId_inscripto(rs.getInt("id_inscripto"));
                insc.setNota(rs.getDouble("nota"));
                insc.setAlumno(ad.buscarAlumnosPorID(rs.getInt("id_alumno")));
                insc.setMateria(md.buscarMateriaPorID(rs.getInt("id_materia")));

                inscripciones.add(insc);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener inscripciones: " + e.getMessage()); 
        }

        return inscripciones;
    }

    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id_alumno) {
        List<Inscripcion> inscripciones = new ArrayList<>();

        String sql = "SELECT * FROM inscripcion WHERE id_alumno = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();

            AlumnoData ad = new AlumnoData();
            MateriaData md = new MateriaData();

            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setId_inscripto(rs.getInt("id_inscripto"));
                insc.setNota(rs.getDouble("nota"));
                insc.setAlumno(ad.buscarAlumnosPorID(id_alumno));
                insc.setMateria(md.buscarMateriaPorID(rs.getInt("id_materia")));

                inscripciones.add(insc);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener inscripciones del alumno: " + e.getMessage()); 
        }

        return inscripciones;
    }

    public void borrarInscripcion(int id_alumno, int id_materia) {
        String sql = "DELETE FROM inscripcion WHERE id_alumno = ? AND id_materia = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_alumno);
            ps.setInt(2, id_materia);
            int filas = ps.executeUpdate();

            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Inscripción eliminada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la inscripción para eliminar.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar inscripcion: " + e.getMessage()); 
        }
    }

    public void actualizarNota(int id_alumno, int id_materia, double nota) {
        String sql = "UPDATE inscripcion SET nota = ? WHERE id_alumno = ? AND id_materia = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, nota);
            ps.setInt(2, id_alumno);
            ps.setInt(3, id_materia);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Nota actualizada correctamente.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar nota: " + e.getMessage());
        }
    }

    public List<Materia> obtenerMateriasCursadas(int id_alumno) {
        List<Materia> materias = new ArrayList<>();

        String sql = "SELECT m.* FROM inscripcion i, materia m WHERE i.id_materia = m.id_materia AND i.id_alumno = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia m = new Materia();
                m.setId_materia(rs.getInt("id_materia"));
                m.setNombre(rs.getString("nombre"));
                m.setAnio(rs.getInt("anio"));
                m.setEstado(rs.getBoolean("estado"));

                materias.add(m);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener materias cursadas: " + e.getMessage());
        }

        return materias;
    }

    public List<Materia> obtenerMateriasNoCursadas(int id_alumno) {
        List<Materia> materias = new ArrayList<>();

        String sql = "SELECT * FROM materia WHERE id_materia NOT IN " +
                     "(SELECT id_materia FROM inscripcion WHERE id_alumno = ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia m = new Materia();
                m.setId_materia(rs.getInt("id_materia"));
                m.setNombre(rs.getString("nombre"));
                m.setAnio(rs.getInt("anio"));
                m.setEstado(rs.getBoolean("estado"));

                materias.add(m);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener materias no cursadas: " + e.getMessage());
        }

        return materias;
    }
}
