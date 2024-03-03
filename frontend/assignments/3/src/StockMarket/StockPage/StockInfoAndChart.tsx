import { useState } from "react";
import { StockInfo } from "./StockInfo";
import { StockPriceChart } from "./StockPriceChart";
import { useParams } from "react-router-dom";
import { useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import { PriceStateProp, Stock } from "../../type/stokcMarket.type";

export function StockInfoAndChart() {
  const { stockName } = useParams();
  const stockList = useSelector((state: RootState) => state.stockData.stocksList);
  const stock: Stock = stockList.filter((stock) => stock?.stock_name === stockName)[0];
  const [selectedStock, setSelectedStock] = useState<string>(stockName);
  const [basePrice, setBasePrice] = useState<number>(stock?.base_price);
  const [previousStatePrice, setPreviousStatePrice] = useState<number>(stock?.base_price);

  const prop: PriceStateProp = {
    setPrevStatePrice: setPreviousStatePrice,
    prevStatePrice: previousStatePrice,
    basePrice: basePrice,
    setBasePrice: setBasePrice,
    selectedStock: selectedStock,
    setSelectedStock: setSelectedStock
  }
  return (
    <div>
      <StockInfo priceStateProp={prop}/>
      <StockPriceChart priceStateProp={prop} />
    </div>
  )
}