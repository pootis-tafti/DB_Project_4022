INSERT INTO Businesses (OID, Type, SerialNUMBER, Name) VALUES
( 1, 'food', 12345, 'Ali Restaurant'),
( 2, 'Bookstore', 12346, 'Sara Bookstore'),
( 4, 'food', 12347, 'zahra cafe');

INSERT INTO addresses (BID,CID,Address) VALUES
(1, 301,'khiyabane enghelab'),
(1, 291,'vardavard'),
(1, 313,'valiasr st');

INSERT INTO Advertisements (BID, AID, Description, Title, Location, DateModified, Price, IsNew) VALUES
( 1, 1, 'Great food', 'Ali Restaurant Ad', 'linkx', '2024-07-05', 100, 0),
( 2, 2, 'Best books', 'Sara Bookstore Ad', 'linky', '2024-07-06', 150, 1),
(1,1, 'Great food', 'Ali Restaurant Ad', 'linkz', '2024-09-01', 200, 0),
(1,1, 'Great food', 'Ali Restaurant Ad', 'linkm', '2024-07-04', 300, 0),
(3,4, 'Great cafe', 'zahra cafe Ad', 'linko', '2024-010-04', 250, 1);

INSERT INTO Comments (ADDID,AID, Type, Description) VALUES
(1,3, 1, 'Great restaurant!'),
(2,1, 2, 'Not so good books.'),(3,1, 2, 'good.');

UPDATE advertisements SET `DateModified`= CURRENT_DATE() where `BID` = 1;
