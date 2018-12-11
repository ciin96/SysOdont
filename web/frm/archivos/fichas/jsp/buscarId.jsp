
<%@page import="java.util.Date"%>
<%@page import="modelos.Pacientes"%>
<%@page import="controladores.FichasControlador"%>
<%@page import="controladores.DetalleFichasControlador"%>
<%@page import="modelos.Doctores"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Fichas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ficha = Integer.parseInt(request.getParameter("id_ficha"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Fichas ficha = new Fichas();
    ficha.setId_ficha(id_ficha);

    FichasControlador.buscarId(ficha);
    if (ficha.getId_ficha() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else{
        Doctores doctor = new Doctores();
        Pacientes paciente = new Pacientes();
        
        
        //Date fecha = new Date();
        java.sql.Date hoy = new java.sql.Date (new java.util.Date().getTime());
        
        System.out.println("hoy"+hoy);
        ficha.setId_ficha(0);
        ficha.setNombre_ficha("");
        ficha.setFecha_ficha(hoy);
        
        doctor.setId_doctor(0);
        doctor.setNombre_doctor("");
        
        paciente.setId_paciente(0);
        paciente.setNombre_paciente("");
        
     
        
        ficha.setDoctor(doctor);
        ficha.setPaciente(paciente);
    }
    String contenido_detalle = DetalleFichasControlador.buscarIdFicha(id_ficha);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_ficha", ficha.getId_ficha());
    obj.put("nombre_ficha", ficha.getNombre_ficha());
    obj.put("fecha_ficha", String.valueOf(ficha.getFecha_ficha()));
    
    obj.put("id_doctor", ficha.getDoctor().getId_doctor());
    obj.put("nombre_doctor", ficha.getDoctor().getNombre_doctor());
    
    obj.put("id_paciente", ficha.getPaciente().getId_paciente());
    obj.put("nombre_paciente", ficha.getPaciente().getNombre_paciente());
    
    
    obj.put("contenido_detalle", contenido_detalle);

    out.print(obj);
    out.flush();
%>