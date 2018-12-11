/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.TipoPagos;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class TipoPagosControlador {
    public static boolean agregar(TipoPagos tipopago){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into tipopagos(nombre_tipopago)"
                    +" values('" +tipopago.getNombre_tipopago()+"')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex){
                System.out.println("Error:"+ ex);
            }
        }
        return valor;
    }
    
    public static TipoPagos buscarId(TipoPagos tipopago){
        
        if (Conexion.conectar()) {
            String sql = "select * from tipopagos where id_tipopago ='" + tipopago.getId_tipopago() + "'";
            System.out.println("sql"+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    tipopago.setId_tipopago(rs.getInt("id_tipopago"));
                    tipopago.setNombre_tipopago(rs.getString("nombre_tipopago"));
   
                }else {
                    tipopago.setId_tipopago(0);
                    tipopago.setNombre_tipopago("");
 
                    //return null;
                    //return tipopago;
                }
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return tipopago;
    }
    
   public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from tipopagos where upper(nombre_tipopago) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_tipopago offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_tipopago") + "</td>"
                                + "<td>" + rs.getString("nombre_tipopago") + "</td>"
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
    
   public static boolean modificar(TipoPagos tipopago) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update tipopagos set nombre_tipopago= '" + tipopago.getNombre_tipopago() + "'"
                    +"where id_tipopago=" + tipopago.getId_tipopago();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }
    
       public static boolean eliminar(TipoPagos tipopago) {
        boolean valor =false;
        if (Conexion.conectar()) {
            String sql = "delete from tipopagos where id_tipopago=" + tipopago.getId_tipopago();
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
