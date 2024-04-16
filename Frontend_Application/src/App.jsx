import "./App.css";
import { Route, Routes } from "react-router-dom";
import Home from "./routes/home/home.component";
import Login from "./routes/login/login.component";
import Register from "./routes/register/register.component";
import MyRecipes from "./routes/myrecipes/myrecipes.component";
import Dashboard from "./routes/dashboard/dashboard.component";
import MyGroceryList from "./routes/mygrocerylist/mygrocerylist.component";
import Settings from "./routes/settings/settings.component";

function App() {
  return (
    <>
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/login' element={<Login />} />
        <Route path='/register' element={<Register />} />
        <Route path='/dashboard' element={<Dashboard />} />
        <Route path='/myrecipes' element={<MyRecipes />} />
        <Route path='/mygrocerylist' element={<MyGroceryList />} />
        <Route path='/settings' element={<Settings />} />
      </Routes>
    </>
  );
}

export default App;
