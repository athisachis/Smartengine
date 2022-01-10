let checkMail = false;

document.addEventListener('DOMContentLoaded', asignarEventos());

function asignarEventos() {

    let camposForm = document.querySelectorAll('.form-control');

    camposForm.forEach(element => {

        element.addEventListener('blur', comprobarCampos);

    });

    document.getElementById('email').addEventListener('blur', AJAXCheckMail);

}

function comprobarCampos(e) {

    if (e.target.name == 'email') {


        if (e.target.value!='') {
            if (isValidEmail(e.target.value)) {

                if ((e.target.classList.contains('error'))) {
                    e.target.classList.remove("error");
                }
    
    
                if (!e.target.classList.contains('exito')) {
                    e.target.classList += ' exito';
    
                }

                /*  let mailAJAX = "email="+e.target.value;
                let yaRegistrado = AJAXCheckMail(mailAJAX);


                console.log(yaRegistrado);

                if (yaRegistrado) {
                    

                    if ((e.target.classList.contains('exito'))) {
                        e.target.classList.remove("exito");
                    }
        
                    if (!e.target.classList.contains('error')) {
                        e.target.classList += ' error';
                    }
        
        
                    document.getElementById("errorMailExiste").style.display = "inline";
                }else{
                    checkMail= true;
                }  */

                document.getElementById("errorMail").style.display = "none";
    
    
            } else {
    
                if ((e.target.classList.contains('exito'))) {
                    e.target.classList.remove("exito");
                }
    
                if (!e.target.classList.contains('error')) {
                    e.target.classList += ' error';
                }
    
    
                document.getElementById("errorMail").style.display = "inline";
            }
        }else{
            if (e.target.classList.contains('error')) {
                e.target.classList.remove('error');
            }

            if (e.target.classList.contains('exito')) {
                e.target.classList.remove('exito');
            }
            document.getElementById("errorMail").style.display = "none";
        }

        


    }

    if (e.target.name == 'contrasenia' || e.target.name == 'contrasenia2') {

        let contrasenia = document.getElementById('contrasenia');
        let contrasenia2 = document.getElementById('contrasenia2');

        if (contrasenia.value != '' && document.getElementById('contrasenia2').value != '') {

            if (checkContrasenias(document.getElementById('contrasenia').value, contrasenia2.value)) {

                if (contrasenia.classList.contains('error')) {
                    contrasenia.classList.remove('error');
                    contrasenia2.classList.remove('error');
                }

                if (!contrasenia.classList.contains('exito') && !contrasenia2.classList.contains('exito')) {
                    contrasenia.classList += ' exito';
                    contrasenia2.classList += ' exito';
                }

                document.getElementById("errorContrasenia").style.display = "none";

            } else {

                if (contrasenia.classList.contains('exito')) {
                    contrasenia.classList.remove('exito');
                    contrasenia2.classList.remove('exito');
                }

                if (!contrasenia.classList.contains('error') && !contrasenia2.classList.contains('error')) {
                    contrasenia.classList += ' error';
                    contrasenia2.classList += ' error';
                }

                document.getElementById("errorContrasenia").style.display = "inline";


            }


        } else {

            if (contrasenia.classList.contains('error')) {
                contrasenia.classList.remove('error');
                contrasenia2.classList.remove('error');
            }

            if (contrasenia.classList.contains('exito')) {
                contrasenia.classList.remove('exito');
                contrasenia2.classList.remove('exito');
            }

            document.getElementById("errorContrasenia").style.display = "none";
        }


    }

    if (e.target.name == 'nombre' || e.target.name == 'apellidos' || e.target.name == 'localidad' || e.target.name == 'provincia') {



        if (e.target.value != '') {
            if (soloLetras(e.target.value)) {

                if ((e.target.classList.contains('error'))) {
                    e.target.classList.remove("error");
                }

                if (!e.target.classList.contains('exito')) {
                    e.target.classList += ' exito';
                }

            } else {
                if ((e.target.classList.contains('exito'))) {
                    e.target.classList.remove("exito");
                }

                if (!e.target.classList.contains('error')) {
                    e.target.classList += ' error';
                }
            }
        } else {
            if (e.target.classList.contains('error')) {
                e.target.classList.remove('error');
            }

            if (e.target.classList.contains('exito')) {
                e.target.classList.remove('exito');
            }
        }


    }

    if (e.target.name == 'nif') {

        if (e.target.value != '') {

            let checkNif=false;

            if (/^[0-9]{8}$/.test(e.target.value) && checkNif == false) {
                let nif = e.target.value;

                let letra = calcularDNI(nif);

                e.target.value = nif + letra;

                if ((e.target.classList.contains('error'))) {
                    e.target.classList.remove("error");
                }

                if (!e.target.classList.contains('exito')) {
                    e.target.classList += ' exito';
                }

                document.getElementById("errorNif").style.display = "none";

                checkNif = true;

            } else {
                if ((e.target.classList.contains('exito'))) {
                    e.target.classList.remove("exito");
                }

                if (!e.target.classList.contains('error')) {
                    e.target.classList += ' error';
                }

                document.getElementById("errorNif").style.display = "inline";
            }
        } else {

            if (e.target.classList.contains('error')) {
                e.target.classList.remove('error');
            }

            if (e.target.classList.contains('exito')) {
                e.target.classList.remove('exito');
            }

            document.getElementById("errorNif").style.display = "none";
        }


    }

    if (e.target.name == 'telefono') {

        if (e.target.value != '') {
            if (/^[0-9]{9}$/.test(e.target.value)) {

                if ((e.target.classList.contains('error'))) {
                    e.target.classList.remove("error");
                }

                if (!e.target.classList.contains('exito')) {
                    e.target.classList += ' exito';
                }

                document.getElementById("errorTlf").style.display = "none";

            } else {
                if ((e.target.classList.contains('exito'))) {
                    e.target.classList.remove("exito");
                }

                if (!e.target.classList.contains('error')) {
                    e.target.classList += ' error';
                }

                document.getElementById("errorTlf").style.display = "inline";
            }
        } else {

            if (e.target.classList.contains('error')) {
                e.target.classList.remove('error');
            }

            if (e.target.classList.contains('exito')) {
                e.target.classList.remove('exito');
            }

            document.getElementById("errorTlf").style.display = "none";
        }



    }

    if (e.target.name=='codPostal') {
        if (e.target.value!='') {
            if (cpValido(e.target.value)) {
                if ((e.target.classList.contains('error'))) {
                    e.target.classList.remove("error");
                }

                if (!e.target.classList.contains('exito')) {
                    e.target.classList += ' exito';
                }

                document.getElementById("errorCP").style.display = "none";
            } else {
                if ((e.target.classList.contains('exito'))) {
                    e.target.classList.remove("exito");
                }

                if (!e.target.classList.contains('error')) {
                    e.target.classList += ' error';
                }

                document.getElementById("errorCP").style.display = "inline";
            }
        } else {
            if (e.target.classList.contains('error')) {
                e.target.classList.remove('error');
            }

            if (e.target.classList.contains('exito')) {
                e.target.classList.remove('exito');
            }

            document.getElementById("errorCP").style.display = "none";
        }
    }

    if (e.target.name=="direccion") {
        
        if (e.target.value!="") {
            if ((e.target.classList.contains('error'))) {
                e.target.classList.remove("error");
            }

            if (!e.target.classList.contains('exito')) {
                e.target.classList += ' exito';
            }
        }else{
            if ((e.target.classList.contains('exito'))) {
                e.target.classList.remove("exito");
            }

            if (!e.target.classList.contains('error')) {
                e.target.classList += ' error';
            }
        }
    }

}



/* VALIDA EL MAIL */

function isValidEmail(mail) {
    return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/.test(mail);
}

/* VALIDA QUE LAS CONTRASENIAS SON IGUALES */

function checkContrasenias(contrasenia, contrasenia2) {

    if (contrasenia == contrasenia2) {
        return true;
    } else {
        return false;
    }

}

/* VALIDA EL CODIGO POSTAL */

function cpValido(codPostal) {

    /* let regEx = '/^[0-5][1-9]{3}[0-9]$/'; */

    return /^(0[1-9]|[1-4][0-9]|5[0-2])[0-9]{3}$/.test(codPostal);
}

/* COMPRUEBA QUE SOLO SE INTRODUZCAN LETRAS */
function soloLetras(palabra) {

    return /^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$/g.test(palabra);
}

function calcularDNI(dni) {

    var juegoCaracteres = "TRWAGMYFPDXBNJZSQVHLCKET";
    var posicion = dni % 23;
    var letra = juegoCaracteres.charAt(posicion);

    return letra;
}


/* COMPRUEBA QUE EL EMAIL NO EXISTE YA EN LA BBDD */
/* function AJAXCheckMail(email) {
    
    let request = new XMLHttpRequest();
    let yaExiste;

    request.onreadystatechange = () => {

        console.log(request.readyState + '  '+ request.status);

        if (request.readyState === 4 && request.status === 200) {
            
            yaExiste = request.responseText;

            console.log(yaExiste.split(":")[1].slice(0,-1));

            return yaExiste;
        }
    }

    request.open("POST", "../AJAXRegistro", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send(email);
    
} */

function AJAXCheckMail() {
    
    let request = new XMLHttpRequest();
    let yaExiste=false;

    let email= 'email='+document.getElementById('email').value;

    request.onreadystatechange = () => {

        if (request.readyState === 4 && request.status === 200) {
            
            yaExiste = request.responseText;

            let campoMail= document.getElementById('email');


            if (yaExiste.split(":")[1].slice(0,-1)=='true') {
                    

                if ((campoMail.classList.contains('exito'))) {
                    campoMail.classList.remove("exito");
                }
    
                if (!campoMail.classList.contains('error')) {
                    campoMail.classList += ' error';
                }
    
    
                document.getElementById("errorMailExiste").style.display = "inline";
            }else{
                document.getElementById("errorMailExiste").style.display = "none";
            }
        }

        
        
    }

    request.open("POST", "../AJAXRegistro", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send(email);
    
} 


