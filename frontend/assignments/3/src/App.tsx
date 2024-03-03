import { useDispatch } from 'react-redux';
import './App.css'
import { AppDispatch } from './redux/store';
import { useEffect } from 'react';
import { getStocks } from './thunks/GetStocks';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Header } from './StockMarket/Header/Header';
import { LandingPageMain } from './StockMarket/Landing Page/LandingPageMain';
import { StockPageMain } from './StockMarket/StockPage/StockPageMain';
import { PortfolioPageMain } from './StockMarket/PortfolioPage/PortfolioPageMain';
import { getPortfolioTransaction } from './thunks/GetPortfolioTransactions';

function App() {
  const reduxDispatch:AppDispatch = useDispatch();

  useEffect(() => {
    reduxDispatch(getStocks("https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/stocks.json"));
    reduxDispatch(getPortfolioTransaction(`http://127.0.0.1:5000/portfolioTransactions`))
  }, [reduxDispatch]);

  return (
    <BrowserRouter>
    <Header />
    <Routes>
      <Route path="/" element={<LandingPageMain />} />
      <Route path="/stock/:stockName" element={<StockPageMain />} />
      <Route path="/portfolio" element={<PortfolioPageMain />} />
    </Routes>
  </BrowserRouter>
  )
}

export default App
