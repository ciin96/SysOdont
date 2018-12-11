<%@page import="controladores.DoctoresControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Doctores"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_doctor = Integer.parseInt(request.getParameter("id_doctor"));
    String nombre_doctor = request.getParameter("nombre_doctor");
    String apellido_doctor = request.getParameter("apellido_doctor");
    int ci_doctor = Integer.parseInt(request.getParameter("ci_doctor"));
    int nroregistro_doctor = Integer.parseInt(request.getParameter("nroregistro_doctor"));
    String direccion_doctor = request.getParameter("direccion_doctor");
    int telefono_doctor = Integer.parseInt(request.getParameter("telefono_doctor"));
    String correo_doctor = request.getParameter("correo_doctor");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Doctores doctor = new Doctores();
    doctor.setId_doctor(id_doctor);
    doctor.setNombre_doctor(nombre_doctor);
    doctor.setApellido_doctor(apellido_doctor);
    doctor.setCi_doctor(ci_doctor);
    doctor.setNroregistro_doctor(nroregistro_doctor);
    doctor.setDireccion_doctor(direccion_doctor);
    doctor.setTelefono_doctor(telefono_doctor);
    doctor.setCorreo_doctor(correo_doctor);

    if (DoctoresControlador.modificar(doctor)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
