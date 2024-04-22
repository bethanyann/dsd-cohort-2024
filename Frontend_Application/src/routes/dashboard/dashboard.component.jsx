import React, { useState, useEffect, useContext } from "react";
import { Outlet, Navigate, useLocation } from "react-router-dom"; // Import Navigate for redirection
import Navbar from "../../components/navbar.component.jsx";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Menu from "../../components/menu.component.jsx";
import SearchRecipesBar from "../../components/search-recipes-bar.component.jsx";
import useAuth from "../../auth-context/useAuth.jsx";
import MyRecipesCardContainer from "../../components/MyRecipesCardContainer.component.jsx";

function Dashboard({ userInfo }) {
  const [searchedRecipes, setSearchedRecipes] = useState([]);
  console.log(userInfo, "dash");

  const dashboardStyle = {
    marginLeft: "220px", // Adjust to fit the width of the menu
    padding: "20px",
  };

  return (
    <div style={dashboardStyle}>
      <Outlet />
      <Navbar isLoggedIn={true} />
      <Grid container justifyContent="center" alignItems="center">
        <Grid item>
          <Typography variant="h3" gutterBottom sx={{ p: 4 }}>
            Welcome, {userInfo ? userInfo.firstName : userInfo.email} to Your Dashboard
          </Typography>
          <Grid item marginBottom={2}>
            <SearchRecipesBar setSearchedRecipes={setSearchedRecipes} />
          </Grid>
          <Grid item>
            <MyRecipesCardContainer recipes={searchedRecipes} />
          </Grid>
        </Grid>
        <Grid item>
          <Menu />
        </Grid>
      </Grid>
    </div>
  );
}

export default Dashboard;
