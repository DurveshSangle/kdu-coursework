import { useContext } from 'react';
import { Item } from '../../type/ItemListProp'
import { ListContext, SearchInputContext, SearchListContext } from '../ToDoList';
import './ItemList.scss';

export function ItemList() {
  const listContext = useContext(ListContext);
  const searchListContext = useContext(SearchListContext);
  const searchInputContext = useContext(SearchInputContext);

  const setList = listContext.setList;
  const list = listContext.list;
  const searchList = searchListContext.searchList;
  const searchInput = searchInputContext.searchInput;

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
