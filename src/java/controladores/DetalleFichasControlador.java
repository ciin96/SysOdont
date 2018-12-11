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
import modelos.Fichas;
import modelos.DetalleFichas;
import modelos.Servicios;
import utiles.Conexion;

/**
 *
 * @author Cinthia
 */
public class DetalleFichasControlador {

    public static boolean agregar(DetalleFichas detalleficha) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallefichas "
                    + "(id_ficha,estado_detalleficha,id_servicio) "
                    + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleficha.getFicha().getId_ficha());
                ps.setString(2, detalleficha.getEstado_detalleficha());
                ps.setInt(3, detalleficha.getServicio().getId_servicio());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
               System.out.println("AGREGARDETALLEFICHA--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("AGREGARDETALLEFICHA--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static DetalleFichas buscarId(DetalleFichas detalleficha) {
        if (Conexion.conectar()) {
            String sql = "select * from detallefichas df "
                    + " join servicios s"
                    + " on df.id_servicio=s.id_servicio"
                    + " where dc.id_detalleficha=" + detalleficha.getId_detalleficha();
            System.out.println("DETALLEFICHA---> " + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    detalleficha.setId_detalleficha(rs.getInt("id_detalleficha"));
                    detalleficha.setEstado_detalleficha(rs.getString("estado_detalleficha"));


                    Fichas ficha = new Fichas();
                    ficha.setId_ficha(rs.getInt("id_ficha"));
                    detalleficha.setFicha(ficha);

                    Servicios servicio = new Servicios();
                    servicio.setId_servicio(rs.getInt("id_servicio"));
                    servicio.setNombre_servicio(rs.getString("nombre_servicio"));
                    detalleficha.setServicio(servicio);
                }
            } catch (SQLException ex) {
                System.out.println("BUSCAR ID DETALLECITA--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalleficha;
    }

    public static String buscarIdFicha(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallefichas df "
                        + "left join fichas f on f.id_ficha=df.id_ficha "
                        + "left join servicios s on df.id_servicio=s.id_servicio "
                        + "where df.id_ficha=" + id + " "
                        + "order by id_detalleficha";
                System.out.println("BUSCARIDDETALLEFICHA--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    //DecimalFormat df = new DecimalFormat( "#,###.00" );
                    String tabla = "";
                    //BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        // BigDecimal estado = rs.getBigDecimal("estado_detallepedido");
                        //total = total.add(estado);
                        //System.out.println("total"+total);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detalleficha") + "</td>"
                                + "<td>" + rs.getString("id_servicio") + "</td>"
                                + "<td>" + rs.getString("nombre_servicio") + "</td>"
                                + "<td>" + rs.getString("estado_detalleficha") + "</td>"
                                //+ "<td class='centrado'>" + df.format(estado) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detalleficha") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                             
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
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


    public static boolean modificar(DetalleFichas detalleficha) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallefichas set "
                    + "id_ficha="+ detalleficha.getFicha().getId_ficha()+", "
                    + "estado_detalleficha='" + detalleficha.getEstado_detalleficha()+"', "
                    + "id_servicio="+ detalleficha.getServicio().getId_servicio()+" "
                    + "where id_detalleficha= " + detalleficha.getId_detalleficha()+ " ";

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

    public static boolean eliminar(DetalleFichas detalleficha) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "detele from detallefichas where id_detalleficha=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleficha.getId_detalleficha());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("ELIMINAR---->" + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("ELIMINAR--->" + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminarServicioFicha(Fichas ficha) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallefichas where id_ficha=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, ficha.getId_ficha());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("ELIMINAR SERVICIO CITA--->" + ficha.getId_ficha());
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
