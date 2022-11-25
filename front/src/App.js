import React from 'react';
import { BrowserRouter as Router} from 'react-router-dom';
import { Navigate, Route, Routes } from "react-router-dom";
import Login from './Login';
import Relojes from './Relojes';
import Justificativos from './Justificativos';
import HoraExtra from './HoraExtra';
import Sueldos from './Sueldos';
import Home from './Home';



function App(){

  return (
    <Router>
        <div>
          <Routes>
              <Route path='/' element={<Navigate to="/home" />} />
              <Route path='/relojes' element={<Relojes/>} />
              <Route path='/justificativos' element={<Justificativos/>} />
              <Route path='/horaextra' element={<HoraExtra/>} />
              <Route path='/sueldos' element = {<Sueldos/>} />
              <Route path="/home" element={ <Home />} />
          </Routes>
        </div>
      </Router>
  );
}

export default App;
