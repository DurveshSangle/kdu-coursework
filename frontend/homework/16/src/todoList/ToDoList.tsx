import { CSSProperties } from 'react'
import { Header } from './Header'
import { Main } from './Main'

const todoList: CSSProperties = {
  width: "100%"
}

export function ToDoList() {

  return (
    <div style={todoList}>
      <Header />
      <Main />
    </div>
  )
}
