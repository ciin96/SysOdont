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
public class Cuentas {
    int id_cuenta;
    Facturas factura;
    int monto_total;
    String estado_cuenta;
    int total_cuota;

    public Cuentas() {
    }

    public Cuentas(int id_cuenta, Facturas factura, int monto_total, String estado_cuenta, int total_cuota) {
        this.id_cuenta = id_cuenta;
        this.factura = factura;
        this.monto_total = monto_total;
        this.estado_cuenta = estado_cuenta;
        this.total_cuota = total_cuota;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public Facturas getFactura() {
        return factura;
    }

    public void setFactura(Facturas factura) {
        this.factura = factura;
    }

    public int getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(int monto_total) {
        this.monto_total = monto_total;
    }

    public String getEstado_cuenta() {
        return estado_cuenta;
    }

    public void setEstado_cuenta(String estado_cuenta) {
        this.estado_cuenta = estado_cuenta;
    }

    public int getTotal_cuota() {
        return total_cuota;
    }

    public void setTotal_cuota(int total_cuota) {
        this.total_cuota = total_cuota;
    }
    
    
    
}
