import { Outlet } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Navbar from "../../components/navbar.component.jsx";
import Menu from "../../components/menu.component";
import MyRecipesCardContainer from "../../components/MyRecipesCardContainer.component.jsx";
import SearchMyRecipesBar from "../../components/search-my-recipes-bar.component.jsx";

const MyRecipes = () => {
  return (
    <>
      <Outlet />
      <Navbar />
      <Grid container justifyContent="center" alignItems="center" marginTop={5}>
        <Grid item>
          <Grid item marginLeft={10}>
          <Typography variant="h3" gutterBottom>
            My Recipes
          </Typography>
          </Grid>
          <Grid item xs={6} marginLeft={50} marginBottom={2}>
          <SearchMyRecipesBar></SearchMyRecipesBar>
          </Grid>
          <Grid item marginLeft={30}>
          <MyRecipesCardContainer></MyRecipesCardContainer>
          </Grid>
        </Grid>
        <Grid item>
          <Menu />
        </Grid>
      </Grid>
    </>
  );
};

export default MyRecipes;
