import React from 'react';
import { Typography, Box } from '@mui/material';

function Home() {
  return (
    <>
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          justifyContent: 'center',
          height: '100vh',
          textAlign: 'center',
        }}
      >
        <Typography variant="h2">Welcome to the Restaurant Reservation System</Typography>
        <p>Book a table at your favorite restaurant with ease!</p>
      </Box>
    </>
  );
}

export default Home;