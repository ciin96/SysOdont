<%@page import="controladores.DetalleCitasControlador"%>
<%@page import="modelos.DetalleCitas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecita = Integer.parseInt(request.getParameter("id_detallecita"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    DetalleCitas detallecita = new DetalleCitas();
    detallecita.setId_detallecita(id_detallecita);

    if (DetalleCitasControlador.eliminar(detallecita)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>