<%@page import="controladores.DoctoresControlador"%>
<%@page import="modelos.Doctores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int nroregistro_doctor = Integer.parseInt(request.getParameter("nroregistro_doctor"));
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Doctores doctor = new Doctores();
    doctor.setNroregistro_doctor(nroregistro_doctor);

    DoctoresControlador.buscarNroregistro(doctor);
    if (doctor.getId_doctor() == 0) {
        tipo = "success";
        mensaje = "Datos de nroregistro repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("nroregistro_doctor", doctor.getNroregistro_doctor());

    out.print(obj);
    out.flush();
%>
