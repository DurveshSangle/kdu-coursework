import { createContext,useEffect, useState } from "react";
import { IContext, Product, ProviderProps} from "./kdumarket.types";

export const GlobalContext = createContext<IContext>({
  allProductsList: [],
  setAllProductsList: () => { },
  productsList: [],
  setProductsList: () => { },
  filterInput: "",
  setFilterInput: () => { },
  sortInput: "",
  setSortInput: () => { },
  searchInput: "",
  setSearchInput: () => { }
})

export const GlobalProvider = ({ children }:ProviderProps) => {
  const [allProductsList, setAllProductsList] = useState<Product[]>([]);
  const [productsList, setProductsList] = useState<Product[]>([]);
  const [filterInput, setFilterInput] = useState<string>("");
  const [sortInput, setSortInput] = useState<string>("");
  const [searchInput, setSearchInput] = useState<string>("");

  useEffect(() => {
    fetch("https://fakestoreapi.com/products")
      .then((response) => response.json())
      .then((data) => {
        setAllProductsList(data);
        console.log(data);
      })
  }, [])

  return (
    <GlobalContext.Provider value={{ allProductsList, setAllProductsList, productsList, setProductsList, filterInput, setFilterInput, sortInput, setSortInput, searchInput, setSearchInput }}>
      {children}
    </GlobalContext.Provider>
  )
}