import React, { CSSProperties } from "react"
import { headDivSyle, subHeadDivStyle } from "./HotelBooking"
import { useDispatch, useSelector } from "react-redux"
import { AppDispatch, RootState } from "../redux/store";
import { Room, setRoom } from "../redux/HotelBookingSlice";

const radioStyle: CSSProperties = {
  margin: "30px 10px",
  padding: ".75rem 2rem",
  boxSizing: "border-box",
  border: "solid 1px #DDD",
  backgroundColor: "#FFF",
}


export function SelectRoom() {

  const reduxDispatch:AppDispatch = useDispatch();

  const roomTypes = useSelector((state: RootState) => state.hotelBooking.roomTypes);
  

  const handleSelectRoom = (event: React.ChangeEvent) => {
    if (event.target.value) {
      const index: string = "roomTypes";
      roomTypes[index].forEach((room: Room) => {  
        if (room.id===Number.parseInt(event.target.value)) {
          reduxDispatch(setRoom(room));    
        }
      })
      // reduxDispatch(setRoom());
    }
  }

  return (
    <div>
      <div style={headDivSyle}>
        Select Room
      </div>
      <div style={subHeadDivStyle}>
        <input type="radio" id="single" name="room" value={1} onChange={handleSelectRoom}/>
        <label htmlFor="single" style={radioStyle}>Single Room</label>
        <input type="radio" id="twin" name="room" value={2} onChange={handleSelectRoom}/>
        <label htmlFor="twin" style={radioStyle}>Twin Room</label>
        <input type="radio" id="delux" name="room" value={3} onChange={handleSelectRoom}/>
        <label htmlFor="delux" style={radioStyle}>Delux</label>
        <input type="radio" id="suite" name="room" value={4} onChange={handleSelectRoom}/>
        <label htmlFor="suite" style={radioStyle}>Presidential Suite</label>
      </div>
    </div>
  )
}