import { CSSProperties } from "react";
import { headDivSyle, subHeadDivStyle } from "./HotelBooking";
import { useDispatch } from "react-redux";
import { AppDispatch } from "../redux/store";
import { setEndDate, setStartDate } from "../redux/HotelBookingSlice";

const dateStyle: CSSProperties = {
  padding: "10px"
}


export function SelectDate() {

  const reduxDispatch: AppDispatch = useDispatch();

  const handleStartDateChange = (event: React.ChangeEvent) => {
    reduxDispatch(setStartDate(event.target.value))
  }

  const handleEndDateChange = (event: React.ChangeEvent) => {
    reduxDispatch(setEndDate(event.target.value))
  }


  return (
    <div>
      <div style={headDivSyle}>Select Data</div>
      <div style={subHeadDivStyle}>
        <input type="date" id="startDate" name="startDate" style={dateStyle} onChange={handleStartDateChange}/>
        <input type="date" id="endDate" name="endDate" style={dateStyle} onChange={handleEndDateChange}/>
      </div>
    </div>
  )
}