import { IHobbies } from "../types/IUser";
import "./Hobbies.css"

export function Hobbies({hobbies}:{hobbies:IHobbies[]}) {
  return (
    <div className="hobbies">
        <div className="head">
            <h1>Hobbies</h1>
        </div>
        <div className="list">
            <ul>
                {
                    hobbies.map((hobby:IHobbies) =>{
                        return (
                            <li key={hobby.id}>{hobby.hobby}</li>
                        )
                    })
                }
            </ul>
        </div>
    </div>
  )
}
