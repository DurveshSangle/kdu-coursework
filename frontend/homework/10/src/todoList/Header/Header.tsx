import React from 'react'
import { Item, ListProp } from '../../type/ItemListProp'
import './Header.scss'

export function Header({ listProp }: { listProp: ListProp }) {
  const list = listProp.list;
  const setSearchList = listProp.setSearchList;
  const setSearchInput = listProp.setSearchInput;

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
