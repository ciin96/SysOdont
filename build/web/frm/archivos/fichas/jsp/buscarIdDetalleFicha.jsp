
<%@page import="modelos.Servicios"%>
<%@page import="modelos.Fichas"%>
<%@page import="modelos.Pacientes"%>
<%@page import="modelos.DetalleFichas"%>
<%@page import="controladores.DetalleFichasControlador"%>
<%@page import="modelos.Doctores"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleficha = Integer.parseInt(request.getParameter("id_detalleficha"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
   
    DetalleFichas fichadetalle = new DetalleFichas();
    fichadetalle.setId_detalleficha(id_detalleficha);

     DetalleFichas detalleficha = DetalleFichasControlador.buscarId(fichadetalle);
    if (detalleficha != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detalleficha = new DetalleFichas();
        detalleficha.setId_detalleficha(0);
        detalleficha.setEstado_detalleficha("");
     
       
       Fichas ficha = new Fichas();
       ficha.setId_ficha(0);
       
       Servicios servicio = new Servicios();
       servicio.setId_servicio(0);
       servicio.setNombre_servicio("");
    
    }


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
  
    obj.put("id_detalleficha", String.valueOf(detalleficha.getId_detalleficha()));
    obj.put("hora_detalleficha",detalleficha.getEstado_detalleficha());
    
    obj.put("id_ficha", String.valueOf(detalleficha.getFicha().getId_ficha()));
    
    
    obj.put("id_servicio", String.valueOf(detalleficha.getServicio().getId_servicio()));
    System.out.println(detalleficha.getServicio().getId_servicio());
    obj.put("nombre_servicio", detalleficha.getServicio().getNombre_servicio());
    System.out.println(detalleficha.getServicio().getNombre_servicio());
    

    out.print(obj);
    out.flush();
%>