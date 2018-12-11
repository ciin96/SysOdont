<%@page import="controladores.HorariosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Horarios"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_horario = Integer.parseInt(request.getParameter("id_horario"));
    String nombre_horario = request.getParameter("nombre_horario");
    String horain_horario = request.getParameter("horain_horario");
    String horafin_horario = request.getParameter("horafin_horario");


    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Horarios horario = new Horarios();
    horario.setId_horario(id_horario);
    horario.setNombre_horario(nombre_horario);
    horario.setHorain_horario(horain_horario);
    horario.setHorafin_horario(horafin_horario);
   

    if (HorariosControlador.modificar(horario)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
