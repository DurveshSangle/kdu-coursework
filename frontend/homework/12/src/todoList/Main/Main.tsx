import { AddItem } from '../AddItem/AddItem'
import { ItemList } from '../ItemList/ItemList'
import './Main.scss';

export function Main() {
  return (
    <div className='main'>
        <AddItem />
        <ItemList />  
    </div>
  )
}
