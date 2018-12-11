<%@page import="controladores.FichasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Fichas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ficha = Integer.parseInt(request.getParameter("id_ficha"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Fichas ficha = new Fichas();
    ficha.setId_ficha(id_ficha);

    if (FichasControlador.eliminar(ficha)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>