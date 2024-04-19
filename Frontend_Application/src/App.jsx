import React from "react";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import Home from "./routes/home/home.component";
import Login from "./routes/login/login.component";
import Register from "./routes/register/register.component";
import Dashboard from "./routes/dashboard/dashboard.component";
import MyRecipes from "./routes/myrecipes/myrecipes.component";
import MyGroceryList from "./routes/mygrocerylist/mygrocerylist.component";
import Settings from "./routes/settings/settings.component";
import { AuthProvider } from "./auth-context/AuthContext.jsx"; // Import AuthProvider
import { useState } from "react";

function App() {
  const [userEmail, setUserEmail] = useState("");

  return (
    <AuthProvider>
      {" "}
      {/* Wrap routes with AuthProvider */}
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login setUserEmail={setUserEmail} />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard userEmail={userEmail} />} />
        <Route path="/myrecipes" element={<MyRecipes userEmail={userEmail} />} />
        <Route path="/mygrocerylist" element={<MyGroceryList userEmail={userEmail} />} />
        <Route path="/settings" element={<Settings userEmail={userEmail} />} />
      </Routes>
    </AuthProvider>
  );
}

export default App;
