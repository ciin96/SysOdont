/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Doctores;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class DoctoresControlador {
    public static boolean agregar(Doctores doctor){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into doctores(nombre_doctor, apellido_doctor, ci_doctor, nroregistro_doctor,  direccion_doctor, telefono_doctor, correo_doctor)"
                    +" values('" +doctor.getNombre_doctor()+"','"
                    + doctor.getApellido_doctor()+"','"
                    + doctor.getCi_doctor()+"','"
                    + doctor.getNroregistro_doctor()+"','"
                    + doctor.getDireccion_doctor()+"','"
                    + doctor.getTelefono_doctor()+"','"
                    + doctor.getCorreo_doctor()+"')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex){
                System.out.println("Error:"+ ex);
            }
        }
        return valor;
    }
    
    public static Doctores buscarId(Doctores doctor){
        
        if (Conexion.conectar()) {
            String sql = "select * from doctores where id_doctor ='" + doctor.getId_doctor() + "'";
            System.out.println("sql"+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    doctor.setId_doctor(rs.getInt("id_doctor"));
                    doctor.setNombre_doctor(rs.getString("nombre_doctor"));
                    doctor.setApellido_doctor(rs.getString("apellido_doctor"));
                    doctor.setCi_doctor(rs.getInt("ci_doctor"));
                    doctor.setNroregistro_doctor(rs.getInt("nroregistro_doctor"));
                    doctor.setDireccion_doctor(rs.getString("direccion_doctor"));
                    doctor.setTelefono_doctor(rs.getInt("telefono_doctor"));
                    doctor.setCorreo_doctor(rs.getString("correo_doctor"));
   
                }else {
                    doctor.setId_doctor(0);
                    doctor.setNombre_doctor("");
                    doctor.setApellido_doctor("");
                    doctor.setCi_doctor(0);
                    doctor.setNroregistro_doctor(0);
                    doctor.setDireccion_doctor("");
                    doctor.setTelefono_doctor(0);
                    doctor.setCorreo_doctor("");
 
                    //return null;
                    //return doctor;
                }
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return doctor;
    }
    
   public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from doctores where upper(nombre_doctor) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_doctor offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_doctor") + "</td>"
                                + "<td>" + rs.getString("nombre_doctor") + "</td>"
                                +"<td>" + rs.getString("apellido_doctor") + "</td>"
                                +"<td>" + rs.getString("ci_doctor") + "</td>"
                                +"<td>" + rs.getString("nroregistro_doctor") + "</td>"
                                +"<td>" + rs.getString("direccion_doctor") + "</td>"
                                +"<td>" + rs.getString("telefono_doctor") + "</td>"
                                +"<td>" + rs.getString("correo_doctor") + "</td>"
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
   
      public static Doctores buscarNroregistro(Doctores doctor) {

        if (Conexion.conectar()) {
            String sql = "select * from doctores where nroregistro_doctor ='" + doctor.getNroregistro_doctor() + "'";
            System.out.println("NROREGISTRO--->" +sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    doctor.setId_doctor(0);

                } else {

                    doctor.setId_doctor(-1);

                    //return null;
                    //return doctor;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return doctor;
    }
      
         public static Doctores buscarCi(Doctores doctor) {

        if (Conexion.conectar()) {
            String sql = "select * from doctores where ci_doctor ='" + doctor.getCi_doctor() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    doctor.setId_doctor(0);

                } else {

                    doctor.setId_doctor(-1);

                    //return null;
                    //return doctor;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return doctor;
    }
         
             public static Doctores buscarCorreo(Doctores doctor) {

        if (Conexion.conectar()) {
            String sql = "select * from doctores where correo_doctor ='" + doctor.getCorreo_doctor() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    doctor.setId_doctor(0);

                } else {

                    doctor.setId_doctor(-1);

                    //return null;
                    //return doctor;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return doctor;
    }
   
    
    public static boolean modificar(Doctores doctor) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update doctores set nombre_doctor= '" + doctor.getNombre_doctor() + "'," 
                    +"apellido_doctor='" + doctor.getApellido_doctor() + "',"
                    +"ci_doctor='" + doctor.getNroregistro_doctor() + "',"
                    +"nroregistro_doctor='" + doctor.getNroregistro_doctor() + "',"
                    +"direccion_doctor='" + doctor.getDireccion_doctor() + "',"
                    +"telefono_doctor='" + doctor.getTelefono_doctor() + "',"
                    +"correo_doctor='" + doctor.getCorreo_doctor()+ "'"
                    +"where id_doctor=" + doctor.getId_doctor();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }
    
       public static boolean eliminar(Doctores doctor) {
        boolean valor =false;
        if (Conexion.conectar()) {
            String sql = "delete from doctores where id_doctor=" + doctor.getId_doctor();
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
