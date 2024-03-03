import { Filter } from "./Filter";
import { TransactionHistory } from "./TransactionHistory";


export function PortfolioPageMain() {
  return (
    <div style={{display:"grid", gridTemplateColumns:"1fr 2fr"}}>
      <Filter />
      <TransactionHistory />
    </div>
  )
}

