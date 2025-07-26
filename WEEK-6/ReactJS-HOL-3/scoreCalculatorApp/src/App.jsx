import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { CalculateScore } from './Components/CalculateScore'

function App() {

  return (
    <>
      <div>
        <CalculateScore Name={"steeve"}
          School="ABC High School"
          total={284}
          goal={3} />
      </div>
    </>
  )
}

export default App
