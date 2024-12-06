import React, { useState } from 'react';
import axios from 'axios';
import { Typography, Grid, Box, Paper} from '@mui/material';
import Navbar from '../components/Navbar';

function ViewReservations() {


    return (
      <>
      <Grid

            
        
        >
        <Box>
            <Navbar />
        </Box>
        <Box>
            <Box>
              
          <Typography variant = 'h4'  style={{ textAlign: 'center', color: 'black', fontWeight: 'bold'}}>View Reservations</Typography>
          </Box>
  
  
  
          </Box>
  
          
  
  
  
      </Grid>
  </>
  
    )
  }

  export default ViewReservations;