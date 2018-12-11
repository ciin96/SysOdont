/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

//import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.DecimalFormat;
import modelos.Citas;
import modelos.DetalleCitas;
import modelos.Horarios;
import modelos.Pacientes;
import modelos.Servicios;
import utiles.Conexion;

/**
 *
 * @author Cinthia
 */
public class DetalleCitasControlador {

    public static boolean agregar(DetalleCitas detallecita) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallecitas "
                    + "(id_cita, id_paciente, id_servicio, id_horario) "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecita.getCita().getId_cita());
                ps.setInt(2, detallecita.getPaciente().getId_paciente());
                ps.setInt(3, detallecita.getServicio().getId_servicio());
                ps.setInt(4, detallecita.getHorario().getId_horario());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static DetalleCitas buscarId(DetalleCitas detallecita) {
        if (Conexion.conectar()) {
            String sql = "select * from detallecitas dc "
                    + " join pacientes p"
                    + " on dc.id_paciente=p.id_paciente "
                    + " join servicios s"
                    + " on dc.id_servicio=s.id_servicio"
                    + " join horarios h"
                    + " on dc.id_horario=h.id_horario"
                    + " where dc.id_detallecita=" + detallecita.getId_detallecita();
            System.out.println("DETALLECITA " + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    detallecita.setId_detallecita(rs.getInt("id_detallecita"));
                    //detallecita.setHora_detallecita(rs.getString("hora_detallecita"));

                    Pacientes paciente = new Pacientes();
                    paciente.setId_paciente(rs.getInt("id_paciente"));
                    paciente.setNombre_paciente(rs.getString("nombre_paciente"));
                    detallecita.setPaciente(paciente);

                    Citas cita = new Citas();
                    cita.setId_cita(rs.getInt("id_cita"));
                    detallecita.setCita(cita);

                    Servicios servicio = new Servicios();
                    servicio.setId_servicio(rs.getInt("id_servicio"));
                    servicio.setNombre_servicio(rs.getString("nombre_servicio"));
                    detallecita.setServicio(servicio);
                    
                    Horarios horario = new Horarios();
                    horario.setId_horario(rs.getInt("id_horario"));
                    horario.setHorain_horario(rs.getString("horain_horario"));
                    horario.setHorafin_horario(rs.getString("horafin_horario"));
                    detallecita.setHorario(horario);
                }
            } catch (SQLException ex) {
                System.out.println("BUSCAR ID DETALLECITA--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallecita;
    }

    public static String buscarIdCita(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallecitas dc "
                        + "left join citas c on c.id_cita=dc.id_cita "
                        + "left join pacientes p on dc.id_paciente=p.id_paciente "
                        + "left join servicios s on dc.id_servicio=s.id_servicio "
                        + "left join horarios h on dc.id_horario=h.id_horario "
                        + "where dc.id_cita=" + id + " "
                        + "order by id_detallecita";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    //DecimalFormat df = new DecimalFormat( "#,###.00" );
                    String tabla = "";
                    //BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        // BigDecimal hora = rs.getBigDecimal("hora_detallepedido");
                        //total = total.add(hora);
                        //System.out.println("total"+total);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallecita") + "</td>"
                                + "<td>" + rs.getString("nombre_paciente") + "</td>"
                                + "<td>" + rs.getString("nombre_servicio") + "</td>"
                                + "<td>" + rs.getString("horain_horario") + "</td>"
                                + "<td>" + rs.getString("horafin_horario") + "</td>"
                                //+ "<td class='centrado'>" + df.format(hora) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallecita") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                
                                
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    } else {
                        //tabla += "<tr><td colspan=3>TOTAL</td><td class='centrado'>"+df.format(total)+"</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    // public static String


    public static boolean modificar(DetalleCitas detallecita) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallecitas set "
                    + "id_cita="+ detallecita.getCita().getId_cita()+", "
                    + "id_paciente="+ detallecita.getPaciente().getId_paciente()+", "
                    + "id_servicio="+ detallecita.getServicio().getId_servicio()+", "
                    + "id_horario="+ detallecita.getHorario().getId_horario()+" "
                    + "where id_detallecita= " + detallecita.getId_detallecita()+ " ";

            System.out.println("MODIFICAR DETALLE " + sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;

    }

    public static boolean eliminar(DetalleCitas detallecita) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "detele from detallecitas where id_detallecita=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecita.getId_detallecita());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("ELIMINAR---->" + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--->" + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminarPacienteCita(Citas cita) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallecitas where id_cita=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, cita.getId_cita());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("ELIMINAR PACIENTE CITA--->" + cita.getId_cita());
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--->" + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--->" + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

}
