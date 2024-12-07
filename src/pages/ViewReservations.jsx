import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Typography, Grid, Box, Paper, List, ListItem, ListItemText } from '@mui/material';
import Navbar from '../components/Navbar';

function ViewReservations() {
  const [reservations, setReservations] = useState([]);
  const [error, setError] = useState('');

  // Function to format the reservation date
  const formatDate = (dateArray) => {
    return `${dateArray[0]}-${dateArray[1].toString().padStart(2, '0')}-${dateArray[2].toString().padStart(2, '0')}`;
  };

  // Function to format the reservation time
  const formatTime = (timeArray) => {
    return `${timeArray[0].toString().padStart(2, '0')}:${timeArray[1].toString().padStart(2, '0')}`;
  };

  useEffect(() => {
    const fetchReservations = async () => {
      const storedCustomer = sessionStorage.getItem('customer'); // Ensure 'customer' key is used
      if (storedCustomer) {
        const customerId = JSON.parse(storedCustomer); // Parse stored customer ID

        try {
          const response = await axios.get(`http://localhost:8080/api/reservations/${customerId}`);
          
          if (response.status === 200 && response.data.length > 0) {
            setReservations(response.data); // Store reservations list directly
          } else {
            setError('No reservations found');
          }
        } catch (error) {
          console.error('Error fetching reservations:', error);
          setError('Failed to load reservations');
        }
      } else {
        setError('No customer logged in');
      }
    };

    fetchReservations();
  }, []);

  return (
    <>
      <Grid>
        <Box>
          <Navbar />
        </Box>
        <Box>
          <Typography variant="h4" style={{ textAlign: 'center', color: 'black', fontWeight: 'bold' }}>
            View Reservations
          </Typography>
          {error && (
            <Typography variant="body1" style={{ color: 'red', textAlign: 'center' }}>
              {error}
            </Typography>
          )}
          <List>
            {reservations.length > 0 ? (
              reservations.map((reservation) => (
                <Paper key={reservation.reservationId} elevation={3} style={{ margin: '10px', padding: '10px' }}>
                  <ListItem>
                    <ListItemText
                      primary={`Reservation at ${reservation.restaurant.name} on ${formatDate(reservation.reservationDate)} at ${formatTime(reservation.reservationTime)}`}
                      secondary={`Party Size: ${reservation.partySize} | Status: ${reservation.status}`}
                    />
                  </ListItem>
                </Paper>
              ))
            ) : (
              <Typography variant="body1" style={{ textAlign: 'center', marginTop: '20px' }}>
                No reservations found.
              </Typography>
            )}
          </List>
        </Box>
      </Grid>
    </>
  );
}

export default ViewReservations;
