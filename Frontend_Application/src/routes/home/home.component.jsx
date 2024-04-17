import { Outlet } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import pic from "../../assets/tomatoes.png";
import Navbar from "../../components/navbar.component.jsx";
import Landing from "../../components/landing.component.jsx";
import DashboardPage from "../dashboard/dashboard.component.jsx";

  // If user is present, show dashboard // If not, place landing page
const Home = () => {
  const userName = "";
  return (
    <div>
      <Outlet />
      <Navbar />
      {/*  If user is present, show dashboard If not, place landing page */}
      {userName ? <DashboardPage /> : <Landing />}
    </div>
  );
};

export default Home;
