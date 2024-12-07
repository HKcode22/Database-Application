import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Typography, Grid, Box, Paper, List, ListItem, ListItemText } from '@mui/material';
import Navbar from '../components/Navbar';

function ViewReservations() {
  const [reservations, setReservations] = useState([]);
  const [error, setError] = useState('');

  // Fetch the user's reservations on component mount
  useEffect(() => {
    const fetchReservations = async () => {
      const storedUser = sessionStorage.getItem('user');
      if (storedUser) {
        const user = JSON.parse(storedUser);  // Parse the stored user data

        try {
          const response = await axios.get(`http://localhost:8080/api/reservations/${user.userId}`);
          setReservations(response.data);
        } catch (error) {
          console.error('Error fetching reservations:', error);
          setError('Failed to load reservations');
        }
      } else {
        setError('No user logged in');
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
                      primary={`Reservation at ${reservation.restaurant.name} on ${reservation.reservationDate} at ${reservation.reservationTime}`}
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
