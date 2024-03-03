import { configureStore } from "@reduxjs/toolkit";
import StockDataReducer from "./StockDataSlice";
import SnackBarReducer from "./SnackBarSlice";
import PortfolioTransactionsReducer from "./PortfolioTransactionSlice";
import HistoryAndNotificationReducer from "./HistoryNotificationSlice";

export const store = configureStore({
  reducer: {
    stockData: StockDataReducer,
    snackBar: SnackBarReducer,
    portfolioTransactions: PortfolioTransactionsReducer,
    historyAndNotifications: HistoryAndNotificationReducer
  }
})

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch