import { Item} from '../type/ItemListProp'
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../redux/store';
import { setList, setSearchInput } from '../redux/ItemListSlice';
import { CSSProperties } from 'react';

const styles = {
  h2: {
    fontWeight: "500",
    marginBottom: "5px",
    fontSize: "25px",
    padding:"10px"
  },
  input: {
    padding: "8px",
    marginRight: "5px",
    marginLeft: "10px",
    fontize: "15px",
    border: "1px solid #eeeeee"
  },
  button: {
    padding: "10px 10px",
    color: "white",
    backgroundColor: "#343a40",
    fontSize: "15px",
    border: "0",
    borderRadius: "5px"
  }
}

const divStyles:CSSProperties = {
  display: "flex",
  flexDirection: "row",
  justifyContent:"space-between"
}

export function AddItem() {

  const list = useSelector((state: RootState) => state.itemList.list);

  const reduxDispatch = useDispatch();

  const clearList = () => {
    const updatedList = list.filter((item:Item) => !item.isCompleted);
    reduxDispatch(setList(updatedList));
  }

  const addItemToList = () => {
    const itemAddInput:HTMLInputElement | null = document.querySelector("#item-add-input");
    if (!itemAddInput) return;
    const itemText:string = itemAddInput.value;
    if (itemText.length === 0) return;
    const lenOfList = list.length;
    const itemId = (lenOfList === 0) ? 1 : list[lenOfList - 1].id + 1;
    const newItem:Item = {
      id: itemId,
      text: itemText,
      isCompleted: 0
    }
    const updatedList = [...list, newItem];
    reduxDispatch(setList(updatedList));
    itemAddInput.value = '';
    reduxDispatch(setSearchInput(''))
  }

  return (
    <div>
      <h2 style={styles.h2}>Add Items</h2>
      <div style={divStyles}>
        <div>
          <input type="text" id='item-add-input' style={styles.input}/>
          <button onClick={addItemToList} style={styles.button}>Submit</button>
        </div>
        <div>
          <button onClick={clearList} style={styles.button}>Clear</button>
        </div>
      </div>
    </div>
  )
}
