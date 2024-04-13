import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import App from "./App.jsx";
import "./index.css";

import Login from "./routes/login/login.component.jsx";
import Register from "./routes/register/register.component.jsx";
<<<<<<< HEAD
import MyRecipes from "./routes/myrecipes/myrecipes.component.jsx";
=======
import Dashboard from "./routes/dasboard/dashboard.component.jsx";
>>>>>>> main

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "login",
        element: <Login />,
      },
      {
        path: "register",
        element: <Register />,
      },
      {
<<<<<<< HEAD
        path: "myrecipes",
        element: <MyRecipes />,
=======
        path: "dashboard",
        element: <Dashboard />,
>>>>>>> main
      },
    ],
  },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
