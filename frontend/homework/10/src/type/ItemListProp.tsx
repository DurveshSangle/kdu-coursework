import React, { SetStateAction } from "react";

export interface ListProp {
    list: Item[];
    setList: React.Dispatch<SetStateAction<Item[]>>,
    searchList: Item[];
    setSearchList: React.Dispatch<SetStateAction<Item[]>>
}

export interface Item{
    id: number;
    text: string;
}