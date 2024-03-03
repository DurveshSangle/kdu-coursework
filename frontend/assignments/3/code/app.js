const express = require("express");
const cors = require("cors");
const http = require('http')
const socketIo = require("socket.io");
const { log } = require("console");

const app = express();
const server = http.createServer(app);
const io = new socketIo.Server(server, {
    cors: {
        origin: 'http://localhost:5173'
    }
});

app.use(cors());
app.use(express.json());

const fetchTransactionHistory = async () => {
  try {
    const response = await fetch("https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/portfolio-transactions.json");
    const data = await response.json();
    return data;
  } catch (e) {
    console.log("Error fetching portfolio transactions");
    throw e;
  }
}

const formatDateAndTime = (timestamp) => {
    const date = new Date(timestamp);
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const period = hours < 12 ? 'AM' : 'PM';
    const formattedHours = hours % 12 || 12; // Convert 0 to 12 for AM/PM format
    const formattedMinutes = minutes < 10 ? `0${minutes}` : minutes;

    // Format date with abbreviated month name
    const options = { day: 'numeric', month: 'short', year: 'numeric' };
    const formattedDate = date.toLocaleDateString('en-US', options);

    // Format time
    const formattedTime = `${formattedHours}:${formattedMinutes} ${period}`;

    return [formattedDate,formattedTime];
}

let portfolioTransactions = [];

// Fetch transaction history asynchronously and update portfolioTransactions when resolved
fetchTransactionHistory().then(data => {
  portfolioTransactions = data;
  portfolioTransactions.forEach(transaction => {
    // Extract relevant data from the transaction object
      const { stock_name, stock_symbol, transaction_price, timestamp, status } = transaction;
  
      const dateTime = formatDateAndTime(timestamp);
  
      // Construct a transaction object
      const transactionData = {
        stockName: stock_name,
        stockSymbol: stock_symbol,
        price: transaction_price,
        date: dateTime[0],
        time: dateTime[1],
        status: status,
        filter: true
      };
      transactionHistory.push(transactionData);
    });
}).catch(error => {
  console.error("Error initializing portfolio transactions:", error);
});

// Initialize a new Map to store transaction history
let transactionHistory = []

// Define a map to store wallet balances for each socket ID
const walletBalances = new Map();

// Define a map to store stock holdings for each socket ID
const stockHoldings = new Map();

io.on("connection", (socket) => {
  console.log("Connection created" + socket.id);

  // Initialize wallet balance for new socket ID
  walletBalances.set(socket.id, 20000); // Initialize with 1000 as an example balance
  // Initialize stock holdings for new socket ID
  stockHoldings.set(socket.id, new Map());


  socket.on("joinRoom", (roomName) => {
    socket.join(roomName);
  });

  socket.on("buyStock", (stockTransaction) => {
    const { stockName, quantity, price, date, time, stockSymbol} = stockTransaction;
    const balance = walletBalances.get(socket.id);
    const amount = price * quantity;
    let status = "Passed";

    if (balance >= amount) {
      stockTransaction.message = `Akaay bought ${quantity} ${stockName}`;
      io.to(stockName).except(socket.id).emit("notification", stockTransaction);
      socket.emit("buyHistory", stockTransaction);

      // Deduct amount from wallet balance
      walletBalances.set(socket.id, balance - amount);

      // Update stock holdings
      const holdings = stockHoldings.get(socket.id);
      const currentQuantity = holdings.get(stockName) || 0;
      holdings.set(stockName, parseInt(currentQuantity) + parseInt(quantity)); 

      

    } else {
      // If transaction is not possible due to insufficient balance, send a notification
      status = "Failed";
      socket.emit("insufficientBalance", "Insufficient balance for this transaction");
    }

    const transactionData = {
      stockName: stockName,
      stockSymbol: stockSymbol,
      price: amount,
      date: date,
      time:time,
      status: status
    };

    transactionHistory.push(transactionData);
    socket.emit("transaction", transactionData);
  });

  socket.on("sellStock", (stockTransaction) => {
    const { stockName, quantity, price, date, time, stockSymbol } = stockTransaction;
    const holdings = stockHoldings.get(socket.id);
    const currentQuantity = holdings.get(stockName) || 0;
    const sellAmount = price * quantity;
    let status = "Passed";
    if (parseInt(currentQuantity) >= quantity) {
      stockTransaction.message = `Akaay sold ${quantity} ${stockName}`;
      io.to(stockName).except(socket.id).emit("notification", stockTransaction);
      socket.emit("sellHistory", stockTransaction);

      // Update stock holdings
      holdings.set(stockName, currentQuantity - quantity);

      // Update wallet balance
      const balance = walletBalances.get(socket.id);
      walletBalances.set(socket.id, balance + sellAmount);
    } else {
      // If transaction is not possible due to insufficient holdings, send a notification
      status = "Failed";
      socket.emit("insufficientHoldings", "Insufficient stock holdings for this transaction");
    }
    const transactionData = {
      stockName: stockName,
      stockSymbol: stockSymbol,
      price: sellAmount,
      date: date,
      time:time,
      status: status
    };

    transactionHistory.push(transactionData);
    socket.emit("transaction", transactionData);
  });

  // Handle disconnection
  socket.on("disconnect", () => {
    // Remove wallet balance entry for disconnected socket ID
    walletBalances.delete(socket.id);
    // Remove stock holdings entry for disconnected socket ID
    stockHoldings.delete(socket.id);
  });
});

app.get("/portfolioTransactions", (req, res) => {
  res.json(transactionHistory);
})

server.listen(5000, () => {
    console.log("Application started on port 5000");
});
