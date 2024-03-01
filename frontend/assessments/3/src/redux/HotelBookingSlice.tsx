import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { getRooms } from "../thunks/getRooms";


interface HotelBookingState {
  roomTypes: Room[];
  fetchState: "fulfilled" | "pending" | "rejected";
  error?: string;
  addOns: AddOn[];
  startDate?: string;
  endDate?: string;
  room?: Room;
  selectedAddOn: string[];
  cost: number;
  roomState: boolean;
  startDateState: boolean;
  endDateState: boolean;
}

export interface Room {
  id: number;
  name: string;
  costPerNight: string;
  currency: string;
  addOns: AddOn[];
}

interface AddOn {
  name: string;
  cost: string;
  currency: string;
}

const initialState: HotelBookingState = {
  roomTypes: [],
  fetchState: "pending",
  addOns: [],
  selectedAddOn: [],
  cost: 0,
  roomState: false,
  startDateState: false,
  endDateState: false
}

const HotelBookingSlice = createSlice({
  name: "hotelBooking",
  initialState,
  reducers: {
    setRoom: (state, action: PayloadAction<Room>) => {
      state.room = action.payload;
      state.selectedAddOn = [];
      state.cost = Number.parseInt(action.payload.costPerNight);
      state.addOns = action.payload.addOns;
      state.roomState = true;
    },
    setSelectedAddOns: (state, action: PayloadAction<string>) => {
      console.log(action.payload);
      state.selectedAddOn.push(action.payload);
      state.cost += Number.parseInt(action.payload);
    },
    setStartDate: (state, action: PayloadAction<string>) => {
      state.startDate = action.payload;
      console.log(action.payload);
      state.startDateState = true;

      if (state.startDateState && state.endDate) {
        const date1: string[] = state.startDate.split("-");
        const date2: string[] = state.endDate.split("-");
        const day1: number = Number.parseInt(date1[2]);
        const day2: number = Number.parseInt(date2[2]);
        state.cost = state.cost * (day2 - day1);
      }

    },
    setEndDate: (state, action: PayloadAction<string>) => {
      state.endDate = action.payload;
      state.endDateState = true;

      if (state.startDate && state.endDateState) {
        const date1: string[] = state.startDate.split("-");
        const date2: string[] = state.endDate.split("-");
        const day1: number = Number.parseInt(date1[2]);
        const day2: number = Number.parseInt(date2[2]);
        state.cost = state.cost * (day2 - day1);
      }
    }
  },
  extraReducers(builder) {
    builder.addCase(getRooms.pending, (state) => {
      state.fetchState = "pending";
    })
    builder.addCase(getRooms.fulfilled, (state, action: PayloadAction<Room[]>) => {
      console.log(action.payload);
      state.fetchState = "fulfilled";
      state.roomTypes = action.payload;      
    })
    builder.addCase(getRooms.rejected, (state) => {
      state.fetchState = "rejected";
      state.error = "Error fethcing data from API"
    })
  }
})


export default HotelBookingSlice.reducer;
export const { setRoom, setSelectedAddOns, setStartDate, setEndDate } = HotelBookingSlice.actions;