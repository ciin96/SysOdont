/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.EstadoCitas;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class EstadoCitasControlador {
    public static boolean agregar(EstadoCitas estadocita){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into estadocitas(nombre_estadocita)"
                    +" values('" +estadocita.getNombre_estadocita()+"')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex){
                System.out.println("Error:"+ ex);
            }
        }
        return valor;
    }
    
    public static EstadoCitas buscarId(EstadoCitas estadocita){
        
        if (Conexion.conectar()) {
            String sql = "select * from estadocitas where id_estadocita ='" + estadocita.getId_estadocita() + "'";
            System.out.println("sql"+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    estadocita.setId_estadocita(rs.getInt("id_estadocita"));
                    estadocita.setNombre_estadocita(rs.getString("nombre_estadocita"));
   
                }else {
                    estadocita.setId_estadocita(0);
                    estadocita.setNombre_estadocita("");
 
                    //return null;
                    //return estadocita;
                }
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return estadocita;
    }
    
   public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from estadocitas where upper(nombre_estadocita) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_estadocita offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_estadocita") + "</td>"
                                + "<td>" + rs.getString("nombre_estadocita") + "</td>"
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
    
   public static boolean modificar(EstadoCitas estadocita) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update estadocitas set nombre_estadocita= '" + estadocita.getNombre_estadocita() + "'"
                    +"where id_estadocita=" + estadocita.getId_estadocita();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }
    
       public static boolean eliminar(EstadoCitas estadocita) {
        boolean valor =false;
        if (Conexion.conectar()) {
            String sql = "delete from estadocitas where id_estadocita=" + estadocita.getId_estadocita();
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
