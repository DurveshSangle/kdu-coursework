import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { Product } from "../type/kdumarket.types";
import { getProducts } from "../thunks/getProducts";

interface ProductsLoadState {
  allProductsList: Product[];
  state: "pending" | "fulfilled" | "rejected";
  error?: string;
  productsList: Product[];
}

const initialState: ProductsLoadState = {
  allProductsList: [],
  state: "pending",
  productsList: []
}

const productsLoadSlice = createSlice({
  name: "productsLoadSlice",
  initialState,
  reducers: {
    setProductList: (state, action:PayloadAction<Product[]>) => {
      state.productsList = action.payload;
    }
  },
  extraReducers(builder) {
    builder.addCase(getProducts.pending, (state) => {
      state.state = "pending";
    })
    builder.addCase(getProducts.fulfilled, (state, action: PayloadAction<Product[]>) => {    
      state.state = "fulfilled";
      state.allProductsList = action.payload;
      state.productsList = action.payload;
    })
    builder.addCase(getProducts.rejected, (state) => {
      state.state = "rejected";
      state.error = "Error while fetching data";
    })
  }
});

export const { setProductList } = productsLoadSlice.actions;
export default productsLoadSlice.reducer;

