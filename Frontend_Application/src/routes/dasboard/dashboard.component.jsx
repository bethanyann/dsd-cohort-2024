import React from "react";
import { Outlet } from "react-router-dom";
import Navbar from "../../components/navbar.component.jsx";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Menu from "../../components/menu.component.jsx";
import SearchRecipesBar from "../../components/SearchRecipesBar.jsx";

function DashboardPage() {
  // Replace 'userName' with the actual user's name from state or props
  const userName = "Mittens";
  const dashboardStyle = {
    marginLeft: '220px', // Adjust to fit the width of the menu
    padding: '20px',
  };

  return (
    <div style={dashboardStyle}>
      <Outlet />
      <Navbar />
      <Grid container justifyContent="center" alignItems="center">
        <Grid item>
          <Typography variant="h3" gutterBottom>
            Welcome, {userName} to Your Dashboard
          </Typography>
          <SearchRecipesBar />
        </Grid>
        <Grid item>
          <Menu />
        </Grid>
      </Grid>
    </div>
  );
}

export default DashboardPage;
