<%@page import="controladores.DoctoresControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page  import="java.sql.ResultSet"%>
<%
    String nombre_doctor = request.getParameter("bnombre");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    

    String mensaje = "Busqueda exitosa";
    String contenido = DoctoresControlador.buscarNombre(nombre_doctor, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>