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
// import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import IconButton from '@mui/material/IconButton';

const Register = () => {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
  });
  const [errors, setErrors] = useState({
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    setErrors({ ...errors, [e.target.name]: '' }); // Clear error when input changes
  };

  const handleRegistration = async (e) => {
    e.preventDefault();

    const newErrors = {};

    // Validation
    if (formData.username.length < 5) {
      newErrors.username = 'Username must be at least 5 characters long';
    }

    if (formData.email.trim().length === 0 || !formData.email.includes('@') || !formData.email.endsWith('.com')) {
      newErrors.email = 'Invalid email address';
    }

    if (formData.password.length < 9) {
      newErrors.password = 'Password must be at least 9 characters long';
    }

    if (!/\d/.test(formData.password)) {
      newErrors.password = 'Password must contain at least one number';
    }

    if (!/[a-zA-Z]/.test(formData.password)) {
      newErrors.password = 'Password must contain at least one letter';
    }

    if (formData.password !== formData.confirmPassword) {
      newErrors.confirmPassword = 'Passwords do not match';
    }

    // Update errors state
    setErrors(newErrors);

    // Submit registration data if there are no validation errors
    if (Object.keys(newErrors).length === 0) {
      try {
        //TODO: change to the proper registration route
        const response = await axios.post('http://localhost:3001/register', formData);
        // handle successful response from backend
        console.log(response.data);
      } catch (error) {
        // handle error response from backend
        console.error('Error:', error);
      }
    }
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
        {/* <ArrowBackIcon /> */}
      </IconButton>
      <Grid container alignItems='center' justifyContent='center' style={{ minHeight: '100vh' }}>
        <Grid item xs={10} sm={6} md={8}>
          <Box elevation={3} sx={{ p: 3 }} boxShadow={3}>
            <Typography variant='h5' align='center' gutterBottom>
              Create an Account
            </Typography>
            <form onSubmit={handleRegistration}>
              <TextField
                fullWidth
                label='First Name'
                name='firstName'
                value={formData.firstName}
                onChange={handleChange}
                variant='outlined'
                margin='normal'
              />
              <TextField
                fullWidth
                label='Last Name'
                name='lastName'
                value={formData.lastName}
                onChange={handleChange}
                variant='outlined'
                margin='normal'
              />
              <TextField
                fullWidth
                label='Username'
                name='username'
                value={formData.username}
                onChange={handleChange}
                variant='outlined'
                margin='normal'
                error={errors.username !== ''}
                helperText={errors.username}
              />
              <TextField
                fullWidth
                label='Email'
                name='email'
                value={formData.email}
                onChange={handleChange}
                variant='outlined'
                margin='normal'
                error={errors.email !== ''}
                helperText={errors.email}
              />
              <TextField
                fullWidth
                label='Password'
                type='password'
                name='password'
                value={formData.password}
                onChange={handleChange}
                variant='outlined'
                margin='normal'
                error={errors.password !== ''}
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
              <Button type='submit' variant='contained' color='primary' fullWidth>
                Register
              </Button>
            </form>
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
