import { useState } from "react";
import "./App.css";
import { Outlet, Router, Route, Routes } from "react-router-dom";
import Home from "./routes/home/home.component";
import Login from "./routes/login/login.component";
import Register from "./routes/register/register.component";
<<<<<<< HEAD
import MyRecipes from "./routes/myrecipes/myrecipes.component";
=======
import Dashboard from "./routes/dasboard/dashboard.component";
>>>>>>> main

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <Routes>
<<<<<<< HEAD
        <Route path='/' element={<Home />} />
        <Route path='/login' element={<Login />} />
        <Route path='/register' element={<Register />} />
        <Route path='/myrecipes' element={<MyRecipes />} />
=======
        <Route path="/" element={<Home />}/>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard />} />
>>>>>>> main
      </Routes>
    </>
  );
}

export default App;
