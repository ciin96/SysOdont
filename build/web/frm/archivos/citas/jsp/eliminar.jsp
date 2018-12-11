<%@page import="controladores.CitasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Citas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cita = Integer.parseInt(request.getParameter("id_cita"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Citas cita = new Citas();
    cita.setId_cita(id_cita);

    if (CitasControlador.eliminar(cita)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>