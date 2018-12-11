/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Cobranzas;
import modelos.Facturas;
import modelos.Servicios;
import modelos.TipoPagos;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class CobranzasControlador {
    public static boolean agregar(Cobranzas cobranza){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into cobranzas(id_factura, id_servicio, id_tipopago, monto_cobranza)"
                    +" values(" +cobranza.getFactura().getId_factura()+","
                    + cobranza.getServicio().getId_servicio()+","
                    + cobranza.getTipopago().getId_tipopago()+", "
                    + cobranza.getMonto_cobranza()+")";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex){
                System.out.println("Error:"+ ex);
            }
        }
        return valor;
    }
    
    public static Cobranzas buscarId(Cobranzas cobranza){
        
        if (Conexion.conectar()) {
            String sql =  "select * from cobranzas c, facturas f, servicios s, tipopagos tp"
                    + " where c.id_factura = f.id_factura "
                    + "and c.id_servicio = s.id_servicio "
                    + "and c.tipopago = tp.id_tipopago "
                    + "and id_cita =" + cobranza.getId_cobranza() + "";
            System.out.println("BUSCARID---->"+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    Facturas factura = new Facturas();
                    Servicios servicio = new Servicios();
                    TipoPagos tipopago = new TipoPagos();
                    
                    cobranza.setId_cobranza(rs.getInt("id_cobranza"));
                    cobranza.setMonto_cobranza(rs.getInt("monto_cobranza"));
                    
                    factura.setId_factura(rs.getInt("id_factura"));
                    
                    servicio.setId_servicio(rs.getInt("id_servicio"));
                    servicio.setNombre_servicio(rs.getString("nombre_servicio"));
                    servicio.setPrecio_servicio(rs.getInt("precio_servicio"));
                    
                    tipopago.setId_tipopago(rs.getInt("id_tipopago"));
                    tipopago.setNombre_tipopago(rs.getString("nombre_tipopago"));
                    
                    cobranza.setFactura(factura);
                    cobranza.setServicio(servicio);
                    cobranza.setTipopago(tipopago);
   
                    
                }
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return cobranza;
    }
    
   public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from cobranzas c, facturas f where fc.id_factura = f.id_factura and  upper(estado_factura) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by id_factura offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("BUSCARNOMBRE--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_cobranza") + "</td>"
                                + "<td>" + rs.getString("id_factura") + "</td>"
                                + "<td>" + rs.getString("numero_factura") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
   public static boolean modificar(Cobranzas cobranza) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update cobranzas set estado_factura= '" + cobranza.getFactura().getEstado_factura() + "',"
                    + "id_factura= " + cobranza.getFactura().getId_factura() + ","
                    + "id_servicio= " + cobranza.getServicio().getId_servicio() +","
                    + "id_tipopago= " + cobranza.getTipopago().getId_tipopago() +","
                    + "monto_cobranza= " + cobranza.getMonto_cobranza()+ " "
                    +"where id_cobranza=" + cobranza.getId_cobranza();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }
    
       public static boolean eliminar(Cobranzas cobranza) {
        boolean valor =false;
        if (Conexion.conectar()) {
            String sql = "delete from cobranzas where id_cobranza=" + cobranza.getId_cobranza();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }
}
