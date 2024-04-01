import { CSSProperties, useContext, useEffect, useRef} from "react"
import { GlobalContext } from "../../type/ContextProvider";
import { useLocation } from "react-router-dom";

export function Filter() {
  const { setFilterInput } = useContext(GlobalContext);

  const filterRef = useRef<HTMLSelectElement>(null);

  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const filter = searchParams.get('filter');
  
  useEffect(() => {
    if (filter) {
      setFilterInput(filter);
      if (filterRef.current) filterRef.current.value = filter;
    }
  }, [filter, setFilterInput]);
  const handleFilter = () => {
    if (filterRef.current) setFilterInput(filterRef.current.value);
  }
 
  const selectStyle: CSSProperties = {
    padding: "5px",
    fontSize: "15px"
  } 
  return (
    <div> 
      <span>Filter: </span>
      <select name="filter" ref={filterRef} onChange={handleFilter} style={selectStyle}>
        <option value="">None</option>
        <option value="men's clothing">men's clothing</option>
        <option value="women's clothing">women's clothing</option>
        <option value="electronics">electronics</option>
        <option value="jewelery">jewelery</option>
      </select>
    </div>
  )
}
