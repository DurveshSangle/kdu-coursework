import { useSelector } from "react-redux"
import { RootState } from "../redux/store"
import { CSSProperties } from "react"

export function SubmitAndCost() {
  const cost = useSelector((state:RootState)=> (state.hotelBooking.cost))

  const roomState = useSelector((state: RootState) => (state.hotelBooking.roomState))
  
  const startDateState = useSelector((state: RootState) => (state.hotelBooking.startDateState))
  
  const endDateState = useSelector((state:RootState)=> (state.hotelBooking.endDateState))

  const styleCost: CSSProperties = {
    textAlign: "left",
    fontSize: "1.3rem",
    marginTop: "20px",
    marginLeft:"25px"
  }

  const styleBtn: CSSProperties = {
    padding: "20px 40px",
    backgroundColor: "#f08a5d",
    border: "0",
    marginTop: "10px",
    color:"white"
  } 
  
  const totalCost = cost + (cost * 18 / 100);

  return (
    <div>
      <div style={styleCost}>
        Cost + 18% GST: {totalCost}
      </div>
      <div style={styleCost}>
        <button style={{ ...styleBtn}} disabled={roomState && startDateState && endDateState}>Submit</button>
      </div>
    </div>
  )
}