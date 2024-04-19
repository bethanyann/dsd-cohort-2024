import { Outlet } from "react-router-dom";
import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Link from "@mui/material/Link";
import { useState } from "react";
import axios from "axios";
import { Link as RouterLink } from "react-router-dom";
import IconButton from "@mui/material/IconButton";
import Snackbar from "@mui/material/Snackbar";
import Alert from "@mui/material/Alert";
import { useNavigate } from "react-router-dom";

const Login = (props) => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const [errors, setErrors] = useState({
    email: "",
    password: "",
  });

  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState("");
  const [errorSnackbarOpen, setErrorSnackbarOpen] = useState(false);
  const [errorSnackbarMessage, setErrorSnackbarMessage] = useState("");
  const setUserInfo = props.setUserInfo;

  const validateEmail = (email) => {
    // Regular expression for email validation
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(String(email).toLowerCase());
  };

  const handleLogin = (e) => {
    e.preventDefault();
    let newErrors = {};

    // Check for blank fields
    for (const key in formData) {
      if (formData[key] === "") {
        newErrors[key] = "This field is required";
      }
    }

    // Validate email
    if (!validateEmail(formData.email)) {
      newErrors.email = "Invalid email address";
    }

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      setErrorSnackbarMessage("Please fix the errors in the form");
      setErrorSnackbarOpen(true);
    } else {
      // Send login data to backend
      axios
        .post("http://localhost:8080/api/v0/users/auth", formData)
        .then((response) => {
          console.log("Login successful:", response.data);
          setFormData({
            email: "",
            password: "",
          });
          setErrors({
            email: "",
            password: "",
          });
          setSnackbarMessage("Login successful!");
          setSnackbarOpen(true);
          // Redirect to dashboard after successful login
          setUserInfo(response.data);
          navigate("/dashboard");
        })
        .catch((error) => {
          console.error("Error logging in:", error);
          setErrorSnackbarMessage("Incorrect email or password");
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
      [name]: "",
    });
  };

  const handleCloseSnackbar = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    setSnackbarOpen(false);
    setErrorSnackbarOpen(false);
  };

  return (
    <div>
      <Outlet />
      <CssBaseline />
      <Grid container alignItems="center" justifyContent="center" style={{ minHeight: "100vh" }}>
        <Grid item xs={10} sm={6} md={8}>
          <Box elevation={3} sx={{ p: 3 }} boxShadow={3}>
            <Typography variant="h5" align="center" gutterBottom>
              Log In
            </Typography>
            <form onSubmit={handleLogin}>
              <TextField
                fullWidth
                label="Email Address"
                name="email"
                type="email"
                value={formData.email}
                onChange={handleChange}
                error={!!errors.email}
                helperText={errors.email}
                style={{ marginBottom: "16px" }}
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
              <Button type="submit" variant="contained" color="primary" fullWidth style={{ marginTop: "16px" }}>
                Log In
              </Button>
            </form>
            <Snackbar
              anchorOrigin={{
                vertical: "top",
                horizontal: "center",
              }}
              open={snackbarOpen}
              autoHideDuration={6000}
              onClose={handleCloseSnackbar}
              message={snackbarMessage}
            />
            <Snackbar
              anchorOrigin={{
                vertical: "top",
                horizontal: "center",
              }}
              open={errorSnackbarOpen}
              autoHideDuration={6000}
              onClose={handleCloseSnackbar}
              message={errorSnackbarMessage}
            />
            <Typography variant="body2" align="center" style={{ marginTop: 10 }}>
              Don't have an account?{" "}
              <Link component={RouterLink} to="/register" underline="hover">
                Sign Up Here
              </Link>
            </Typography>
          </Box>
        </Grid>
      </Grid>
    </div>
  );
};

export default Login;
