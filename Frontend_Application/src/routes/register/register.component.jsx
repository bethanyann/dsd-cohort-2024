import { Outlet } from 'react-router-dom';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Link from '@mui/material/Link';
import { useState } from 'react';
import axios from 'axios';
import { Link as RouterLink } from 'react-router-dom';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import IconButton from "@mui/material/IconButton";
import Snackbar from "@mui/material/Snackbar";
import Alert from '@mui/material/Alert';


const Register = () => {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    confirmPassword: '',
  });

  const [errors, setErrors] = useState({
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    confirmPassword: '',
  });

  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');
  const [errorSnackbarOpen, setErrorSnackbarOpen] = useState(false);
  const [errorSnackbarMessage, setErrorSnackbarMessage] = useState('');

  const validateEmail = (email) => {
    // Regular expression for email validation
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(String(email).toLowerCase());
  };

  const validatePassword = (password) => {
    // Password should be at least 9 characters and include at least one number and one special character
    const re = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{9,}$/;
    return re.test(password);
  };

  const handleRegistration = (e) => {
    e.preventDefault();
    let newErrors = {};

    // Check for blank fields
    for (const key in formData) {
      if (formData[key] === '') {
        newErrors[key] = 'This field is required';
      }
    }

    // Validate email
    if (!validateEmail(formData.email)) {
      newErrors.email = 'Invalid email address';
    }

    // Validate password
    if (!validatePassword(formData.password)) {
      newErrors.password = 'Password must be at least 9 characters long and contain at least one number and one special character';
    }

    // Check if passwords match
    if (formData.password !== formData.confirmPassword) {
      newErrors.confirmPassword = 'Passwords do not match';
    }

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      setErrorSnackbarMessage('Please fix the errors in the form');
      setErrorSnackbarOpen(true);
    } else {
      // Send data to backend
      fetch('http://localhost:8080/api/v0/users/createuser', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then((data) => {
        console.log('Form submitted successfully:', data);
        // Reset form and errors
        setFormData({
          firstName: '',
          lastName: '',
          email: '',
          password: '',
          confirmPassword: '',
        });
        setErrors({
          firstName: '',
          lastName: '',
          email: '',
          password: '',
          confirmPassword: '',
        });
        setSnackbarMessage('Registration successful!');
        setSnackbarOpen(true);
      })
      .catch((error) => {
        console.error('Error sending form data:', error);
        setErrorSnackbarMessage('Error sending form data');
        setErrorSnackbarOpen(true);
      });
    }
  };
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
    setErrors({
      ...errors,
      [name]: '',
    });
  };

  const handleCloseSnackbar = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setSnackbarOpen(false);
    setErrorSnackbarOpen(false);
  };

  return (
    <div>
      <Outlet />
      <CssBaseline />
      <IconButton
        component={RouterLink}
        to='/'
        aria-label='back'
        style={{ position: 'absolute', top: '10px', left: '10px' }}>
        <ArrowBackIcon />
      </IconButton>
      <Grid container alignItems='center' justifyContent='center' style={{ minHeight: '100vh' }}>
        <Grid item xs={10} sm={6} md={8}>
          <Box elevation={3} sx={{ p: 3 }} boxShadow={3}>
            <Typography variant='h5' align='center' gutterBottom>
              Create an Account
            </Typography>
            <form onSubmit={handleRegistration} >
              <TextField
                fullWidth
                label="First Name"
                name="firstName"
                value={formData.firstName}
                onChange={handleChange}
                error={!!errors.firstName}
                helperText={errors.firstName}
                style={{ marginBottom: '16px' }}
              />
              <TextField
                fullWidth
                label="Last Name"
                name="lastName"
                value={formData.lastName}
                onChange={handleChange}
                error={!!errors.lastName}
                helperText={errors.lastName}
                style={{ marginBottom: '16px' }}
              />
              <TextField
                fullWidth
                label="Email Address"
                name="email"
                type="email"
                value={formData.email}
                onChange={handleChange}
                error={!!errors.email}
                helperText={errors.email}
                style={{ marginBottom: '16px' }}
              />
              <TextField
                fullWidth
                label="Password"
                name="password"
                type="password"
                value={formData.password}
                onChange={handleChange}
                error={!!errors.password}
                helperText={errors.password}
              />
              <TextField
                fullWidth
                label='Confirm Password'
                type='password'
                name='confirmPassword'
                value={formData.confirmPassword}
                onChange={handleChange}
                variant='outlined'
                margin='normal'
                error={errors.confirmPassword !== ''}
                helperText={errors.confirmPassword}
              />
              <Button type='submit' variant='contained' color='primary' fullWidth style={{ marginTop: '16px' }} >
                Register
              </Button>
            </form>
            <Snackbar
              anchorOrigin={{
                vertical: 'top', 
                horizontal: 'center' 
              }}
              open={snackbarOpen}
              autoHideDuration={6000}
              onClose={handleCloseSnackbar}
              message={snackbarMessage}
            />
            <Snackbar
              anchorOrigin={{
                vertical: 'top', 
                horizontal: 'center' 
              }}
              open={errorSnackbarOpen}
              autoHideDuration={6000}
              onClose={handleCloseSnackbar}
              message={errorSnackbarMessage}
            />
            <Typography variant='body2' align='center' style={{ marginTop: 10 }}>
              Already have an account?{' '}
              <Link href='/login' underline='hover'>
                Log in Here
              </Link>
            </Typography>
          </Box>
        </Grid>
      </Grid>
    </div>
  );
};

export default Register;
