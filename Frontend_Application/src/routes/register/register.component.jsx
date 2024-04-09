import { Outlet } from "react-router-dom";
import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Box from '@mui/material/Box';
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Link from "@mui/material/Link";
import { useState } from "react";
import axios from 'axios';

const Register = () => {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    username: '',
    email: '',
    password: '',
    confirmPassword: ''
  });
  const [error, setError] = useState('');

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleRegistration = async (e) => {
    e.preventDefault();

    // Validation
    if (formData.username.length < 5) {
      setError('Username must be at least 5 characters long');
      return;
    }

    if (formData.password.length < 9) {
      setError('Password must be at least 9 characters long');
      return;
    }

    if (!/\d/.test(formData.password)) {
      setError('Password must contain at least one number');
      return;
    }

    if (!/[a-zA-Z]/.test(formData.password)) {
      setError('Password must contain at least one letter');
      return;
    }

    if (formData.password !== formData.confirmPassword) {
      setError('Passwords do not match');
      return;
    }

    // Submit registration data
    try {
      const response = await axios.post('./linkWIP', formData);
      console.log(response.data); // handle successful response from backend
    } catch (error) {
      console.error('Error:', error); // handle error response from backend
    }
  };

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
          <form onSubmit={handleRegistration}>
            <Box
              elevation={3}
              sx={{ p: 3 }}
              boxShadow={3}>
              <Typography variant="h5" align="center" gutterBottom>
                Create an Account
              </Typography>
              <TextField
                fullWidth
                label="First Name"
                name="firstName"
                value={formData.firstName}
                onChange={handleChange}
                variant="outlined"
                margin="normal"
              />
              <TextField
                fullWidth
                label="Last Name"
                name="lastName"
                value={formData.lastName}
                onChange={handleChange}
                variant="outlined"
                margin="normal"
              />
              <TextField
                fullWidth
                label="Username"
                name="username"
                value={formData.username}
                onChange={handleChange}
                variant="outlined"
                margin="normal"
              />
              <TextField
                fullWidth
                label="Email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                variant="outlined"
                margin="normal"
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
              />
              <TextField
                fullWidth
                label="Confirm Password"
                type="password"
                name="confirmPassword"
                value={formData.confirmPassword}
                onChange={handleChange}
                variant="outlined"
                margin="normal"
                error={error !== ''}
                helperText={error}
              />
              <Button variant="contained" color="primary" fullWidth>
                Register
              </Button>
              <Typography variant="body2" align="center" style={{ marginTop: 10 }}>
                Already have an account?{' '}
                <Link href="/login" underline="hover">
                  Log in Here
                </Link>
              </Typography>
            </Box>
          </form>
        </Grid>
      </Grid>
    </div>
  );
};

export default Register;
