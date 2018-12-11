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
public class Fichas {
    int id_ficha;
    Doctores doctor;
    Pacientes paciente;
    String nombre_ficha;
    Date fecha_ficha;

    public Fichas() {
    }

    public Fichas(int id_ficha, Doctores doctor, Pacientes paciente, String nombre_ficha, Date fecha_ficha) {
        this.id_ficha = id_ficha;
        this.doctor = doctor;
        this.paciente = paciente;
        this.nombre_ficha = nombre_ficha;
        this.fecha_ficha = fecha_ficha;
    }

    public int getId_ficha() {
        return id_ficha;
    }

    public void setId_ficha(int id_ficha) {
        this.id_ficha = id_ficha;
    }

    public Doctores getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctores doctor) {
        this.doctor = doctor;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    public String getNombre_ficha() {
        return nombre_ficha;
    }

    public void setNombre_ficha(String nombre_ficha) {
        this.nombre_ficha = nombre_ficha;
    }

    public Date getFecha_ficha() {
        return fecha_ficha;
    }

    public void setFecha_ficha(Date fecha_ficha) {
        this.fecha_ficha = fecha_ficha;
    }

   
    
    
}
