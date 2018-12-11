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
import modelos.Pacientes;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class PacientesControlador {
    public static boolean agregar(Pacientes paciente){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into pacientes(nombre_paciente, apellido_paciente, fechanac_paciente, ci_paciente,  direccion_paciente, telefono_paciente, correo_paciente)"
                    +" values('" +paciente.getNombre_paciente()+"','"
                    + paciente.getApellido_paciente()+"','"
                    + paciente.getFechanac_paciente()+"','"
                    + paciente.getCi_paciente()+"','"
                    + paciente.getDireccion_paciente()+"','"
                    + paciente.getTelefono_paciente()+"','"
                    + paciente.getCorreo_paciente()+"')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex){
                System.out.println("Error:"+ ex);
            }
        }
        return valor;
    }
    
    public static Pacientes buscarId(Pacientes paciente){
        
        if (Conexion.conectar()) {
            String sql = "select * from pacientes where id_paciente ='" + paciente.getId_paciente() + "'";
            System.out.println("sql"+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    paciente.setId_paciente(rs.getInt("id_paciente"));
                    paciente.setNombre_paciente(rs.getString("nombre_paciente"));
                    paciente.setApellido_paciente(rs.getString("apellido_paciente"));
                    paciente.setFechanac_paciente(rs.getDate("fechanac_paciente"));
                    paciente.setCi_paciente(rs.getInt("ci_paciente"));
                    paciente.setDireccion_paciente(rs.getString("direccion_paciente"));
                    paciente.setTelefono_paciente(rs.getInt("telefono_paciente"));
                    paciente.setCorreo_paciente(rs.getString("correo_paciente"));
   
                }else {
                    paciente.setId_paciente(0);
                    paciente.setNombre_paciente("");
                    paciente.setApellido_paciente("");
                    paciente.setFechanac_paciente(null);
                    paciente.setCi_paciente(0);
                    paciente.setDireccion_paciente("");
                    paciente.setTelefono_paciente(0);
                    paciente.setCorreo_paciente("");
 
                    //return null;
                    //return paciente;
                }
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return paciente;
    }
       public static String buscarNombrecita(int id_paciente, String nombre, int pagina, Date hoy) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from pacientes p, citas c, detallecitas dc where c.id_cita = dc.id_cita and p.id_paciente = dc.id_paciente and c.id_doctor = "+id_paciente+" and c.fecha_cita = '"+hoy+"' and upper(nombre_paciente) like '%%'order by p.id_paciente offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("BUSCARNOMBREPACIENTECITA--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_paciente") + "</td>"
                                + "<td>" + rs.getString("nombre_paciente") + "</td>"
                                +"<td>" + rs.getString("apellido_paciente") + "</td>"
                                +"<td>" + rs.getString("fechanac_paciente") + "</td>"
                                +"<td>" + rs.getString("ci_paciente") + "</td>"
                                +"<td>" + rs.getString("direccion_paciente") + "</td>"
                                +"<td>" + rs.getString("telefono_paciente") + "</td>"
                                +"<td>" + rs.getString("correo_paciente") + "</td>"
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
   public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from pacientes where upper(nombre_paciente) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_paciente offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_paciente") + "</td>"
                                + "<td>" + rs.getString("nombre_paciente") + "</td>"
                                +"<td>" + rs.getString("apellido_paciente") + "</td>"
                                +"<td>" + rs.getString("fechanac_paciente") + "</td>"
                                +"<td>" + rs.getString("ci_paciente") + "</td>"
                                +"<td>" + rs.getString("direccion_paciente") + "</td>"
                                +"<td>" + rs.getString("telefono_paciente") + "</td>"
                                +"<td>" + rs.getString("correo_paciente") + "</td>"
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
    
   public static boolean modificar(Pacientes paciente) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update pacientes set nombre_paciente= '" + paciente.getNombre_paciente() + "'," 
                    +"apellido_paciente='" + paciente.getApellido_paciente() + "',"
                    +"fechanac_paciente='" + paciente.getFechanac_paciente() + "',"
                    +"ci_paciente='" + paciente.getCi_paciente() + "',"
                    +"direccion_paciente='" + paciente.getDireccion_paciente() + "',"
                    +"telefono_paciente='" + paciente.getTelefono_paciente() + "',"
                    +"correo_paciente='" + paciente.getCorreo_paciente() + "'"
                    +"where id_paciente=" + paciente.getId_paciente();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }
    
       public static boolean eliminar(Pacientes paciente) {
        boolean valor =false;
        if (Conexion.conectar()) {
            String sql = "delete from pacientes where id_paciente=" + paciente.getId_paciente();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }
       
       
       
       public static Pacientes buscarCi(Pacientes paciente) {

        if (Conexion.conectar()) {
            String sql = "select * from pacientes where ci_paciente ='" + paciente.getCi_paciente() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    paciente.setId_paciente(0);

                } else {

                    paciente.setId_paciente(-1);

                    //return null;
                    //return paciente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return paciente;
    }
         
             public static Pacientes buscarCorreo(Pacientes paciente) {

        if (Conexion.conectar()) {
            String sql = "select * from pacientes where correo_paciente ='" + paciente.getCorreo_paciente() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    paciente.setId_paciente(0);

                } else {

                    paciente.setId_paciente(-1);

                    //return null;
                    //return paciente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return paciente;
    }
       
       
       
       
       
}


