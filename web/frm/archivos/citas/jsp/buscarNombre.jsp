<%@page import="controladores.CitasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page  import="java.sql.ResultSet"%>
<%
    String nombre_cita = request.getParameter("bnombre");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    

    String mensaje = "Busqueda exitosa";
    String contenido = CitasControlador.buscarNombre(nombre_cita, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>