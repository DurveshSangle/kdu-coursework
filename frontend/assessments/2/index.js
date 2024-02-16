window.onload = () => {
    fetch("http://127.0.0.1:5000/baseData")
        .then(response => {
            return response.json();
        }).then(data => {
            const stock = data.stock;
            const history = data.history;

            const stockInfo = document.getElementById("stock-info");
            const img = document.createElement("img");
            img.src = stock.logo;
            stockInfo.appendChild(img);
            const p = document.createElement("p");
            p.innerText = stock.name;
            stockInfo.appendChild(p);
            
            const stockPrice = document.getElementById("stock-price");
            const p1 = document.createElement("p");
            p1.id = "price";
            p1.innerText = stock.price;
            const p2 = document.createElement("p");
            p2.id = "delta-percent";
            p2.innerText = "0%";
            stockPrice.appendChild(p1);
            stockPrice.appendChild(p2);

            const sideContainer = document.getElementById("side-container");

            history.forEach(element => {
                const eachTransaction = document.createElement("div");
                eachTransaction.classList.add("each-transaction");
                const transactionInfo = document.createElement("div");
                transactionInfo.classList.add("transaction-info");

                const p3 = document.createElement("p");
                p3.innerText = element.quantity + " stocks; price: " + element.price;
                const p4 = document.createElement("p");
                p4.classList.add("transaction-info-date");
                p4.innerText = element.dateTime;

                transactionInfo.appendChild(p3);
                transactionInfo.appendChild(p4);

                eachTransaction.appendChild(transactionInfo);

                const transactionAction = document.createElement("div");
                transactionAction.classList.add("transaction-action");
                transactionAction.innerHTML = element.action;
                eachTransaction.appendChild(transactionAction);

                sideContainer.appendChild(eachTransaction);
            });
        });
}

const socket = io("http://localhost:5000");
const priceDiv = document.getElementById("price");

socket.on("updates", (price) => {
    priceDiv.innerText = price;
    window.location.reload();
})


const buyBtn = document.getElementById("buy-btn");
const sellBtn = document.getElementById("sell-btn");
const input = document.getElementById("input");
buyBtn.addEventListener("click", () => {
    const qty = input.value + "";
    if (qty.length == 0) return;
    fetch("http://127.0.0.1:5000/transaction", {
        method: "POST",
        body: JSON.stringify({
            qty: qty,
            action: "Buy"
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    });
})

sellBtn.addEventListener("click", () => {
    const qty = input.value + "";
    if (qty.length == 0) return;
    fetch("http://127.0.0.1:5000/transaction", {
        method: "POST",
        body: JSON.stringify({
            qty: qty,
            action: "Sell"
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    });
})
