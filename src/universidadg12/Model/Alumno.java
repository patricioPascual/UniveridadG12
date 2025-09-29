/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidadg12.Model;
import java.time.LocalDate;

/**
 *
 * @author patri
 */
public class Alumno {
    private int id_alumno=-1;
    private int dni;
    private String Apellido;
    private String nombre;
    private LocalDate fechaNacimiento;
    private boolean estado;

    public Alumno(int dni, String Apellido, String nombre, LocalDate fechaNacimiento, boolean estado) {
        this.dni = dni;
        this.Apellido = Apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public int getDni() {
        return dni;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public boolean isEstado() {
        return estado;
    }
    
    
}
