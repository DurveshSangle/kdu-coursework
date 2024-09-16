import { ISkills } from "../types/IUser"
import "./Skills.css"

export function Skills({ skills }: { skills:ISkills[]}) {
  return (
    <div className="skills">
        <div className="head">
            <h1>Skills</h1>
        </div>
        <div className="list">
            <ul>
                {
                    skills.map((skill:ISkills) =>{
                        return (
                            <li key={skill.id}>{skill.skill}</li>
                        )
                    })
                }
            </ul>
        </div>
    </div>
  )
}
