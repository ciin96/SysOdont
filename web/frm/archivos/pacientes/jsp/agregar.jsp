<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.PacientesControlador"%>
<%@page import="modelos.Pacientes"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_paciente = Integer.parseInt(request.getParameter("id_paciente"));
    String nombre_paciente = request.getParameter("nombre_paciente");
    String apellido_paciente = request.getParameter("apellido_paciente");
    int ci_paciente = Integer.parseInt(request.getParameter("ci_paciente"));
    String direccion_paciente = request.getParameter("direccion_paciente");
    int telefono_paciente = Integer.parseInt(request.getParameter("telefono_paciente"));
    String correo_paciente = request.getParameter("correo_paciente");
    
    String sfechanac_paciente = request.getParameter("fechanac_paciente");
    java.sql.Date fechanac_paciente = Utiles.stringToSqlDate(sfechanac_paciente);
    
    String tipo ="error";
    String mensaje ="Datos no agregados.";
    
    Pacientes paciente = new Pacientes();
    paciente.setId_paciente(id_paciente);
    paciente.setNombre_paciente(nombre_paciente);
    paciente.setApellido_paciente(apellido_paciente);
    paciente.setFechanac_paciente(fechanac_paciente);
    paciente.setCi_paciente(ci_paciente);
    paciente.setDireccion_paciente(direccion_paciente);
    paciente.setTelefono_paciente(telefono_paciente);
    paciente.setCorreo_paciente(correo_paciente);
    
   if (PacientesControlador.agregar(paciente)){
       tipo = "success";
       mensaje = "Datos agregados";
   }
   
  JSONObject obj = new JSONObject();
  obj.put("tipo", tipo);
  obj.put("id_paciente", String.valueOf(paciente.getId_paciente()));
  obj.put("mensaje", mensaje);
  out.print(obj);
  out.flush();
    
    %>