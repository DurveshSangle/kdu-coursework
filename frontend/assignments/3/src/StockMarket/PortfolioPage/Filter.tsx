import { DatePicker, LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import { useEffect, useState } from "react";
import { setDisplayTransactions } from "../../redux/PortfolioTransactionSlice";
import { Transaction } from "../../type/stokcMarket.type";

export function Filter() {
  const stockNameList = useSelector((state: RootState) => state.portfolioTransactions.stockNameList);
  const transactionHistory: Transaction[] = useSelector((state: RootState) => state.portfolioTransactions.portfolioTransactions)
  const reduxDispatch = useDispatch();

  const [searchQuery, setSearchQuery] = useState<string | null>(null);
  const [startDate, setStartDate] = useState<string | null>(null);
  const [endDate, setEndDate] = useState<string | null>(null);
  const [statusFilters, setStatusFilters] = useState<string[]>([]);
  const [selectedStocks, setSelectedStocks] = useState<string[]>([]);

  useEffect(() => {
    const filteredTransactions:Transaction[] = transactionHistory.map(transaction => {
      // Create a copy of the transaction object
      const updatedTransaction = { ...transaction };
    
      // Filter by search query
      if (searchQuery && !updatedTransaction.stockName.toLowerCase().includes(searchQuery.toLowerCase())) {
        return null; // Return null for transactions that don't match the search query
      }
    
      // Filter by start date
      if (startDate && new Date(updatedTransaction.date) < new Date(startDate)) {
        return null; // Return null for transactions that occurred before the start date
      }
    
      // Filter by end date
      if (endDate && new Date(updatedTransaction.date) > new Date(endDate)) {
        return null; // Return null for transactions that occurred after the end date
      }
    
      // Filter by status
      if (statusFilters.length > 0 && !statusFilters.includes(updatedTransaction.status)) {
        return null; // Return null for transactions that don't match the status filter
      }
    
      // Filter by selected stocks
      updatedTransaction.filter = selectedStocks.includes(updatedTransaction.stockName)
      
      if (selectedStocks.length === 0) updatedTransaction.filter = true;
    
      return updatedTransaction; // Return the updated transaction object
    }).filter(Boolean); // Remove null values from the array
    
    reduxDispatch(setDisplayTransactions(filteredTransactions));    
  }, [searchQuery,startDate, endDate, statusFilters, selectedStocks, reduxDispatch, transactionHistory])
  
  const handleStatusChange = (status: string) => {
    const updatedStatusFilters = statusFilters.includes(status)
      ? statusFilters.filter(filter => filter !== status)
      : [...statusFilters, status];
    setStatusFilters(updatedStatusFilters);
  };

  const handleStockChange = (stockName: string) => {
    const updatedSelectedStocks = selectedStocks.includes(stockName)
      ? selectedStocks.filter(stock => stock !== stockName)
      : [...selectedStocks, stockName];
    setSelectedStocks(updatedSelectedStocks);
  };

  return (
    <div style={{margin:"50px auto", border:"1px solid black", borderRadius:"20px", backgroundColor:"#e9ecef", height:"700px"}}>
      <div style={{display:"flex", fontSize:"1.5rem", flexDirection:"row", justifyContent:"space-between", borderBottom:"1px solid black", padding:"10px"}}>
        <h2>Filters</h2>
        <button style={{border:"0", backgroundColor:"transparent", fontSize:"1.3rem"}}>Clear All</button>
      </div>
      <div style={{padding:"10px", borderBottom:"1px solid black"}}>
        <input type="search" placeholder="Search for a stock" style={{padding:"10px", fontSize:"1.2rem"}} onChange={e => setSearchQuery(e.target.value)}/>
      </div>
      <div style={{padding:"10px", borderBottom:"1px solid black"}}>
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <DatePicker label="Start date" sx={{width:"150px"}} value={startDate} onChange={date => setStartDate(date)}/>
          <DatePicker label="End date" sx={{width:"150px"}} value={endDate} onChange={date => setEndDate(date)}/>
        </LocalizationProvider>
      </div>
      <div style={{display:"flex", flexDirection:"column", alignItems:"start", padding:"10px", fontSize:"1.2rem", borderBottom:"1px solid black"}}>
        <div style={{margin:"3px"}}>
          <input type="checkbox" name="status" id="passed" style={{marginRight:"10px"}} onChange={() => handleStatusChange('Passed')}/>
          <label htmlFor="passed">Passed</label>
        </div>
        <div style={{margin:"3px"}}>
          <input type="checkbox" name="status" id="failed" style={{ marginRight: "10px" }} onChange={() => handleStatusChange('Failed')}/>
          <label htmlFor="failed">Failed</label>
        </div>
      </div>
      <div style={{display:"flex", flexDirection:"column", alignItems:"start", padding:"10px", fontSize:"1.2rem", height:"400px", overflow:"scroll", overflowX:"hidden"}}>
        {stockNameList.map((stock) => (
          <div key={stock} style={{margin:"3px"}}>
            <input type="checkbox" name="stock" id={stock} style={{marginRight:"10px"}} onChange={() => handleStockChange(stock)}/>
            <label htmlFor={stock}>{stock}</label>
          </div>
        ))}
      </div>
    </div>
  )
}