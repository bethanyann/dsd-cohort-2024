import React, { useContext, useEffect, useState } from "react";
import { Outlet } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Navbar from "../../components/navbar.component.jsx";
import Menu from "../../components/menu.component";
import MyRecipesCardContainer from "../../components/MyRecipesCardContainer.component.jsx";
import SearchMyRecipesBar from "../../components/search-my-recipes-bar.component.jsx";
import { AuthContext } from "../../auth-context/AuthContext.jsx";

const MyRecipes = (props) => {
  const { user } = useContext(AuthContext);
  const userInfo = props.userInfo;
  const [myRecipes, setMyRecipes] = useState([]);

  console.log(userInfo, "myreceipes");
  const recipeStyles = {
    marginLeft: "220px", // Adjust to fit the width of the menu
    padding: "20px",
  };

  useEffect(() => {
    console.log("Fetching recipes...");
    const fetchRecipes = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/v0/users/getuserfavorites/" + userInfo.email);
        const data = await response.json();
        setMyRecipes(data);
        console.log(data);
      } catch (error) {
        console.error("Error fetching recipes:", error);
      }
    };

    fetchRecipes();
  }, []);

  return (
    <div style={recipeStyles}>
      <Outlet />
      <Navbar />
      <Grid container justifyContent="center" alignItems="center" marginTop={5}>
        <Grid item>
          <Grid item>
            <Typography variant="h3" gutterBottom>
              My Recipes
            </Typography>
          </Grid>
          <Grid item marginBottom={2}>
            <SearchMyRecipesBar />
          </Grid>
          <Grid item>
            <MyRecipesCardContainer type="remove" userInfo={userInfo} recipes={myRecipes} />
          </Grid>
        </Grid>
        <Grid item>
          <Menu />
        </Grid>
      </Grid>
    </div>
  );
};

export default MyRecipes;
