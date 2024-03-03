import { useSelector } from "react-redux";
import { SelectedTab } from "./SelectedTab";
import { TabSwitch } from "./TabSwitch";
import { RootState } from "../../redux/store";
import { CircularProgress } from "@mui/material";


export function LandingPageMain() {
  

  const stockFetchState = useSelector((state: RootState) => state.stockData.stockFetchState);

  return (
  <>
      {(stockFetchState === "pending") ? <CircularProgress /> : 
        <>
        <TabSwitch />
          <SelectedTab />
        </>
      }
      </>
  )
}