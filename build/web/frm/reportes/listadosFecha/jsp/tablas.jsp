<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="utiles.Conexion"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/pdf"%>
<%
    String dirPath="/rpt";
    String realPath=this.getServletContext().getRealPath(dirPath);
    String listado=request.getParameter("l");
    /*int desde=Integer.parseInt(request.getParameter("d"));
    int hasta=Integer.parseInt(request.getParameter("h"));*/
    
    String jasperReport=listado+".jasper";
    JasperPrint print=null;
    Connection conn=null;
    
    Date desde = Date.valueOf(request.getParameter("d").toString());
    Date hasta = Date.valueOf(request.getParameter("h").toString());

    
    try{
        Conexion.conectar();
        conn=Conexion.getConn();
        Map parameters=new HashMap();
        parameters.put("DESDE",desde);
        parameters.put("HASTA",hasta);
        
        
        print =JasperFillManager.fillReport(realPath+"//"+jasperReport, parameters,conn);
        response.setContentType("application/pdf");
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }catch(Exception ex){
        ex.printStackTrace();
        System.out.println(ex.getMessage());
    }
    finally{
        if(conn!=null){
            conn.close();
        }
    }
%>

