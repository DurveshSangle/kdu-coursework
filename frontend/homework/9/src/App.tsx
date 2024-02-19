import './App.css';
import { UserProfile } from './user-profile/UserProfile';

function App() {
  const user = {
    "name": "Amey",
    "fullName": "Amey Aditya",
    "qualification": "SSE",
    "skills": [
        {
            "id": 1,
            "skill": "Python"
        },
        {
            "id": 2,
            "skill": "React"
        }
    ],
    "hobbies": [
        {
            "id": 1,
            "hobby": "Cricket"
        }
    ]
  }

  return (
    <UserProfile user={user} />
  );
}

export default App;
