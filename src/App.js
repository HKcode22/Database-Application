import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import RestaurantSearch from './pages/RestaurantSearch';
import Reservation from './pages/Reservation';
import Profile from './pages/Profile';
import Test from './pages/Test';
import ViewReservations from './pages/ViewReservations';


function App() {
  return (
    <Router>
      <div className="container">
        <Routes>
          <Route
            path="/"
            element={
              <div>
                <Navbar />
                <Home />
              </div>
            }
          />
          <Route path="/search" component={RestaurantSearch} />
          <Route path="/reserve/:restaurantId" element={<Reservation />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/restaurants" element={<RestaurantSearch />} />
          <Route path="/reservation" element={<Reservation />} />
          <Route path="/profile" element={<Profile />} />
          <Route path = "/ViewReservations" element={<ViewReservations />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;