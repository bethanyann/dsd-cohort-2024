import { Outlet } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Navbar from "../../components/navbar.component.jsx";
import Menu from "../../components/menu.component";
import DietaryPrefSwitchboard from "../../components/dietary-pref-switchboard.component.jsx";

const Settings = () => {
  return (
    <>
      <Outlet />
      <Navbar />
      <Grid container justifyContent='center' alignItems='center'>
        <Grid item>
          <Typography variant='h3' gutterBottom sx={{ p: 4 }}>
            My Dietary Preferences
          </Typography>
          <DietaryPrefSwitchboard />
        </Grid>
        <Grid item>
          <Menu />
        </Grid>
      </Grid>
    </>
  );
};

export default Settings;
