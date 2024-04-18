import React, { useState, useEffect } from "react";
import { Outlet, Navigate, useLocation } from "react-router-dom"; // Import Navigate for redirection
import Navbar from "../../components/navbar.component.jsx";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Menu from "../../components/menu.component.jsx";
import SearchRecipesBar from "../../components/search-recipes-bar.component.jsx";
import useAuth from "../../auth-context/useAuth.jsx";

function Dashboard(props) {
  const { user } = useAuth();
  const location = useLocation(); // Use useLocation hook to access location
  const [userData, setUserData] = useState(null);

  useEffect(() => {
    if (location.state.email) { // Access location.state.email directly
      retrieveUserData(location.state.email);
    }
  }, [user, location]);

  async function retrieveUserData(email) {
    try {
      const response = await fetch(`http://localhost:8080/api/v0/users/finduserbyemail?email=${email}`);
      if (response.ok) {
        const userData = await response.json();
        setUserData(userData);
        console.log(`response: ${userData}`);
      } else {
        console.error('Failed to retrieve user data:', response.status);
      }
    } catch (err) {
      console.error('Error retrieving user data:', err);
    }
  }

  const dashboardStyle = {
    marginLeft: "220px", // Adjust to fit the width of the menu
    padding: "20px",
  };

  return (
    <div style={dashboardStyle}>
      <Outlet />
      <Navbar />
      <Grid container justifyContent='center' alignItems='center'>
        <Grid item>
          <Typography variant='h3' gutterBottom sx={{ p: 4 }}>
            Welcome, {userData ? userData.firstName : location.state.email} to Your Dashboard
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

export default Dashboard;