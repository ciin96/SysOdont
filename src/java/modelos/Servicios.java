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
public class Servicios {
    int id_servicio;
    String nombre_servicio;
    String descripcion_servicio;
    int precio_servicio;
    String observacion_servicio;

    public Servicios() {
    }

    public Servicios(int id_servicio, String nombre_servicio, String descripcion_servicio, int precio_servicio, String observacion_servicio) {
        this.id_servicio = id_servicio;
        this.nombre_servicio = nombre_servicio;
        this.descripcion_servicio = descripcion_servicio;
        this.precio_servicio = precio_servicio;
        this.observacion_servicio = observacion_servicio;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getNombre_servicio() {
        return nombre_servicio;
    }

    public void setNombre_servicio(String nombre_servicio) {
        this.nombre_servicio = nombre_servicio;
    }

    public String getDescripcion_servicio() {
        return descripcion_servicio;
    }

    public void setDescripcion_servicio(String descripcion_servicio) {
        this.descripcion_servicio = descripcion_servicio;
    }

    public int getPrecio_servicio() {
        return precio_servicio;
    }

    public void setPrecio_servicio(int precio_servicio) {
        this.precio_servicio = precio_servicio;
    }

    public String getObservacion_servicio() {
        return observacion_servicio;
    }

    public void setObservacion_servicio(String observacion_servicio) {
        this.observacion_servicio = observacion_servicio;
    }
    
    

    
}
