<%@page import="controladores.DoctoresControlador"%>
<%@page import="modelos.Doctores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String correo_doctor = request.getParameter("correo_doctor");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Doctores doctor = new Doctores();
    doctor.setCorreo_doctor(correo_doctor);

    DoctoresControlador.buscarCorreo(doctor);
    if (doctor.getId_doctor() == 0) {
        tipo = "success";
        mensaje = "Datos de correo repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("correo_doctor", doctor.getCorreo_doctor());

    out.print(obj);
    out.flush();
%>
