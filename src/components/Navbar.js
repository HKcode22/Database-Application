import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/Navbar.css'; 

function Navbar() {
  const navigate = useNavigate();
  const user = JSON.parse(sessionStorage.getItem('customer')); 

  const handleLogout = () => {
    sessionStorage.removeItem('customer'); 
    navigate('/login');
  };

  return (
    <nav className="navbar">
      <ul className="navbar-links">
        <li><Link to="/">Home</Link></li>
        {!user && <li><Link to="/login">Login</Link></li>}
        {!user && <li><Link to="/register">Register</Link></li>}
        {user && <li><Link to="/restaurants">Restaurants</Link></li>}
        {user && <li><Link to="/reservation">Reservation</Link></li>}
        {user && <li><Link to="/profile">Profile</Link></li>}
        {user && <li><Link to="/viewReservations">View Reservations</Link></li>}
        {user && (
          <li>
            <button onClick={handleLogout} className="logout-button">Logout</button>
          </li>
        )}
      </ul>
    </nav>
  );
}

export default Navbar;
