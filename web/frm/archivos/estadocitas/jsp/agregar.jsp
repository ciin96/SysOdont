<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.EstadoCitasControlador"%>
<%@page import="modelos.EstadoCitas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_estadocita = Integer.parseInt(request.getParameter("id_estadocita"));
    String nombre_estadocita = request.getParameter("nombre_estadocita");
    
    
    String tipo ="error";
    String mensaje ="Datos no agregados.";
    
    EstadoCitas estadocita = new EstadoCitas();
    estadocita.setId_estadocita(id_estadocita);
    estadocita.setNombre_estadocita(nombre_estadocita);
    
   if (EstadoCitasControlador.agregar(estadocita)){
       tipo = "success";
       mensaje = "Datos agregados";
   }
   
  JSONObject obj = new JSONObject();
  obj.put("tipo", tipo);
  obj.put("mensaje", mensaje);
  out.print(obj);
  out.flush();
    
    %>