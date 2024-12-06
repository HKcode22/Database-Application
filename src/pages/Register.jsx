import React, { useState } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
function Register() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('Customer');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Send data as JSON in the request body
      const response = await axios.post('http://localhost:8080/api/users/register', {
        email,
        password,
        role
      }, {
        headers: {
          'Content-Type': 'application/json' // Ensure the backend knows it's JSON data
        }
      });
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
            <option value="Customer">Customer</option>
            <option value="Restaurant_Admin">Restaurant Owner</option>
          </select>
        </div>
        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default Register;