<%@page import="controladores.PacientesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Pacientes"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_paciente = Integer.parseInt(request.getParameter("id_paciente"));
    String nombre_paciente = request.getParameter("nombre_paciente");
    String apellido_paciente = request.getParameter("apellido_paciente");
    int edad_paciente = Integer.parseInt(request.getParameter("edad_paciente"));
    int ci_paciente = Integer.parseInt(request.getParameter("ci_paciente"));
    String direccion_paciente = request.getParameter("direccion_paciente");
    int telefono_paciente = Integer.parseInt(request.getParameter("telefono_paciente"));
    String correo_paciente = request.getParameter("correo_paciente");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Pacientes paciente = new Pacientes();
    paciente.setId_paciente(id_paciente);
    paciente.setNombre_paciente(nombre_paciente);
    paciente.setApellido_paciente(apellido_paciente);
    paciente.setEdad_paciente(edad_paciente);
    paciente.setCi_paciente(ci_paciente);
    paciente.setDireccion_paciente(direccion_paciente);
    paciente.setTelefono_paciente(telefono_paciente);
    paciente.setCorreo_paciente(correo_paciente);

    if (PacientesControlador.eliminar(paciente)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>