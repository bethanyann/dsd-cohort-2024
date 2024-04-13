import "./App.css";
import { Route, Routes } from "react-router-dom";
import Home from "./routes/home/home.component";
import Login from "./routes/login/login.component";
import Register from "./routes/register/register.component";
import Dashboard from "./routes/dasboard/dashboard.component";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </>
  );
}

export default App;
