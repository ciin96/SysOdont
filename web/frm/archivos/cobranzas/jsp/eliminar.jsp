<%@page import="controladores.CobranzasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Cobranzas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cobranza = Integer.parseInt(request.getParameter("id_cobranza"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Cobranzas cobranza = new Cobranzas();
    cobranza.setId_cobranza(id_cobranza);

    if (CobranzasControlador.eliminar(cobranza)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>