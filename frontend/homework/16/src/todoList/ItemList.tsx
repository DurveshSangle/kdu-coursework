import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../redux/store';
import { Item } from '../type/ItemListProp';
import { setList } from '../redux/ItemListSlice';
import { CSSProperties } from 'react';

const h2 = {
  fontWeight: "500",
  marginLeft: "10px"
}

const ul = {
  paddingLeft: "10px"
}

const p = {
  marginLeft: "10px"
}

const li = {
  listStyleType: "none"
}

const button = {
  backgroundColor: "#dc3545",
  color: "white",
  border: "0",
  padding: "10px"
}

const liDiv:CSSProperties = {
  display: "flex",
  flexDirection: "row",
  justifyContent: "space-between",
  border: "1px solid #eeeeee",
  padding: "10px 20px",
  alignItems: "center",
}

const liCheckStyle:CSSProperties = {
  display: "flex",
  flexDirection: "row",
  gap: "5px",
  alignItems:"center"
}

export function ItemList() {
  const list = useSelector((state: RootState) => state.itemList.list)
  const searchList = useSelector((state: RootState) => state.itemList.searchList)
  const searchInput = useSelector((state: RootState) => state.itemList.searchInput)

  const reduxDispatch = useDispatch();

  const deleteItem = (id:number) => {
    let updatedList = [...list];
    updatedList = updatedList.filter((item: Item) => item.id!==id)
    reduxDispatch(setList(updatedList))
  }

  const handleCheckChange = (itemId:number) => {
    const updatedList:Item[] = [];
    list.forEach((item:Item) => {
      const newItem:Item = {
        id: item.id,
        text: item.text,
        isCompleted: (item.id === itemId) ? 1 - item.isCompleted : item.isCompleted
      }
      updatedList.push(newItem);
    })
    reduxDispatch(setList(updatedList))
  }

  if (list.length === 0) {
    return (
      <div className='item-list'>
        <h2 style={h2}>Items</h2>
        <p style={p}>No items in list</p>
      </div>
    )
  }

  let toIterateOverList = searchList;
  if (searchInput === '') toIterateOverList = list;
  else if (searchList.length === 0) {
    return (
      <div className='item-list'>
        <h2 style={h2}>Items</h2>
        <p style={p}>No items match the search item name</p>
      </div>
    )
  }

  return (
    <div>
          <h2 style={h2}>Items</h2>
          <ul style={ul}>
              {
                toIterateOverList.map((item:Item) => {
                  return (
                    <div style={item.isCompleted ? { ...liDiv, backgroundColor:"grey", opacity:"0.5"} : liDiv} key={item.id}>
                      <div style={liCheckStyle}>
                        <input type="checkbox" checked={(item.isCompleted==1)} onChange={()=>handleCheckChange(item.id)}/>
                        <li style={item.isCompleted ? { ...li, textDecoration:"line-through" } : li}>{item.text}</li>
                      </div>
                      <button style={button} onClick={()=>deleteItem(item.id)}>X</button>
                    </div>
                  )
                })
              }
          </ul>
    </div>
  )
}
