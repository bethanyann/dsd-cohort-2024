import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Button from "@mui/material/Button";
import Grid from "@mui/material/Grid";
import logo from "../assets/gaia_logo_white.png";
import { createTheme, ThemeProvider } from '@mui/material/styles';

const theme = createTheme({
    palette: {
        primary: {
            main: '#00695c', // Set primary color for buttons
        },
        secondary: {
            main: '#dd2c00', // Set secondary color for buttons
        },
    },
});

function Navbar({ isLoggedIn, logout }) {
    let navigate = useNavigate();

    const handleSignInClick = () => {
        navigate('/login');
    };

    const handleLogoClick = () => {
        navigate('/');
    };
    const handleLogoutClick = () => {
        navigate('/');
    };

    return (
        <ThemeProvider theme={theme}>
            <AppBar position="fixed" style={{ width: '100%' }} sx={{ backgroundColor: '#ACE1AF', zIndex: (theme) => theme.zIndex.drawer + 1 }}>
                <Toolbar>
                    <Grid container alignItems="center" justifyContent="space-between">
                        {/* Logo on the left-hand side */}
                        <Grid item>
                            <img src={logo} alt="Your Logo" style={{ height: 50, cursor: 'pointer' }} onClick={handleLogoClick} />
                        </Grid>
                        {/* Sign in button or logout button on the right-hand side */}
                        <Grid item>
                            {isLoggedIn ? (
                                <Button variant="outlined" color="secondary" onClick={handleLogoutClick} sx={{backgroundColor: "white"}}>
                                    Logout
                                </Button>
                            ) : (
                                <Button variant="outlined" color="primary" onClick={handleSignInClick} sx={{backgroundColor: "white"}}>
                                    Sign In
                                </Button>
                            )}
                        </Grid>
                    </Grid>
                </Toolbar>
            </AppBar>
        </ThemeProvider>
    );
}

export default Navbar;
