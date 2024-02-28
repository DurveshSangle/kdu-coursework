import {PayloadAction, createSlice } from "@reduxjs/toolkit";
import { getProducts } from "../thunks/getProducts";

interface SnackbarState {
  message: string;
  show: boolean;
}

const initialState: SnackbarState = {
  message: "",
  show: false
}

const snackbarSlice = createSlice({
  name: "snackbar",
  initialState,
  reducers: {
    setShowToFalse: (state) => {
      state.show = false
    },
    setMessage: (state, action:PayloadAction<string>) => {
      state.message = action.payload;
    }
  },
  extraReducers(builder) {
    builder.addCase(getProducts.fulfilled, (state) => {
      state.message = "Successfully fetched the products"
      state.show = true;
    })
    builder.addCase(getProducts.rejected, (state) => {
      state.message = "Error fetching the products"
      state.show = true;
    })
  }
})

export const {setShowToFalse,setMessage} = snackbarSlice.actions
export default snackbarSlice.reducer;