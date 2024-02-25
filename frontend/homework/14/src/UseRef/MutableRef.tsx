import { useRef, useEffect, useState } from 'react';

export function MuteableRef() {
  const mutableRef = useRef(0);
  const [temp, setTemp] = useState(0);

  useEffect(() => {
    mutableRef.current += 4;
  }, [])

  useEffect(() => {
    console.log(mutableRef.current);
  },[temp]);

  return (
    <div style={{padding:"50px",margin:"30px", backgroundColor:"#f3f3f3", fontSize:"1.5rem"}}>
      <h1>Mutable Value Example</h1>
      <p>Mutable value:, {mutableRef.current}</p>
      <button onClick={()=>setTemp((prevState)=> prevState+1)}>CLick Me</button>
    </div>
  );
}