import React, { useContext } from 'react'
import { Item } from '../../type/ItemListProp'
import './Header.scss'
import { ListContext, SearchInputContext, SearchListContext } from '../ToDoList';

export function Header() {
  const listContext = useContext(ListContext);
  const searchListContext = useContext(SearchListContext);
  const searchInputContext = useContext(SearchInputContext);

  const list = listContext.list;
  const setSearchList = searchListContext.setSearchList;
  const setSearchInput = searchInputContext.setSearchInput;

  const searchItem = (event: React.ChangeEvent<HTMLInputElement>) => {
    const searchText: string = event.target.value.trim().toLowerCase(); // Trim whitespace and convert to lowercase for case-insensitive search
    const searchResultList = list.filter((item: Item) => {  
      return item.text.toLowerCase().includes(searchText);
    });
    setSearchInput(searchText);
    setSearchList(searchResultList);
  };

  return (
    <div className='header'>
        <h1>Item Lister</h1>
        <input type="text" placeholder='Search Items' onChange={searchItem} id='item-search-input'/>
    </div>
  )
}
