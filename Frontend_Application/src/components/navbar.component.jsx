import React from 'react';
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Grid from "@mui/material/Grid";
import logo from "../assets/green_leaf_2.png"

function Navbar() {
  return (
    <AppBar position="static" elevation={0} sx={{ backgroundColor: 'transparent' }}>
  <Toolbar>
        <Grid container alignItems="center" justifyContent="space-between">
          {/* Logo on the left-hand side */}
          <Grid item>
            <img src={logo} alt="Your Logo" style={{ height: 50 }} />
          </Grid>
          {/* Sign in button on the right-hand side */}
          <Grid item>
            <Button href ="/login" variant="outlined" color="primary">Sign In</Button>
          </Grid>
        </Grid>
      </Toolbar>
    </AppBar>
  );
}

export default Navbar;

