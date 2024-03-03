import { useSelector } from "react-redux";
import { Transaction } from "../../type/stokcMarket.type";
import { RootState } from "../../redux/store";

export function TransactionHistory() {
  
  console.log("Re-Rendered!!!");

  const displayTransactions:Transaction[] = useSelector((state: RootState) => state.portfolioTransactions.displayTransactions);

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

  const groupedTransactions = groupTransactionsByDate(displayTransactions);

  // Sort the dates in ascending order
  const sortedDates = Object.keys(groupedTransactions).sort((a, b) => {
    return new Date(b) - new Date(a);
  });

  return (
    <div style={{padding:"20px",margin:"40px", fontSize:"1.3rem", display:"flex", flexDirection:"column", gap:"20px", fontWeight:"100"}}>
      {sortedDates.map((date) => (
        <div key={date}>
          <h3 style={{paddingBottom:"10px",marginBottom:"5px", borderBottom:"1px dotted black", color:"#909091", fontSize:"1rem"}}>{date}</h3>
          <div>
            {groupedTransactions[date].map((transaction, index) => (
              <div key={index} style={{display:"grid", gridTemplateColumns:"1fr 1fr 1fr 1fr", marginBottom:"5px"}}>
                <p>{transaction.stockName}</p>
                <p>{transaction.stockSymbol}</p>
                <p>₹{transaction.price}</p>
                <p>{transaction.time} {(transaction.status === 'Passed')?<span style={{color: 'green', marginLeft: '5px'}}>●</span>:<span style={{color: 'red', marginLeft: '5px'}}>●</span>}</p>
              </div>
            ))}
          </div>
        </div>
      ))}
    </div>
  );
}
