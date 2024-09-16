import { IUser } from "../types/IUser"
import { Header } from "./Header"
import { Hobbies } from "./Hobbies"
import { Skills } from "./Skills"

import "./UserProfile.css"

export function UserProfile({user}:{user:IUser}) {
  return (
    <div className='user-profile'>
      <Header user={user}/>
      <div className="main">
        <Skills skills={user.skills} />
        <Hobbies hobbies={user.hobbies} />
      </div>
    </div>
  )
}
