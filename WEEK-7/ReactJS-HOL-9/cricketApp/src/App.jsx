import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import PlayersPage from './PlayersPage.jsx'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <PlayersPage />
    </>
  )
}

export default App
