import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { Item } from "../type/ItemListProp";

interface ItemsListState{
  list: Item[];
  searchList: Item[];
  searchInput: string;
}

const initialState: ItemsListState = {
  list: [],
  searchList: [],
  searchInput: ""
}

const ItemsListSlice = createSlice({
  name: "ItemLister",
  initialState,
  reducers: {
    setList: (state, action: PayloadAction<Item[]>) => {
      state.list = action.payload;
    },
    setSearchList: (state, action: PayloadAction<Item[]>) => {
      state.searchList = action.payload;
    },
    setSearchInput: (state, action: PayloadAction<string>) => {
      state.searchInput = action.payload;
    },
  }
})

export const { setList, setSearchList, setSearchInput } = ItemsListSlice.actions;
export default ItemsListSlice.reducer;