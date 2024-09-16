export interface IUser {
    name: string;
    fullName: string;
    qualification: string;
    skills: ISkills[];
    hobbies: IHobbies[];
}

export interface ISkills {
    id: number;
    skill: string;
}

export interface IHobbies {
    id: number;
    hobby: string;
}