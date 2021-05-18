const socket = {}
let websocket = null;
const wsURI = 'ws://localhost:8080/backend/user';
function loaded() {
   // document.getElementById("conectar").addEventListener('click', socketConnect);
}
;
//=============================== CONEXION ========================================
function socketConnect() {
    if ('WebSocket' in window) {
        websocket = new WebSocket(wsURI);
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket(wsURI);
    } else {
        alert('Tu navegador no soporta WebSockets');
        return;
    }
    websocket.addEventListener("open", function (event) {//envia al server
        console.log('conneccted...');
        socketSend("conexion","*");
    });
    websocket.addEventListener("message", function (event) {//recibe desde el servidor
        //console.log("From server : ", event.data);
        renderTickets(event.data);
    });
}
const renderTickets = (data) => {
    const res = JSON.parse(data);
    const total = document.getElementById("totalCompra");
    total.textContent = '$' + res[1];
    console.log(res);
    var listado = document.getElementById("tickets");
    listado.innerHTML = '';
    res[0].forEach((t) => {
        rowTicket(listado, t);
    });
};
function agregarCarrito(id, precio, descripcion,catDisponible) {
    
    const car = document.getElementById("itemsCarrito");
    const div = document.createElement("div");
    div.innerHTML = `
    <div class="card text-white border-dark mb-1 p-0" style=" border-radius: 10px;  background-color:#339cff;">
            <div class="card-body" >
     <img src="vuelo-3.jpg" class="card-img" style="max-height: 50px">
            <p>No.${id} - USA-FRANCIA - $ ${precio}</p>
            <p style="text-align: ">${descripcion}</p>
            </div>
    </div>`;
    socketSend("addToCar",id);
    car.appendChild(div);
};

function rowTicket(listado, ticket) {
    const div = document.createElement("div");
    div.innerHTML =
            `<div class="card text-white  mt-1" style="max-width:auto">
            <img src="vuelo-3.jpg" class="card-img" style="max-height: 200px">
            <div class="card-img-overlay" >
            <h5>No.${ticket.idTiquete}</h5> <h5>Disponibles  : <div id="${ticket.idTiquete}" class="d-inline p-2 text-white ml-auto"  style="border-radius: 20px;  background-color:#00cc00;"> ${ticket.catDisponible} </div> </h5>
                    <h4 style="text-align: center">USA-FRANCIA</h4>
                    <h4 style="text-align: center">Precio: ${ticket.precio}</h4>
                    <h4 style="text-align: center">${ticket.descrip}</h4>
            </div>
     </div>` + "<button style='border: none;' class='d-inline p-2 bg-primary text-white' onclick='agregarCarrito(\"" + ticket.idTiquete + "\", \"" + ticket.precio + "\", \"" + ticket.descrip + "\", \"" + ticket.catDisponible + "\"); ' >Add a Carrito</button>";
    listado.appendChild(div);
}
;
const socketBtnSend = () => {
    const inputmsj = document.getElementById("inputmensajes");
    const message = inputmsj.value;
    send(message);
};
function socketSend(method,message) {
    var mensaje = [method,message];
   // console.log(mensaje);
    websocket.send(JSON.stringify(mensaje));
}

function socketDelete() {

    var message = ["/delete", JSON.stringify(objeto)];
    websocket.send(JSON.stringify(message));
}
function socketInsert() {
    var message = ["/insert", JSON.stringify(objeto)];
    websocket.send(JSON.stringify(message));
}
function socketUpdate() {
    var message = ["/update", JSON.stringify(objeto)];
    websocket.send(JSON.stringify(message));
}
function closeSocket() {
    websocket.close();
}
function socketDisconnect() {
    if (websocket != null) {
        websocket.close();
        websocket = null;
    }
}
document.addEventListener('DOMContentLoaded', loaded);


socket.socketConnect = socketConnect;
socket.socketSend = socketSend;
socket.socketDelete = socketDelete;
socket.socketInsert = socketInsert;
socket.socketUpdate = socketUpdate;
socket.socketDisconnect = socketDisconnect;
socket.socketBtnSend = socketBtnSend;
exports = socket;