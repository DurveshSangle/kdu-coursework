import { useSelector } from "react-redux";
import { useNavigate, useParams } from "react-router-dom";
import { RootState } from "../../redux/store";

export function ProductDetailsMain() {
  const { productId } = useParams();

  const allProductsList = useSelector((state: RootState) => state.productsLoad.allProductsList);

  const navigate = useNavigate();

  const product = allProductsList.filter((product) => (product.id + "") === productId)[0];
  
  const handleGoBack = () => {
    navigate(-1);
  }

  return (
    <div style={{display:"flex", alignItems:"center", flexDirection:"column", height:"95vh",width:"100%", padding:"150px 20%",boxSizing:"border-box", backgroundColor:"#f3f3f3"}}>
      <h1 style={{fontSize:"2.5rem",margin:"20px", fontWeight:"700"}}>{ product?.title}</h1>
      <div style={{display:"grid", gridTemplateColumns:"1fr 1fr"}}>
        <img src={ product?.image} alt="" style={{width:"300px", margin:"auto"}}/>
        <div style={{display:"flex",flexDirection:"column", gap:"20px"}}>
          <p style={{ fontSize: "1.8rem", color:"#262369", fontWeight:"600" }}>Price : $ {product?.price}</p>
          <p style={{ fontSize: "1.8rem", color:"#262369", fontWeight:"600" }}>Rating : { product?.rating.rate} ({product?.rating.count})</p>
          <h3 style={{fontSize:"1.5rem", color:"#262369", fontWeight:"600"}}>Product Description:</h3>
          <p style={{fontSize:"1.5rem", color:"#262369", fontWeight:"100"}}>{ product?.description}</p>
          <button style={{width:"40%", padding:"5px", fontSize:"1.5rem"}} onClick={handleGoBack}>Back To Products</button>
        </div>
      </div>
    </div>
  )
}