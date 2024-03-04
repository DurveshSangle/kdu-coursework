import React, { CSSProperties } from 'react'
import { Item } from '../type/ItemListProp'
import { useDispatch, useSelector } from 'react-redux';
import { setSearchInput, setSearchList } from '../redux/ItemListSlice';
import { RootState } from '../redux/store';

const divStyles:CSSProperties = {
  display: "flex",
  flexDirection: "row",
  justifyContent: "space-around",
  alignItems: "center",
  width: "100%",
  backgroundColor: "#28a745"
}

const styles = {
  h1: {
      color: "white"
  },
  input: {
      width: "30%",
      padding: "5px 10px"
  }
}


export function Header() {
  const list = useSelector((state: RootState) => state.itemList.list)
  const searchInput = useSelector((state: RootState) => state.itemList.searchInput)

  const reduxDispatch = useDispatch();

  const searchItem = (event: React.ChangeEvent<HTMLInputElement>) => {
    const searchText: string = event.target.value.trim().toLowerCase(); // Trim whitespace and convert to lowercase for case-insensitive search
    const searchResultList = list.filter((item: Item) => {  
      return item.text.toLowerCase().includes(searchText);
    });
    reduxDispatch(setSearchInput(event.target.value))
    reduxDispatch(setSearchList(searchResultList));
  };

  return (
    <div style={divStyles}> 
        <h1 style={styles.h1}>Item Lister</h1>
        <input style={styles.input} type="text" placeholder='Search Items' value={searchInput} onChange={searchItem}/>
    </div>
  )
}
