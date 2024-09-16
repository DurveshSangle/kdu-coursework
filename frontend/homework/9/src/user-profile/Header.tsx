import { IUser } from "../types/IUser"
import "./Header.css"

export function Header({user}: {user:IUser}) {
  return (
      <div className="header">
        <h1>{user.name}</h1>
        <h3>{user.fullName}</h3>
        <h1>{user.qualification}</h1>
      </div>
  )
}
