import React, { useContext } from "react";
import { Outlet } from "react-router-dom";
import Navbar from "../../components/navbar.component.jsx";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Menu from "../../components/menu.component.jsx";
import SearchIngredientBar from "../../components/search-ingredient-bar.component.jsx";
import IngredientCardContainer from "../../components/ingredient-card-container.component.jsx";
import IngredientCard from "../../components/ingredient-card.component.jsx";
import { AuthContext } from "../../auth-context/AuthContext.jsx";

function MyGroceryList(props) {
  const { user } = useContext(AuthContext);
  const userEmail = props.userEmail;
  const groceryStyle = {
    marginLeft: "220px", // Adjust to fit the width of the menu
    padding: "20px",
  };

  return (
    <div style={groceryStyle}>
      <Outlet />
      <Navbar />
      <Grid container justifyContent="center" alignItems="center">
        <Grid item>
          <Typography variant="h3" gutterBottom sx={{ p: 4 }}>
            My Grocery List
          </Typography>
          <Grid item marginBottom={2}>
            <SearchIngredientBar />
          </Grid>
          <Grid item>
            <IngredientCardContainer></IngredientCardContainer>
          </Grid>
        </Grid>
        <Grid item>
          <Menu />
        </Grid>
      </Grid>
    </div>
  );
}

export default MyGroceryList;
