import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { Transaction } from "../type/stokcMarket.type";
import { getPortfolioTransaction } from "../thunks/GetPortfolioTransactions";

interface PortfolioTransactionState {
  portfolioTransactions: Transaction[];
  fetchState: 'pending' | 'fulfilled' | 'rejected';
  displayTransactions: Transaction[];
  error?: string;
  stockNameList: string[];
}

const initialState: PortfolioTransactionState = {
  portfolioTransactions: [],
  fetchState: "pending",
  displayTransactions: [],
  stockNameList: [],
  error: undefined, // Initialize error as undefined
}

const PortfolioTransactionSlice = createSlice({
  name: "portfolioTransactionsSlice",
  initialState,
  reducers: {
    updatePortfolio: (state, action: PayloadAction<Transaction>) => {
      // Immutably update portfolioTransactions and displayTransactions
      state.portfolioTransactions = [...state.portfolioTransactions, action.payload];
      state.displayTransactions = [...state.portfolioTransactions, action.payload];
    },
    setDisplayTransactions: (state, action: PayloadAction<Transaction[]>) => {
      // Immutably set displayTransactions
      state.displayTransactions = action.payload;
    },
    setError: (state, action: PayloadAction<string>) => {
      // Set error state
      state.error = action.payload;
    }
  },
  extraReducers(builder) {
    builder.addCase(getPortfolioTransaction.fulfilled, (state, action: PayloadAction<Transaction[]>) => {
      // Immutably update portfolioTransactions, displayTransactions, and stockNameList
      state.portfolioTransactions = action.payload;
      state.displayTransactions = action.payload;
      const stocks: string[] = [];
      action.payload.forEach((transaction) => {
        if (!stocks.includes(transaction.stockName)) {
          stocks.push(transaction.stockName);
        }
      });
      state.stockNameList = stocks;
      state.fetchState = 'fulfilled';
    })
    builder.addCase(getPortfolioTransaction.rejected, (state, action) => {
      // Handle rejected promise, set fetchState to 'rejected' and update error
      state.fetchState = 'rejected';
      state.error = action.error.message ?? 'An error occurred';
    })
  }
});

export const { updatePortfolio, setDisplayTransactions, setError } = PortfolioTransactionSlice.actions;
export default PortfolioTransactionSlice.reducer;
