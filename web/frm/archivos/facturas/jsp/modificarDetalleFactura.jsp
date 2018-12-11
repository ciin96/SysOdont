<%@page import="modelos.Servicios"%>
<%@page import="controladores.DetalleFacturasControlador"%>
<%@page import="modelos.DetalleFacturas"%>
<%@page import="modelos.Pacientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Facturas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallefactura = Integer.parseInt(request.getParameter("id_detallefactura"));
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
    int id_servicio = Integer.parseInt(request.getParameter("id_servicio"));
    int cantidad_factura = Integer.parseInt(request.getParameter("cantidad_factura"));
    
    
    Facturas factura = new Facturas();
    factura.setId_factura(id_factura);
    
    Servicios servicio = new Servicios();
    servicio.setId_servicio(id_servicio);
    
    String tipo ="error";
    String mensaje ="Datos no modificados.";
    
    DetalleFacturas detallefactura = new DetalleFacturas();
    detallefactura.setId_detallefactura(id_detallefactura);
    detallefactura.setCantidad_factura(cantidad_factura);
  
    detallefactura.setFactura(factura);
    detallefactura.setServicio(servicio);
    
   if (DetalleFacturasControlador.modificar(detallefactura)){
       tipo = "success";
       mensaje = "Datos modificados";
   }
   
  JSONObject obj = new JSONObject();
  obj.put("tipo", tipo);
  obj.put("mensaje", mensaje);
  out.print(obj);
  out.flush();
    
    %>
