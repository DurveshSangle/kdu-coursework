import { configureStore } from "@reduxjs/toolkit";
import productSlice from "./productSlice";
import productsLoadSlice from "./productsLoadSlice";
import snackbarSlice from "./snackbarSlice";

export const store = configureStore({
  reducer: {
    products: productSlice,
    productsLoad: productsLoadSlice,
    snackbar: snackbarSlice
  }
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;