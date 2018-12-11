
function agregarServicio(){
    var datosFormulario = $("#formPrograma").serialize();
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
            $("#id_servicio").focus();
            $("#id_servicio").select();
        },
        error: function (e){
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            $("#id_servicio").focus();
        }
    });
}

function buscarIdServicio() {
    var datosFormulario = $("#formPrograma").serialize();
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
            $("#id_servicio").val(json.id_servicio);
            $("#nombre_servicio").val(json.nombre_servicio);
            $("#descripcion_servicio").val(json.descripcion_servicio);
            $("#precio_servicio").val(json.precio_servicio);
            $("#observacion_servicio").val(json.observacion_servicio);
            
            
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
               //siguienteCampo("#nombre_servicio", "#botonAgregar", true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               //siguienteCampo("#nombre_servicio", "#botonModificar", true);
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


function buscarNombreServicio() {
    var datosFormulario = $("#formBuscar").serialize();
  
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
              $("#id_servicio").val(id);
              $("#nombre_servicio").focus();
              buscarIdServicio();
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

function modificarServicio() {
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
                $("#id_servicio").focus();
                $("#id_servicio").select();
            },
            error: function (e) {
                $("#mensajes").html("No se pudo modificar los datos.");
            },
            complete: function (objeto, exito, error) {
                
            }
        });
    }
    
    function eliminarServicio() {
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
                $("#id_servicio").focus();
                $("#id_servicio").select();
            },
            error: function (json) {
                $("#mensaje").html("No se pudo modificar los datos.");
            },
            complete: function (objeto, exito, error) {
                if (exito === "success") {
                }
            }
        });
    }
    
    function validarFormulario() {
        var valor = true;
        if ($("#nombre_servicio").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Nombre no puede estar vacio.");
            $("#nombre_servicio").focus();
        }
        
        if ($("#descripcion_servicio").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Descripcion no puede estar vacio.");
            $("#descripcion_servicio").focus();
        }
        
        if ($("#precio_servicio").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Precio no puede estar vacio.");
            $("#precio_servicio").focus();
        }
        
         if ($("#observacion_servicio").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Observacion no puede estar vacio.");
            $("#observacion_servicio").focus();
        }
       
        return valor;
    }
    
    function limpiarFormulario(){
        $("#id_servicio").val("0");
        $("#nombre_servicio").val("");
        $("#descripcion_servicio").val("");
        $("#precio_servicio").val("0");
        $("#observacion_servicio").val("");
    }
    
     function soloLetras(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";

    tecla_especial = false;
    for (var i in especiales) {
        if (key === especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) === -1 && !tecla_especial) {
        alert("ingrese solo letras");
        return false;
    }
}

function SoloNumeros(evt) {
    if (window.event) {//asignamos el valor de la tecla a keynum
        keynum = evt.keyCode; //IE
    } else {
        keynum = evt.which; //FF
    }
    //comprobamos si se encuentra en el rango numérico y que teclas no recibirá.
    if ((keynum > 47 && keynum < 58) || keynum === 8 || keynum === 13 || keynum === 6) {
        return true;
    } else {
        alert("Solo ingrese numeros");
        return false;
    }
}