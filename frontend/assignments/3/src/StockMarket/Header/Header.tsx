import { useState } from 'react';
import { AppBar, Box, IconButton, Link, Toolbar, Typography, Drawer, List, ListItem, ListItemText, useMediaQuery } from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import marketLogo from "../../assets/marketLogo.png";
import { useNavigate } from 'react-router-dom';

export const Header = () => {
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);
  const navigation = useNavigate();

  const handleLogoClick = () => {
    navigation("/");
  };

  const toggleDrawer = () => {
    setIsDrawerOpen(!isDrawerOpen);
  };

  const handleMenuClick = (link) => {
    navigation(link);
    setIsDrawerOpen(false); // Close the drawer after clicking a menu item
  };

  // Check if the screen size is small (less than or equal to 'sm' breakpoint)
  const isSmallScreen = useMediaQuery('(max-width:414px)');

  return (
    <AppBar position="static">
      <Toolbar>
        {isSmallScreen && (
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={toggleDrawer}
            sx={{ mr: 2 }}
          >
            <MenuIcon />
          </IconButton>
        )}
        <a href="/" onClick={handleLogoClick} style={{ marginRight: '10px', textDecoration: 'none' }}>
          <img src={marketLogo} alt="Logo" style={{ height: '40px', cursor: 'pointer' }} />
        </a>
        <Typography variant="h5" component="div" sx={{ flexGrow: 1 }}>
          KDU Stock Market
        </Typography>
        {!isSmallScreen && (
          <Box>
            <Link href="#" color="inherit" sx={{ mr: 2 }} style={{ textDecoration: "none", fontSize:"1.5rem" }}>
              Summarizer
            </Link>
            <Link href="/portfolio" color="inherit" style={{ textDecoration: "none", fontSize:"1.5rem" }}>
              My Portfolio
            </Link>
          </Box>
        )}
      </Toolbar>
      <Drawer
        anchor="right"
        open={isDrawerOpen}
        onClose={toggleDrawer}
      >
        <List sx={{ width: 250 }}>
          <ListItem button onClick={() => handleMenuClick('/')}>
            <ListItemText primary="Home" />
          </ListItem>
          <ListItem button onClick={() => handleMenuClick('#')}>
            <ListItemText primary="Summarizer" />
          </ListItem>
          <ListItem button onClick={() => handleMenuClick('/portfolio')}>
            <ListItemText primary="My Portfolio" />
          </ListItem>
        </List>
      </Drawer>
    </AppBar>
  );
}
