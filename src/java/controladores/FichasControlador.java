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
import modelos.Fichas;
import utiles.Conexion;
import utiles.Utiles;
import modelos.Doctores;
import modelos.Pacientes;

/**
 *
 * @author ALUMNO
 */
public class FichasControlador {

    public static boolean agregar(Fichas ficha) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into fichas(nombre_ficha, fecha_ficha, id_doctor, id_paciente)"
                    + " values('" + ficha.getNombre_ficha() + "','"
                    + ficha.getFecha_ficha() + "',"
                    + ficha.getDoctor().getId_doctor() + ","
                    + ficha.getPaciente().getId_paciente() + ")";

            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_ficha = keyset.getInt(1);
                    ficha.setId_ficha(id_ficha);
                    Conexion.getConn().commit();
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static boolean facturar(Fichas ficha) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "select * from fichas f, doctores d, pacientes p, detallefichas df, servicios s  where f.id_doctor = d.id_doctor and f.id_paciente = p.id_paciente  and df.id_ficha = f.id_ficha and s.id_servicio = df.id_servicio and f.id_ficha =" + ficha.getId_ficha() + "";
            System.out.println("FACTURAR---->" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) { //Si encuentra el id traerá todos los datos

                    String sql1 = "insert into facturas(id_paciente, fecha_factura)"
                            + " values('" + ficha.getPaciente().getId_paciente() + "','"
                        
                            + ficha.getFecha_ficha() + ")";
                    System.out.println("INSERT-->" + sql1);
                    try {
                        Conexion.getSt().executeUpdate(sql1, Statement.RETURN_GENERATED_KEYS);
                        ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                        if (keyset.next()) {
                            int id_factura = keyset.getInt(1);
                            ficha.setId_ficha(id_factura);
                            Conexion.getConn().commit();
                            
                            
                            
                            
                        }
                        valor = true;
                    } catch (SQLException ex) {
                        System.out.println("Error:" + ex);
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }

        }
        return valor;
    }

    public static Fichas buscarId(Fichas ficha) {

        if (Conexion.conectar()) {
            String sql = "select * from fichas f, doctores d, pacientes p"
                    + " where f.id_doctor = d.id_doctor "
                    + "and f.id_paciente = p.id_paciente "
                    + "and id_ficha =" + ficha.getId_ficha() + "";
            System.out.println("BUSCARID---->" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) { //Si encuentra el id traerá todos los datos
                    Doctores doctor = new Doctores();
                    Pacientes paciente = new Pacientes();

                    ficha.setId_ficha(rs.getInt("id_ficha"));
                    ficha.setNombre_ficha(rs.getString("nombre_ficha"));
                    ficha.setFecha_ficha(rs.getDate("fecha_ficha"));

                    doctor.setId_doctor(rs.getInt("id_doctor"));
                    doctor.setNombre_doctor(rs.getString("nombre_doctor"));

                    paciente.setId_paciente(rs.getInt("id_paciente"));
                    paciente.setNombre_paciente(rs.getString("nombre_paciente"));

                    ficha.setDoctor(doctor);
                    ficha.setPaciente(paciente);
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return ficha;
    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(nombre);
                String sql = "select * from fichas f, pacientes p where f.id_paciente = p.id_paciente and   upper(nombre_paciente) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by id_ficha offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("BUSCARNOMBRE--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_ficha") + "</td>"
                                + "<td>" + rs.getString("fecha_ficha") + "</td>"
                                + "<td>" + rs.getString("nombre_paciente") + "</td>"
                                + "<td>" + rs.getString("apellido_paciente") + "</td>"
                                //+ "<td>" + rs.getString("observacion_ficha") + "</td>"

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

    public static boolean modificar(Fichas ficha) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update fichas set nombre_ficha= '" + ficha.getNombre_ficha() + "',"
                    + "fecha_ficha='" + ficha.getFecha_ficha() + "',"
                    + "id_doctor= " + ficha.getDoctor().getId_doctor() + ","
                    + "id_paciente= " + ficha.getPaciente().getId_paciente() + " "
                    + "where id_ficha=" + ficha.getId_ficha();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Fichas ficha) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from fichas where id_ficha=" + ficha.getId_ficha();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }
}
