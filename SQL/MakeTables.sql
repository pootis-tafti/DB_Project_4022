-- Active: 1716117406866@@localhost@3306@main
CREATE TABLE Accounts (
    AID INT NOT NULL,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Phone VARCHAR(50),
    Email VARCHAR(50),
    ActiveCity VARCHAR(50),
    PRIMARY KEY (AID)
);

CREATE TABLE Businesses (
    BID INT NOT NULL,
    OID INT NOT NULL,
    Type VARCHAR(50),
    SerialNUMBER INT,
    Name VARCHAR(50),
    PRIMARY KEY (BID),
    FOREIGN KEY (AID) REFERENCES Accounts(AID)
);

CREATE TABLE Addresses(
    BID INT NOT NULL, 
    Address VARCHAR(255),
    CID INT NOT NULL,
    FOREIGN KEY (BID) REFERENCES Businesses(BID),
    FOREIGN KEY (CID) REFERENCES Cities(id)
);

CREATE TABLE Advertisements (
    ADDID INT PRIMARY KEY,
    BID INT,
    AID INT,
    Description TEXT,
    Title VARCHAR(50),
    Location VARCHAR(255),
    Date DATE,
    Price INT,
    FOREIGN KEY (BID) REFERENCES Businesses(BID),
    FOREIGN KEY (AID) REFERENCES Accounts(AID)
);

CREATE TABLE Accesses (
    BID INT,
    AID INT,
    Level TINYINT,
    FOREIGN KEY (BID) REFERENCES Businesses(BID),
    FOREIGN KEY (AID) REFERENCES Accounts(AID)
);

CREATE TABLE Comments (
    ADDID INT ,
    Type VARCHAR(50),
    Description TEXT,
    FOREIGN KEY (ADDID) REFERENCES Advertisements(ADDID)
);

CREATE TABLE AddStatus (
    ADDID INT PRIMARY KEY,
    AdminNote TEXT,
    LastUpdated DATE,
    FOREIGN KEY (ADDID) REFERENCES Advertisements(ADDID)
);

CREATE TABLE Images (
    ADDID INT,
    ImageLink VARCHAR(255),
    FOREIGN KEY (ADDID) REFERENCES Advertisements(ADDID)
);


