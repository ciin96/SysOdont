<%@page import="controladores.FacturasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page  import="java.sql.ResultSet"%>
<%
    String nombre_factura = request.getParameter("bnombre");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    

    String mensaje = "Busqueda exitosa";
    String contenido = FacturasControlador.buscarNombre(nombre_factura, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>