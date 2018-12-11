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
public class DetalleFacturas {
    int id_detallefactura;
    Facturas factura;
    Servicios servicio;
    int cantidad_factura;
    int precio_detallefactura;

    public DetalleFacturas() {
    }

    public DetalleFacturas(int id_detallefactura, Facturas factura, Servicios servicio, int cantidad_factura, int precio_detallefactura) {
        this.id_detallefactura = id_detallefactura;
        this.factura = factura;
        this.servicio = servicio;
        this.cantidad_factura = cantidad_factura;
        this.precio_detallefactura = precio_detallefactura;
    }

    public int getId_detallefactura() {
        return id_detallefactura;
    }

    public void setId_detallefactura(int id_detallefactura) {
        this.id_detallefactura = id_detallefactura;
    }

    public Facturas getFactura() {
        return factura;
    }

    public void setFactura(Facturas factura) {
        this.factura = factura;
    }

    public Servicios getServicio() {
        return servicio;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }

    public int getCantidad_factura() {
        return cantidad_factura;
    }

    public void setCantidad_factura(int cantidad_factura) {
        this.cantidad_factura = cantidad_factura;
    }

    public int getPrecio_detallefactura() {
        return precio_detallefactura;
    }

    public void setPrecio_detallefactura(int precio_detallefactura) {
        this.precio_detallefactura = precio_detallefactura;
    }


    
}
