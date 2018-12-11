<%@page import="controladores.HorariosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Horarios"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_horario = Integer.parseInt(request.getParameter("id_horario"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Horarios horario = new Horarios();
    horario.setId_horario(id_horario);

    if (HorariosControlador.eliminar(horario)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>