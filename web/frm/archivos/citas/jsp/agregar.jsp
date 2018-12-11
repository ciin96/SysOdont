
<%@page import="modelos.EstadoCitas"%>
<%@page import="modelos.Doctores"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.CitasControlador"%>
<%@page import="modelos.Citas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cita = Integer.parseInt(request.getParameter("id_cita"));
    int id_doctor = Integer.parseInt(request.getParameter("id_doctor"));
    int id_estadocita = 3;
    //int id_horario = Integer.parseInt(request.getParameter("id_horario"));
    String nombre_cita = request.getParameter("nombre_cita");
    
    String sfecha_cita = request.getParameter("fecha_cita");
    java.sql.Date fecha_cita = Utiles.stringToSqlDate(sfecha_cita);
    
    Doctores doctor = new Doctores();
    doctor.setId_doctor(id_doctor);
    
    EstadoCitas estadocita = new EstadoCitas();
    estadocita.setId_estadocita(id_estadocita);
    
    //Horarios horario =  new Horarios();
    //horario.setId_horario(id_horario);
    
    String tipo ="error";
    String mensaje ="Datos no agregados.";
    
    Citas cita = new Citas();
    cita.setId_cita(id_cita);
    cita.setNombre_cita(nombre_cita);
    cita.setFecha_cita(fecha_cita);
    
    cita.setDoctor(doctor);
    cita.setEstadocita(estadocita);
    //cita.setHorario(horario);
    
   if (CitasControlador.agregar(cita)){
       tipo = "success";
       mensaje = "Datos agregados";
   }
   
  JSONObject obj = new JSONObject();
  obj.put("tipo", tipo);
  obj.put("id_cita", String.valueOf(cita.getId_cita()));
  obj.put("mensaje", mensaje);
  out.print(obj);
  out.flush();
    
    %>