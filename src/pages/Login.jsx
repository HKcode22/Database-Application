import React, { useState } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import '../styles/Center.css'; 

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    try {
      const response = await axios.post('http://localhost:8080/api/users/login', {
        email,
        password,
      });

      // Assuming response.data contains user details like userId, role, etc.
      console.log('Login successful:', response.data);

      // Store the user in sessionStorage
      sessionStorage.setItem('user', JSON.stringify(response.data));

      // Redirect or perform some action
      alert('Login successful!');
    } catch (error) {
      if (error.response) {
        // Server responded with a status other than 200
        console.error('Error response:', error.response.data);
        alert(`Error: ${error.response.data}`);
      } else if (error.request) {
        // Request was made but no response received
        console.error('No response from server:', error.request);
        alert('No response from the server. Check network.');
      } else {
        console.error('Error setting up request:', error.message);
        alert('An error occurred. Check the console for details.');
      }
    }
  };
  
  return (
    <div className="center-container">
      < Navbar />
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
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default Login;
