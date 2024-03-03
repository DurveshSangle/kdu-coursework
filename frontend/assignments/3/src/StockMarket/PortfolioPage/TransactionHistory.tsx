import { VariableSizeList } from 'react-window';
import { useSelector } from 'react-redux';
import { RootState } from '../../redux/store';
import { Transaction } from '../../type/stokcMarket.type';

export function TransactionHistory() {
  // Select displayTransactions from Redux store
  const displayTransactions: Transaction[] = useSelector(
    (state: RootState) => state.portfolioTransactions.displayTransactions
  );

  // Ensure displayTransactions is not undefined or null before proceeding
  if (!displayTransactions) {
    return <div>Loading...</div>; // Handle loading state if displayTransactions is not available yet
  }

  // Function to group transactions by date
  const groupTransactionsByDate = (transactions: Transaction[]) => {
    const groupedTransactions: { [key: string]: Transaction[] } = {};
    transactions.forEach((transaction) => {
      if (!groupedTransactions[transaction.date]) {
        groupedTransactions[transaction.date] = [];
      }
      groupedTransactions[transaction.date].push(transaction);
    });
    return groupedTransactions;
  };
  const isSmallScreen = window.innerWidth <= 414;

  const groupedTransactions = groupTransactionsByDate(displayTransactions);

  // Sort the dates in ascending order
  const sortedDates = Object.keys(groupedTransactions).sort((a, b) => {
    return new Date(b).getTime() - new Date(a).getTime();
  });

  // Render transactions for each date
  const Row = ({ index, style }: { index: number, style: React.CSSProperties }) => {
    const date = sortedDates[index];
    const transactions = groupedTransactions[date];
    return (
      <div style={style}>
        <h3 style={{ paddingBottom: "10px", marginBottom: "5px", borderBottom: "1px dotted black", color: "#909091", fontSize: "1rem" }}>{date}</h3>
        {transactions.map((transaction, idx) => (
          <div
          key={idx}
          style={{
            display: "grid",
            gridTemplateColumns: isSmallScreen ? "1fr 1fr" : "1fr 1fr 1fr 1fr",
            gridTemplateRows: isSmallScreen ? "1fr 1fr" : "auto",
            marginBottom: "5px",
            opacity: transaction.filter ? "inherit" : "0.3",
          }}
        >
          <p>{transaction.stockName}</p>
          <p>{transaction.stockSymbol}</p>
          <p>₹{transaction.price}</p>
          <p>
            {transaction.time}{" "}
            {transaction.status === "Passed" ? (
              <span style={{ color: "green", marginLeft: "5px" }}>●</span>
            ) : (
              <span style={{ color: "red", marginLeft: "5px" }}>●</span>
            )}
          </p>
        </div>
        
        ))}
      </div>
    );
  };

  const getItemSize = (index:number) => {
    const date = sortedDates[index];
    const transactions = groupedTransactions[date];
    return (!isSmallScreen) ? 40 * transactions.length + 30: 100*transactions.length + 40;
  }

  return (
    <div style={{ padding: "20px", margin: "40px", fontSize: "1.3rem", fontWeight: "100" }}>
      {/* Render transactions grouped by date */}
      <VariableSizeList
        height={800} // Specify the height of the list
        itemCount={sortedDates.length} // Number of items in the list
        itemSize={getItemSize} // Specify the height of each item
        width="100%" // Specify the width of the list
      >
        {Row}
      </VariableSizeList>
    </div>
  );
}
