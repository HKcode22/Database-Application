-- Login: Verify user credentials
SELECT user_id, username, email
FROM Users
WHERE username = ? AND password_hash = ?;

-- Register: Insert a new user
INSERT INTO Users (username, email, password_hash)
VALUES (?, ?, ?);

-- Search Restaurants
SELECT restaurant_id, name, location, cuisine_type, rating
FROM Restaurants
WHERE name LIKE CONCAT('%', ?, '%') OR location LIKE CONCAT('%', ?, '%');

-- Make a Reservation
INSERT INTO Reservations (user_id, restaurant_id, reservation_date, num_guests)
VALUES (?, ?, ?, ?);

-- View User Reservations
SELECT r.reservation_id, res.name AS restaurant_name, r.reservation_date, r.num_guests
FROM Reservations r
JOIN Restaurants res ON r.restaurant_id = res.restaurant_id
WHERE r.user_id = ?
ORDER BY r.reservation_date DESC;
