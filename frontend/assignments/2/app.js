const express = require("express");
const cors = require("cors");
const http = require('http')
const socketIo = require("socket.io");
const bodyParser = require('body-parser');

const app = express();
const server = http.createServer(app);
const io = new socketIo.Server(server, {
    cors: {
        origin: 'http://127.0.0.1:5500'
    }
});

app.use(cors());
app.use(express.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.set('view engine', 'ejs');

let posts = [
    {
        userName: "Rohit Sharma",
        userHandle: "@sharmaji",
        content: "India will win next world cup. ",
        imageContent: "./images/kapilDev.png"
    },
    {
        userName: "Rohit Sharma",
        userHandle: "@sharmaji",
        content: "India will win next world cup. ",
        imageContent: "./images/kapilDev.png"
    },
    {
        userName: "Rohit Sharma",
        userHandle: "@sharmaji",
        content: "India will win next world cup. ",
        imageContent: "./images/kapilDev.png"
    },
    {
        userName: "Rohit Sharma",
        userHandle: "@sharmaji",
        content: "India will win next world cup. ",
        imageContent: "./images/kapilDev.png"
    },
    {
        userName: "Rohit Sharma",
        userHandle: "@sharmaji",
        content: "India will win next world cup. ",
        imageContent: "./images/kapilDev.png"
    },
    {
        userName: "Rohit Sharma",
        userHandle: "@sharmaji",
        content: "India will win next world cup. ",
        imageContent: "./images/boy.png"
    },
    {
        userName: "Rohit Sharma",
        userHandle: "@sharmaji",
        content: "India will win next world cup. ",
        imageContent: "./images/kapilDev.png"
    },
    {
        userName: "Rohit Sharma",
        userHandle: "@sharmaji",
        content: "India will win next world cup. ",
        imageContent: "./images/kapilDev.png"
    }
];

const users = [
    {
        username: "durvesh",
        password: "1234",
        name: "Durvesh Sangle",
        handle: "@dsangle",
        img: "./images/boy.png"
    },
    {
        username: "rohitSharma",
        password: "1234",
        name: "Rohit Sharma",
        handle: "@sharmaji",
        img: "./images/man1.png"
    },
    {
        username: "mandhanaSmriti",
        password: "1234",
        name: "Smriti Mandhana",
        handle: "@s",
        img: "./images/girl.png"
    }
];

let messagesStore = [

];

let userSockets = [
   
];

let activeUsers = [

];

io.on("connection", (socket) => {
    console.log("Connection created" + socket.id); 

    socket.on("storeSocketId", (data) => {
        const obj = {
            socketId: socket.id,
            name: data.name,
        }
        const obj1 = {
            socketId: socket.id,
            name: data.name,
            handle: data.handle,
            img: data.img
        }
        userSockets.push(obj);
        io.except(socket.id).emit("userLoggedIn", obj1);
        socket.emit("activeUsers", activeUsers);
        activeUsers.push(obj1);
        // console.log(data);
    })

    socket.on("sendMsg", (message) => {
        // Extract sender and receiver socket IDs from the incoming message
        const senderSocketId = message.sender;
        const receiverSocketId = message.receiver;

        const obj = {
            senderId: senderSocketId,
            receiverId: receiverSocketId,
            content: message.content,
            time: message.time
        };

        messagesStore.push(obj);

        io.to(receiverSocketId).emit("addMsg", {
            content: message.content,
            time: message.time,
            action: 'receive',
            from: senderSocketId
        });
    })
});

app.post("/api/login", (req, res) => {
    const credentials = req.body;
    let found = false;
    for (let user of users) {
        if (user.username === credentials.username && user.password === credentials.password) {
            res.status(200).json(user);
            found = true;
        }
    }
    if(!found) res.status(401).json("Invalid username password");
})

app.get('/api/posts', (req, res) => {
    const page = parseInt(req.query.page) // Current page number
    const pageSize = parseInt(req.query.pageSize) // Number of posts per page

   // Calculate start and end indices for pagination
    const startIndex = Math.max(posts.length - (page * pageSize), 0);
    const endIndex = Math.max(posts.length - ((page - 1) * pageSize), 0);

    // Get the subset of posts for the current page
    let paginatedPosts = posts.slice(startIndex, endIndex);
    paginatedPosts = paginatedPosts.reverse();

    // Simulate a delay (replace this with actual database query)
    setTimeout(() => {
        res.json({
            posts: paginatedPosts
        });
    }, 1500);
});

// Route for creating a new post
app.post('/api/posts', (req, res) => {
    const content = req.body.content;
    const imgContent = req.body.imgContent;
    const user = req.body.user;

    const newPost = {
        id:posts.length+1,
        userName: user.name,
        userHandle: user.handle,
        img: user.img,
        content: content,
        imageContent: imgContent
    }
    
    posts.push(newPost);
    
    // Respond with a success message or the newly created post
    res.json({ message: 'Post created successfully', post: newPost });
});

app.get('/api/post', (req, res) => {
    const postId = req.query.id;

    let found = false;
    posts.forEach(post => {
        if (post.id === postId) {
            found = true;
            res.json(post);  
        } 
    })

    if (!found) res.status(404).json("Not found post with this id");
});

app.get("/api/chats/:socketSelf/:socketOther", (req, res) => {
    const socketSelf = req.params.socketSelf;
    const socketOther = req.params.socketOther;
    let chats = [];
    messagesStore.forEach((msg) => {
        if (msg.senderId === socketSelf && msg.receiverId === socketOther) {
            const obj1 = {
                content: msg.content,
                time: msg.time,
                action: "sent"
            }
            chats.push(obj1);
        }
        else if (msg.senderId === socketOther && msg.receiverId === socketSelf) {
            const obj1 = {
                content: msg.content,
                time: msg.time,
                action: "receive"
            }
            chats.push(obj1);
        }
    })
    const obj = {
        name: findName(socketOther),
        chats: chats
    }
    res.json(obj);
})

function findName(socketId) {
    let name = "temp";
    userSockets.forEach(obj => {
        if (obj.socketId === socketId) {
            console.log("Found socketOther name");
            name = obj.name;
        }
    })
    return name;
}

server.listen(5000, () => {
    console.log("Application started on port 5000");
});