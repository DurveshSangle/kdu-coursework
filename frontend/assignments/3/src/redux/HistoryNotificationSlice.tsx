import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { StockTransaction } from "../type/stokcMarket.type";

interface HistoryAndNotificationState {
  transactions: Record<string, StockTransaction[]>;
  notifications: Record<string, StockTransaction[]>;
}

const initialState: HistoryAndNotificationState = {
  transactions: {},
  notifications: {}
}

const HistoryAndNotificationSlice = createSlice({
  name: "historyAndNotifications",
  initialState,
  reducers: {
    setTransactions: (state, action: PayloadAction<Record<string, StockTransaction[]>>) => {
      state.transactions = action.payload;
    },
    setNotification: (state, action: PayloadAction<Record<string, StockTransaction[]>>) => {
      state.notifications = action.payload;
    }
  }
})

export const { setTransactions, setNotification } = HistoryAndNotificationSlice.actions;
export default HistoryAndNotificationSlice.reducer;
