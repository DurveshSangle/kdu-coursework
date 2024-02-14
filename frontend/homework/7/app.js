const express = require("express");
const cors = require("cors");
const http = require('http')
const socketIo = require("socket.io");

const app = express();
const server = http.createServer(app);
const io = new socketIo.Server(server, {
    cors: {
        origin: 'http://127.0.0.1:5500'
    }
});

app.use(cors());
app.use(express.json());

io.on("connection", (socket) => {
    console.log("Connection created"); 

    socket.on('createMessage',(newMessage) => {
        console.log('newMessage', newMessage);
        io.except(socket.id).emit("createMessage", newMessage);
    }); 
});


server.listen(5000, () => {
    console.log("Application started on port 5000");
});