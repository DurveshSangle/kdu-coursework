import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import Snackbar from '@mui/material/Snackbar';
import { SnackbarContent } from "@mui/material";
import { setShowToFalse } from "../../redux/snackbarSlice";

export function SnackbarCompo() {
  const messages = useSelector((state: RootState) => state.snackbar.message);
  const show = useSelector((state: RootState) => state.snackbar.show);

  const productStates = useSelector((state: RootState) => state.productsLoad);

  const reduxDispatch = useDispatch();

  
  const palette = {
    primary: {
      backgroundColor: "green"
    },
    secondary: {
      backgroundColor: "red" 
    }
  }

  return (
    <Snackbar
      open={show}
      autoHideDuration={5000}
      onClose={()=>reduxDispatch(setShowToFalse())}
      anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}>
      <SnackbarContent style={productStates.state === 'fulfilled' ? palette.primary : palette.secondary} message={messages} />
    </Snackbar>
  )
}