import { useState, useEffect } from "react";
import { StockInfo } from "./StockInfo";
import { StockPriceChart } from "./StockPriceChart";
import { useParams } from "react-router-dom";
import { useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import { PriceStateProp,Stock} from "../../type/stokcMarket.type";

export function StockInfoAndChart() {
  const { stockName } = useParams<{ stockName: string }>();
  const stockList = useSelector((state: RootState) => state.stockData.stocksList);
  const [selectedStock, setSelectedStock] = useState<string>("");
  const [basePrice, setBasePrice] = useState<number>(0);
  const [previousStatePrice, setPreviousStatePrice] = useState<number>(0);
  const [stock, setStock] = useState<Stock | null>(null);

  useEffect(() => {
    // Find the stock by its name
    const selectedStock = stockList.find(stock => stock?.stock_name === stockName);
    if (selectedStock && stockName) {
      setStock(selectedStock);
      setBasePrice(selectedStock.base_price);
      setPreviousStatePrice(selectedStock.base_price);
      setSelectedStock(stockName);
    } else {
      // Handle case where stock is not found
      // For example, redirect to a not found page or display an error message
      console.error(`Stock with name ${stockName} not found.`);
    }
  }, [stockName, stockList]);

  const prop: PriceStateProp = {
    setPrevStatePrice: setPreviousStatePrice,
    prevStatePrice: previousStatePrice,
    basePrice: basePrice,
    setBasePrice: setBasePrice,
    selectedStock: selectedStock,
    setSelectedStock: setSelectedStock
  };

  return (
    <div>
      {stock && (
        <>
          <StockInfo priceStateProp={prop} />
          <StockPriceChart priceStateProp={prop} />
        </>
      )}
    </div>
  );
}
