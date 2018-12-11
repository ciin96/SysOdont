<%@page import="controladores.TipoPagosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.TipoPagos"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_tipopago = Integer.parseInt(request.getParameter("id_tipopago"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    TipoPagos tipopago = new TipoPagos();
    tipopago.setId_tipopago(id_tipopago);

    if (TipoPagosControlador.eliminar(tipopago)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>