
<%@page import="modelos.Usuarios"%>
<%@page import="modelos.Pacientes"%>
<%@page import="controladores.FacturasControlador"%>
<%@page import="controladores.DetalleFacturasControlador"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Facturas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Facturas factura = new Facturas();
    factura.setId_factura(id_factura);

    FacturasControlador.buscarId(factura);
    if (factura.getId_factura() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else{
        Pacientes paciente = new Pacientes();
        //Usuarios usuario = new Usuarios();
        
        factura.setId_factura(0);
        factura.setCantidadc_factura(0);
        factura.setNumero_factura(0);
        factura.setEstado_factura("");
        factura.setFecha_factura(null);
        
        paciente.setId_paciente(0);
        paciente.setNombre_paciente("");
        
        //usuario.setId_usuario(0);
        //usuario.setNombre_usuario("");
        
        factura.setPaciente(paciente);
        //factura.setUsuario(usuario);
    }
    String contenido_detalle = DetalleFacturasControlador.buscarIdFactura(id_factura);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_factura", factura.getId_factura());
    obj.put("cantidadc_factura", factura.getCantidadc_factura());
    obj.put("numero_factura", factura.getNumero_factura());
    obj.put("estado_factura", factura.getEstado_factura());
    obj.put("fecha_factura", String.valueOf(factura.getFecha_factura()));
    
    obj.put("id_paciente", factura.getPaciente().getId_paciente());
    obj.put("nombre_paciente", factura.getPaciente().getNombre_paciente());
    
    //obj.put("id_usuario", factura.getUsuario().getId_usuario());
    //obj.put("nombre_usuario", factura.getUsuario().getNombre_usuario());
    
    
    
    obj.put("contenido_detalle", contenido_detalle);

    out.print(obj);
    out.flush();
%>