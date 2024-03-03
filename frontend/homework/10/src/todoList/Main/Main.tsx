import { ListProp } from '../../type/ItemListProp'
import { AddItem } from '../AddItem/AddItem'
import { ItemList } from '../ItemList/ItemList'
import './Main.scss';

export function Main({ listProp }: { listProp: ListProp }) {
  return (
    <div className='main'>
        <AddItem listProp={listProp}/>
        <ItemList listProp={listProp}/>  
    </div>
  )
}
