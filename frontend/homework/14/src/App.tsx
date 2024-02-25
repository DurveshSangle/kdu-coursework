import './App.css'
import { Form } from './UseRef/Form'
import { MuteableRef } from './UseRef/MutableRef'
import { ScrollToTop } from './UseRef/ScrollToTop'
import { Timer } from './UseRef/Timer'

function App() {
  return (
    <>
      <MuteableRef />
      <Form />
      <Timer />
      <ScrollToTop />
    </>
  )
}

export default App
