/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Cinthia
 */
public class DetalleCitas {
    int id_detallecita;
    Pacientes paciente;
    Servicios servicio;
    Citas cita;
    Horarios horario;

    public DetalleCitas() {
    }

    public DetalleCitas(int id_detallecita, Pacientes paciente, Servicios servicio, Citas cita, Horarios horario) {
        this.id_detallecita = id_detallecita;
        this.paciente = paciente;
        this.servicio = servicio;
        this.cita = cita;
        this.horario = horario;
    }

    public int getId_detallecita() {
        return id_detallecita;
    }

    public void setId_detallecita(int id_detallecita) {
        this.id_detallecita = id_detallecita;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    public Servicios getServicio() {
        return servicio;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }

    public Citas getCita() {
        return cita;
    }

    public void setCita(Citas cita) {
        this.cita = cita;
    }

    public Horarios getHorario() {
        return horario;
    }

    public void setHorario(Horarios horario) {
        this.horario = horario;
    }


    
}
