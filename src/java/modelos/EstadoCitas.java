/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author ALUMNO
 */
public class EstadoCitas {
    int id_estadocita;
    String nombre_estadocita;

    public EstadoCitas() {
    }

    public EstadoCitas(int id_estadocita, String nombre_estadocita) {
        this.id_estadocita = id_estadocita;
        this.nombre_estadocita = nombre_estadocita;
    }

    public int getId_estadocita() {
        return id_estadocita;
    }

    public void setId_estadocita(int id_estadocita) {
        this.id_estadocita = id_estadocita;
    }

    public String getNombre_estadocita() {
        return nombre_estadocita;
    }

    public void setNombre_estadocita(String nombre_estadocita) {
        this.nombre_estadocita = nombre_estadocita;
    }
    
    
    
    
}
