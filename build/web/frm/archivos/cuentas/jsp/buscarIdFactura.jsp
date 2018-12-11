
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
        
        factura.setId_factura(0);
        factura.setSubtotal_factura(0);
        factura.setDescuento_factura(0);
        factura.setFecha_factura(null);
        
        paciente.setId_paciente(0);
        paciente.setNombre_paciente("");
        
        factura.setPaciente(paciente);
    }
        
    String contenido_detalle = DetalleFacturasControlador.buscarIdFactura(id_factura);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_factura", factura.getId_factura());
    obj.put("subtotal_factura", factura.getSubtotal_factura());
    obj.put("descuento_factura", factura.getDescuento_factura());
    obj.put("fecha_factura", String.valueOf(factura.getFecha_factura()));
    
    obj.put("id_paciente", factura.getPaciente().getId_paciente());
    obj.put("nombre_paciente", factura.getPaciente().getNombre_paciente());
    
    
    obj.put("contenido_detalle", contenido_detalle);

    out.print(obj);
    out.flush();
%>