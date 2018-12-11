/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.Cuentas;
import modelos.Facturas;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class CuentasControlador {
    public static boolean agregar(Cuentas cuenta){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into cuentas( id_factura, monto_total, estado_cuenta, total_cuota)"
                    +" values('" + cuenta.getFactura().getId_factura()+"',"
                    + cuenta.getMonto_total()+","
                    + cuenta.getEstado_cuenta()+","
                    + cuenta.getTotal_cuota()+")";
          
            try {
               Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_cuenta = keyset.getInt(1);
                    cuenta.setId_cuenta(id_cuenta);
                    Conexion.getConn().commit();
                }
                valor = true;
            } catch (SQLException ex){
                System.out.println("Error:"+ ex);
            }
        }
        return valor;
    }
    
    public static Cuentas buscarId(Cuentas cuenta){
        
        if (Conexion.conectar()) {
            String sql =  "select * from cuentas c, factura f"
                    + " where c.id_factura = f.id_factura "
                    + "and id_cuenta =" + cuenta.getId_cuenta() + "";
            System.out.println("BUSCARID---->"+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) { //Si encuentra el id traerá todos los datos
                    Facturas factura = new Facturas();
                    
                    cuenta.setId_cuenta(rs.getInt("id_cuenta"));
                    cuenta.setMonto_total(rs.getInt("monto_total"));
                    cuenta.setEstado_cuenta(rs.getString("estado_cuenta"));
                    cuenta.setTotal_cuota(rs.getInt("total_cuota"));
                    
                    factura.setId_factura(rs.getInt("id_factura"));
                    
                    
                    cuenta.setFactura(factura);
                }
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return cuenta;
    }
    
   public static String buscarEstado(String estado, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(estado);
                String sql = "select * from cuentas where upper(estado_cuenta) like '%" +
                        estado.toUpperCase() + "%'"
                        + "order by id_cuenta offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("BUSCARNOMBRE--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_cuenta") + "</td>"
                                + "<td>" + rs.getString("id_factura") + "</td>"
                                + "<td>" + rs.getString("monto_total") + "</td>"
                                + "<td>" + rs.getString("estado_cuenta") + "</td>"
                                + "<td>" + rs.getString("total_cuota") + "</td>"
                                
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
    
   public static boolean modificar(Cuentas cuenta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update cuentas set estado_cuenta= '" + cuenta.getEstado_cuenta() + "',"
                    +"monto_total='" + cuenta.getMonto_total() + "',"
                    +"estado_cuenta=" + cuenta.getEstado_cuenta()+","
                    +"total_cuota= " + cuenta.getTotal_cuota()+ " "
                    +"id_factura= " + cuenta.getFactura().getId_factura() + ", "
                    +"where id_cuenta=" + cuenta.getId_cuenta();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }
    
       public static boolean eliminar(Cuentas cuenta) {
        boolean valor =false;
        if (Conexion.conectar()) {
            String sql = "delete from cuentas where id_cuenta=" + cuenta.getId_cuenta();
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
