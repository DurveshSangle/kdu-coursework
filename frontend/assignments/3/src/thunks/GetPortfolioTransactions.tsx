import { createAsyncThunk } from "@reduxjs/toolkit";
import { Transaction } from "../type/stokcMarket.type";

export const getPortfolioTransaction = createAsyncThunk("getPortfolioTransaction", async (url: string) => {
  try {
    const response = await fetch(url);
    const data: Transaction[] = await response.json();
    console.log(data);
    return data;
  }
  catch (e) {
    console.log("Error fetching portfolio transaction data" + e);
    throw e;
  }
})