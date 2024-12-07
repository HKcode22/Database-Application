import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import { useNavigate } from 'react-router-dom';
import { Typography } from '@mui/material';

function Profile() {
  const [user, setUser] = useState(null); // To hold user details
  const [editMode, setEditMode] = useState(false); // To toggle edit mode
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProfile = async () => {
      const storedUser = sessionStorage.getItem('user');
      if (storedUser) {
        const parsedUser = JSON.parse(storedUser);
        setUser(parsedUser);
        setEmail(parsedUser.email);
        setPhoneNumber(parsedUser.phoneNumber || ''); // Handle missing phone number gracefully
      } else {
        console.error('No user found in session storage');
      }
      setLoading(false);
    };

    fetchProfile();
  }, []);

  const handleEditToggle = () => {
    setEditMode(!editMode);
  };

  const handleUpdate = async (e) => {
    e.preventDefault();

    if (!user) {
      alert('User not logged in.');
      return;
    }

    try {
      const response = await axios.put(`http://localhost:8080/api/customers/${user.customerId}`, {
        email,
        password,
        phoneNumber,
      });

      alert('Account updated successfully!');
      // Update session storage and user state
      const updatedUser = { ...user, email, phoneNumber };
      sessionStorage.setItem('user', JSON.stringify(updatedUser));
      setUser(updatedUser);
      setEditMode(false);
    } catch (error) {
      console.error('Error updating account:', error);
      alert('An error occurred while updating your account. Please try again.');
    }
  };

  const handleLogout = () => {
    sessionStorage.removeItem('user');
    alert('You have been logged out.');
    navigate('/login'); // Redirect to login page
  };

  const handleDeleteAccount = async () => {
    if (!user) {
      alert('User not logged in.');
      return;
    }

    const confirmDelete = window.confirm('Are you sure you want to delete your account? This action cannot be undone.');
    if (!confirmDelete) return;

    try {
      await axios.delete(`http://localhost:8080/api/customers/${user.customerId}`);
      sessionStorage.removeItem('user');
      alert('Account deleted successfully.');
      navigate('/register'); // Redirect to registration page
    } catch (error) {
      console.error('Error deleting account:', error);
      alert('An error occurred while deleting your account. Please try again.');
    }
  };

  if (loading) {
    return (
      <div>
        <Navbar />
        <Typography sx={{ textAlign: 'center' }}>Loading...</Typography>
      </div>
    );
  }

  if (!user) {
    return (
      <div>
        <Navbar />
        <Typography sx={{ textAlign: 'center' }}>You are not logged in. Please log in to view your profile.</Typography>
      </div>
    );
  }

  return (
    <div className="center-container">
      <Navbar />
      <h2>Profile</h2>
      <p>Welcome, {user.name || 'User'}</p>

      {!editMode ? (
        <div>
          <p><strong>Email:</strong> {user.email}</p>
          <p><strong>Phone Number:</strong> {user.phoneNumber || 'Not provided'}</p>
          <button onClick={handleEditToggle}>Edit Account</button>
          <button onClick={handleLogout}>Logout</button>
          <button onClick={handleDeleteAccount}>Delete Account</button>
        </div>
      ) : (
        <form onSubmit={handleUpdate}>
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
            />
          </div>
          <div>
            <label>Phone Number:</label>
            <input
              type="text"
              value={phoneNumber}
              onChange={(e) => setPhoneNumber(e.target.value)}
            />
          </div>
          <button type="submit">Update</button>
          <button type="button" onClick={handleEditToggle}>Cancel</button>
        </form>
      )}
    </div>
  );
}

export default Profile;
