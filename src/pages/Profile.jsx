import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import { Typography } from '@mui/material';
function Profile() {
  const [reservations, setReservations] = useState([]);
  const [user, setUser] = useState(null); // To hold user details (if needed)

  // Fetch user profile and reservations when the component mounts
  useEffect(() => {
    const fetchProfile = async () => {
      try {
        // Assume there's an API endpoint that fetches the current user's profile
        const userResponse = await axios.get('/api/user');
        setUser(userResponse.data);

        // Fetch reservations for the logged-in user
        const reservationsResponse = await axios.get('/api/reservations');
        setReservations(reservationsResponse.data);
      } catch (error) {
        console.error('Error fetching profile data', error);
      }
    };
    fetchProfile();
  }, []);

  // Handle reservation cancellation
  const cancelReservation = async (reservationId) => {
    try {
      await axios.delete(`/api/reservations/${reservationId}`);
      setReservations(reservations.filter(reservation => reservation.id !== reservationId));
      alert('Reservation cancelled');
    } catch (error) {
      console.error('Error cancelling reservation', error);
    }
  };

  if (!user) {
    return <div> 
    <Navbar />
      <Typography sx = {{textAlign: 'center'}}> Not Logged In </Typography>
      </div>
      
  }

  return (
    <div className="center-container">
      
      <h2>Profile</h2>
      <p>Welcome, {user.name}</p>
      <h3>Your Reservations</h3>

      {reservations.length > 0 ? (
        <ul>
          {reservations.map((reservation) => (
            <li key={reservation.id}>
              <p>
                <strong>Restaurant:</strong> {reservation.restaurantName} <br />
                <strong>Date:</strong> {reservation.date} <br />
                <strong>Time:</strong> {reservation.time} <br />
                <strong>Party Size:</strong> {reservation.partySize} <br />
                <button onClick={() => cancelReservation(reservation.id)}>Cancel Reservation</button>
              </p>
            </li>
          ))}
        </ul>
      ) : (
        <p>You don't have any reservations yet.</p>
      )}
    </div>
  );
}

export default Profile;