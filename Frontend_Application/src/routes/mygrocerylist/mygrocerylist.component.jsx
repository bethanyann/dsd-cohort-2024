import React from "react";
import { Outlet } from "react-router-dom";
import Navbar from "../../components/navbar.component.jsx";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Menu from "../../components/menu.component.jsx";
import SearchIngredientBar from "../../components/search-ingredient-bar.component.jsx";
import IngredientCardContainer from "../../components/ingredient-card-container.component.jsx";
import IngredientCard from "../../components/ingredient-card.component.jsx";

function MyGroceryList() {
  return (
    <>
      <Outlet />
      <Navbar />
      <Grid container justifyContent='center' alignItems='center'>
        <Grid item>
          <Typography variant='h3' gutterBottom sx={{ p: 4 }}>
            My Grocery List
          </Typography>
          <SearchIngredientBar />
          <IngredientCardContainer>
            <IngredientCard />
          </IngredientCardContainer>
        </Grid>
        <Grid item>
          <Menu />
        </Grid>
      </Grid>
    </>
  );
}

export default MyGroceryList;
