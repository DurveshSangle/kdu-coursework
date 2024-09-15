import { CSSProperties } from 'react'
import { AddItem } from './AddItem'
import { ItemList } from './ItemList'

const main: CSSProperties = {
  display: "flex",
  flexDirection: "column",
  width: "70%",
  border: "2px solid #eeeeee",
  margin: "10px auto",
  padding: "10px",
  paddingTop: "0"
}  

export function Main() {
  return (
    <div style={main}>
        <AddItem />
        <ItemList />  
    </div>
  )
}
