import React, { useState } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import '../styles/Center.css'; 

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(''); // Reset error state before trying the login

    try {
      const response = await axios.post('http://localhost:8080/api/users/login', {
        email,
        password,
      });

      // Assuming response.data contains user details like customerId, message, etc.
      console.log('Login successful:', response.data);

      // Store the customerId in sessionStorage
      sessionStorage.setItem('customer', JSON.stringify(response.data.customerId));

      // Redirect or perform some action (if necessary)
      alert('Login successful!');
    } catch (error) {
      if (error.response) {
        // Server responded with a status other than 200
        console.error('Error response:', error.response.data);
        setError(`Error: ${error.response.data.message || 'Login failed'}`);
      } else if (error.request) {
        // Request was made but no response received
        console.error('No response from server:', error.request);
        setError('No response from the server. Check network.');
      } else {
        console.error('Error setting up request:', error.message);
        setError('An error occurred. Check the console for details.');
      }
    }
  };

  return (
    <div className="center-container">
      <Navbar />
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Email:</label>
          <input 
            type="email" 
            value={email} 
            onChange={(e) => setEmail(e.target.value)} 
            required 
          />
        </div>
        <div>
          <label>Password:</label>
          <input 
            type="password" 
            value={password} 
            onChange={(e) => setPassword(e.target.value)} 
            required 
          />
        </div>
        {error && <div style={{ color: 'red' }}>{error}</div>} {/* Show error message if any */}
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default Login;
