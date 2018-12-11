<%@page import="controladores.ServiciosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Servicios"%>
<%@page import="java.sql.ResultSet"%>
<%
  int id_servicio = Integer.parseInt(request.getParameter("id_servicio"));  
  String tipo = "error";
  String mensaje = "Datos no encontrados";
  String nuevo = "true";
Servicios servicio = new Servicios();
servicio.setId_servicio(id_servicio);

ServiciosControlador.buscarId(servicio);
if(servicio.getId_servicio()!=0) {
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "false";
}else{
    tipo = "success";
    mensaje = "Datos encontrados";
    nuevo = "true";
}

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_servicio", servicio.getId_servicio());
    obj.put("nombre_servicio", servicio.getNombre_servicio());
    obj.put("descripcion_servicio", servicio.getDescripcion_servicio());
    obj.put("precio_servicio", servicio.getPrecio_servicio());
    obj.put("observacion_servicio", servicio.getObservacion_servicio());
    
    
    out.print(obj);
    out.flush();
%>