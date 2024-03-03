import { Alert, Snackbar } from "@mui/material";
import { StockInfoAndChart } from "./StockInfoAndChart";
import { StockTransactions } from "./StockTransactions";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import { setOpen } from "../../redux/SnackBarSlice";

export function StockPageMain() {
  const reduxDispatch = useDispatch();
  const snackbarMessage = useSelector((state: RootState) => state.snackBar.message);
  const snackbarOpen = useSelector((state: RootState) => state.snackBar.open);

  const handleCloseSnackbar = () => {
    reduxDispatch(setOpen(false));
  }

  return (
    <div style={{display:"grid", gridTemplateColumns:"3fr 1fr"}}>
      <StockInfoAndChart />
      <StockTransactions />
      <Snackbar open={snackbarOpen} autoHideDuration={3000} onClose={handleCloseSnackbar}>
        <Alert elevation={6} variant="filled" severity="error" onClose={handleCloseSnackbar}>
          {snackbarMessage}
        </Alert>
      </Snackbar>
    </div> 
  )
}