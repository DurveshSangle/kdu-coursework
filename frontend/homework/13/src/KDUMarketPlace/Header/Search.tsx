import { CSSProperties, useContext, useRef } from "react";
import { GlobalContext } from "../../type/ContextProvider";
import { useNavigate } from "react-router-dom";


export function Search() {
  const { setSearchInput } = useContext(GlobalContext);
  const searchBarRef = useRef<HTMLInputElement>(null);

  const navigate = useNavigate();

  const handleSearch = () => {
    navigate("/")
    if (searchBarRef.current) {
      setSearchInput(searchBarRef.current.value)
    }
  }

  const handleCancel = () => {
    if (searchBarRef.current) {
      searchBarRef.current.value = '';
      setSearchInput(searchBarRef.current.value)    
    }
  }

  const inputStyle: CSSProperties = {
    padding: "5px",
    fontSize: "15px"
  }

  const imageStyle: CSSProperties = {
    width: "20px"
  }

  const searchStyle: CSSProperties = {
    display: "flex",
    alignItems: "center"
  }

  return (
    <div style={searchStyle}>
      <input type="text" placeholder="Search.." ref={searchBarRef} style={inputStyle}/>
      <button onClick={handleSearch}>
        <img src="./../../../../public/search.svg" alt="" style={imageStyle}/>
      </button>
      <button style={{padding:"5px"}} onClick={handleCancel}>X</button>
    </div>
  )
}

