import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import { Outlet, Router, Route, Routes } from "react-router-dom";
import Home from "./routes/home/home.component";
import Login from "./routes/login/login.component";
import Register from "./routes/register/register.component";
import Dashboard from "./routes/dasboard/dashboard.component";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <Routes>
        <Route path="/" element={<Home />}/>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </>
  );
}

export default App;
