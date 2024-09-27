import { CSSProperties, useRef } from "react"
import { setSortInput } from "../../redux/productSlice";
import { useDispatch } from "react-redux";


export function Sort() {
  const reduxDispatch = useDispatch();

  const sortRef = useRef<HTMLSelectElement>(null);

  const selectStyle: CSSProperties = {
    padding: "5px",
    fontSize: "15px"
  } 

  const handleSort = () => {
    if (sortRef.current) reduxDispatch(setSortInput(sortRef.current.value));
  }

  return (
    <div>
      <span>Sort: </span>
      <select name="" id="" style={selectStyle} ref={sortRef} onChange={handleSort}>
        <option value="">None</option>
        <option value="asc">ASC</option>
        <option value="desc">DESC</option>
      </select>
    </div>
  )
}
