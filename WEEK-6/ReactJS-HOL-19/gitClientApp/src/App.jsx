import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import GitClient from './GitClient';

function App() {
  const[repositories, setRepositories] = useState([]);

  useEffect(() => {
    GitClient.getRepositories('Sree-45').then(r => setRepositories(r.data));
  }, [])

  return (
    <>
      <div className='App'>
        <h1>Git repositories of User - Sreeshanth</h1>
        {repositories.map(r => 
          <p key={r.name}>{r.name}</p>
        )}
      </div>
    </>
  )
}

export default App
