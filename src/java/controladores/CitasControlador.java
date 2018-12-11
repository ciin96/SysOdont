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
import modelos.Citas;
import utiles.Conexion;
import utiles.Utiles;
import modelos.Doctores;
import modelos.EstadoCitas;
import modelos.Horarios;

/**
 *
 * @author ALUMNO
 */
public class CitasControlador {
    public static boolean agregar(Citas cita){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "insert into citas(nombre_cita, fecha_cita, id_doctor, id_estadocita)"
                    +" values('" +cita.getNombre_cita()+"','"
                    + cita.getFecha_cita()+"',"
                    + cita.getDoctor().getId_doctor()+","
                    + cita.getEstadocita().getId_estadocita()+")";
          
            try {
               Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_cita = keyset.getInt(1);
                    cita.setId_cita(id_cita);
                    Conexion.getConn().commit();
                }
                valor = true;
            } catch (SQLException ex){
                System.out.println("Error:"+ ex);
            }
        }
        return valor;
    }
    
    public static Citas buscarId(Citas cita){
        
        if (Conexion.conectar()) {
            String sql =  "select * from citas c, doctores d, estadocitas et"
                    + " where c.id_doctor = d.id_doctor "
                    + "and c.id_estadocita = et.id_estadocita "
                    + "and id_cita =" + cita.getId_cita() + "";
            System.out.println("BUSCARID---->"+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) { //Si encuentra el id traerá todos los datos
                    Doctores doctor = new Doctores();
                    EstadoCitas estadocita = new EstadoCitas();
                    //Horarios horario = new Horarios();
                    
                    cita.setId_cita(rs.getInt("id_cita"));
                    cita.setNombre_cita(rs.getString("nombre_cita"));
                    cita.setFecha_cita(rs.getDate("fecha_cita"));
                    
                    doctor.setId_doctor(rs.getInt("id_doctor"));
                    doctor.setNombre_doctor(rs.getString("nombre_doctor"));
                    
                    estadocita.setId_estadocita(rs.getInt("id_estadocita"));
                    estadocita.setNombre_estadocita(rs.getString("nombre_estadocita"));
                    
                    //horario.setId_horario(rs.getInt("id_horario"));
                    //horario.setHorain_horario(rs.getString("horain_horario"));
                    //horario.setHorafin_horario(rs.getString("horafin_horario"));
                    
                    cita.setDoctor(doctor);
                    cita.setEstadocita(estadocita);
                    //cita.setHorario(horario);
                }
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return cita;
    }
    
   public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                 String sql = "select * from citas c, doctores d where c.id_doctor = d.id_doctor and  upper(nombre_doctor) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by id_cita offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("BUSCARNOMBRE--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_cita") + "</td>"
                                + "<td>" + rs.getString("nombre_doctor") + "</td>"
                                + "<td>" + rs.getString("apellido_doctor") + "</td>"
                                + "<td>" + rs.getString("fecha_cita") + "</td>"
                                
                                
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
    
   public static boolean modificar(Citas cita) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update citas set nombre_cita= '" + cita.getNombre_cita() + "',"
                    +"fecha_cita='" + cita.getFecha_cita() + "',"
                    +"id_estadocita=" + cita.getEstadocita().getId_estadocita()+","
                    +"id_doctor= " + cita.getDoctor().getId_doctor() + " "
                    +"where id_cita=" + cita.getId_cita();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }
    
       public static boolean eliminar(Citas cita) {
        boolean valor =false;
        if (Conexion.conectar()) {
            String sql = "delete from citas where id_cita=" + cita.getId_cita();
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
