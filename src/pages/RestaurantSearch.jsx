import React, { useState } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import { useNavigate } from 'react-router-dom';

function RestaurantSearch() {
  const [location, setLocation] = useState('');
  const [category, setCategory] = useState('');
  const [name, setName] = useState('');
  const [restaurants, setRestaurants] = useState([]);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSearch = async () => {
    try {
      // Reset error state before making a new request
      setError('');

      // Construct query parameters only with non-empty values
      const params = {};
      if (location) params.location = location;
      if (category) params.category = category;
      if (name) params.name = name;

      const response = await axios.get('http://localhost:8080/api/search/restaurants', { params });
      setRestaurants(response.data);

      if (response.data.length === 0) {
        setError('No restaurants found matching the criteria.');
      }
    } catch (error) {
      console.error('Error fetching restaurants', error);
      setError('An error occurred while fetching restaurants. Please try again.');
    }
  };

  const handleReserve = (restaurantId, restaurantName) => {
    navigate(`/reserve/${restaurantId}`, { state: { restaurantName } }); // Pass restaurant name via state
  };

  return (
    <div className="center-container">
      <Navbar />
      <h2>Search Restaurants</h2>
      <label>Search Restaurants or Location</label>
      <div>
        <label>Search:</label>
        <input type="text" value={location} onChange={(e) => setLocation(e.target.value)} />
      </div>

      <button onClick={handleSearch}>Search</button>

      {error && <p className="error-message">{error}</p>}

      <ul>
        {restaurants.length > 0 &&
          restaurants.map((restaurant) => (
            <li key={restaurant.restaurantId}>
              <strong>{restaurant.name}</strong> - {restaurant.streetAddress}, {restaurant.city}, {restaurant.state}
              <button
                onClick={() => handleReserve(restaurant.restaurantId, restaurant.name)}
              >
                Reserve
              </button>
            </li>
          ))}
      </ul>
    </div>
  );
}

export default RestaurantSearch;
