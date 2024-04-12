import React from 'react';
import { styled, useTheme } from '@mui/material/styles';
import { Drawer, List, ListItemButton, ListItemIcon, ListItemText } from '@mui/material';
import ShoppingBagIcon from '@mui/icons-material/ShoppingBag';
import RestaurantIcon from '@mui/icons-material/Restaurant';
import SettingsIcon from '@mui/icons-material/Settings';
import { Link as RouterLink } from 'react-router-dom';

function Menu() {
    const DrawerHeader = styled('div')(({ theme }) => ({
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'flex-end',
        padding: theme.spacing(0, 1),
        // necessary for content to be below app bar
        ...theme.mixins.toolbar,
    }));

    return (
        <Drawer variant="permanent">
            <DrawerHeader />
                <List>
                    <ListItemButton sx={{marginTop: "4rem"}} component={RouterLink} to="/groceries">
                        <ListItemIcon>
                            <ShoppingBagIcon />
                        </ListItemIcon>
                        <ListItemText primary="My Grocery List" />
                    </ListItemButton>
                    <ListItemButton sx={{marginTop: "4rem"}} component={RouterLink} to="/recipes">
                        <ListItemIcon>
                            <RestaurantIcon />
                        </ListItemIcon>
                        <ListItemText primary=" My Recipes" />
                    </ListItemButton>
                    <ListItemButton sx={{marginTop: "4rem"}} component={RouterLink} to="/settings">
                        <ListItemIcon>
                            <SettingsIcon />
                        </ListItemIcon>
                        <ListItemText primary="Dietary Preferences" />
                    </ListItemButton>
                </List>
        </Drawer>
    );
}

export default Menu;