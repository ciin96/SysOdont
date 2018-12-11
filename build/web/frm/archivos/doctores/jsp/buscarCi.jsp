<%@page import="controladores.DoctoresControlador"%>
<%@page import="modelos.Doctores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int ci_doctor = Integer.parseInt(request.getParameter("ci_doctor"));
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Doctores doctor = new Doctores();
    doctor.setCi_doctor(ci_doctor);

    DoctoresControlador.buscarCi(doctor);
    if (doctor.getId_doctor() == 0) {
        tipo = "success";
        mensaje = "Datos de ci repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("ci_doctor", doctor.getCi_doctor());

    out.print(obj);
    out.flush();
%>
