-- Active: 1716876444801@@localhost@3306@main
CREATE TABLE Accounts (
    AID INT UNIQUE AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Phone VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    ActiveCity INT NOT NULL,
    BIT Status NOT NULL DEFAULT 1,
    PRIMARY KEY (AID),
    Foreign Key (ActiveCity) REFERENCES Cities(id)
);

CREATE TABLE Businesses (
    BID INT UNIQUE AUTO_INCREMENT,
    OID INT NOT NULL,
    Type VARCHAR(50) NOT NULL,
    SerialNUMBER INT NOT NULL,
    Name VARCHAR(50) NOT NULL,
    PRIMARY KEY (BID),
    FOREIGN KEY (OID) REFERENCES Accounts(AID)
);

CREATE TABLE Addresses(
    BID INT NOT NULL, 
    Address VARCHAR(255) NOT NULL,
    CID INT NOT NULL,
    FOREIGN KEY (BID) REFERENCES Businesses(BID),
    FOREIGN KEY (CID) REFERENCES Cities(id)
);

CREATE TABLE  IF NOT EXISTS Advertisements (
    ADDID INT UNIQUE AUTO_INCREMENT,
    BID INT,
    AID INT NOT NULL,
    Description TEXT NOT NULL,
    Title VARCHAR(50) NOT NULL,
    Location VARCHAR(255) NOT NULL,
    DateModified DATE NOT NULL,
    Price INT NOT NULL,
    IsNew BIT NOT NULL,
    FOREIGN KEY (BID) REFERENCES Businesses(BID),
    FOREIGN KEY (AID) REFERENCES Accounts(AID)
);

CREATE TABLE Comments (
    ADDID INT NOT NULL,
    Type TINYINT NOT NULL,
    Description TEXT NOT NULL,
    FOREIGN KEY (ADDID) REFERENCES Advertisements(ADDID),
    Foreign Key (Type) REFERENCES Type(ID)
);

CREATE TABLE AddStatus (
    Status BIT NOT NULL,
    ADDID INT UNIQUE NOT NULL,
    AdminNote TEXT NOT NULL,
    LastUpdated DATE,
    FOREIGN KEY (ADDID) REFERENCES Advertisements(ADDID),
    Foreign Key (Status) REFERENCES Status(ID)
);

create TABLE Status(
    ID BIT NOT NULL,
    Status VARCHAR(8),
    PRIMARY KEY (ID)
);

INSERT INTO Status(ID, Status) VALUES 
    (1, 'Accepted'),
    (0, 'Declined');

CREATE TABLE Images (
    ADDID INT,
    ImageLink VARCHAR(255),
    FOREIGN KEY (ADDID) REFERENCES Advertisements(ADDID)
);

CREATE TABLE Admins(
    AID INT NOT NULL,
    Foreign Key (AID) REFERENCES Accounts(AID)
);

CREATE TABLE Type(
    ID TINYINT UNIQUE NOT NULL,
    Name VARCHAR(30),
    PRIMARY KEY (ID)
);

INSERT INTO Type(ID, Name) VALUES 
    (1,'positive feedback'),
    (2, 'negative feedback(complaint)'),
    (3, 'brand value'),
    (4, 'other');
