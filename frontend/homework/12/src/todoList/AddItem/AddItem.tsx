import  { useContext } from 'react'
import { Item} from '../../type/ItemListProp'
import './AddItem.scss';
import { ListContext, SearchInputContext} from '../ToDoList';

export function AddItem() {
  const listContext = useContext(ListContext);
  const searchInputContext = useContext(SearchInputContext);

  const setList = listContext.setList;
  const list = listContext.list;
  const setSearchInput = searchInputContext.setSearchInput;

  const addItemToList = () => {
    const itemAddInput:HTMLInputElement | null = document.querySelector("#item-add-input");
    if (!itemAddInput) return;
    const itemText:string = itemAddInput.value;
    if (itemText.length === 0) return;
    const lenOfList = list.length;
    const itemId = (lenOfList === 0) ? 1 : list[lenOfList - 1].id + 1;
    const newItem:Item = {
      id: itemId,
      text: itemText
    }
    const updatedList = [...list, newItem];
    setList(updatedList);
    itemAddInput.value = '';
    setSearchInput('');
  }

  return (
    <div className='add-items'>
          <h2>Add Items</h2>
          <div className='addItem-input-submit'>
              <input type="text" id='item-add-input'/>
              <button onClick={addItemToList}>Submit</button>
          </div>
    </div>
  )
}
