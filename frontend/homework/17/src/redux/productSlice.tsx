import { PayloadAction, createSlice } from "@reduxjs/toolkit";

interface ProductsState {
  filterInput: string;
  sortInput: string;
  searchInput: string;
}

const initialState: ProductsState = {
  filterInput: "",
  sortInput: "",
  searchInput: ""
}

const productSlice = createSlice({
  name: "products",
  initialState,
  reducers: {
    setFilterInput: (state,action:PayloadAction<string>) => {
      state.filterInput = action.payload;
    },
    setSortInput: (state,action:PayloadAction<string>) => {
      state.sortInput = action.payload;
    },
    setSearchInput: (state,action:PayloadAction<string>) => {
      state.searchInput = action.payload;
    }
  }
})

export const { setFilterInput, setSortInput, setSearchInput } = productSlice.actions;
export default productSlice.reducer;