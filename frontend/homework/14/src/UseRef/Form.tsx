import { useRef, useEffect } from 'react';

export function Form() {
  const firstInputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    if (firstInputRef.current) {
      firstInputRef.current.focus();
    }
  }, []);

  return (
    <form style={{padding:"50px",margin:"30px", backgroundColor:"#f3f3f3", fontSize:"1.5rem"}}>
      <label htmlFor='firstName'>First Name:</label>
      <input type="text" ref={firstInputRef} id='firstName' style={{padding:"5px"}}/> <br />
      <label htmlFor="lastName">Last Name:</label>
      <input type="text" id='lastName' style={{padding:"5px"}}/>
    </form>
  );
}
