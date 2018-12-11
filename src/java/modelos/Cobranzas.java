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
public class Cobranzas {
    int id_cobranza;
    Facturas factura;
    TipoPagos tipopago;
    Servicios servicio;
    int monto_cobranza;

    public Cobranzas() {
    }

    public Cobranzas(int id_cobranza, Facturas factura, TipoPagos tipopago, Servicios servicio, int monto_cobranza) {
        this.id_cobranza = id_cobranza;
        this.factura = factura;
        this.tipopago = tipopago;
        this.servicio = servicio;
        this.monto_cobranza = monto_cobranza;
    }

    public int getId_cobranza() {
        return id_cobranza;
    }

    public void setId_cobranza(int id_cobranza) {
        this.id_cobranza = id_cobranza;
    }

    public Facturas getFactura() {
        return factura;
    }

    public void setFactura(Facturas factura) {
        this.factura = factura;
    }

    public TipoPagos getTipopago() {
        return tipopago;
    }

    public void setTipopago(TipoPagos tipopago) {
        this.tipopago = tipopago;
    }

    public Servicios getServicio() {
        return servicio;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }

    public int getMonto_cobranza() {
        return monto_cobranza;
    }

    public void setMonto_cobranza(int monto_cobranza) {
        this.monto_cobranza = monto_cobranza;
    }
    
    
    
    
    
}
