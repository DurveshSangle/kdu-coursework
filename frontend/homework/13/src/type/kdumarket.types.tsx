import React from 'react'

export interface Product {
  id: number;
  title: string;
  price: number;
  description: string;
  category: string;
  image: string;
  rating: Rating;
}
  
interface Rating {
  rate: number;
  count: number;
}

//Used for searchInput, filterInput, sortInput
export interface IContext {
  allProductsList: Product[];
  setAllProductsList: React.Dispatch<React.SetStateAction<Product[]>>;
  productsList: Product[];
  setProductsList: React.Dispatch<React.SetStateAction<Product[]>>;
  filterInput: string;
  setFilterInput: React.Dispatch<React.SetStateAction<string>>;
  sortInput: string;
  setSortInput: React.Dispatch<React.SetStateAction<string>>;
  searchInput: string;
  setSearchInput: React.Dispatch<React.SetStateAction<string>>;
}

export interface ProviderProps {
  children: React.ReactNode;
}