
<%@page import="modelos.Pacientes"%>
<%@page import="modelos.Doctores"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.FichasControlador"%>
<%@page import="modelos.Fichas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ficha = Integer.parseInt(request.getParameter("id_ficha"));
   
   
    
    String tipo ="error";
    String mensaje ="Datos no agregados.";
    
    Fichas ficha = new Fichas();
    ficha.setId_ficha(id_ficha);
    
    
    
   if (FichasControlador.facturar(ficha)){
       tipo = "success";
       mensaje = "Datos agregados";
   }
   
  JSONObject obj = new JSONObject();
  obj.put("tipo", tipo);
  obj.put("id_ficha", String.valueOf(ficha.getId_ficha()));
  obj.put("mensaje", mensaje);
  out.print(obj);
  out.flush();
    
    %>