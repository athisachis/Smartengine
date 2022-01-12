document.addEventListener('DOMContentLoaded', asignarEventos());

function asignarEventos() {
    
    let cards = document.querySelectorAll('.card');

    let desplegable = document.querySelectorAll('.dropdown-item');

    cards.forEach(card => {

        card.addEventListener('click', irCategoria);

    });

    desplegable.forEach(opcion => {

        opcion.addEventListener('click', irCategoria);

    });

}

function irCategoria() {
    
    let categoria= this.id;

    document.getElementById('categoriaElegida').value = categoria;

    document.getElementById('botonCategoria').click();
}