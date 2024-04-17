import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import App from "./App.jsx";
import "./index.css";

import Login from "./routes/login/login.component.jsx";
import Register from "./routes/register/register.component.jsx";
import MyRecipes from "./routes/myrecipes/myrecipes.component.jsx";
import Dashboard from "./routes/dashboard/dashboard.component.jsx";
import MyGroceryList from "./routes/mygrocerylist/mygrocerylist.component.jsx";
import Settings from "./routes/settings/settings.component.jsx";

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
        path: "myrecipes",
        element: <MyRecipes />,
      },
      {
        path: "dashboard",
        element: <Dashboard />,
      },
      {
        path: "mygrocerylist",
        element: <MyGroceryList />,
      },
      {
        path: "settings",
        element: <Settings />,
      },
    ],
  },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
