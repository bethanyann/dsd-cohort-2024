import { useState } from "react";
import "./App.css";
import { Outlet, Router, Route, Routes } from "react-router-dom";
import Home from "./routes/home/home.component";
import Login from "./routes/login/login.component";
import Register from "./routes/register/register.component";
import MyRecipes from "./routes/myrecipes/myrecipes.component";
import Dashboard from "./routes/dasboard/dashboard.component";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/myrecipes" element={<MyRecipes />} />
      </Routes>
    </>
  );
}

export default App;
