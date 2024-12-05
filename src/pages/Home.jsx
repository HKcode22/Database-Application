import React from 'react';
import '../styles/Center.css';
import { Typography, Box, Grid} from '@mui/material';
import Navbar  from '../components/Navbar';
function Home() {
  return (
    <Grid>
      <Navbar />

    <Box sx={{
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
      justifyContent: 'center',
      height: '100vh',
      textAlign: 'center'
    }}>
      <Typography variant = 'h2'>Welcome to the Restaurant Reservation System</Typography>
      <p>Book a table at your favorite restaurant with ease!</p>
      <button>Test</button>
      
    
    </Box>
    </Grid>
  );
}

export default Home;