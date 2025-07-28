import { useState } from 'react'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Home from './Home'
import TrainerList from './Components/TrainerList'
import TrainerDetail from './Components/TrainerDetail'

function App() {
  return (
    <>
      <BrowserRouter>
        <div className="App">
        <h1>My Academy Trainers App</h1>
          <nav>
            <Link to="/">Home</Link>
            <span>  |  </span>
            <Link to="/trainers">Trainers List</Link>
          </nav>
          
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/trainers" element={<TrainerList />} />
            <Route path="/trainers/:id" element={<TrainerDetail />} />
          </Routes>
        </div>
      </BrowserRouter>
    </>
  )
}

export default App
