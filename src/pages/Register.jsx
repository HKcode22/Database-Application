import React, { useState } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';

function Register() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('customer');
  const [name, setName] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [restaurantDetails, setRestaurantDetails] = useState({
    name: '',
    streetAddress: '',
    city: '',
    state: '',
    zipCode: '',
    phoneNumber: '',
    openingHours: '',
    category: '',
    customCategory: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    const payload = {
      email,
      password,
      role,
    };

    if (role === 'customer') {
      if (!name || !phoneNumber) {
        console.error("Name and Phone Number are required for customer registration");
        return;
      }
      payload.name = name;
      payload.phoneNumber = phoneNumber;
    } else if (role === 'restaurant_admin') {
      if (!restaurantDetails.name) {
        console.error("Restaurant name is required for restaurant registration");
        return;
      }
      // If category is "Other", use the custom category
      if (restaurantDetails.category === "other") {
        restaurantDetails.category = restaurantDetails.customCategory;
      }
      payload.restaurantDetails = restaurantDetails;
    }

    console.log('Payload:', payload);  // Log the payload to ensure all data is present.

    try {
      const response = await axios.post('http://localhost:8080/api/users/register', payload);
      console.log('Registration successful', response.data);
    } catch (error) {
      console.error('Error registering', error);
    }
  };

  return (
    <div className="center-container">
      <Navbar />
      <h2>Register</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Email:</label>
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div>
          <label>Password:</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </div>
        <div>
          <label>Role:</label>
          <select value={role} onChange={(e) => setRole(e.target.value)}>
            <option value="customer">Customer</option>
            <option value="restaurant_admin">Restaurant Owner</option>
          </select>
        </div>

        {role === 'customer' && (
          <>
            <div>
              <label>Name:</label>
              <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
            </div>
            <div>
              <label>Phone Number:</label>
              <input type="text" value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} />
            </div>
          </>
        )}

        {role === 'restaurant_admin' && (
          <>
            <div>
              <label>Restaurant Name:</label>
              <input type="text" value={restaurantDetails.name} onChange={(e) => setRestaurantDetails({ ...restaurantDetails, name: e.target.value })} />
            </div>
            <div>
              <label>Street Address:</label>
              <input type="text" value={restaurantDetails.streetAddress} onChange={(e) => setRestaurantDetails({ ...restaurantDetails, streetAddress: e.target.value })} />
            </div>
            <div>
              <label>City:</label>
              <input type="text" value={restaurantDetails.city} onChange={(e) => setRestaurantDetails({ ...restaurantDetails, city: e.target.value })} />
            </div>
            <div>
              <label>State:</label>
              <input type="text" value={restaurantDetails.state} onChange={(e) => setRestaurantDetails({ ...restaurantDetails, state: e.target.value })} />
            </div>
            <div>
              <label>Zip Code:</label>
              <input type="text" value={restaurantDetails.zipCode} onChange={(e) => setRestaurantDetails({ ...restaurantDetails, zipCode: e.target.value })} />
            </div>
            <div>
              <label>Phone Number:</label>
              <input type="text" value={restaurantDetails.phoneNumber} onChange={(e) => setRestaurantDetails({ ...restaurantDetails, phoneNumber: e.target.value })} />
            </div>
            <div>
              <label>Opening Hours:</label>
              <input type="text" value={restaurantDetails.openingHours} onChange={(e) => setRestaurantDetails({ ...restaurantDetails, openingHours: e.target.value })} />
            </div>
            <div>
              <label>Category:</label>
              <select
                value={restaurantDetails.category}
                onChange={(e) => setRestaurantDetails({ ...restaurantDetails, category: e.target.value })}
              >
                <option value="">Select a category</option>
                <option value="dessert">Dessert</option>
                <option value="fast_food">Fast Food</option>
                <option value="fine_dining">Fine Dining</option>
                <option value="casual_dining">Casual Dining</option>
                <option value="cafe">Cafe</option>
                <option value="other">Other</option>
              </select>
            </div>
            {restaurantDetails.category === 'other' && (
              <div>
                <label>Custom Category:</label>
                <input
                  type="text"
                  value={restaurantDetails.customCategory}
                  onChange={(e) => setRestaurantDetails({ ...restaurantDetails, customCategory: e.target.value })}
                />
              </div>
            )}
          </>
        )}

        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default Register;
