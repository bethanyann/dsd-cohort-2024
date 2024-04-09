import { Outlet } from "react-router-dom";
import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Box from '@mui/material/Box';
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Link from "@mui/material/Link";

const Login = () => {

  return (
    <div>
      <Outlet />
      <CssBaseline />
      <Grid
        container
        alignItems="center"
        justifyContent="center"
        style={{ minHeight: '100vh' }}
      >
        <Grid item xs={10} sm={6} md={8}>
          <Box
            elevation={3}
            sx={{ p: 3 }}
            boxShadow={3}>
            <Typography variant="h5" align="center" gutterBottom>
              Log in to Your Account
            </Typography>
            <TextField
              label="Email"
              variant="outlined"
              fullWidth
              margin="normal"
            />
            <TextField
              label="Password"
              type="password"
              variant="outlined"
              fullWidth
              margin="normal"
            />
            <Button variant="contained" color="primary" fullWidth>
              Log In
            </Button>
            <Typography variant="body2" align="center" style={{ marginTop: 10 }}>
              Don't have an account?{' '}
              <Link href="/register" underline="hover">
                Register Here
              </Link>
            </Typography>
          </Box>
        </Grid>
      </Grid>
    </div>
  );
};

export default Login;
