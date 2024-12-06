import React, { useState } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';

function RestaurantSearch() {
  const [location, setLocation] = useState('');
  const [category, setCategory] = useState('');
  const [restaurants, setRestaurants] = useState([]);

  const handleSearch = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/restaurants?location=${location}&category=${category}`);
      setRestaurants(response.data);
    } catch (error) {
      console.error('Error fetching restaurants', error);
    }
  };

  return (
    <div className="center-container">
      <Navbar />
      <h2>Search Restaurants</h2>
      <div>
        <label>Location:</label>
        <input type="text" value={location} onChange={(e) => setLocation(e.target.value)} />
      </div>
      <div>
        <label>Category:</label>
        <input type="text" value={category} onChange={(e) => setCategory(e.target.value)} />
      </div>
      <button onClick={handleSearch}>Search</button>

      <ul>
        {restaurants.map((restaurant) => (
          <li key={restaurant.id}>{restaurant.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default RestaurantSearch;