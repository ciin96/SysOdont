
function agregarFactura(){
    var datosFormulario = $("#formPrograma").serialize();
    alert(datosFormulario)
    $.ajax({
        type: 'POST',
        url: "jsp/agregar.jsp",
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#botonAgregar").prop('disabled', true);
            $("#detallefactura").prop('hidden', false);
            $("#id_factura").val(json.id_factura);
            buscarIdFactura();
            //$("#id_factura").focus();
            //$("#id_factura").select();
        },
        error: function (e){
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success"){
        }
    }
    });
}

function buscarIdFactura() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_factura").val(json.id_factura);
            $("#cantidadc_factura").val(json.cantidadc_factura);
            $("#numero_factura").val(json.numero_factura );
            $("#estado_factura").val(json.estado_factura);
            $("#fecha_factura").val(json.fecha_factura);
            
            $("#id_paciente").val(json.id_paciente);
            $("#nombre_paciente").val(json.nombre_paciente);
            
            
            
            $("#contenidoDetalle").html(json.contenido_detalle);
            
          
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#nombre_factura", "#botonAgregar", true);
               $("#detalle").prop('hidden', true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', false);
               siguienteCampo("#nombre_factura", "#botonModificar", true);
               $("#detalle").prop('hidden', false);
           }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo traer los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}


function buscarEstadoFactura() {
    var datosFormulario = $("#formBuscar").serialize();
    //alert(datosFormulario);
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarEstado.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_factura").val(id);
              $("#estado_factura").focus();
              buscarIdFactura();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}

function modificarFactura() {
        var datosFormulario = $("#formPrograma").serialize();
        
        $.ajax({
            type: 'POST',
            url: "jsp/modificar.jsp",
            data: datosFormulario,
            dataType: 'json',
            beforeSend: function (objeto) {
                $("#mensajes").html("Enviando datos al Servidor ...");
            },
            success: function (json) {
                $("#mensajes").html(json.mensaje);
                limpiarFormulario();
                $("#id_factura").focus();
                $("#id_factura").select();
                buscarIdFactura();
            },
            error: function (e) {
                $("#mensajes").html("No se pudo modificar los datos.");
            },
            complete: function (objeto, exito, error) {
                
            }
        });
    }
    
    function eliminarFactura() {
        var datosFormulario = $("#formPrograma").serialize();
        $.ajax ({
            type: 'POST',
            url: "jsp/eliminar.jsp",
            data: datosFormulario,
            dataType: 'json',
            beforeSend: function (objeto) {
                $("#mensajes").html("Enviando datos al Servidor ...");
            },
            success: function (json) {
                $("#mensajes").html(json.mensaje);
                limpiarFormulario();
                eliminarDetalleFactura();
                $("#id_factura").focus();
                $("#id_factura").select();
            },
            error: function (e) {
                $("#mensaje").html("No se pudo modificar los datos.");
            },
            complete: function (objeto, exito, error) {
                if (exito === "success") {
                }
            }
        });
    }
    
    
    
    function limpiarFormulario(){
        $("#id_factura").val("0");
        $("#nombre_factura").val("");
        $("#fecha_factura").val("dd//mm/aaa");
        $("#hora_factura").val("0").val("");
    }
 
/**USUARIOS
function buscarIdUsuario() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdUsuario.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_usuario").val(json.id_usuario);
            $("#nombre_usuario").val(json.nombre_usuario);
            $("#login_usuario").val(json.login_usuario);
            $("#password_usuario").val(json.password_usuario);
            
            $("#id_rol").val(json.id_rol);
            $("#nombre_rol").val(json.nombre_rol);
            
            
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
               //siguienteCampo("#nombre_usuario", "#botonAgregar", true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               //siguienteCampo("#nombre_usuario", "#botonModificar", true);
           }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}


function buscarNombreUsuario() {
    var datosFormulario = $("#formBuscar").serialize();
  
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreUsuario.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_usuario").val(id);
              $("#nombre_usuario").focus();
              buscarIdUsuario();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}*/
    
    
//PACIENTES
function buscarIdPaciente() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPaciente.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_paciente").val(json.id_paciente);
            $("#nombre_paciente").val(json.nombre_paciente);
          
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}


function buscarNombrePaciente() {
    var datosFormulario = $("#formBuscar").serialize();
  
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombrePaciente.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_paciente").val(id);
              $("#nombre_paciente").focus();
              buscarIdPaciente();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}


//SERVICIOS
function buscarIdServicio() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdServicio.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_servicio").val(json.id_servicio);
            $("#nombre_servicio").val(json.nombre_servicio);
            $("#descripcion_servicio").val(json.descripcion_servicio);
            $("#precio_servicio").val(json.precio_servicio);
            $("#observacion_servicio").val(json.observacion_servicio);
            
       
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}


function buscarNombreServicio() {
    var datosFormulario = $("#formBuscar").serialize();
  
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreServicio.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_servicio").val(id);
              $("#nombre_servicio").focus();
              buscarIdServicio();
              $("#buscar").fadeOut("slow");
              $("#panelLinea").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}

function agregarLinea() {
    $("#id_detallefactura").val("0");
    
    
    $("#cantidad_factura").val("");
    $("#id_servicio").val("0");
    $("#nombre_servicio").val("");
    $("#precio_servicio").val("");
    
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#cantidad_factura", "#botonAgregarLinea", true);
}

function editarLinea(id) {
    $("#id_detallefactura").val(id);
   
    $("#cantidad_factura").val("");
    $("#id_servicio").val("0");
    $("#nombre_servicio").val("");
    $("#precio_servicio").val("");
    
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    
    $("#id_servicio").focus();
    $("#id_servicio").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdDetalleFactura();
    siguienteCampo("#cantidad_factura", "#botonModificarLinea", true);
}

//DETALLESSS
function agregarDetalleFactura() {
    var datosFormulario = $("#formLinea").serialize();
    var id_factura = $("#id_factura").val();
    datosFormulario += "&id_factura=" + id_factura;
    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarDetalleFactura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdFactura();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

//BUSCARDETALLE
function buscarIdDetalleFactura() {
    var datosFormulario = $("#formLinea").serialize();
    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdDetalleFactura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#cantidad_factura").val(json.cantidad_factura);
            $("#id_servicio").val(json.id_servicio);
            $("#nombre_servicio").val(json.nombre_servicio);
            $("#precio_servicio").val(json.precio_servicio);
           
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function modificarDetalleFactura() {
    var datosFormulario = $("#formLinea").serialize();
    var id_factura = $("#id_factura").val();
    datosFormulario += "&id_factura=" + id_factura;
    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarDetalleFactura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdFactura();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function eliminarDetalleFactura() {
    var datosFormulario = $("#formLinea").serialize();
    var id_factura = $("#id_factura").val();
    datosFormulario += "&id_factura=" + id_factura;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarDetalleFactura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json)
        {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdFactura();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}


