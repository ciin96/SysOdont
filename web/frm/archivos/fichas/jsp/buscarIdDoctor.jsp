<%@page import="controladores.DoctoresControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Doctores"%>
<%@page import="java.sql.ResultSet"%>
<%
  int id_doctor = Integer.parseInt(request.getParameter("id_doctor"));  
  String tipo = "error";
  String mensaje = "Datos no encontrados";
  String nuevo = "true";
Doctores doctor = new Doctores();
doctor.setId_doctor(id_doctor);

DoctoresControlador.buscarId(doctor);
if(doctor.getId_doctor()!=0) {
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
    
    obj.put("id_doctor", doctor.getId_doctor());
    obj.put("nombre_doctor", doctor.getNombre_doctor());
    obj.put("apellido_doctor", doctor.getApellido_doctor());
    obj.put("ci_doctor", doctor.getCi_doctor());
    obj.put("nroregistro_doctor", doctor.getNroregistro_doctor());
    obj.put("direccion_doctor", doctor.getDireccion_doctor());
    obj.put("telefono_doctor", doctor.getTelefono_doctor());
    obj.put("correo_doctor", doctor.getCorreo_doctor());
    
    out.print(obj);
    out.flush();
%>