<%@page import="controladores.PacientesControlador"%>
<%@page import="modelos.Pacientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String correo_paciente = request.getParameter("correo_paciente");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Pacientes paciente = new Pacientes();
    paciente.setCorreo_paciente(correo_paciente);

    PacientesControlador.buscarCorreo(paciente);
    if (paciente.getId_paciente() == 0) {
        tipo = "success";
        mensaje = "Datos de correo repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("correo_paciente", paciente.getCorreo_paciente());

    out.print(obj);
    out.flush();
%>
