import React from "react";
import { styled, useTheme } from "@mui/material/styles";
import {
  List,
  ListItemButton,
  ListItemIcon,
  ListItemText,
} from "@mui/material";
import CottageIcon from "@mui/icons-material/Cottage";
import ShoppingBagIcon from "@mui/icons-material/ShoppingBag";
import RestaurantIcon from "@mui/icons-material/Restaurant";
import SettingsIcon from "@mui/icons-material/Settings";
import { Link as RouterLink } from "react-router-dom";

function Menu() {
  const menuStyle = {
    position: "fixed",
    left: 0,
    top: 64, // Adjust according to your Navbar height
    bottom: 0,
    width: "20%", // Adjust as needed
    backgroundColor: "#ffffff",
    borderRight: "1px solid #ccc",
    padding: "16px",
    overflowY: "auto",
  };

  return (
    <div style={menuStyle}>
      <List>
        <ListItemButton
          sx={{ marginTop: "2rem" }}
          component={RouterLink}
          to='/dashboard'
        >
          <ListItemIcon>
            <CottageIcon />
          </ListItemIcon>
          <ListItemText primary='My Dashboard' />
        </ListItemButton>
        <ListItemButton
          sx={{ marginTop: "2rem" }}
          component={RouterLink}
          to='/mygrocerylist'
        >
          <ListItemIcon>
            <ShoppingBagIcon />
          </ListItemIcon>
          <ListItemText primary='My Grocery List' />
        </ListItemButton>
        <ListItemButton
          sx={{ marginTop: "2rem" }}
          component={RouterLink}
          to='/myrecipes'
        >
          <ListItemIcon>
            <RestaurantIcon />
          </ListItemIcon>
          <ListItemText primary=' My Recipes' />
        </ListItemButton>
        <ListItemButton
          sx={{ marginTop: "2rem" }}
          component={RouterLink}
          to='/settings'
        >
          <ListItemIcon>
            <SettingsIcon />
          </ListItemIcon>
          <ListItemText primary='Dietary Preferences' />
        </ListItemButton>
      </List>
    </div>
  );
}

export default Menu;
