// src/components/Navbar.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Navbar.css';  // Make sure to import the CSS file for styling

function Navbar() {
  return (
    <nav className="navbar">
      <ul className="navbar-links">
        <li><Link to="/">Home</Link></li>
        <li><Link to="/login">Login</Link></li>
        <li><Link to="/register">Register</Link></li>
        <li><Link to="/restaurants">Restaurants</Link></li>
        <li><Link to="/reservation">Reservation</Link></li>
        <li><Link to="/profile">Profile</Link></li>
        <li><Link to="/viewReservations">View Reservations</Link></li>
      </ul>
    </nav>
  );
}

export default Navbar;