import { configureStore } from "@reduxjs/toolkit";
import StockDataReducer from "./StockDataSlice";
import SnackBarReducer from "./SnackBarSlice";
import PortfolioTransactionsReducer from "./PortfolioTransactionSlice";

export const store = configureStore({
  reducer: {
    stockData: StockDataReducer,
    snackBar: SnackBarReducer,
    portfolioTransactions: PortfolioTransactionsReducer
  }
})

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch