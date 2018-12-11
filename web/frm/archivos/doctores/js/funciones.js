  
function agregarDoctor(){
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
            $("#id_doctor").focus();
            $("#id_doctor").select();
        },
        error: function (e){
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            $("#id_doctor").focus();
        }
    });
}

function buscarIdDoctor() {
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
            $("#id_doctor").val(json.id_doctor);
            $("#nombre_doctor").val(json.nombre_doctor);
            $("#apellido_doctor").val(json.apellido_doctor);
            $("#ci_doctor").val(json.ci_doctor);
            $("#nroregistro_doctor").val(json.nroregistro_doctor);
            $("#direccion_doctor").val(json.direccion_doctor);
            $("#telefono_doctor").val(json.telefono_doctor);
            $("#correo_doctor").val(json.correo_doctor);
            
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
               //siguienteCampo("#nombre_doctor", "#botonAgregar", true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               //siguienteCampo("#nombre_doctor", "#botonModificar", true);
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


function buscarNombreDoctor() {
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

function buscarNroregistro() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNroregistro.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {


            } else {
                alert("Datos de nroregistro repetidos");
                $("#nroregistro_doctor").val("");
                $("#nroregistro_doctor").focus();
                //siguienteCampo("#ci_doctor", "#botonModificar", true);
            }
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


function buscarCi() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCi.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {


            } else {
                alert("Datos de ci repetidos");
                $("#ci_doctor").val("");
                $("#ci_doctor").focus();
                //siguienteCampo("#nombre_doctor", "#botonModificar", true);
            }
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

function buscarCorreo() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCorreo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {


            } else {
                alert("Datos de correo repetidos");
                $("#correo_doctor").val("");
                $("#correo_doctor").focus();
                //siguienteCampo("#telefono_doctor", "#botonModificar", true);
            }
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


function modificarDoctor() {
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
                $("#id_doctor").focus();
                $("#id_doctor").select();
            },
            error: function (e) {
                $("#mensajes").html("No se pudo modificar los datos.");
            },
            complete: function (objeto, exito, error) {
                
            }
        });
    }
    
    function eliminarDoctor() {
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
                $("#id_doctor").focus();
                $("#id_doctor").select();
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
        if ($("#nombre_doctor").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Nombre no puede estar vacio.");
            $("#nombre_doctor").focus();
        }
        
        if ($("#apellido_doctor").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Apellido no puede estar vacio.");
            $("#apellido_doctor").focus();
        }
        
        if ($("#ci_doctor").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Ci no puede estar vacio.");
            $("#ci_doctor").focus();
        }
        
        if ($("#nroregistro_doctor").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Registro no puede estar vacio.");
            $("#nroregistro_doctor").focus();
        }
        
        if ($("#direccion_doctor").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Direccion no puede estar vacio.");
            $("#direccion_doctor").focus();
        }
        
        if ($("#telefono_doctor").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Teléfono no puede estar vacio.");
            $("#telefono_doctor").focus();
        }
        
         if ($("#correo_doctor").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Correo no puede estar vacio.");
            $("#correo_doctor").focus();
        }
        return valor;
    }
    
    function limpiarFormulario(){
        $("#id_doctor").val("0");
        $("#nombre_doctor").val("");
        $("#apellido_doctor").val("");
        $("#ci_doctor").val("");
        $("#nroregistro_doctor").val("");
        $("#direccion_doctor").val("");
        $("#telefono_doctor").val("");
        $("#correo_doctor").val("");
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




