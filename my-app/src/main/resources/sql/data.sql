-- Insert sample users
INSERT INTO Users (username, email, password_hash) VALUES
('john_doe', 'john@example.com', 'hashed_password_1'),
('jane_doe', 'jane@example.com', 'hashed_password_2');

-- Insert sample restaurants
INSERT INTO Restaurants (name, location, cuisine_type, rating) VALUES
('Pizza Palace', 'Downtown', 'Italian', 4.5),
('Sushi Corner', 'Uptown', 'Japanese', 4.7);

-- Insert sample reservations
INSERT INTO Reservations (user_id, restaurant_id, reservation_date, num_guests) VALUES
(1, 1, '2024-12-06 19:00:00', 4),
(2, 2, '2024-12-07 20:00:00', 2);
