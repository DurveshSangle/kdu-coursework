const addBtn = document.getElementById("addBtn");
const input = document.getElementById("inputBox");

addBtn.addEventListener("click",addItemToList)

function addItemToList(){
    const item = input.value;
    if(item.length==0){
        alert("Give some input");
        return;
    }
    const list = document.getElementById("items");
    const listItem = document.createElement("li");
    const textSpan = document.createElement("span");
    textSpan.textContent = item;
    listItem.appendChild(textSpan);

    addDeleteBtn(list,listItem);
    addEditBtn(listItem,textSpan);

    list.appendChild(listItem);
}

function addDeleteBtn(list,listItem){
    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "DELETE";
    deleteBtn.addEventListener("click",()=>{
        list.removeChild(listItem)
    })
    listItem.appendChild(deleteBtn);
}

function addEditBtn(listItem,textSpan){
    const editBtn = document.createElement("button");
    editBtn.textContent = "EDIT";

    editBtn.addEventListener("click", ()=>{
        listItem.removeChild(editBtn);
        const itemInput = document.createElement("input");
        const saveBtn = document.createElement("button");
        saveBtn.textContent = "SAVE";
        saveBtn.addEventListener("click",()=>{
            if(itemInput.value.length==0){
                alert("Item value cannot be blank !!");
                return;
            }
            textSpan.textContent = itemInput.value;
            listItem.appendChild(editBtn);
            listItem.removeChild(itemInput);
            listItem.removeChild(saveBtn);
        })
        listItem.appendChild(itemInput);
        listItem.appendChild(saveBtn);
    })

    listItem.appendChild(editBtn);
}