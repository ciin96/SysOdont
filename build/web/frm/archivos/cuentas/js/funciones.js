
function agregarCuenta(){
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
            $("#detallecuenta").prop('hidden', false);
            $("#id_cuenta").val(json.id_cuenta);
            buscarIdCuenta();
            //$("#id_cuenta").focus();
            //$("#id_cuenta").select();
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

function buscarIdCuenta() {
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
            $("#id_cuenta").val(json.id_cuenta);
            $("#monto_total").val(json.monto_total);
            $("#estado_cuenta").val(json.estado_cuenta);
            $("#total_cuota").val(json.total_cuota);
            
            $("#id_factura").val(json.id_factura);
            
            
            $("#contenidoDetalle").html(json.contenido_detalle);
            
          
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#nombre_cuenta", "#botonAgregar", true);
               $("#detalle").prop('hidden', true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', false);
               siguienteCampo("#nombre_cuenta", "#botonModificar", true);
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


function buscarEstadoCuenta() {
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
              $("#id_cuenta").val(id);
              $("#nombre_cuenta").focus();
              buscarIdCuenta();
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

function modificarCuenta() {
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
                $("#id_cuenta").focus();
                $("#id_cuenta").select();
                buscarIdCuenta();
            },
            error: function (e) {
                $("#mensajes").html("No se pudo modificar los datos.");
            },
            complete: function (objeto, exito, error) {
                
            }
        });
    }
    
    function eliminarCuenta() {
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
                eliminarDetalleCuenta();
                $("#id_cuenta").focus();
                $("#id_cuenta").select();
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
        $("#id_cuenta").val("0");
        $("#monto_total").val("");
        $("#estado_cuenta").val("");
        $("#total_cuota").val("");
        
    }
 

//FACTURAS
function buscarIdFactura() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdFactura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_factura").val(json.id_factura);
            $("#subtotal_factura").val(json.subtotal_factura);
            $("#descuento_factura").val(json.descuento_factura);
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


function buscarNombreFactura() {
    var datosFormulario = $("#formBuscar").serialize();
    alert(datosFormulario);
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreFactura.jsp',
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
              $("#nombre_factura").focus();
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

    
function agregarLinea() {
    $("#id_detallecuenta").val("0");
    
   
    $("#id_cuenta").val("0");
    $("#monto_cuota").val("0");
    $("#nro_cuota").val("0");
    $("#estado_cuota").val("0");
    
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#estado_detallecuenta", "#botonAgregarLinea", true);
}

function editarLinea(id) {
    $("#id_detallecuenta").val(id);
   
    $("#nro_cuota").val("0");
    $("#monto_cuota").val("0");
    $("#estado_cuota").val("");
    
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    
    $("#id_cuenta").focus();
    $("#id_cuenta").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdDetalleCuenta();
    siguienteCampo("#estado_detallecuenta", "#botonModificarLinea", true);
}

//DETALLESSS
function agregarDetalleCuenta() {
    var datosFormulario = $("#formLinea").serialize();
    var id_cuenta = $("#id_cuenta").val();
    datosFormulario += "&id_cuenta=" + id_cuenta;
    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarDetalleCuenta.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdCuenta();
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
function buscarIdDetalleCuenta() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdDetalleCuenta.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_cuenta").val(json.id_cuenta);
            $("#nro_cuota").val(json.nro_cuota);
            $("#monto_cuota").val(json.monto_cuota);
            $("#estado_cuota").val(json.estado_cuota);
           
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

function modificarDetalleCuenta() {
    var datosFormulario = $("#formLinea").serialize();
    var id_cuenta = $("#id_cuenta").val();
    datosFormulario += "&id_cuenta=" + id_cuenta;
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarDetalleCuenta.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdCuenta();
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

function eliminarDetalleCuenta() {
    var datosFormulario = $("#formLinea").serialize();
    var id_cuenta = $("#id_cuenta").val();
    datosFormulario += "&id_cuenta=" + id_cuenta;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarDetalleCuenta.jsp',
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
            buscarIdCuenta();

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


