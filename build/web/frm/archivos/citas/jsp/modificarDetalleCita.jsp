<%@page import="modelos.Horarios"%>
<%@page import="modelos.Servicios"%>
<%@page import="controladores.DetalleCitasControlador"%>
<%@page import="modelos.DetalleCitas"%>
<%@page import="modelos.Pacientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.CitasControlador"%>
<%@page import="modelos.Citas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecita = Integer.parseInt(request.getParameter("id_detallecita"));
    int id_cita = Integer.parseInt(request.getParameter("id_cita"));
    int id_servicio = Integer.parseInt(request.getParameter("id_servicio"));
    int id_paciente= Integer.parseInt(request.getParameter("id_paciente"));
    int id_horario= Integer.parseInt(request.getParameter("id_horario"));
  
    Pacientes paciente = new Pacientes();
    paciente.setId_paciente(id_paciente);
    
    Horarios horario = new Horarios();
    horario.setId_horario(id_horario);
    
    String tipo ="error";
    String mensaje ="Datos no agregados.";
 
    Citas cita = new Citas();
    cita.setId_cita(id_cita);
    
    Servicios servicio = new Servicios();
    servicio.setId_servicio(id_servicio);
    
    DetalleCitas detallecita = new DetalleCitas();
    detallecita.setId_detallecita(id_detallecita);
    
 
    detallecita.setCita(cita);
    detallecita.setServicio(servicio);
    detallecita.setPaciente(paciente);
    detallecita.setHorario(horario);
    
   if (DetalleCitasControlador.modificar(detallecita)){
       tipo = "success";
       mensaje = "Datos modificados";
   }
   
  JSONObject obj = new JSONObject();
  obj.put("tipo", tipo);
  obj.put("mensaje", mensaje);
  out.print(obj);
  out.flush();
    
    %>
