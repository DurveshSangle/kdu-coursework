import { configureStore } from "@reduxjs/toolkit";
import ItemsListReducer from "./ItemListSlice"

export const store = configureStore({
  reducer: {
    itemList: ItemsListReducer
  }
});

export type RootState = ReturnType<typeof store.getState>;