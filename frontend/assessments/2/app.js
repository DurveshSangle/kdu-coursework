const express = require("express");
const cors = require("cors");
const http = require('http')
const socketIo = require("socket.io");

const app = express();
const server = http.createServer(app);
const io = new socketIo.Server(server, {
    cors: {
        origin: 'http://127.0.0.1:5501'
    }
});

app.use(cors());
app.use(express.json());

let stock = {
    price: 400,
    name: "Zomato",
    logo: "https://mir-s3-cdn-cf.behance.net/projects/404/af1307136664867.Y3JvcCw1NzUzLDQ1MDAsNDIsMA.jpg"
}

let history = [
   
]

io.on("connection", (socket) => {
    console.log("Connection created"); 

    setInterval(() => {
        const priceDelta = -10 + 20 * Math.random();
        stock.price += priceDelta;
        stock.price = Math.max(0, stock.price);
        io.emit("updates", stock.price);
    },5000);

    socket.on('createMessage',(newMessage) => {
        console.log('newMessage', newMessage);
        io.except(socket.id).emit("createMessage", newMessage);
    }); 
});

app.get("/baseData", (req, res) => {
    console.log("Here");
    res.status(200).json({stock,history});
})

app.post("/transaction", (req, res) => {
    const obj = {
        price:stock.price,
        quantity: req.body.qty,
        action: req.body.action,
        dateTime: "Fri, 02 Feb 1996 21:04:05 GMT"
    }
    history.push(obj);
})

server.listen(5000, () => {
    console.log("Application started on port 5000");
});

