import { useRef, useState } from "react"

export function Timer() {
  const [seconds, setSeconds] = useState<number>(0);

  const interval = useRef<number>(0);

  const handleStart = () => {
    interval.current = setInterval(() => {
      setSeconds((prevState:number) => prevState + 1);
    }, 1000);
  }
  
  const handlePause = () => {
    clearInterval(interval.current);
  }

  const handleReset = () => {
    setSeconds(0);
    clearInterval(interval.current);
  }

  return (
    <div style={{padding:"50px",margin:"30px", backgroundColor:"#f3f3f3"}}> 
      <h1>Timer:  {seconds} sec</h1>
      <div>
        <button onClick={handleStart}>Start</button>
        <button onClick={handlePause}>Pause</button>
        <button onClick={handleReset}>Reset</button>
      </div>
    </div>
  )
}