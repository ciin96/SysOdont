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
import modelos.Facturas;
import modelos.Pacientes;
//import modelos.Usuarios;
import utiles.Conexion;
import utiles.Utiles;
/**
 *
 * @author ALUMNO
 */
public class FacturasControlador {
    public static boolean agregar(Facturas factura){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into facturas(estado_factura, fecha_factura, id_paciente, cantidadc_factura, numero_factura)"
                    +" values('" + factura.getEstado_factura()+"','"
                    + factura.getFecha_factura()+"',"
                    + factura.getPaciente().getId_paciente()+","
                    //+ factura.getUsuario().getId_usuario()+","
                    + factura.getCantidadc_factura()+ ","
                    + factura.getNumero_factura()+")";
            System.out.println("AGREGARFACTURA---->" + sql);
          
            try {
               Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_factura = keyset.getInt(1);
                    factura.setId_factura(id_factura);
                    Conexion.getConn().commit();
                }
                valor = true;
            } catch (SQLException ex){
                System.out.println("Error:"+ ex);
            }
        }
        return valor;
    }
    
    public static Facturas buscarId(Facturas factura){
        
        if (Conexion.conectar()) {
            String sql =  "select * from facturas f, pacientes p, usuarios u"
                    + " where f.id_paciente = p.id_paciente "
                    //+ "and f.id_usuario = u.id_usuario "
                    + "and id_factura =" + factura.getId_factura() + "";
            System.out.println("BUSCARID---->"+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) { //Si encuentra el id traerá todos los datos
                    Pacientes paciente = new Pacientes();
                    //Usuarios usuario = new Usuarios();
                    
                    factura.setId_factura(rs.getInt("id_factura"));
                    factura.setCantidadc_factura(rs.getInt("cantidadc_factura"));
                    factura.setNumero_factura(rs.getInt("numero_factura"));
                    factura.setEstado_factura(rs.getString("estado_factura"));
                    factura.setFecha_factura(rs.getDate("fecha_factura"));
                    
                    paciente.setId_paciente(rs.getInt("id_paciente"));
                    paciente.setNombre_paciente(rs.getString("nombre_paciente"));
                    
                    //usuario.setId_usuario(rs.getInt("id_usuario"));
                    //usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                    
                    factura.setPaciente(paciente);
                    //factura.setUsuario(usuario);
                }
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return factura;
    }
    
   public static String buscarEstado(String estado, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(estado);
               String sql = "select * from facturas f, pacientes p where f.id_paciente = p.id_paciente and  upper(estado_factura) like '%"
                        + estado.toUpperCase() + "%'"
                        + "order by id_factura offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("BUSCARESTADO--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_factura") + "</td>"
                                + "<td>" + rs.getString("fecha_factura") + "</td>"
                                + "<td>" + rs.getString("nombre_paciente") + "</td>"
                                + "<td>" + rs.getString("apellido_paciente") + "</td>"
                                
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
    
   public static boolean modificar(Facturas factura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update facturas set estado_factura= '" + factura.getEstado_factura()+ "',"
                    +"cantidadc_factura=" + factura.getCantidadc_factura() + ","
                    +"numero_factura=" + factura.getNumero_factura()+","
                    +"estado_factura= " + factura.getEstado_factura()+ ","
                    +"fecha_factura='" + factura.getFecha_factura() + "',"
                    +"id_paciente= " + factura.getPaciente().getId_paciente() + ","
                    //+"id_usuario=" + factura.getUsuario().getId_usuario() +" "
                    +"where id_factura=" + factura.getId_factura();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }
    
       public static boolean eliminar(Facturas factura) {
        boolean valor =false;
        if (Conexion.conectar()) {
            String sql = "delete from facturas where id_factura=" + factura.getId_factura();
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
