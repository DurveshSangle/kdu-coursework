import { useContext } from "react"

import { ProductTile } from "./ProductTile";
import { GlobalContext } from "../../type/ContextProvider";

export function ProductGrid() {
  const { productsList } = useContext(GlobalContext);

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