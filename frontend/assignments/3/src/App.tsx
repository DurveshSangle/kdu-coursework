// App.js
import React, { useCallback, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Header } from './StockMarket/Header/Header';
import { LandingPageMain } from './StockMarket/Landing Page/LandingPageMain';
import { StockPageMain } from './StockMarket/StockPage/StockPageMain';
import { PortfolioPageMain } from './StockMarket/PortfolioPage/PortfolioPageMain';
import { getStocks } from './thunks/GetStocks';
import { getPortfolioTransaction } from './thunks/GetPortfolioTransactions';
import { AppDispatch } from './redux/store';

function App() {
  const reduxDispatch:AppDispatch = useDispatch();

  const fetchStocks = useCallback(() => {
    reduxDispatch(getStocks("https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/stocks.json"));
  }, [reduxDispatch]);

  const fetchPortfolioTransactions = useCallback(() => {
    reduxDispatch(getPortfolioTransaction(`http://127.0.0.1:5000/portfolioTransactions`));
  }, [reduxDispatch]);

  useEffect(() => {
    fetchStocks();
    fetchPortfolioTransactions();
  }, [fetchStocks, fetchPortfolioTransactions]);

  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/" element={<LandingPageMain />} />
        <Route path="/stock/:stockName" element={<StockPageMain />} />
        <Route path="/portfolio" element={<PortfolioPageMain />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
