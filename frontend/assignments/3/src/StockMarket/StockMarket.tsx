import { Header } from "./Header/Header";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { LandingPageMain } from "./Landing Page/LandingPageMain";
import { StockPageMain } from "./StockPage/StockPageMain";
import { PortfolioPageMain } from "./PortfolioPage/PortfolioPageMain";


export function StockMarket() {
  return (
    <div>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<LandingPageMain />} />
          <Route path="/stock/:stockName" element={<StockPageMain />} />
          <Route path="/portfolio" element={<PortfolioPageMain />} />
        </Routes>
      </BrowserRouter>
    </div>
  )
}