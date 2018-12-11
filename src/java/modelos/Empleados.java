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
public class Empleados {
    int id_empleado;
    String nombre_empleado;
    String apellido_empleado;
    int ci_empleado;
    String direccion_empleado;
    int telefono_empleado;
    String correo_empleado;

    public Empleados() {
    }

    public Empleados(int id_empleado, String nombre_empleado, String apellido_empleado, int ci_empleado, String direccion_empleado, int telefono_empleado, String correo_empleado) {
        this.id_empleado = id_empleado;
        this.nombre_empleado = nombre_empleado;
        this.apellido_empleado = apellido_empleado;
        this.ci_empleado = ci_empleado;
        this.direccion_empleado = direccion_empleado;
        this.telefono_empleado = telefono_empleado;
        this.correo_empleado = correo_empleado;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public String getApellido_empleado() {
        return apellido_empleado;
    }

    public void setApellido_empleado(String apellido_empleado) {
        this.apellido_empleado = apellido_empleado;
    }

    public int getCi_empleado() {
        return ci_empleado;
    }

    public void setCi_empleado(int ci_empleado) {
        this.ci_empleado = ci_empleado;
    }

    public String getDireccion_empleado() {
        return direccion_empleado;
    }

    public void setDireccion_empleado(String direccion_empleado) {
        this.direccion_empleado = direccion_empleado;
    }

    public int getTelefono_empleado() {
        return telefono_empleado;
    }

    public void setTelefono_empleado(int telefono_empleado) {
        this.telefono_empleado = telefono_empleado;
    }

    public String getCorreo_empleado() {
        return correo_empleado;
    }

    public void setCorreo_empleado(String correo_empleado) {
        this.correo_empleado = correo_empleado;
    }
    
    
    
}
