import { ProductGrid } from "./ProductGrid";

export function Main() {
  return (
    <div style={{display:"flex",flexDirection:"column",alignItems:"center", padding:"20px", width:"100%",boxSizing:"border-box", backgroundColor:"#f3f3f3"}}>
      <h1 style={{ fontSize: "3rem", color: "#292773" }}> <span style={{ color: "black" }}>KDU</span> MARKETPLACE</h1>
      <ProductGrid />
    </div>
  )
}