<%@page import="controladores.ServiciosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Servicios"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_servicio = Integer.parseInt(request.getParameter("id_servicio"));
    String nombre_servicio = request.getParameter("nombre_servicio");
    String descripcion_servicio = request.getParameter("descripcion_servicio");
    int precio_servicio = Integer.parseInt(request.getParameter("precio_servicio"));
    String observacion_servicio = request.getParameter("observacion_servicio");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Servicios servicio = new Servicios();
    servicio.setId_servicio(id_servicio);
    servicio.setNombre_servicio(nombre_servicio);
    servicio.setDescripcion_servicio(descripcion_servicio);
    servicio.setPrecio_servicio(precio_servicio);
    servicio.setObservacion_servicio(observacion_servicio);

    if (ServiciosControlador.eliminar(servicio)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>