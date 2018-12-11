<%@page import="controladores.PacientesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Pacientes"%>
<%@page import="java.sql.ResultSet"%>
<%
  int id_paciente = Integer.parseInt(request.getParameter("id_paciente"));  
  String tipo = "error";
  String mensaje = "Datos no encontrados";
  String nuevo = "true";
Pacientes paciente = new Pacientes();
paciente.setId_paciente(id_paciente);

PacientesControlador.buscarId(paciente);
if(paciente.getId_paciente()!=0) {
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "false";
}else{
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "true";
}

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_paciente", paciente.getId_paciente());
    obj.put("nombre_paciente", paciente.getNombre_paciente());
    obj.put("apellido_paciente", paciente.getApellido_paciente());
    obj.put("fechanac_paciente", String.valueOf(paciente.getFechanac_paciente()));
    obj.put("ci_paciente", paciente.getCi_paciente());
    obj.put("direccion_paciente", paciente.getDireccion_paciente());
    obj.put("telefono_paciente", paciente.getTelefono_paciente());
    obj.put("correo_paciente", paciente.getCorreo_paciente());
    
    out.print(obj);
    out.flush();
%>