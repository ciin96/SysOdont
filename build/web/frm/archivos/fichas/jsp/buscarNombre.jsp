<%@page import="controladores.FichasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page  import="java.sql.ResultSet"%>
<%
    String nombre_ficha = request.getParameter("bnombre");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
       
    String mensaje = "Busqueda exitosa";
    String contenido = FichasControlador.buscarNombre(nombre_ficha, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>