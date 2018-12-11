
<%@page import="modelos.Facturas"%>
<%@page import="controladores.CuentasControlador"%>
<%@page import="controladores.DetalleCuentasControlador"%>
<%@page import="modelos.Horarios"%>
<%@page import="modelos.EstadoCuentas"%>
<%@page import="modelos.Doctores"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Cuentas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Cuentas cuenta = new Cuentas();
    cuenta.setId_cuenta(id_cuenta);

    CuentasControlador.buscarId(cuenta);
    if (cuenta.getId_cuenta() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else{
        Facturas factura = new Facturas();
        
        cuenta.setId_cuenta(0);
        cuenta.setMonto_total(0);
        cuenta.setTotal_cuota(0);
        cuenta.setEstado_cuenta("");
        
        factura.setId_factura(0);
        
        cuenta.setFactura(factura);
    }
    String contenido_detalle = DetalleCuentasControlador.buscarIdCuenta(id_cuenta);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_cuenta", cuenta.getId_cuenta());
    obj.put("monto_total", cuenta.getMonto_total());
    obj.put("estado_cuenta", cuenta.getEstado_cuenta());
    obj.put("total_cuota", cuenta.getTotal_cuota());
    
    
    obj.put("id_factura", cuenta.getFactura().getId_factura());
   
    
    obj.put("contenido_detalle", contenido_detalle);

    out.print(obj);
    out.flush();
%>