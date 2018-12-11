/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author Cinthia
 */
public class Citas {
    int id_cita;
    String nombre_cita;
    Doctores doctor;
    EstadoCitas estadocita;
    Date fecha_cita;

    public Citas() {
    }

    public Citas(int id_cita, String nombre_cita, Doctores doctor, EstadoCitas estadocita, Date fecha_cita) {
        this.id_cita = id_cita;
        this.nombre_cita = nombre_cita;
        this.doctor = doctor;
        this.estadocita = estadocita;
        this.fecha_cita = fecha_cita;
    }

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public String getNombre_cita() {
        return nombre_cita;
    }

    public void setNombre_cita(String nombre_cita) {
        this.nombre_cita = nombre_cita;
    }

    public Doctores getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctores doctor) {
        this.doctor = doctor;
    }

    public EstadoCitas getEstadocita() {
        return estadocita;
    }

    public void setEstadocita(EstadoCitas estadocita) {
        this.estadocita = estadocita;
    }

    public Date getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(Date fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    
    
}
