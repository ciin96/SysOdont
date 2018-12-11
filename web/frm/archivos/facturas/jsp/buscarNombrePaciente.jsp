<%@page import="controladores.PacientesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page  import="java.sql.ResultSet"%>
<%
    String nombre_paciente = request.getParameter("bnombre");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    

    String mensaje = "Busqueda exitosa";
    String contenido = PacientesControlador.buscarNombre(nombre_paciente, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>