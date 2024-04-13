import React, { useState } from 'react';
import { Outlet } from "react-router-dom";
import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Box from '@mui/material/Box';
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Link from "@mui/material/Link";
import Snackbar from "@mui/material/Snackbar";
import Alert from '@mui/material/Alert';
import { Link as RouterLink } from 'react-router-dom';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import IconButton from "@mui/material/IconButton";

const Login = () => {

  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });

  const [errors, setErrors] = useState({
    email: '',
    password: '',
  });

  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    // Clear error when input changes
    setErrors({ ...errors, [e.target.name]: '' }); 
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    const newErrors = {};

    // Validation
    if (!formData.email.trim()) {
      newErrors.email = 'Email is required';
    } else if (!formData.email.includes('@') || !formData.email.endsWith('.com')) {
      newErrors.email = 'Invalid email address';
    }

    if (!formData.password.trim()) {
      newErrors.password = 'Password is required';
    }

    // Update errors state
    setErrors(newErrors);

    // Display error message in Snackbar if there are validation errors
    if (Object.keys(newErrors).length > 0) {
      setSnackbarMessage('Please fix the errors in the form.');
      setSnackbarOpen(true);
      return;
    }

    // Submit login data if there are no validation errors
    try {
      const response = await axios.post('http://localhost:3001/login', formData);
      console.log(response.data);
    } catch (error) {
      console.error('Error:', error);
      setSnackbarMessage('An error occurred while logging in.');
      setSnackbarOpen(true);
    }
  };

  const handleSnackbarClose = () => {
    setSnackbarOpen(false);
  };


  return (
    <div>
      <Outlet />
      <CssBaseline />
      <IconButton component={RouterLink} to="/" aria-label="back" style={{ position: 'absolute', top: '10px', left: '10px' }}>
        <ArrowBackIcon />
      </IconButton>
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
            <form onSubmit={handleLogin}>
              <TextField
                fullWidth
                label="Email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                variant="outlined"
                margin="normal"
                error={errors.email !== ''}
                helperText={errors.email}
              />
              <TextField
                fullWidth
                label="Password"
                type="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                variant="outlined"
                margin="normal"
                error={errors.password !== ''}
                helperText={errors.password}
              />
              <Button type="submit" variant="contained" color="primary" style={{ marginTop: '16px' }} fullWidth>Login</Button>
            </form>
            <Snackbar
              anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
              open={snackbarOpen}
              autoHideDuration={6000}
              onClose={handleSnackbarClose}
            >
              <Alert onClose={handleSnackbarClose} severity="error">
                {snackbarMessage}
              </Alert>
            </Snackbar>
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
