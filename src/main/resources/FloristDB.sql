CREATE TABLE Trees(
	id VARCHAR(4) PRIMARY KEY REFERENCES Products(id),
    height FLOAT
    );
CREATE TABLE Flowers(
	id VARCHAR(4) PRIMARY KEY REFERENCES Products(id),
    color VARCHAR(25)
    );
CREATE TABLE Decorations(
	id VARCHAR(4) PRIMARY KEY REFERENCES Products(id),
    material VARCHAR(10),
    CONSTRAINT material_CK CHECK(material("Wood", "Metal"))
    );
    CREATE TABLE Products(
    id VARCHAR(4) PRIMARY KEY,
    price DOUBLE,
    amount INT
    );
CREATE TABLE Purchases(
	productId INT PRIMARY KEY REFERENCES Products(id),
    ticketID INT REFERENCES Tickets(id),
    amount INT
    );
CREATE TABLE Tickets(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    totalPrice DOUBLE,
    floristId INT REFERENCES Florists(id)
    );
CREATE TABLE Florists(
	id INT AUTO_INCREMENT PRIMARY KEY,
    floristName VARCHAR(75)
    );