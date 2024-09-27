import { RootState } from "../../redux/store";
import { ProductTile } from "./ProductTile";
import { useSelector } from 'react-redux';


export function ProductGrid() {
  const productsList = useSelector((state: RootState) => state.productsLoad.productsList);
  console.log("NEW" + productsList);
  
  
 
  return (
    <div style={{display:"grid",gridTemplateColumns:"1fr 1fr 1fr 1fr", margin: "40px"}}>
      {
        productsList.map((product) => {
          return (
            <ProductTile key={product.id} product={product}/>
          )
        })
      }
    </div>
  )
}