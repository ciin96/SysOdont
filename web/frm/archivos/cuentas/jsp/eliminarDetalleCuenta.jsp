<%@page import="controladores.DetalleCuentasControlador"%>
<%@page import="modelos.DetalleCuentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecuenta = Integer.parseInt(request.getParameter("id_detallecuenta"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    DetalleCuentas detallecuenta = new DetalleCuentas();
    detallecuenta.setId_detallecuenta(id_detallecuenta);

    if (DetalleCuentasControlador.eliminar(detallecuenta)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>