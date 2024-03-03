import { Filter } from "./Filter";
import { TransactionHistory } from "./TransactionHistory";

export function PortfolioPageMain() {
  const isSmallScreen = window.innerWidth <= 414;

  return (
    <div style={{margin: "30px auto" }}>
      {isSmallScreen ? (
        <div>
          <Filter />
          <TransactionHistory />
        </div>
      ) : (
        <div style={{ display: "grid", gridTemplateColumns: "1fr 2fr" }}>
          <Filter />
          <TransactionHistory />
        </div>
      )}
    </div>
  );
}
