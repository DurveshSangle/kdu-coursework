import { CSSProperties, useContext, useRef } from "react"
import { GlobalContext } from "../../type/ContextProvider";


export function Sort() {
  const { setSortInput} = useContext(GlobalContext);

  const sortRef = useRef<HTMLSelectElement>(null);

  const selectStyle: CSSProperties = {
    padding: "5px",
    fontSize: "15px"
  } 

  const handleSort = () => {
    if (sortRef.current) setSortInput(sortRef.current.value);
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
