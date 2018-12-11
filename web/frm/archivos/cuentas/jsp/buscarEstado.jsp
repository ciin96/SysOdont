<%@page import="controladores.CuentasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page  import="java.sql.ResultSet"%>
<%
    String estado_cuenta = request.getParameter("bestado");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    

    String mensaje = "Busqueda exitosa";
    String contenido = CuentasControlador.buscarEstado(estado_cuenta, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>