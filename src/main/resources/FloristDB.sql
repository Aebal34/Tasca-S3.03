CREATE TABLE Trees(
	id INT REFERENCES Products(id),
    productType CHARACTER REFERENCES Products(productType),
    height DOUBLE,
    PRIMARY KEY (ProductType, id),
	CONSTRAINT productType_CK CHECK(productType('T')));
CREATE TABLE Flowers(
	id INT REFERENCES Products(id),
    productType CHARACTER REFERENCES Products(productType),
    color VARCHAR(25),
    PRIMARY KEY (ProductType, id),	
	CONSTRAINT productType_CK CHECK(productType('F')));
CREATE TABLE Decorations(
	id INT REFERENCES Products(id),
    material VARCHAR(10),
    productType CHARACTER REFERENCES Products(productType),
    PRIMARY KEY (ProductType, id),
    CONSTRAINT material_CK CHECK(material("Wood", "Metal")),
     CONSTRAINT productType_CK CHECK(productType('D')));
CREATE TABLE Products(
	id INT AUTO_INCREMENT PRIMARY KEY,
    price DOUBLE,
    ammount INT,
	productType CHARACTER
    CONSTRAINT productType_CK CHECK(productType('T','F','D')));
CREATE TABLE Purchases(
	productId INT PRIMARY KEY REFERENCES Products(id),
    ticketID INT REFERENCES Tickets(id),
    ammount INT);
CREATE TABLE Tickets(
	id INT AUTO_INCREMENT PRIMARY KEY,
    totalPrice DOUBLE,
    floristId INT REFERENCES Florists(id));
CREATE TABLE Florists(
	id INT AUTO_INCREMENT PRIMARY KEY,
    floristName VARCHAR(75));