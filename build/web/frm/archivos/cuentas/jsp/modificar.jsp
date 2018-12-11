
<%@page import="modelos.Facturas"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.CuentasControlador"%>
<%@page import="modelos.Cuentas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
    int monto_total = Integer.parseInt(request.getParameter("monto_total"));
    int total_cuota = Integer.parseInt(request.getParameter("total_cuota"));
    String estado_cuenta = request.getParameter("estado_cuenta");
    
    Facturas factura = new Facturas();
    factura.setId_factura(id_factura);
    
    String tipo ="error";
    String mensaje ="Datos no agregados.";
    
    Cuentas cuenta = new Cuentas();
    cuenta.setId_cuenta(id_cuenta);
    cuenta.setMonto_total(monto_total);
    cuenta.setTotal_cuota(total_cuota);
    cuenta.setEstado_cuenta(estado_cuenta);
        
  
    cuenta.setFactura(factura);
    
   if (CuentasControlador.modificar(cuenta)){
       tipo = "success";
       mensaje = "Datos agregados";
   }
   
  JSONObject obj = new JSONObject();
  obj.put("tipo", tipo);
  obj.put("mensaje", mensaje);
  out.print(obj);
  out.flush();
    
    %>