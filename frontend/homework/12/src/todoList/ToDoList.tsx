import { createContext, useState } from 'react';
import { Header } from './Header/Header'
import { Main } from './Main/Main'
import { Item} from '../type/ItemListProp'

interface IListContext {
  list: Item[];
  setList: React.Dispatch<React.SetStateAction<Item[]>>
}

interface ISearchListContext {
  searchList: Item[];
  setSearchList: React.Dispatch<React.SetStateAction<Item[]>>
}

interface ISearchInputContext {
  searchInput: string;
  setSearchInput: React.Dispatch<React.SetStateAction<string>>
}

export const ListContext = createContext<IListContext>({
  list: [],
  setList: () => {}
})

export const SearchListContext = createContext<ISearchListContext>({
  searchList: [],
  setSearchList: () => {}
})

export const SearchInputContext = createContext<ISearchInputContext>({
  searchInput:"",
  setSearchInput: () => {}
})

interface ProviderProps {
  children: React.ReactNode;
}

const ListProvider = ({children}: ProviderProps) => {
  const [list, setList] = useState<Item[]>([]);

  return (
    <ListContext.Provider value={{ list, setList}}>
      {children}
    </ListContext.Provider>
  );
}

const SearchListProvider = ({children}: ProviderProps) => {
  const [searchList, setSearchList] = useState<Item[]>([]);

  return (
    <SearchListContext.Provider value={{ searchList, setSearchList}}>
      {children}
    </SearchListContext.Provider>
  );
}

const InputSearchProvider = ({children}: ProviderProps) => {
  const [searchInput, setSearchInput] = useState('');

  return (
    <SearchInputContext.Provider value={{ searchInput, setSearchInput}}>
      {children}
    </SearchInputContext.Provider>
  );
}

export function ToDoList() {

  return (
    <ListProvider>
      <SearchListProvider>
        <InputSearchProvider>
          <div className='todo-list'>
            <Header />
            <Main />
          </div>
        </InputSearchProvider>
      </SearchListProvider>
    </ListProvider>
  )
}
