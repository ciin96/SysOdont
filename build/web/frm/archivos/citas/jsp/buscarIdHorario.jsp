<%@page import="controladores.HorariosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Horarios"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_horario = Integer.parseInt(request.getParameter("id_horario"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Horarios horario = new Horarios();
    horario.setId_horario(id_horario);

    HorariosControlador.buscarId(horario);
    if (horario.getId_horario() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "true";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_horario", horario.getId_horario());
    obj.put("nombre_horario", horario.getNombre_horario());
    obj.put("horain_horario", horario.getHorain_horario());
    obj.put("horafin_horario", horario.getHorafin_horario());

    out.print(obj);
    out.flush();
%>