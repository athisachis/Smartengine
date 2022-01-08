

document.addEventListener('DOMContentLoaded', asignarEventos());

function asignarEventos() {
    
    document.getElementById('botonLogin').addEventListener('click', comprobarCampos);

}

function comprobarCampos(e) {

    let email= document.getElementById('email');
    let contrasenia = document.getElementById('contrasenia');
    let checkCampos= false;
    let checkMail= false;

    if(email.value!=""&&contrasenia.value!=""){
        checkCampos= true;
    }

    if (!isValidEmail(email.value)) {
        e.preventDefault();
        swal({
            text: "El formato del mail no es correcto",
            icon: "error",
          });
    }
    


    if (checkCampos==false) {
        e.preventDefault();
        swal({
            text: "Todos los campos deben estar rellenos",
            icon: "error",
          });
    }

    /* if (checkMail==false) {
        e.preventDefault();
        swal({
            text: "El formato del mail no es correcto",
            icon: "error",
          });
    } */

}

function isValidEmail(mail) { 
    return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/.test(mail); 
  }

