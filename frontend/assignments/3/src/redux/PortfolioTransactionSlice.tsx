import { PayloadAction, createSlice } from "@reduxjs/toolkit";
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
  stockNameList: []
}

// eslint-disable-next-line react-refresh/only-export-components
const PortfolioTransactionSlice = createSlice({
  name: "portfolioTransactionsSlice",
  initialState,
  reducers: {
    updatePortfolio: (state, action: PayloadAction<Transaction>) => {
      const portfolioList = state.portfolioTransactions;
      const updatedList = [...portfolioList, action.payload]
      state.portfolioTransactions = updatedList
      state.displayTransactions = [...updatedList];
    },
    setDisplayTransactions: (state, action:PayloadAction<Transaction[]>) => {
      state.displayTransactions = action.payload;
    }
  },
  extraReducers(builder) {
    builder.addCase(getPortfolioTransaction.fulfilled, (state, action:PayloadAction<Transaction[]>) => {
      state.portfolioTransactions = action.payload;
      state.displayTransactions = action.payload;
      const stocks:string[] = [];
      action.payload.forEach((transaction) => {
        if (!stocks.includes(transaction.stockName)) {
          stocks.push(transaction.stockName);
        }
      })
      state.stockNameList = stocks;
      state.fetchState = 'fulfilled';
    })
  }
})

export const { updatePortfolio, setDisplayTransactions } = PortfolioTransactionSlice.actions;
export default PortfolioTransactionSlice.reducer;