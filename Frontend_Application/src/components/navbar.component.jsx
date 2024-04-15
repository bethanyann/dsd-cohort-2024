import React from 'react';
import { useNavigate } from 'react-router-dom';
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Button from "@mui/material/Button";
import Grid from "@mui/material/Grid";
import logo from "../assets/green_leaf_2.png";

function Navbar() {
    let navigate = useNavigate();

    return (
        <AppBar position="fixed" style={{ width: '100%' }} sx={{ backgroundColor: 'transparent' }}>
            <Toolbar>
                <Grid container alignItems="center" justifyContent="space-between">
                    {/* Logo on the left-hand side */}
                    <Grid item>
                        <img src={logo} alt="Your Logo" style={{ height: 50 }} onClick={() => { navigate('/') }} />
                    </Grid>
                    {/* Sign in button on the right-hand side */}
                    <Grid item>
                        <Button variant="outlined" color="primary" onClick={() => { navigate('/login') }}>Sign In</Button>
                    </Grid>
                </Grid>
            </Toolbar>
        </AppBar>
    );
}

export default Navbar;

