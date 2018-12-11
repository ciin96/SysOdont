
<%@page import="modelos.Usuarios"%>
<%@page import="modelos.Pacientes"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.FacturasControlador"%>
<%@page import="modelos.Facturas"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
    int id_paciente = Integer.parseInt(request.getParameter("id_paciente"));
    //int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
    int cantidadc_factura = Integer.parseInt(request.getParameter("cantidadc_factura"));
    int numero_factura = Integer.parseInt(request.getParameter("numero_factura"));
    
    String estado_factura = request.getParameter("estado_factura");
    
    String sfecha_factura = request.getParameter("fecha_factura");
    java.sql.Date fecha_factura = Utiles.stringToSqlDate(sfecha_factura);
    
    Pacientes paciente = new Pacientes();
    paciente.setId_paciente(id_paciente);
    
    //Usuarios usuario = new Usuarios();
    //usuario.setId_usuario(id_usuario);
    
    String tipo ="error";
    String mensaje ="Datos no modificados.";
    
    Facturas factura = new Facturas();
    factura.setId_factura(id_factura);
    factura.setCantidadc_factura(cantidadc_factura);
    factura.setNumero_factura(numero_factura);
    factura.setEstado_factura(estado_factura);
    factura.setFecha_factura(fecha_factura);
    
    factura.setPaciente(paciente);
    //factura.setUsuario(usuario);
    
    
   if (FacturasControlador.modificar(factura)){
       tipo = "success";
       mensaje = "Datos modificados";
   }
   
  JSONObject obj = new JSONObject();
  obj.put("tipo", tipo);
  obj.put("mensaje", mensaje);
  out.print(obj);
  out.flush();
    
    %>