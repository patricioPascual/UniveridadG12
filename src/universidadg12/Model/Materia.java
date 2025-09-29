/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidadg12.Model;

/**
 *
 * @author patri
 */
public class Materia {
    private int id_materia;
    private String nombre;
    private int anio;
    private boolean estado;

    public Materia(int id_materia, String nombre, int anio, boolean estado) {
        this.id_materia = id_materia;
        this.nombre = nombre;
        this.anio = anio;
        this.estado = estado;
    }
    
}
