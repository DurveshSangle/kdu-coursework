import { createAsyncThunk } from "@reduxjs/toolkit";
import { Stock } from "../type/stokcMarket.type";

export const getStocks = createAsyncThunk("getStocks", async (url:string) => {
  try {
    const response = await fetch(url);
    const data: Stock[] = await response.json();
    return data;
  }
  catch (e) {
    console.log("Error");
    throw e;
  }
})

