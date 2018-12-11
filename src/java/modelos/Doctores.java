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
public class Doctores {
    int id_doctor;
    String nombre_doctor;
    String apellido_doctor;
    int ci_doctor;
    int nroregistro_doctor;
    String direccion_doctor;
    int telefono_doctor;
    String correo_doctor;

    public Doctores() {
    }

    public Doctores(int id_doctor, String nombre_doctor, String apellido_doctor, int ci_doctor, int nroregistro_doctor, String direccion_doctor, int telefono_doctor, String correo_doctor) {
        this.id_doctor = id_doctor;
        this.nombre_doctor = nombre_doctor;
        this.apellido_doctor = apellido_doctor;
        this.ci_doctor = ci_doctor;
        this.nroregistro_doctor = nroregistro_doctor;
        this.direccion_doctor = direccion_doctor;
        this.telefono_doctor = telefono_doctor;
        this.correo_doctor = correo_doctor;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public String getNombre_doctor() {
        return nombre_doctor;
    }

    public void setNombre_doctor(String nombre_doctor) {
        this.nombre_doctor = nombre_doctor;
    }

    public String getApellido_doctor() {
        return apellido_doctor;
    }

    public void setApellido_doctor(String apellido_doctor) {
        this.apellido_doctor = apellido_doctor;
    }

    public int getCi_doctor() {
        return ci_doctor;
    }

    public void setCi_doctor(int ci_doctor) {
        this.ci_doctor = ci_doctor;
    }

    public int getNroregistro_doctor() {
        return nroregistro_doctor;
    }

    public void setNroregistro_doctor(int nroregistro_doctor) {
        this.nroregistro_doctor = nroregistro_doctor;
    }

    public String getDireccion_doctor() {
        return direccion_doctor;
    }

    public void setDireccion_doctor(String direccion_doctor) {
        this.direccion_doctor = direccion_doctor;
    }

    public int getTelefono_doctor() {
        return telefono_doctor;
    }

    public void setTelefono_doctor(int telefono_doctor) {
        this.telefono_doctor = telefono_doctor;
    }

    public String getCorreo_doctor() {
        return correo_doctor;
    }

    public void setCorreo_doctor(String correo_doctor) {
        this.correo_doctor = correo_doctor;
    }
    
    
}
