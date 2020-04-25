DROP TABLE IF EXISTS customers;

create table customers (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) not null,
	email VARCHAR(50) unique,
	phone VARCHAR(50) unique,
	city VARCHAR(50),
	state VARCHAR(50),
	country VARCHAR(50),
	gender VARCHAR(50)
);