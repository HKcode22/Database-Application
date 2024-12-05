import axios from "axios";

const API_BASE_URL = "http://localhost:3306/api"; //Backend URL

export const fetchRestaurants = () => axios.get(`${API_BASE_URL}/restaurants`);
export const fetchRestaurantDetails = (id) => axios.get(`${API_BASE_URL}/restaurants/${id}`);
export const createReservation = (data) => axios.post(`${API_BASE_URL}/reservations/create`, data);
export const searchRestaurants = (query) => axios.get(`${API_BASE_URL}/search/restaurants`, { params: query });
export const loginUser = (credentials) => axios.post(`${API_BASE_URL}/users/login`, credentials);
export const registerUser = (data) => axios.post(`${API_BASE_URL}/users/register/customer`, data);
