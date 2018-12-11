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
public class Facturas {
    int id_factura;
    Pacientes paciente;
    Date fecha_factura;
    int cantidadc_factura;
    int numero_factura;
    String estado_factura;
    Usuarios usuario;

    public Facturas() {
    }

    public Facturas(int id_factura, Pacientes paciente, Date fecha_factura, int cantidadc_factura, int numero_factura, String estado_factura, Usuarios usuario) {
        this.id_factura = id_factura;
        this.paciente = paciente;
        this.fecha_factura = fecha_factura;
        this.cantidadc_factura = cantidadc_factura;
        this.numero_factura = numero_factura;
        this.estado_factura = estado_factura;
        this.usuario = usuario;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    public Date getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(Date fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public int getCantidadc_factura() {
        return cantidadc_factura;
    }

    public void setCantidadc_factura(int cantidadc_factura) {
        this.cantidadc_factura = cantidadc_factura;
    }

    public int getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(int numero_factura) {
        this.numero_factura = numero_factura;
    }

    public String getEstado_factura() {
        return estado_factura;
    }

    public void setEstado_factura(String estado_factura) {
        this.estado_factura = estado_factura;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    
    
}
