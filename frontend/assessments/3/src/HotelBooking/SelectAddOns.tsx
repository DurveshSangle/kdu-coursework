import { CSSProperties } from "react";
import { headDivSyle, subHeadDivStyle } from "./HotelBooking";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../redux/store";
import { setSelectedAddOns } from "../redux/HotelBookingSlice";

const multiCheckStyle: CSSProperties = {
  margin: "30px 10px",
  padding: ".75rem 2rem",
  boxSizing: "border-box",
  border: "solid 1px #DDD",
  backgroundColor: "#FFF",
}

export function SelectAddOns() {
  const reduxDispatch: AppDispatch = useDispatch();

  const handleAddOn = (event: React.ChangeEvent) => {
    reduxDispatch(setSelectedAddOns(event.target.value))
  }

  const addOns = useSelector((state: RootState) => state.hotelBooking.addOns);

  return (
    <div>
      <div style={headDivSyle}>
        Select additional add ons / preferences
      </div>
      <div style={subHeadDivStyle}>
        {
          addOns.map((addOn) => {
            return (
              <>
                <input type="checkbox" id={addOn.name} name="add-ons" onChange={handleAddOn} value={addOn.cost}/>
                <label htmlFor={addOn.name} style={multiCheckStyle}>{addOn.name}</label>
              </>
            )
          })
        }
      </div>
    </div>
  )
}