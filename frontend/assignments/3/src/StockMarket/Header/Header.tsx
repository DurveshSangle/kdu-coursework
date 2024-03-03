import { AppBar, Box, Link, Toolbar, Typography } from '@mui/material';
import marketLogo from "../../assets/marketLogo.png";
import { useNavigate} from 'react-router-dom';

export const Header = () => {
  const navigation = useNavigate();
  const handleLogoClick = () => {
    navigation("/");
  }
  return (
    <AppBar position="static">
      <Toolbar>
      <a href="/" onClick={handleLogoClick} style={{ marginRight: '10px', textDecoration: 'none' }}>
          <img src={marketLogo} alt="Logo" style={{ height: '40px', cursor: 'pointer' }} />
        </a>
        <Typography variant="h5" component="div">
          KDU Stock Market
        </Typography>
        <Box sx={{ flexGrow: 1 }} />
        <Box>
          <Link href="#" color="inherit" style={{ marginRight: '15px', textDecoration: "none", fontSize:"1.5rem" }}>
            Summarizer
          </Link>
          <Link href="/portfolio" color="inherit" style={{ marginRight: '10px', textDecoration: "none", fontSize:"1.5rem" }}>
            My Portfolio
          </Link>
        </Box>
      </Toolbar>
    </AppBar>
  );
}