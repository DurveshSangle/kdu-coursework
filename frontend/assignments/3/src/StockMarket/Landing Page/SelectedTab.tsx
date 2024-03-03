import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import { Pagination, Stack, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";
import Paper from '@mui/material/Paper';
import { Stock } from "../../type/stokcMarket.type";
import { CSSProperties, useState } from "react";
import CancelIcon from '@mui/icons-material/Cancel';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import AddCircleOutlineOutlinedIcon from '@mui/icons-material/AddCircleOutlineOutlined';
import { addStockToWatchList, removeStockFromWatchList } from "../../redux/StockDataSlice";
import { Link } from "react-router-dom";

const tableContainerStyle: CSSProperties = {
  width: "70%",
  margin: "auto"
}

export function SelectedTab() {
  const reduxDispatch = useDispatch();
  const tab = useSelector((state: RootState) => state.stockData.tab);
  const stockList = useSelector((state: RootState) => state.stockData.stocksList);
  const watchList = useSelector((state: RootState) => state.stockData.watchList);

  //Pagination logic
  const [currentPage, setCurrentPage] = useState(1);
  let dataList:Stock[];
  if (tab === 1) {
    dataList = stockList;
  }
  else {
    dataList = watchList;
  }
  const itemsPerPage = 10;
  const numberOfPages = Math.ceil(dataList.length / itemsPerPage) + (dataList.length % itemsPerPage == 0 ? -1 : 0);
  
  const startIndex = (currentPage - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const handlePageChange = (_event: unknown, page:number) => {
    setCurrentPage(page);
  };
  const displayedData = dataList.slice(startIndex, endIndex);

  //Add, remove, tick watchList icons logic
  const [currentlyHovered, setCurrentlyHovered] = useState("");
  const handleAddToWatchList = (stock:Stock) => {
    reduxDispatch(addStockToWatchList(stock));
  }
  const handleRemoveFromWatchList = (stock: Stock) => {
    reduxDispatch(removeStockFromWatchList(stock));
  }

  return (
    <div style={tableContainerStyle}>
      <TableContainer component={Paper}>
      <Table aria-label="simple table"> 
        <TableHead>
          <TableRow sx={{ 'td, th': { borderBottom: "2px solid black" } }}>
            <TableCell sx={{ width: '70%' }} align="left">Company</TableCell>
            <TableCell align="right">Base Price</TableCell>
            <TableCell align="right">Watchlist</TableCell>
          </TableRow>
        </TableHead>
        <TableBody >
          {displayedData.map((stock:Stock) => (
            <TableRow
              component={Link}
              to={`/stock/${stock.stock_name}`}
              key={stock.stock_symbol}
              sx={{ '&:last-child td, &:last-child th': { border: 0 }}}
            >
              <TableCell sx={{ width: '70%' }} align="left">{stock.stock_name}</TableCell>
              <TableCell align="right">{stock.base_price}</TableCell>
              <TableCell align="right"
                onClick={(watchList.includes(stock)) ? () => handleRemoveFromWatchList(stock) : () => handleAddToWatchList(stock)}
                onMouseEnter={() => setCurrentlyHovered(stock.stock_name)}
                onMouseLeave={() => setCurrentlyHovered("")}>
                {
                  (watchList.includes(stock)) ? 
                    (stock.stock_name === currentlyHovered ? <CancelIcon /> :<CheckCircleIcon/>) :
                    (<AddCircleOutlineOutlinedIcon/>)
                }
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
        </Table>
        <Stack spacing={2} style={{alignItems:"center", padding:"5px"}}>
          <Pagination count={numberOfPages} page={currentPage} onChange={handlePageChange}
            sx={{'& .MuiPaginationItem-page.Mui-selected': {outline: '2px solid #1976d2'} }}/>
        </Stack>
      </TableContainer>
    </div>
  )
}
