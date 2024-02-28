import { useSelector } from "react-redux";
import { ProductGrid } from "./ProductGrid";
import { SnackbarCompo } from "./Snackbar";
import { RootState } from "../../redux/store";
import { CircularProgress } from "@mui/material";

export function Main() {
  const productStates = useSelector((state: RootState) => state.productsLoad.state);
  return (
    <div style={{display:"flex",flexDirection:"column",alignItems:"center", padding:"20px", width:"100%",boxSizing:"border-box", backgroundColor:"#f3f3f3"}}>
      <h1 style={{ fontSize: "3rem", color: "#292773" }}> <span style={{ color: "black" }}>KDU</span> MARKETPLACE</h1>
      {productStates === 'pending' ? (<div style={{height:"300px", marginTop:"200px"}}><CircularProgress /></div>) :
      (
        <>
          <ProductGrid />
          <SnackbarCompo />
        </>
      )}
      
    </div>
  )
}