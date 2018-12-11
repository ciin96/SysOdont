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
public class DetalleFichas {
    int id_detalleficha;
    Servicios servicio;
    Fichas ficha;
    String estado_detalleficha;

    public DetalleFichas() {
    }

    public DetalleFichas(int id_detalleficha, Servicios servicio, Fichas ficha, String estado_detalleficha) {
        this.id_detalleficha = id_detalleficha;
        this.servicio = servicio;
        this.ficha = ficha;
        this.estado_detalleficha = estado_detalleficha;
    }

    public int getId_detalleficha() {
        return id_detalleficha;
    }

    public void setId_detalleficha(int id_detalleficha) {
        this.id_detalleficha = id_detalleficha;
    }

    public Servicios getServicio() {
        return servicio;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }

    public Fichas getFicha() {
        return ficha;
    }

    public void setFicha(Fichas ficha) {
        this.ficha = ficha;
    }

    public String getEstado_detalleficha() {
        return estado_detalleficha;
    }

    public void setEstado_detalleficha(String estado_detalleficha) {
        this.estado_detalleficha = estado_detalleficha;
    }

    
   

    
    

   
    
    
    
}
