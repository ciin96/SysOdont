/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author ALUMNO
 */
public class Pacientes {
    int id_paciente;
    String nombre_paciente;
    String apellido_paciente;
    Date fechanac_paciente;
    int ci_paciente;
    String direccion_paciente;
    int telefono_paciente;
    String correo_paciente;

    public Pacientes() {
    }

    public Pacientes(int id_paciente, String nombre_paciente, String apellido_paciente, Date fechanac_paciente, int ci_paciente, String direccion_paciente, int telefono_paciente, String correo_paciente) {
        this.id_paciente = id_paciente;
        this.nombre_paciente = nombre_paciente;
        this.apellido_paciente = apellido_paciente;
        this.fechanac_paciente = fechanac_paciente;
        this.ci_paciente = ci_paciente;
        this.direccion_paciente = direccion_paciente;
        this.telefono_paciente = telefono_paciente;
        this.correo_paciente = correo_paciente;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNombre_paciente() {
        return nombre_paciente;
    }

    public void setNombre_paciente(String nombre_paciente) {
        this.nombre_paciente = nombre_paciente;
    }

    public String getApellido_paciente() {
        return apellido_paciente;
    }

    public void setApellido_paciente(String apellido_paciente) {
        this.apellido_paciente = apellido_paciente;
    }

    public Date getFechanac_paciente() {
        return fechanac_paciente;
    }

    public void setFechanac_paciente(Date fechanac_paciente) {
        this.fechanac_paciente = fechanac_paciente;
    }

    public int getCi_paciente() {
        return ci_paciente;
    }

    public void setCi_paciente(int ci_paciente) {
        this.ci_paciente = ci_paciente;
    }

    public String getDireccion_paciente() {
        return direccion_paciente;
    }

    public void setDireccion_paciente(String direccion_paciente) {
        this.direccion_paciente = direccion_paciente;
    }

    public int getTelefono_paciente() {
        return telefono_paciente;
    }

    public void setTelefono_paciente(int telefono_paciente) {
        this.telefono_paciente = telefono_paciente;
    }

    public String getCorreo_paciente() {
        return correo_paciente;
    }

    public void setCorreo_paciente(String correo_paciente) {
        this.correo_paciente = correo_paciente;
    }

 
    
   
}
