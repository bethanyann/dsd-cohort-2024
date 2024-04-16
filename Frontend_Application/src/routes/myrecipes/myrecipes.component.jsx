import { Outlet } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Navbar from "../../components/navbar.component.jsx";
import Menu from "../../components/menu.component";

const MyRecipes = () => {
  return (
    <>
      <Outlet />
      <Navbar />
      <Grid container justifyContent='center' alignItems='center'>
        <Grid item>
          <Typography variant='h3' gutterBottom>
            My Recipes
          </Typography>
        </Grid>
        <Grid item>
          <Menu />
        </Grid>
      </Grid>
    </>
  );
};

export default MyRecipes;
