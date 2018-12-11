/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import modelos.Cuentas;
import modelos.DetalleCuentas;
import utiles.Conexion;

/**
 *
 * @author Cinthia
 */
public class DetalleCuentasControlador {

    public static boolean agregar(DetalleCuentas detallecuenta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallecuentas "
                    + "(id_cuenta, nro_cuota, monto_cuota, estado_cuota "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecuenta.getCuenta().getId_cuenta());
                ps.setInt(2, detallecuenta.getNro_cuota());
                ps.setInt(3, detallecuenta.getMonto_cuota());
                ps.setString(4, detallecuenta.getEstado_cuota());
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

    public static DetalleCuentas buscarId(DetalleCuentas detallecuenta) {
        if (Conexion.conectar()) {
            String sql = "select * from detallecuentas dc "
                    + " join cuentas c"
                    + " on dc.id_cuenta=c.id_cuenta "
                    + " where dc.id_detallecuenta=" + detallecuenta.getId_detallecuenta();
            System.out.println("DETALLECITA " + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    detallecuenta.setId_detallecuenta(rs.getInt("id_detallecuenta"));
                    detallecuenta.setNro_cuota(rs.getInt("nro_cuota"));
                    detallecuenta.setMonto_cuota(rs.getInt("monto_cuota"));
                    detallecuenta.setEstado_cuota(rs.getString("estado_cuota"));


                    Cuentas cuenta = new Cuentas();
                    cuenta.setId_cuenta(rs.getInt("id_cuenta"));
                    detallecuenta.setCuenta(cuenta);

                  
                }
            } catch (SQLException ex) {
                System.out.println("BUSCAR ID DETALLECITA--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallecuenta;
    }

    public static String buscarIdCuenta(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallecuentas dc "
                        + "left join cuentas c on c.id_cuenta=dc.id_cuenta "
                        + "where dc.id_cuenta=" + id + " "
                        + "order by id_detallecuenta";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat( "#,###" );
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                         BigDecimal hora = rs.getBigDecimal("hora_detallepedido");
                        total = total.add(hora);
                        System.out.println("total"+total);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallecuenta") + "</td>"
                                + "<td>" + rs.getString("id_paciente") + "</td>"
                                + "<td>" + rs.getString("nombre_paciente") + "</td>"
                                + "<td>" + rs.getString("id_servicio") + "</td>"
                                + "<td>" + rs.getString("nombre_servicio") + "</td>"
                                + "<td>" + rs.getString("hora_detallecuenta") + "</td>"
                                //+ "<td class='centrado'>" + df.format(hora) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallecuenta") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                
                                
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    } else {
                        tabla += "<tr><td colspan=3>TOTAL</td><td class='centrado'>"+df.format(total)+"</td></tr>";
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


    public static boolean modificar(DetalleCuentas detallecuenta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallecuentas set "
                    + "id_cuenta="+ detallecuenta.getCuenta().getId_cuenta()+", "
                    + "nro_cuota='" + detallecuenta.getNro_cuota()+"', "
                    + "monto_cuota="+ detallecuenta.getMonto_cuota()+", "
                    + "estado_cuota="+ detallecuenta.getEstado_cuota()+" "
                    + "where id_detallecuenta= " + detallecuenta.getId_detallecuenta()+ " ";

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

    public static boolean eliminar(DetalleCuentas detallecuenta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "detele from detallecuentas where id_detallecuenta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecuenta.getId_detallecuenta());
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

    //public static boolean eliminarPacienteCuenta(Cuentas cuenta) {
        //boolean valor = false;
        //if (Conexion.conectar()) {
            //String sql = "delete from detallecuentas where id_cuenta=?";
            //try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                //ps.setInt(1, cuenta.getId_cuenta());
                //ps.executeUpdate();
               // ps.close();
                //Conexion.getConn().commit();
                //System.out.println("ELIMINAR PACIENTE CITA--->" + cuenta.getId_cuenta());
                //valor = true;
            //} catch (SQLException ex) {
                //System.out.println("--->" + ex.getLocalizedMessage());
                //try {
                   // Conexion.getConn().rollback();
                //} catch (SQLException ex1) {
                    //System.out.println("--->" + ex1.getLocalizedMessage());
                //}
            //}
        //}
        //Conexion.cerrar();
        //return valor;
    //}

}
