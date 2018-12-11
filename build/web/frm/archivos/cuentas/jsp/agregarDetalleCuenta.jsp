<%@page import="controladores.DetalleCuentasControlador"%>
<%@page import="modelos.DetalleCuentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Cuentas"%>
<%@page import="java.sql.ResultSet"%>
<%
    //int id_detallecuenta = Integer.parseInt(request.getParameter("id_detallecuenta"));
    int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));
    int nro_cuota = Integer.parseInt(request.getParameter("nro_cuota"));
    int monto_cuota = Integer.parseInt(request.getParameter("monto_cuta"));
    String estado_cuota = request.getParameter("estado_cuota");
 
    Cuentas cuenta = new Cuentas();
    cuenta.setId_cuenta(id_cuenta);

    
    String tipo ="error";
    String mensaje ="Datos no agregados.";
    
    DetalleCuentas detallecuenta = new DetalleCuentas();
    detallecuenta.setNro_cuota(nro_cuota);
    detallecuenta.setMonto_cuota(monto_cuota);
    detallecuenta.setEstado_cuota(estado_cuota);
    
    
    
    detallecuenta.setCuenta(cuenta);
    
   if (DetalleCuentasControlador.agregar(detallecuenta)){
       tipo = "success";
       mensaje = "Datos agregados";
   }
   
  JSONObject obj = new JSONObject();
  obj.put("tipo", tipo);
  obj.put("mensaje", mensaje);
  out.print(obj);
  out.flush();
    
    %>