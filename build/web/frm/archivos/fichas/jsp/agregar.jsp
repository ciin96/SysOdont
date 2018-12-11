
<%@page import="modelos.Pacientes"%>
<%@page import="modelos.Doctores"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.FichasControlador"%>
<%@page import="modelos.Fichas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ficha = Integer.parseInt(request.getParameter("id_ficha"));
    int id_doctor = Integer.parseInt(request.getParameter("id_doctor"));
    int id_paciente = Integer.parseInt(request.getParameter("id_paciente"));
    String nombre_ficha = request.getParameter("nombre_ficha");
    
    String sfecha_ficha = request.getParameter("fecha_ficha");
    java.sql.Date fecha_ficha = Utiles.stringToSqlDate(sfecha_ficha);
    
    Doctores doctor = new Doctores();
    doctor.setId_doctor(id_doctor);
    
    Pacientes paciente = new Pacientes();
    paciente.setId_paciente(id_paciente);
   
    
    String tipo ="error";
    String mensaje ="Datos no agregados.";
    
    Fichas ficha = new Fichas();
    ficha.setId_ficha(id_ficha);
    ficha.setNombre_ficha(nombre_ficha);
    ficha.setFecha_ficha(fecha_ficha);
    
    ficha.setDoctor(doctor);
    ficha.setPaciente(paciente);
    
   if (FichasControlador.agregar(ficha)){
       tipo = "success";
       mensaje = "Datos agregados";
   }
   
  JSONObject obj = new JSONObject();
  obj.put("tipo", tipo);
  obj.put("id_ficha", String.valueOf(ficha.getId_ficha()));
  obj.put("mensaje", mensaje);
  out.print(obj);
  out.flush();
    
    %>