
function agregarFicha(){
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario)
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
            $("#detalleficha").prop('hidden', false);
            $("#id_ficha").val(json.id_ficha);
            buscarIdFicha();
            //$("#id_ficha").focus();
            //$("#id_ficha").select();
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
function crearFacturas(){
    var datosFormulario = $("#formPrograma").serialize();
   alert(datosFormulario)
    $.ajax({
        type: 'POST',
        url: "jsp/facturar.jsp",
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#botonAgregar").prop('disabled', true);
            $("#detalleficha").prop('hidden', false);
            $("#id_ficha").val(json.id_ficha);
            buscarIdFicha();
            //$("#id_ficha").focus();
            //$("#id_ficha").select();
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
function buscarIdFicha() {
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
            $("#id_ficha").val(json.id_ficha);
            $("#nombre_ficha").val(json.nombre_ficha);
            $("#fecha_ficha").val(json.fecha_ficha);
            
            $("#id_doctor").val(json.id_doctor);
            $("#nombre_doctor").val(json.nombre_doctor);
            
            $("#id_paciente").val(json.id_paciente);
            $("#nombre_paciente").val(json.nombre_paciente);
            
            
            
            $("#contenidoDetalle").html(json.contenido_detalle);
            
                 if (json.nombre_ficha === "PENDIENTE") {
               $("#botonFacturar").prop('disabled', false);
           
           } else {
                $("#botonFacturar").prop('disabled', true);
           }
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#nombre_ficha", "#botonAgregar", true);
               $("#detalle").prop('hidden', true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', false);
               siguienteCampo("#nombre_ficha", "#botonModificar", true);
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


function buscarNombreFicha() {
    var datosFormulario = $("#formBuscar").serialize();
    //alert(datosFormulario);
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombre.jsp',
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
              $("#id_ficha").val(id);
              $("#nombre_ficha").focus();
              buscarIdFicha();
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

function modificarFicha() {
        var datosFormulario = $("#formPrograma").serialize();
        alert(datosFormulario);
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
                $("#id_ficha").focus();
                $("#id_ficha").select();
                buscarIdFicha();
            },
            error: function (e) {
                $("#mensajes").html("No se pudo modificar los datos.");
            },
            complete: function (objeto, exito, error) {
                
            }
        });
    }
    
    function eliminarFicha() {
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
                eliminarDetalleFicha();
                $("#id_ficha").focus();
                $("#id_ficha").select();
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
        $("#id_ficha").val("0");
        $("#nombre_ficha").val("");
        $("#fecha_ficha").val("dd//mm/aaa");
    }
 

    
    /*DOCTORES*/
  function buscarIdDoctor() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdDoctor.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_doctor").val(json.id_doctor);
            $("#nombre_doctor").val(json.nombre_doctor);
            
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


function buscarNombreDoctor() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreDoctor.jsp',
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
              $("#id_doctor").val(id);
              $("#nombre_doctor").focus();
              buscarIdDoctor();
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
    var id_doctor = $("#id_doctor").val();
    datosFormulario += "&id_doctor=" + id_doctor;
    
    alert(datosFormulario);
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
    $("#id_detalleficha").val("0");
    
   
    $("#id_servicio").val("0");
    $("#nombre_servicio").val("");
    $("#estado_detalleficha").val("");
    
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#estado_detalleficha", "#botonAgregarLinea", true);
}

function editarLinea(id) {
    $("#id_detalleficha").val(id);
   
   
    $("#id_servicio").val("0");
    $("#nombre_servicio").val("");
    
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    
    $("#id_servicio").focus();
    $("#id_servicio").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdDetalleFicha();
    siguienteCampo("#estado_detalleficha", "#botonModificarLinea", true);
}

//DETALLESSS
function agregarDetalleFicha() {
    var datosFormulario = $("#formLinea").serialize();
    var id_ficha = $("#id_ficha").val();
    datosFormulario += "&id_ficha=" + id_ficha;
    alert (datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarDetalleFicha.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdFicha();
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
function buscarIdDetalleFicha() {
    var datosFormulario = $("#formLinea").serialize();
    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdDetalleFicha.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#hora_detalleficha").val(json.hora_detalleficha);
            $("#id_servicio").val(json.id_servicio);
            $("#nombre_servicio").val(json.nombre_servicio);
           
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

function modificarDetalleFicha() {
    var datosFormulario = $("#formLinea").serialize();
    var id_ficha = $("#id_ficha").val();
    datosFormulario += "&id_ficha=" + id_ficha;
    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarDetalleFicha.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdFicha();
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

function eliminarDetalleFicha() {
    var datosFormulario = $("#formLinea").serialize();
    var id_ficha = $("#id_ficha").val();
    datosFormulario += "&id_ficha=" + id_ficha;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarDetalleFicha.jsp',
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
            buscarIdFicha();

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


