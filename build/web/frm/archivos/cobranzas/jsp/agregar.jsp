<%@page import="modelos.TipoPagos"%>
<%@page import="modelos.Servicios"%>
<%@page import="modelos.Facturas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.CobranzasControlador"%>
<%@page import="modelos.Cobranzas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cobranza = Integer.parseInt(request.getParameter("id_cobranza"));
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
    int id_servicio = Integer.parseInt(request.getParameter("id_servicio"));
    int id_tipopago = Integer.parseInt(request.getParameter("id_tipopago"));
    int monto_cobranza = Integer.parseInt(request.getParameter("monto_cobranza"));
    
    Facturas factura = new Facturas();
    factura.setId_factura(id_factura);
    
    Servicios servicio = new Servicios();
    servicio.setId_servicio(id_servicio);
    
    TipoPagos tipopago = new TipoPagos();
    tipopago.setId_tipopago(id_tipopago);
    
    String tipo ="error";
    String mensaje ="Datos no agregados.";
    
    Cobranzas cobranza = new Cobranzas();
    cobranza.setId_cobranza(id_cobranza);
    cobranza.setMonto_cobranza(monto_cobranza);
    
   if (CobranzasControlador.agregar(cobranza)){
       tipo = "success";
       mensaje = "Datos agregados";
   }
   
  JSONObject obj = new JSONObject();
  obj.put("tipo", tipo);
  obj.put("mensaje", mensaje);
  out.print(obj);
  out.flush();
    
    %>