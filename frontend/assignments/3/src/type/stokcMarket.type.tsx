import { Socket } from "socket.io-client";

export interface Stock {
  stock_name: string;
  stock_symbol: string;
  base_price: number;
}

export interface PriceStateProp {
  setPrevStatePrice: React.Dispatch<React.SetStateAction<number>>;
  prevStatePrice: number;
  basePrice: number;
  setBasePrice: React.Dispatch<React.SetStateAction<number>>;
  selectedStock: string;
  setSelectedStock: React.Dispatch<React.SetStateAction<string>>;
}

export interface StockTransaction {
  userName?: string;
  stockName: string | undefined;
  quantity?: string;
  timestamp: string;
  type?: string;
  time: string;
  message?: string;
  price: number;
  stockSymbol: string;
  date: string;
}

export interface Transaction {
  stockName: string;
  stockSymbol: string;
  price: number;
  date: string;
  time: string;
  status: string;
}

// types.ts
export interface SocketState {
  socket: Socket | null;
}

export const INITIALIZE_SOCKET = 'INITIALIZE_SOCKET';

interface InitializeSocketAction {
  type: typeof INITIALIZE_SOCKET;
  payload: Socket;
}

export type SocketActionTypes = InitializeSocketAction;
