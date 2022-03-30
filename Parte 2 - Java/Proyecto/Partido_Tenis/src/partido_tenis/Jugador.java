/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partido_tenis;

/**
 *
 * @author Usuario
 */
public class Jugador {
    
    private String nombre;
    private double probabilidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(double probabilidad) {
        this.probabilidad = probabilidad;
    }

    public Jugador(String nombre, double probabilidad) {
        this.nombre = nombre;
        this.probabilidad = probabilidad;
    }

    public Jugador() {
    }
    
    
    
}
