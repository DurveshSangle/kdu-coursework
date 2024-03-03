import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { Stock } from "../type/stokcMarket.type";
import { getStocks } from "../thunks/GetStocks";
import { toSorted } from "../utility/SortStocksByName";

interface StockDataState {
  tab: number;
  stockFetchState: "pending" | "fulfilled" | "rejected";
  stocksList: Stock[];
  watchList: Stock[];
}

const initialState: StockDataState  = {
  tab: 1,
  stockFetchState: "pending",
  stocksList: [],
  watchList: []
}

// eslint-disable-next-line react-refresh/only-export-components
const StockDataSlice = createSlice({
  name: "landingPageSlice",
  initialState,
  reducers: {
    setTab: (state, action: PayloadAction<number>) => {
      state.tab = action.payload;
    },
    addStockToWatchList: (state, action: PayloadAction<Stock>) => {
      const updatedWatchList: Stock[] = [...state.watchList];
      updatedWatchList.push(action.payload);
      state.watchList = toSorted(updatedWatchList);
    },
    removeStockFromWatchList: (state, action: PayloadAction<Stock>) => {
      const stockToRemove = action.payload;
      const updatedWatchList: Stock[] = state.watchList.filter(stock => stock.stock_name !== stockToRemove.stock_name);
      state.watchList = toSorted(updatedWatchList);
    }
  },
  extraReducers(builder) {
    builder.addCase(getStocks.fulfilled, (state, action: PayloadAction<Stock[]>) => {
      state.stocksList = action.payload;
      state.stockFetchState = "fulfilled";
    })
  }
})

export const { setTab, addStockToWatchList, removeStockFromWatchList} = StockDataSlice.actions;
export default StockDataSlice.reducer;