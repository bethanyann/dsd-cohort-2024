import { Outlet } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box"
import pic from "../../assets/tomatoes.png"
import Navbar from "../../components/navbar.component.jsx"

const Home = () => {
  return (
    <div>
      <Outlet />
      <Navbar />
      <Grid
      container
      spacing={0}
      alignItems="center"
    
      justifyContent="flex-start" // Align content to the left
      style={{ minHeight: '100vh' }}
    >
      {/* Grid item for the content */}
      <Grid item xs={12} sm={6} md={6} lg={4}>
        <Box marginLeft={4}> {/* Add left margin for spacing */}
          <Typography variant="h1" gutterBottom align="left">
            Come Shop with us!
          </Typography>
          <Typography variant="body1" paragraph align="left" >
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus eleifend justo ac
            libero vulputate, id fringilla dolor tempus.
          </Typography>
          <Grid 
          container spacing={2} 
          justifyContent="flex-end">
            <Grid item marginRight={4}>
              <Button variant="contained" color="primary" size="large">
                Explore
              </Button>
            </Grid>
          </Grid>
        </Box>
      </Grid>
      {/* Grid item for the image */}
      <Grid item xs={12} sm={6} md={6} lg={8}>
        <img src={pic} alt="fresh veggie image" style={{ width: '75%', height: '75%' }} />
      </Grid>
    </Grid>
    </div>
  );
};

export default Home;
