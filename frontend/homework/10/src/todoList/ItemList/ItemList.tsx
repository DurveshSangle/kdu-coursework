import { Item, ListProp } from '../../type/ItemListProp'
import './ItemList.scss';

export function ItemList({ listProp }: { listProp: ListProp }) {
  const setList = listProp.setList;
  const list = listProp.list;
  const searchList = listProp.searchList;
  const searchInput = listProp.searchInput;

  const deleteItem = (id:number) => {
    let updatedList = [...list];
    updatedList = updatedList.filter((item: Item) => item.id!==id)
    setList(updatedList);
  }

  if (list.length === 0) {
    return (
      <div className='item-list'>
        <h2>Items</h2>
        <p>No items in list</p>
      </div>
    )
  }
  let toIterateOverList = searchList;
  if (searchInput === '') toIterateOverList = list;
  else if (searchList.length === 0) {
    return (
      <div className='item-list'>
        <h2>Items</h2>
        <p>No items match the search item name</p>
      </div>
    )
  }

  return (
    <div className='item-list'>
          <h2>Items</h2>
          <ul>
              {
                toIterateOverList.map((item:Item) => {
                  return (
                    <div className="li-div" key={item.id}>
                      <li>{item.text}</li>
                      <button onClick={()=>deleteItem(item.id)}>X</button>
                    </div>
                  )
                })
              }
          </ul>
    </div>
  )
}
