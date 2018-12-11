<%@page import="modelos.Servicios"%>
<%@page import="controladores.DetalleFichasControlador"%>
<%@page import="modelos.DetalleFichas"%>
<%@page import="modelos.Pacientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.FichasControlador"%>
<%@page import="modelos.Fichas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleficha = Integer.parseInt(request.getParameter("id_detalleficha"));
    int id_ficha = Integer.parseInt(request.getParameter("id_ficha"));
    int id_servicio = Integer.parseInt(request.getParameter("id_servicio"));
    String estado_detalleficha = request.getParameter("estado_detalleficha");
  
    
    String tipo ="error";
    String mensaje ="Datos no agregados.";
   
    Fichas ficha = new Fichas();
    ficha.setId_ficha(id_ficha);
    
    Servicios servicio = new Servicios();
    servicio.setId_servicio(id_servicio);
    
    DetalleFichas detalleficha = new DetalleFichas();
    detalleficha.setId_detalleficha(id_detalleficha);
    detalleficha.setEstado_detalleficha(estado_detalleficha);
    
    
    detalleficha.setFicha(ficha);
    detalleficha.setServicio(servicio);
    
   if (DetalleFichasControlador.modificar(detalleficha)){
       tipo = "success";
       mensaje = "Datos modificados";
   }
   
  JSONObject obj = new JSONObject();
  obj.put("tipo", tipo);
  obj.put("mensaje", mensaje);
  out.print(obj);
  out.flush();
    
    %>
