--table for user tables
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('customer', 'restaurant_admin') NOT NULL,
    last_login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--Customers table (linked with Users)
CREATE TABLE Customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    name VARCHAR(100),
    phone_number VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

--Restaurants table (linked with Users)
CREATE TABLE Restaurants (
    restaurant_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    name VARCHAR(100),
    email VARCHAR(100),
    street_address VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    zip_code VARCHAR(10),
    category_id INT,
    phone_number VARCHAR(15),
    opening_hours VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (category_id) REFERENCES Categories(category_id)
);

--Reservations table
CREATE TABLE Reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    reservation_date DATE NOT NULL,
    reservation_time TIME NOT NULL,
    party_size INT NOT NULL,
    timeCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('pending', 'confirmed', 'cancelled') DEFAULT 'pending',
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(restaurant_id)
);

--Tables table for managing seating capacity and availability
CREATE TABLE Tables (
    table_id INT AUTO_INCREMENT PRIMARY KEY,
    restaurant_id INT NOT NULL,
    capacity INT NOT NULL,
    status ENUM('available', 'occupied') DEFAULT 'available',
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(restaurant_id)
);

--Menus table for restaurant menu 
CREATE TABLE Menus (
    menu_id INT AUTO_INCREMENT PRIMARY KEY,
    restaurant_id INT NOT NULL,
    item_name VARCHAR(100) NOT NULL,
    item_price DECIMAL(10, 2) NOT NULL,
    description TEXT,
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(restaurant_id)
);

--Reviews table for customer reviews of restaurants (ensures only 1-5 for the stars)
CREATE TABLE Reviews (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5)
    review_text TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(restaurant_id)
);

-- Categories for restaurant types (Italian, American, etc.)
CREATE TABLE Categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL
);

--ReservationHistory table for storing customer's past reservations
CREATE TABLE ReservationHistory (
    history_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    reservation_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
    FOREIGN KEY (reservation_id) REFERENCES Reservations(reservation_id)
);

-- Notifications table for alerting users (customers/restaurants)
CREATE TABLE Notifications (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('unread', 'read') DEFAULT 'unread',
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
