
function agregarPaciente(){
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
            $("#id_paciente").focus();
            $("#id_paciente").select();
        },
        error: function (e){
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            $("#id_paciente").focus();
        }
    });
}

function buscarIdPaciente() {
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
            $("#id_paciente").val(json.id_paciente);
            $("#nombre_paciente").val(json.nombre_paciente);
            $("#apellido_paciente").val(json.apellido_paciente);
            $("#fechanac_paciente").val(json.fechanac_paciente);
            $("#ci_paciente").val(json.ci_paciente);
            $("#direccion_paciente").val(json.direccion_paciente);
            $("#telefono_paciente").val(json.telefono_paciente);
            $("#correo_paciente").val(json.correo_paciente);
            
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
               //siguienteCampo("#nombre_paciente", "#botonAgregar", true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               //siguienteCampo("#nombre_paciente", "#botonModificar", true);
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


function buscarNombrePaciente() {
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

function modificarPaciente() {
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
                $("#id_paciente").focus();
                $("#id_paciente").select();
            },
            error: function (e) {
                $("#mensajes").html("No se pudo modificar los datos.");
            },
            complete: function (objeto, exito, error) {
                
            }
        });
    }
    
    function eliminarPaciente() {
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
                $("#id_paciente").focus();
                $("#id_paciente").select();
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

    
    function limpiarFormulario(){
        $("#id_paciente").val("0");
        $("#nombre_paciente").val("");
        $("#apellido_paciente").val("");
        $("#fechanac_paciente").val("dd//mm/aaa");
        $("#ci_paciente").val("");
        $("#direccion_paciente").val("");
        $("#telefono_paciente").val("");
        $("#correo_paciente").val("");
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

 function validarFormulario() {
        var valor = true;
        if ($("#nombre_paciente").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Nombre no puede estar vacio.");
            $("#nombre_paciente").focus();
        }
        
        if ($("#apellido_paciente").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Apellido no puede estar vacio.");
            $("#apellido_paciente").focus();
        }
        
        if ($("#fechanac_paciente").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Fecha no puede estar vacio.");
            $("#fechanac_paciente").focus();
        }
        
        if ($("#ci_paciente").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Ci no puede estar vacio.");
            $("#ci_paciente").focus();
        }
        
        
        if ($("#direccion_paciente").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Direccion no puede estar vacio.");
            $("#direccion_paciente").focus();
        }
        
        if ($("#telefono_paciente").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Teléfono no puede estar vacio.");
            $("#telefono_paciente").focus();
        }
        
         if ($("#correo_paciente").val().trim() === "") {
            valor = false;
            $("#mensajes").html("Correo no puede estar vacio.");
            $("#correo_paciente").focus();
        }
        return valor;
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
                $("#ci_paciente").val("");
                $("#ci_paciente").focus();
                //siguienteCampo("#nombre_paciente", "#botonModificar", true);
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
                $("#correo_paciente").val("");
                $("#correo_paciente").focus();
                //siguienteCampo("#telefono_paciente", "#botonModificar", true);
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