import React, { useState } from 'react';
import axios from 'axios';
import { useParams, useLocation } from 'react-router-dom';
import Navbar from '../components/Navbar';

function Reservation() {
  const { restaurantId } = useParams(); // Fetch restaurantId from the route
  const { state } = useLocation(); // Access state passed from navigate
  const restaurantName = state?.restaurantName || 'Unknown Restaurant'; // Fallback in case state is missing
  const [date, setDate] = useState('');
  const [time, setTime] = useState('');
  const [partySize, setPartySize] = useState(1);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/create', {
        restaurantId,
        date,
        time,
        partySize,
      });
      console.log('Reservation successful', response.data);
    } catch (error) {
      console.error('Error making reservation', error);
    }
  };

  return (
    <div className="center-container">
      <Navbar />
      <h2>Make a Reservation</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Restaurant ID:</label>
          <input type="text" value={restaurantId} readOnly />
        </div>
        <div>
          <label>Restaurant Name:</label>
          <input type="text" value={restaurantName} readOnly />
        </div>
        <div>
          <label>Date:</label>
          <input type="date" value={date} onChange={(e) => setDate(e.target.value)} required />
        </div>
        <div>
          <label>Time:</label>
          <input type="time" value={time} onChange={(e) => setTime(e.target.value)} required />
        </div>
        <div>
          <label>Party Size:</label>
          <input type="number" value={partySize} onChange={(e) => setPartySize(e.target.value)} required min="1" />
        </div>
        <button type="submit">Reserve</button>
      </form>
    </div>
  );
}

export default Reservation;
