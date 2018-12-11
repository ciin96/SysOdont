
<%@page import="modelos.EstadoCitas"%>
<%@page import="modelos.Doctores"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.CitasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Citas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cita = Integer.parseInt(request.getParameter("id_cita"));
    int id_doctor = Integer.parseInt(request.getParameter("id_doctor"));
    //int id_horario = Integer.parseInt(request.getParameter("id_horario"));
    int id_estadocita = Integer.parseInt(request.getParameter("id_estadocita"));
    String nombre_cita = request.getParameter("nombre_cita");
    
    String sfecha_cita = request.getParameter("fecha_cita");
    java.sql.Date fecha_cita = Utiles.stringToSqlDate(sfecha_cita);
    
    Doctores doctor = new Doctores();
    doctor.setId_doctor(id_doctor);
    
    EstadoCitas estadocita = new EstadoCitas();
    estadocita.setId_estadocita(id_estadocita);
    
    Horarios horario = new Horarios();
    //horario.setId_horario(id_horario);


    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Citas cita = new Citas();
    cita.setId_cita(id_cita);
    cita.setNombre_cita(nombre_cita);
    cita.setFecha_cita(fecha_cita);
    
    cita.setDoctor(doctor);
    cita.setEstadocita(estadocita);
    //cita.setHorario(horario);
   

    if (CitasControlador.modificar(cita)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
