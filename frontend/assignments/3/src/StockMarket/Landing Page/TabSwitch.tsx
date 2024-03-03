import { Tab, Tabs } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { setTab } from "../../redux/StockDataSlice";
import { RootState } from "../../redux/store";

export function TabSwitch() {
  const reduxDispatch = useDispatch();
  const currentTab = useSelector((state:RootState) => state.stockData.tab);

  const handleTabChange = (_event:React.SyntheticEvent,value:number) => {
    reduxDispatch(setTab(value));
  }

  return (
    <div>
      <Tabs value={currentTab} onChange={handleTabChange} indicatorColor="primary">
        <Tab label="Explore" value={1} />
        <Tab label="My WatchList" value={2} />
      </Tabs>
    </div>
  )
}