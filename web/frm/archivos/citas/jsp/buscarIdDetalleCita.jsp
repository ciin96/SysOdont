
<%@page import="modelos.Horarios"%>
<%@page import="modelos.Servicios"%>
<%@page import="modelos.Citas"%>
<%@page import="modelos.Pacientes"%>
<%@page import="modelos.DetalleCitas"%>
<%@page import="controladores.DetalleCitasControlador"%>
<%@page import="modelos.Doctores"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecita = Integer.parseInt(request.getParameter("id_detallecita"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
   
    DetalleCitas citadetalle = new DetalleCitas();
    citadetalle.setId_detallecita(id_detallecita);

     DetalleCitas detallecita = DetalleCitasControlador.buscarId(citadetalle);
    if (detallecita != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallecita = new DetalleCitas();
        detallecita.setId_detallecita(0);
     
       Pacientes paciente = new Pacientes();
       paciente.setId_paciente(0);
       paciente.setNombre_paciente("");
       detallecita.setPaciente(paciente);
       
       Citas cita = new Citas();
       cita.setId_cita(0);
       
       Servicios servicio = new Servicios();
       servicio.setId_servicio(0);
       servicio.setNombre_servicio("");
       
       Horarios horario = new Horarios();
       horario.setId_horario(0);
       horario.setHorain_horario("");
       horario.setHorafin_horario("");
    
    }


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
  
    obj.put("id_detallecita", String.valueOf(detallecita.getId_detallecita()));
   
    
    obj.put("id_cita", String.valueOf(detallecita.getCita().getId_cita()));
    
    obj.put("id_paciente", String.valueOf(detallecita.getPaciente().getId_paciente()));
    System.out.println(detallecita.getPaciente().getId_paciente());
    obj.put("nombre_paciente", detallecita.getPaciente().getNombre_paciente());
     System.out.println(detallecita.getPaciente().getNombre_paciente());
    obj.put("id_servicio", String.valueOf(detallecita.getServicio().getId_servicio()));
    obj.put("nombre_servicio", detallecita.getServicio().getNombre_servicio());
    
    obj.put("id_horario", String.valueOf(detallecita.getHorario().getId_horario()));
    obj.put("horain_horario",detallecita.getHorario().getHorain_horario());
    obj.put("horafin_horario",detallecita.getHorario().getHorafin_horario());
    

    out.print(obj);
    out.flush();
%>