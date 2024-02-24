import { Link } from "react-router-dom";
import { Product } from "../../type/kdumarket.types";

export function ProductTile({product}:Readonly<{product:Product}>) {
  return (
    <Link to={`/product/${product.id}`} style={{textDecoration:"none", color:"black"}}>
      <div style={{display:"flex",flexDirection:"column", alignItems:"center", height:"350px",padding:"10px", backgroundColor:"#ffffff", margin:"15px", justifyContent:"space-around"}}>
        <img src={product.image} alt={product.title} style={{ height: "200px", width:"150px"}} />
        <h2 style={{ fontSize: "1.5rem", textAlign: "center" }}>{product.title}</h2>
        <h2 style={{fontSize:"1.2rem", textAlign:"center", color:"#474483"}}>{product.category}</h2>
        <div style={{display:"flex", justifyContent:"space-around", width:"100%"}}>
          <span style={{fontSize:"1.3rem", backgroundColor:"gold", padding:"5px", borderRadius:"10px"}}>{product.rating.rate}</span>  
          <span style={{fontSize:"1.5rem", padding:"5px", color:"#474483", fontWeight:"600"}}>$ {product.price}</span>
        </div>
      </div>
    </Link>
  )
}