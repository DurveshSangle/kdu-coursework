import { useState } from 'react';
import { Header } from './Header/Header'
import { Main } from './Main/Main'
import { Item, ListProp } from '../type/ItemListProp'

export function ToDoList() {
  const itemList: Item[] = [];
  const [list, setList] = useState(itemList);
  const [searchList, setSearchList] = useState(itemList);
  const listProp:ListProp = {
    list: list,
    setList: setList,
    searchList: searchList,
    setSearchList: setSearchList
  };
  return (
    <div className='todo-list'>
        <Header listProp={listProp}/>
        <Main listProp={listProp}/>
    </div>
  )
}
