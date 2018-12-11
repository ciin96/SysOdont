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
import modelos.Facturas;
import modelos.DetalleFacturas;
//import modelos.Pacientes;
import modelos.Servicios;
import utiles.Conexion;

/**
 *
 * @author Cinthia
 */
public class DetalleFacturasControlador {

    public static boolean agregar(DetalleFacturas detallefactura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallefacturas "
                    + "(id_factura, id_servicio, cantidad_factura) "
                    + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallefactura.getFactura().getId_factura());
                ps.setInt(2, detallefactura.getServicio().getId_servicio());
                ps.setInt(3, detallefactura.getCantidad_factura());
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

    public static DetalleFacturas buscarId(DetalleFacturas detallefactura) {
        if (Conexion.conectar()) {
            String sql = "select * from detallefacturas df "
                    + " join servicios s"
                    + " on df.id_servicio=s.id_servicio"
                    + " where df.id_detallefactura=" + detallefactura.getId_detallefactura();
            System.out.println("DETALLEFICHA" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    detallefactura.setId_detallefactura(rs.getInt("id_detallefactura"));
                    detallefactura.setCantidad_factura(rs.getInt("cantidad_factura"));


                    Facturas factura = new Facturas();
                    factura.setId_factura(rs.getInt("id_factura"));
                    detallefactura.setFactura(factura);

                    Servicios servicio = new Servicios();
                    servicio.setId_servicio(rs.getInt("id_servicio"));
                    servicio.setNombre_servicio(rs.getString("nombre_servicio"));
                    servicio.setPrecio_servicio(rs.getInt("precio_servicio"));
                    detallefactura.setServicio(servicio);
                }
            } catch (SQLException ex) {
                System.out.println("BUSCAR ID DETALLECITA--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallefactura;
    }

       public static String buscarIdFactura(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallefacturas df "
                        + "left join facturas f on f.id_factura=df.id_factura "
                        + "left join servicios s on df.id_servicio=s.id_servicio "
                        + "where df.id_factura=" + id + " "
                        + "order by id_detallefactura";
                System.out.println("BUSCARIDFACTURADETALLE--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat( "#,###" );
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    BigDecimal totalC = BigDecimal.ZERO;
                    BigDecimal totalS = BigDecimal.ZERO;
                    
                    //int precio= 0;
                    //int cantidad = 0;
                    int totalT = 0;
                    //int totalS = 0;
                    
                    while (rs.next()) {
                        int precio = rs.getInt("precio_servicio");
                        int cantidad = rs.getInt("cantidad_factura");
                        
                        
                        totalT = precio * cantidad;
                        
                        //totalS = totalS.add(totalT);
                        
                         //BigDecimal precio = rs.getBigDecimal("precio_detallefactura");
                        //total = total.add(precio);
                        System.out.println("totalT"+totalT);
                        //System.out.println("totalS"+totalS);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallefactura") + "</td>"
                                + "<td>" + rs.getString("id_servicio") + "</td>"
                                + "<td>" + rs.getString("nombre_servicio") + "</td>"
                                + "<td class='centrado'>" + df.format(precio) + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallefactura") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                
                                
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    } else {
                        tabla += "<tr><td colspan=3>TOTAL</td><td class='centrado'>"+df.format(totalT)+"</td></tr>";
                         //tabla += "<tr><td colspan=3>TOTAL</td><td class='centrado'>"+df.format(totalS)+"</td></tr>";
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


    public static boolean modificar(DetalleFacturas detallefactura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallefacturas set "
                    + "id_factura="+ detallefactura.getFactura().getId_factura()+", "
                    + "cantidad_factura='" + detallefactura.getCantidad_factura()+"', "
                    + "id_servicio="+ detallefactura.getServicio().getId_servicio()+" "
                    + "where id_detallefactura= " + detallefactura.getId_detallefactura()+ " ";

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

    public static boolean eliminar(DetalleFacturas detallefactura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallefacturas where id_detallefactura=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallefactura.getId_detallefactura());
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

    public static boolean eliminarPacienteFactura(Facturas factura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallefacturas where id_factura=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, factura.getId_factura());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("ELIMINAR PACIENTE CITA--->" + factura.getId_factura());
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
