<%@page import="controladores.EstadoCitasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.EstadoCitas"%>
<%@page import="java.sql.ResultSet"%>
<%
  int id_estadocita = Integer.parseInt(request.getParameter("id_estadocita"));  
  String tipo = "error";
  String mensaje = "Datos no encontrados";
  String nuevo = "true";
EstadoCitas estadocita = new EstadoCitas();
estadocita.setId_estadocita(id_estadocita);

EstadoCitasControlador.buscarId(estadocita);
if(estadocita.getId_estadocita()!=0) {
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "false";
}else{
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "true";
}

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_estadocita", estadocita.getId_estadocita());
    obj.put("nombre_estadocita", estadocita.getNombre_estadocita());
    
    
    out.print(obj);
    out.flush();
%>