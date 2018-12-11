<%@page import="controladores.DetalleFacturasControlador"%>
<%@page import="modelos.DetalleFacturas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallefactura = Integer.parseInt(request.getParameter("id_detallefactura"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    DetalleFacturas detallefactura = new DetalleFacturas();
    detallefactura.setId_detallefactura(id_detallefactura);

    if (DetalleFacturasControlador.eliminar(detallefactura)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>