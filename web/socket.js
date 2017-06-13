var websocket;
var usua;
var mensajes = document.getElementById("mensajes");
var chat = document.getElementById("txtChat");
function AbrirSocket(){
    if(websocket !== undefined && websocket.readyState !== WebSocket.CLOSED){
        writeResponse("WebSocket ya está abierto.");
        return;
    }
    websocket = new WebSocket("ws://" + document.location.host + document.location.pathname + "Websocket");

    websocket.onopen = function(event){
        if(event.data === undefined){
            return;
        }
        writeResponse(event.data);
    };
    websocket.onmessage = function(event){
        writeResponse(event.data);
    };
    websocket.onclose = function(event){
        writeResponse("Conexión cerrada");
    }; 
}
function Enviar(){
    var text = document.getElementById("txtMensaje").value;
    websocket.send(text);
}
function Cerrar(){
    websocket.close();
}
function writeResponse(text){ 
    mensajes.innerHTML += "<p></p>" + text;
}
function abrir2(){
    if(websocket !== undefined && websocket.readyState !== WebSocket.CLOSED){
        writeResponse("WebSocket ya está abierto.");
        return;
    }
    websocket = new WebSocket("ws://localhost:8080/websockets/Websocket");

    websocket.onopen = function(event){
        writeResponse("Conexion establecida");
    };
    websocket.onmessage = function(event){
        writeResponse(event.data);
    };
    websocket.onclose = function(event){
        writeResponse("Conexión cerrada");
    };
}
function usuario(){
    usua = document.getElementById("txtUsu").value;
    if(websocket !== undefined && websocket.readyState !== WebSocket.CLOSED){
        writeResponse2("WebSocket ya está abierto.");
        return;
    }
    websocket = new WebSocket("ws://" + document.location.host + document.location.pathname + "Websocket");

    websocket.onopen = function(event){
        //writeResponse(event.data);
        writeResponse2("Bienvenido " + usua);
    };
    websocket.onmessage = function(event){
        //writeResponse(event.data);
        writeResponse2(event.data);
    };
    websocket.onclose = function(event){
        //writeResponse("Conexión cerrada");
        writeResponse2("Conexión cerrada");
    }; 
    document.getElementById("txtUsu").disabled = true;
    document.getElementById("btnOk").disabled = true;
}
function Enviar2(){
    var texto = document.getElementById("txtMen").value;
    websocket.send(usua + ": " + texto);
}
function writeResponse2(texto){
    chat.value += texto + "\n";
}
