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
public class DetalleCuentas {
    int id_detallecuenta;
    Cuentas cuenta;
    int nro_cuota;
    int monto_cuota;
    String estado_cuota;

    public DetalleCuentas() {
    }

    public DetalleCuentas(int id_detallecuenta, Cuentas cuenta, int nro_cuota, int monto_cuota, String estado_cuota) {
        this.id_detallecuenta = id_detallecuenta;
        this.cuenta = cuenta;
        this.nro_cuota = nro_cuota;
        this.monto_cuota = monto_cuota;
        this.estado_cuota = estado_cuota;
    }

    public int getId_detallecuenta() {
        return id_detallecuenta;
    }

    public void setId_detallecuenta(int id_detallecuenta) {
        this.id_detallecuenta = id_detallecuenta;
    }

    public Cuentas getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuentas cuenta) {
        this.cuenta = cuenta;
    }

    public int getNro_cuota() {
        return nro_cuota;
    }

    public void setNro_cuota(int nro_cuota) {
        this.nro_cuota = nro_cuota;
    }

    public int getMonto_cuota() {
        return monto_cuota;
    }

    public void setMonto_cuota(int monto_cuota) {
        this.monto_cuota = monto_cuota;
    }

    public String getEstado_cuota() {
        return estado_cuota;
    }

    public void setEstado_cuota(String estado_cuota) {
        this.estado_cuota = estado_cuota;
    }

  
    
    
}
