document.addEventListener('DOMContentLoaded', asignarEventos());

function asignarEventos() {

    let unidadMas = document.querySelectorAll('#udMas');
    let unidadMenos = document.querySelectorAll('#udMenos');

    unidadMas.forEach(opcion => {

        opcion.addEventListener('click', masCantidad);

    });

    unidadMenos.forEach(opcion => {

        opcion.addEventListener('click', menosCantidad);

    });

}


function masCantidad(e) {

    let idProducto = parseInt(e.target.parentElement.parentElement.children[2].innerText);

    let cantidad = parseInt(e.target.parentElement.parentElement.children[1].innerText) + 1;

    e.target.parentElement.parentElement.children[1].innerHTML = `<h5>${cantidad}</h5>`;

    AJAX(idProducto, cantidad);

}

function menosCantidad(e) {

    let idProducto = parseInt(e.target.parentElement.parentElement.children[2].innerText);

    let cantidad = parseInt(e.target.parentElement.parentElement.children[1].innerText) - 1;

    if (cantidad>0) {
        e.target.parentElement.parentElement.children[1].innerHTML = `<h5>${cantidad}</h5>`;

        AJAX(idProducto, cantidad);
    }



}

function AJAX(idProducto, cantidad) {

    let request = new XMLHttpRequest;

    let infoCambio = "idProducto=" + idProducto + "&cantidad=" + cantidad;

    request.open("POST", "../AJAXCantidad", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send(infoCambio);

}