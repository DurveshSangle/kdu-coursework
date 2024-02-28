import { CSSProperties, useEffect } from "react"

import { Header } from "./Header/Header"
import { Main } from "./HomePage/Main"
import { BrowserRouter, Route, Routes } from "react-router-dom"
import { ProductDetailsMain } from "./ProductDetailsPage/ProductDetailsMain"
import { NotFoundPage } from "./NotFoundPage/NotFoundPage"
import { useDispatch, useSelector } from "react-redux"
import { AppDispatch, RootState } from "../redux/store"
import { getProducts } from "../thunks/getProducts"
import { Product } from "../type/kdumarket.types"
import { setProductList } from "../redux/productsLoadSlice"


export function KduMarketHome() {
  const style: CSSProperties = {
    width: "100%"
  }

  const reduxDispatch: AppDispatch = useDispatch();
  const allProductsList = useSelector((state: RootState) => state.productsLoad.allProductsList);
  const filterInput = useSelector((state: RootState) => state.products.filterInput);
  const sortInput = useSelector((state: RootState) => state.products.sortInput);
  const searchInput = useSelector((state: RootState) => state.products.searchInput);

  useEffect(() => {
    reduxDispatch(getProducts())
  }, [reduxDispatch])
  
  useEffect(() => {
    let list = [...allProductsList];
    if (filterInput !== "") list = list.filter((product) => (product.category === filterInput));
    if (sortInput === "asc") {
      list.sort((a: Product, b: Product) => a.price - b.price);
    }
    else if (sortInput === "desc") {
      list.sort((a: Product, b: Product) => b.price - a.price);
    } 
    console.log(filterInput+"**");
    reduxDispatch(setProductList(list));
  }, [filterInput, sortInput, allProductsList, reduxDispatch])


  useEffect(() => {
    const list = allProductsList.filter((product) => (product.title.toLowerCase().includes(searchInput.toLowerCase())));
    reduxDispatch(setProductList(list));
    console.log(searchInput);
    
  },[allProductsList,searchInput,reduxDispatch])

  return (
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
  )
}