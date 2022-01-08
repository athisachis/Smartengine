document.getElementById("formModPassword").addEventListener("submit", function(event){
    event.preventDefault();
    waitingDialog.show("Cambiando contraseña...");
    document.getElementById("enviar").disabled = true;
    let url = "UsuarioForms";        
    let datos = new FormData(document.getElementById("formModPassword"));
    fetch(url, {
        method: "POST",
        body: datos
    })
    .then(function(response){
        if(response.ok){
            console.log("Entra en OK");
            return response.json();
        } else {
            console.log("Entra en else");
            waitingDialog.hide();
            let respuestaJSON = response.json();
            $.notify(respuestaJSON[1],{className: respuestaJSON[0]});
            document.getElementById("enviar").disabled = false;
        }
    })
    .then(function(respuestaJSON){
        console.log("Entra en segundo then");
        waitingDialog.hide();
        $.notify(respuestaJSON[1],{className: respuestaJSON[0]});            
        document.getElementById("enviar").disabled = false;
    })
    .catch(function(err){
        console.log("Entra en catch");
        waitingDialog.hide();
        $.notify("Error al modificar la contraseña",{className: 'error'});            
    })
});