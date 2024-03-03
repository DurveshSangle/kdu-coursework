import { useEffect, useRef } from "react";
import { Link, useParams } from "react-router-dom";
import { RootState } from "../../redux/store";
import { useSelector } from "react-redux";
import { Avatar, Typography, Select, MenuItem, SelectChangeEvent } from "@mui/material";
import { deepOrange } from "@mui/material/colors";
import { PriceStateProp, StockTransaction} from "../../type/stokcMarket.type";
import { socket } from "./../socket"
import { getCurrentTimestamp } from "../../utility/getCurrentTimeStamp";
import { getCurrentTime } from "../../utility/getCurrentTime";
import { getCurrentDateFormatted } from "../../utility/getCurrentDate";

export function StockInfo({ priceStateProp }: { priceStateProp: PriceStateProp }) {
  const { stockName } = useParams();

  const stockList = useSelector((state: RootState) => state.stockData.stocksList);
  const selectedStock = priceStateProp?.selectedStock;
  const setSelectedStock = priceStateProp?.setSelectedStock;

  const stockSymbol = stockList.filter((stock) => (stock.stock_name === stockName))[0].stock_symbol;

  useEffect(() => {
    console.log("selectedStock:", selectedStock);
    if (stockName) setSelectedStock(stockName);
  }, [stockName, selectedStock,setSelectedStock]);

  const handleChange = (event: SelectChangeEvent<string>) => {
    console.log("handleChange:", event.target.value);
    setSelectedStock(event.target.value);
  };

  const percentageChange = ((priceStateProp?.basePrice - priceStateProp?.prevStatePrice) / priceStateProp?.prevStatePrice) * 100;

  socket.emit("joinRoom", stockName);

  const inputRef = useRef<HTMLInputElement>(null);

  const handleBuyStock = () => {
    const buyQunatity = inputRef.current?.value;
    const buyTransaction: StockTransaction = {
      stockName: stockName,
      quantity: buyQunatity,
      timestamp: getCurrentTimestamp(),
      time: getCurrentTime(),
      price: priceStateProp?.basePrice,
      date: getCurrentDateFormatted(),
      stockSymbol: stockSymbol
    }
    socket.emit("buyStock", buyTransaction);
  }

  const handleSellStock = () => {
    const buyQunatity = inputRef.current?.value;
    const sellTransaction: StockTransaction = {
      stockName: stockName,
      stockSymbol: stockSymbol,
      date:getCurrentDateFormatted(),
      quantity: buyQunatity,
      timestamp: getCurrentTimestamp(),
      time: getCurrentTime(),
      price: priceStateProp?.basePrice,
    }
    socket.emit("sellStock", sellTransaction);
  }

  return (
    <div style={{ display: "grid", gridTemplateColumns: "4fr 4fr 3fr 1fr 1fr", margin: "20px 30px", gap: "10px", marginLeft: "80px" }}>
      <div style={{ display: "flex", flexDirection: "row", justifyContent: "space-around", alignItems: "center" }}>
        <Select
          value={selectedStock}
          onChange={handleChange}
          style={{ minWidth: 200 }}
          MenuProps={{
            PaperProps: {
              style: {
                maxHeight: 300, // Set maximum height
                maxWidth: 100,
                overflowY: "auto", // Enable vertical scrolling
              },
            },
          }}
        >
          {stockList.map(stock => (
            <MenuItem key={stock.stock_name} value={stock.stock_name} >
              <Link to={`/stock/${stock.stock_name}`} style={{ textDecoration: "none", color: "inherit" }}>
                <div style={{ display: "flex", flexDirection: "row", alignItems: "center", padding: "5px 120px" }}>
                  <Avatar sx={{ bgcolor: deepOrange[500], marginRight: 1, padding: "5px 10px" }} variant="square">{stock.stock_symbol}</Avatar>
                  <Typography>{stock.stock_name}</Typography>
                </div>
              </Link>
            </MenuItem>
          ))}
        </Select>
      </div>
      <div style={{ display: "flex", flexDirection: "row", justifyContent: "space-around", alignItems: "center", border: "1px solid black", padding: "0 20px" }}>
        <Typography sx={{ fontSize: "1.5rem" }}>
          Price: <span style={{ color: percentageChange > 0 ? "#2f9e44" : "#e03131" }}>
            {priceStateProp?.basePrice}
            {percentageChange > 0 ? ' ↑' : ' ↓'}
          </span>
          <span style={{ fontSize: "1rem", marginLeft: "0.5rem" }}>
            ({percentageChange.toFixed(2)}%)
          </span>
        </Typography>
      </div>

      <input ref={inputRef} type="text" placeholder="Enter Qty" style={{ margin: "auto", padding: "25px 30px", fontSize:"1.3rem" }} />

      <button style={{ backgroundColor: "#b2f2bb" }} onClick={handleBuyStock}>Buy</button>
      <button style={{ backgroundColor: "#ffc9c9" }} onClick={handleSellStock}>Sell</button>
    </div>
  )
}