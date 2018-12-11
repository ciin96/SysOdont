
<%@page import="modelos.Servicios"%>
<%@page import="modelos.Facturas"%>
<%@page import="modelos.Pacientes"%>
<%@page import="modelos.DetalleFacturas"%>
<%@page import="controladores.DetalleFacturasControlador"%>
<%@page import="modelos.Doctores"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallefactura = Integer.parseInt(request.getParameter("id_detallefactura"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
   
    DetalleFacturas facturadetalle = new DetalleFacturas();
    facturadetalle.setId_detallefactura(id_detallefactura);

     DetalleFacturas detallefactura = DetalleFacturasControlador.buscarId(facturadetalle);
    if (detallefactura != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallefactura = new DetalleFacturas();
        detallefactura.setId_detallefactura(0);
        detallefactura.setCantidad_factura(0);
     
       
       Facturas factura = new Facturas();
       factura.setId_factura(0);
       
       Servicios servicio = new Servicios();
       servicio.setId_servicio(0);
       servicio.setNombre_servicio("");
       servicio.setPrecio_servicio(0);
    
    }


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
  
    obj.put("id_detallefactura", String.valueOf(detallefactura.getId_detallefactura()));
    obj.put("cantidad_factura",detallefactura.getCantidad_factura());
    
    obj.put("id_factura", String.valueOf(detallefactura.getFactura().getId_factura()));
    

    obj.put("id_servicio", String.valueOf(detallefactura.getServicio().getId_servicio()));
    System.out.println(detallefactura.getServicio().getId_servicio());
    obj.put("nombre_servicio", detallefactura.getServicio().getNombre_servicio());
    System.out.println(detallefactura.getServicio().getNombre_servicio());
    obj.put("precio_servicio", detallefactura.getServicio().getPrecio_servicio());
    

    out.print(obj);
    out.flush();
%>