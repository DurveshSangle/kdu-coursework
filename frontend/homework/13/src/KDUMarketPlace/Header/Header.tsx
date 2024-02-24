import { CSSProperties } from "react";
import { Filter } from "./Filter";
import { Search } from "./Search";
import { Sort } from "./Sort";
import { useLocation } from "react-router-dom";

export function Header() {
  const headerStyle: CSSProperties = {
    backgroundColor: "#2a2a72",
    display: "flex",
    flexDirection: "row",
    justifyContent: "space-between",
    width: "100%",
    padding: "10px 30px",
    alignItems: "center",
    boxSizing:"border-box"
  }

  const style: CSSProperties = {
    display: "flex",
    flexDirection: "row",
    color: "white",
    fontSize: "20px",
    gap: "10px"
  }

  const location = useLocation();
  const hideFilterAndSort = location.pathname.includes("/product"); // Adjust the route path as needed

  return (
    <div style={headerStyle}>
      <div>
        <Search />
      </div>
      {!hideFilterAndSort && ( // Conditionally render Filter and Sort components
        <div style={style}>
          <Filter />
          <Sort />
        </div>
      )}
    </div>
  )
}
