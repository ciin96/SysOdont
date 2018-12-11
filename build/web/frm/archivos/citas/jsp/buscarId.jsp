
<%@page import="controladores.CitasControlador"%>
<%@page import="controladores.DetalleCitasControlador"%>
<%@page import="modelos.EstadoCitas"%>
<%@page import="modelos.Doctores"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Citas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cita = Integer.parseInt(request.getParameter("id_cita"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Citas cita = new Citas();
    cita.setId_cita(id_cita);

    CitasControlador.buscarId(cita);
    if (cita.getId_cita() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else{
        Doctores doctor = new Doctores();
        EstadoCitas estadocita = new EstadoCitas();
        //Horarios horario = new Horarios();
        
        cita.setId_cita(0);
        cita.setNombre_cita("");
        cita.setFecha_cita(null);
        
        doctor.setId_doctor(0);
        doctor.setNombre_doctor("");
        
        estadocita.setId_estadocita(0);
        estadocita.setNombre_estadocita("");
        
        //horario.setId_horario(0);
        //horario.setHorain_horario("");
        //horario.setHorafin_horario("");
        
        cita.setDoctor(doctor);
        cita.setEstadocita(estadocita);
        //cita.setHorario(horario);
    }
    String contenido_detalle = DetalleCitasControlador.buscarIdCita(id_cita);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_cita", cita.getId_cita());
    obj.put("nombre_cita", cita.getNombre_cita());
    obj.put("fecha_cita", String.valueOf(cita.getFecha_cita()));
    
    obj.put("id_doctor", cita.getDoctor().getId_doctor());
    obj.put("nombre_doctor", cita.getDoctor().getNombre_doctor());
    
    obj.put("id_estadocita", cita.getEstadocita().getId_estadocita());
    obj.put("nombre_estadocita", cita.getEstadocita().getNombre_estadocita());
    
    //obj.put("id_horario", cita.getHorario().getId_horario());
    //obj.put("horain_horario", cita.getHorario().getHorain_horario());
    //obj.put("horafin_horario", cita.getHorario().getHorafin_horario());
    
    obj.put("contenido_detalle", contenido_detalle);

    out.print(obj);
    out.flush();
%>