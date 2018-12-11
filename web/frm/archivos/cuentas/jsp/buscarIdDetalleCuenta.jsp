
<%@page import="modelos.Servicios"%>
<%@page import="modelos.Cuentas"%>
<%@page import="modelos.Pacientes"%>
<%@page import="modelos.DetalleCuentas"%>
<%@page import="controladores.DetalleCuentasControlador"%>
<%@page import="modelos.Doctores"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecuenta = Integer.parseInt(request.getParameter("id_detallecuenta"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
   
    DetalleCuentas cuentadetalle = new DetalleCuentas();
    cuentadetalle.setId_detallecuenta(id_detallecuenta);

     DetalleCuentas detallecuenta = DetalleCuentasControlador.buscarId(cuentadetalle);
    if (detallecuenta != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallecuenta = new DetalleCuentas();
        detallecuenta.setId_detallecuenta(0);
        detallecuenta.setHora_detallecuenta("");
     
       Pacientes paciente = new Pacientes();
       paciente.setId_paciente(0);
       paciente.setNombre_paciente("");
       detallecuenta.setPaciente(paciente);
       
       Cuentas cuenta = new Cuentas();
       cuenta.setId_cuenta(0);
       
       Servicios servicio = new Servicios();
       servicio.setId_servicio(0);
       servicio.setNombre_servicio("");
    
    }


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
  
    obj.put("id_detallecuenta", String.valueOf(detallecuenta.getId_detallecuenta()));
    obj.put("hora_detallecuenta",detallecuenta.getHora_detallecuenta());
    
    obj.put("id_cuenta", String.valueOf(detallecuenta.getCuenta().getId_cuenta()));
    
    obj.put("id_paciente", String.valueOf(detallecuenta.getPaciente().getId_paciente()));
    System.out.println(detallecuenta.getPaciente().getId_paciente());
    obj.put("nombre_paciente", detallecuenta.getPaciente().getNombre_paciente());
     System.out.println(detallecuenta.getPaciente().getNombre_paciente());
    obj.put("id_servicio", String.valueOf(detallecuenta.getServicio().getId_servicio()));
    obj.put("nombre_servicio", detallecuenta.getServicio().getNombre_servicio());
    

    out.print(obj);
    out.flush();
%>