import { Stock } from "../type/stokcMarket.type";

export const toSorted = (data: Stock[]): Stock[] => {
  return data.sort((a, b) => {
    const nameA = a.stock_name.toLowerCase();
    const nameB = b.stock_name.toLowerCase();
    if (nameA < nameB) {
      return -1;
    }
    if (nameA > nameB) {
      return 1;
    }
    return 0;
  });
};