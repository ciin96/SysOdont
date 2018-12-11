<%@page import="modelos.Horarios"%>
<%@page import="modelos.Servicios"%>
<%@page import="controladores.DetalleCitasControlador"%>
<%@page import="modelos.DetalleCitas"%>
<%@page import="modelos.Pacientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Citas"%>
<%@page import="java.sql.ResultSet"%>
<%
    //int id_detallecita = Integer.parseInt(request.getParameter("id_detallecita"));
    int id_paciente = Integer.parseInt(request.getParameter("id_paciente"));
    int id_cita = Integer.parseInt(request.getParameter("id_cita"));
    int id_servicio = Integer.parseInt(request.getParameter("id_servicio"));
    int id_horario = Integer.parseInt(request.getParameter("id_horario"));
    
    Pacientes paciente = new Pacientes();
    paciente.setId_paciente(id_paciente);
    
    Citas cita = new Citas();
    cita.setId_cita(id_cita);
    
    Servicios servicio = new Servicios();
    servicio.setId_servicio(id_servicio);
    
    Horarios horario = new Horarios();
    horario.setId_horario(id_horario);
    
    String tipo ="error";
    String mensaje ="Datos no agregados.";
    
    DetalleCitas detallecita = new DetalleCitas();
    
    detallecita.setPaciente(paciente);
    detallecita.setCita(cita);
    detallecita.setServicio(servicio);
    detallecita.setHorario(horario);
    
   if (DetalleCitasControlador.agregar(detallecita)){
       tipo = "success";
       mensaje = "Datos agregados";
   }
   
  JSONObject obj = new JSONObject();
  obj.put("tipo", tipo);
  obj.put("mensaje", mensaje);
  out.print(obj);
  out.flush();
    
    %>