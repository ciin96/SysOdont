/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Servicios;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class ServiciosControlador {
    public static boolean agregar(Servicios servicio){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into servicios(nombre_servicio, descripcion_servicio, precio_servicio, observacion_servicio )"
                    +" values('" +servicio.getNombre_servicio()+"','"
                    + servicio.getDescripcion_servicio()+"','"
                    + servicio.getPrecio_servicio()+"','"
                    + servicio.getObservacion_servicio()+"')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex){
                System.out.println("Error:"+ ex);
            }
        }
        return valor;
    }
    
    public static Servicios buscarId(Servicios servicio){
        
        if (Conexion.conectar()) {
            String sql = "select * from servicios where id_servicio ='" + servicio.getId_servicio() + "'";
            System.out.println("sql"+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    servicio.setId_servicio(rs.getInt("id_servicio"));
                    servicio.setNombre_servicio(rs.getString("nombre_servicio"));
                    servicio.setDescripcion_servicio(rs.getString("descripcion_servicio"));
                    servicio.setPrecio_servicio(rs.getInt("precio_servicio"));
                    servicio.setObservacion_servicio(rs.getString("observacion_servicio"));
   
                }else {
                    servicio.setId_servicio(0);
                    servicio.setNombre_servicio("");
                    servicio.setDescripcion_servicio("");
                    servicio.setPrecio_servicio(0);
                    servicio.setObservacion_servicio("");
 
                    //return null;
                    //return servicio;
                }
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return servicio;
    }
    
   public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from servicios where upper(nombre_servicio) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_servicio offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_servicio") + "</td>"
                                + "<td>" + rs.getString("nombre_servicio") + "</td>"
                                +"<td>" + rs.getString("descripcion_servicio") + "</td>"
                                +"<td>" + rs.getString("precio_servicio") + "</td>"
                                +"<td>" + rs.getString("observacion_servicio") + "</td>"
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
    
   public static boolean modificar(Servicios servicio) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update servicios set nombre_servicio= '" + servicio.getNombre_servicio() + "'," 
                    +"descripcion_servicio='" + servicio.getDescripcion_servicio() + "',"
                    +"precio_servicio='" + servicio.getPrecio_servicio() + "',"
                    +"observacion_servicio='" + servicio.getObservacion_servicio() + "'"
                    +"where id_servicio=" + servicio.getId_servicio();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }
    
       public static boolean eliminar(Servicios servicio) {
        boolean valor =false;
        if (Conexion.conectar()) {
            String sql = "delete from servicios where id_servicio=" + servicio.getId_servicio();
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
