<%@page import="controladores.PacientesControlador"%>
<%@page import="modelos.Pacientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int ci_paciente = Integer.parseInt(request.getParameter("ci_paciente"));
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Pacientes paciente = new Pacientes();
    paciente.setCi_paciente(ci_paciente);

    PacientesControlador.buscarCi(paciente);
    if (paciente.getId_paciente() == 0) {
        tipo = "success";
        mensaje = "Datos de ci repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("ci_paciente", paciente.getCi_paciente());

    out.print(obj);
    out.flush();
%>
