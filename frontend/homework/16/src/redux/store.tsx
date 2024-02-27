import { Tuple,configureStore } from "@reduxjs/toolkit";
import ItemsListReducer from "./ItemListSlice";
import storage from "redux-persist/lib/storage";
import persistReducer from "redux-persist/es/persistReducer";
import { persistStore } from "redux-persist";
import { thunk } from "redux-thunk";


const persistConfig = {
  key: 'root',
  storage
};

const persistedReducer = persistReducer(persistConfig, ItemsListReducer);


export const Store = configureStore({
  reducer: {
    itemList: persistedReducer
  },
  middleware: () => new Tuple(thunk),
});

export const persistor = persistStore(Store);

export type RootState = ReturnType<typeof Store.getState>;