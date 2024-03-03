import { useEffect, useState } from 'react';
import { socket } from "../socket"
import { StockTransaction, Transaction } from '../../type/stokcMarket.type';
import { useDispatch } from 'react-redux';
import { setMessage} from '../../redux/SnackBarSlice';
import { updatePortfolio } from '../../redux/PortfolioTransactionSlice';

export function StockTransactions() {
  const [transactions, setTransactions] = useState<StockTransaction[]>([]);
  const [notifications, setNotifications] = useState<StockTransaction[]>([]);

  const reduxDispatch = useDispatch();

  useEffect(() => {
    socket.on('buyHistory', (transactionData) => {
      // Update the transactions state with the new transaction data
      transactionData.type = "Buy";
      setTransactions(prevTransactions => [...prevTransactions, transactionData]);
    });

    socket.on('sellHistory', (transactionData) => {
      // Update the transactions state with the new transaction data
      transactionData.type = "Sell";
      setTransactions(prevTransactions => [...prevTransactions, transactionData]);
    });

    socket.on('notification', (notificationData) => {
      setNotifications(prevNotifications => [...prevNotifications, notificationData]);
    });

    socket.on('insufficientBalance', (message) => {
      reduxDispatch(setMessage(message));
    });

    socket.on('insufficientHoldings', (message) => {
      reduxDispatch(setMessage(message));
    })

    socket.on("transaction", (transactionData: Transaction) => {
      reduxDispatch(updatePortfolio(transactionData));
    })

    return () => {
      socket.off('buyHistory');
      socket.off('notification');
      socket.off('sellHistory');
      socket.off('insufficientBalance');
      socket.off('insufficientHoldings')
    };
  }, [reduxDispatch]);

  return (
    <div>
      <div style={{ padding: "10px", paddingTop:"0",  margin: "20px", border: "1px solid black", height: "400px", overflow: "scroll", overflowX: "hidden", position: "relative" }}>
        <h2 style={{ fontSize: "2rem", padding:"10px", position: "sticky", top: 0, backgroundColor: "white", zIndex: 1 }}>History</h2>
        <div>
          {transactions.map((transaction) => (
            <div key={transaction.timestamp} style={{ display: "flex", flexDirection: "row", justifyContent: "space-between", margin: "10px", fontSize: "1.3rem", border: "1px solid black", padding: "10px" }}>
              <div>
                <div>
                    {transaction?.quantity} Stocks
                </div>
                <div>
                    {transaction?.timestamp}
                </div>
              </div>
              <div style={{color:(transaction.type==='Buy'?'green':'red')}}>
                {transaction.type}
              </div>
            </div>
          ))}
        </div>
      </div>
      <div style={{padding:"10px", margin:"20px", border:"1px solid black", height:"400px", overflow:"scroll", overflowX:"hidden"}}>
        {notifications.map((notification) => (
          <div key={notification.time} style={{ margin:"10px", fontSize:"1.3rem", padding:"10px"}}>
            <div style={{fontSize:"1.5rem"}}>
              {notification.message}
            </div>
            <div style={{fontSize:"1rem", marginTop:"5px"}}>
              {notification.time}
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}
