import { CSSProperties } from "react"

import { Header } from "./Header/Header"
import { Main } from "./HomePage/Main"
import { BrowserRouter, Route, Routes } from "react-router-dom"
import { ProductDetailsMain } from "./ProductDetailsPage/ProductDetailsMain"
import { GlobalProvider } from "../type/ContextProvider"
import { NotFoundPage } from "./NotFoundPage/NotFoundPage"


export function KduMarketHome() {
  const style: CSSProperties = {
    width: "100%"
  }
  return (
    <GlobalProvider>
      <BrowserRouter>
        <div style={style}>
          <Header />
          <Routes>
            <Route path="/" element={<Main />} />
            <Route path='/product/:productId' element={<ProductDetailsMain /> } />
            <Route path="*" element={<NotFoundPage/>} />
          </Routes>
        </div>
      </BrowserRouter>
    </GlobalProvider>
  )
}