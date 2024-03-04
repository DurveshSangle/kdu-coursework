import { configureStore } from "@reduxjs/toolkit";
import HotelBookingReducer from "./HotelBookingSlice";

export const store = configureStore({
  reducer: {
    hotelBooking: HotelBookingReducer
  }
})

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;