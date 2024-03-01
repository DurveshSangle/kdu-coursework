import { CSSProperties, useEffect } from "react";
import { SelectAddOns } from "./SelectAddOns";
import { SelectDate } from "./SelectDate";
import { SelectRoom } from "./SelectRoom";
import { AppDispatch } from "../redux/store";
import { useDispatch } from "react-redux";
import { getRooms } from "../thunks/getRooms";
import { SubmitAndCost } from "./SubmitAndCost";

const divStyle: CSSProperties = {
  width: "100%",
  padding: "30px",
  textAlign: "center",
  boxSizing: "border-box",
}

const h1: CSSProperties = {
  fontSize: "2rem"
}

// eslint-disable-next-line react-refresh/only-export-components
export const headDivSyle: CSSProperties = {
  fontSize: "1.5rem",
  padding: "20px",
  backgroundColor: "#f08a5d",
  textAlign: "left",
  color: "white",
  margin: "20px"
}

export const subHeadDivStyle: CSSProperties = {
  textAlign: "left",
  marginLeft: "20px"
}

export function HotelBooking() {

  const reduxDispatch: AppDispatch = useDispatch();

  useEffect(() => {
    reduxDispatch(getRooms());
  },[reduxDispatch])


  return (
    <div style={divStyle}>
      <h1 style={h1}>Hotel Booking</h1>
      <SelectRoom />
      <SelectDate />
      <SelectAddOns />
      <SubmitAndCost />
    </div>
  )
}