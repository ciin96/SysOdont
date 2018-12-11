<%@page import="controladores.CuentasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Cuentas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Cuentas cuenta = new Cuentas();
    cuenta.setId_cuenta(id_cuenta);

    if (CuentasControlador.eliminar(cuenta)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>