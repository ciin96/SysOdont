<%@page import="controladores.PacientesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page  import="java.sql.ResultSet"%>
<%
    String nombre_paciente = request.getParameter("bnombre");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    int id_doctor = Integer.parseInt(request.getParameter("id_doctor"));

    java.sql.Date hoy = new java.sql.Date(new java.util.Date().getTime());

    System.out.println("hoy" + hoy);

    String mensaje = "Busqueda exitosa";
    String contenido = PacientesControlador.buscarNombrecita(id_doctor, nombre_paciente, pagina, hoy);

    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);

    out.print(obj);
    out.flush();
%>