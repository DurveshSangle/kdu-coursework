import { PayloadAction, createSlice } from "@reduxjs/toolkit";

interface SnackBarState {
  message: string;
  open: boolean;
}

const initialState: SnackBarState = {
  message: "",
  open: false
}

// eslint-disable-next-line react-refresh/only-export-components
const SnackBarSlice = createSlice({
  name: "sncakBarSlice",
  initialState,
  reducers: {
    setMessage: (state,action:PayloadAction<string>) => {
      state.message = action.payload;
      state.open = true;
    },
    setOpen: (state, action: PayloadAction<boolean>) => {
      state.open = action.payload;
    }
  }
})

export const { setMessage, setOpen } = SnackBarSlice.actions;
export default SnackBarSlice.reducer;