document.addEventListener('DOMContentLoaded', asignarEventos());

function asignarEventos() {

    let desplegable = document.querySelectorAll('.dropdown-item');

    desplegable.forEach(opcion => {

        opcion.addEventListener('click', irCategoria);

    });

}

function irCategoria() {
    
    let categoria= this.id;

    document.getElementById('categoriaElegida').value = categoria;

    document.getElementById('botonCategoria').click();
}