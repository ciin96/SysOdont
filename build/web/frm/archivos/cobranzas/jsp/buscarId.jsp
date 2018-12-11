<%@page import="modelos.TipoPagos"%>
<%@page import="modelos.Servicios"%>
<%@page import="modelos.Facturas"%>
<%@page import="controladores.CobranzasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Cobranzas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cobranza = Integer.parseInt(request.getParameter("id_cobranza"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";

    Cobranzas cobranza = new Cobranzas();
    cobranza.setId_cobranza(id_cobranza);

    CobranzasControlador.buscarId(cobranza);
    if (cobranza.getId_cobranza() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        Facturas factura = new Facturas();
        Servicios servicio = new Servicios();
        TipoPagos tipopago = new TipoPagos();

        cobranza.setId_cobranza(0);
        cobranza.setMonto_cobranza(0);

        factura.setId_factura(0);
        
        servicio.setId_servicio(0);
        servicio.setNombre_servicio("");
        servicio.setPrecio_servicio(0);
        
        tipopago.setId_tipopago(0);
        tipopago.setNombre_tipopago("");

        cobranza.setFactura(factura);
        cobranza.setServicio(servicio);
        cobranza.setTipopago(tipopago);
    }
   

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_cobranza", cobranza.getId_cobranza());
    obj.put("monto_cobranza", cobranza.getMonto_cobranza());

    obj.put("id_factura", cobranza.getFactura().getId_factura());

    obj.put("id_servicio", cobranza.getServicio().getId_servicio());
    obj.put("nombre_servicio", cobranza.getServicio().getId_servicio());
    obj.put("precio_servicio", cobranza.getServicio().getPrecio_servicio());

    obj.put("it_tipopago", cobranza.getTipopago().getId_tipopago());
    obj.put("nombre_tipopago", cobranza.getTipopago().getNombre_tipopago());


    out.print(obj);
    out.flush();
%>