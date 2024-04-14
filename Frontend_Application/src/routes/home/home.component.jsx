import { Outlet } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Navbar from "../../components/navbar.component.jsx";
import Landing from "../../components/landing.component.jsx";

const Home = () => {
  return (
    <div>
      <Outlet />
      <Navbar />
      <Landing />
    </div>
  );
};

export default Home;
