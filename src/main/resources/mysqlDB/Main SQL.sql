USE army;

DELETE FROM army.soldiers
WHERE army.soldiers.id >= 1;

DELETE FROM army.commanders
WHERE army.commanders.id >= 1;

DELETE FROM army.beginners
WHERE army.beginners.id >= 1;

DELETE FROM army.tanks
WHERE army.tanks.id >= 1;

DELETE FROM army.aircrafts
WHERE army.aircrafts.id >= 1;

DELETE FROM army.armoredpersonnelcarriers
WHERE army.armoredpersonnelcarriers.id >= 1;

DELETE FROM army.barracks
WHERE army.barracks.id >= 1;

DELETE FROM army.boats
WHERE army.boats.id >= 1;

DELETE FROM army.helicopters
WHERE army.helicopters.id >= 1;

DELETE FROM army.infantryfightingvehicles
WHERE army.infantryfightingvehicles.id >= 1;

DELETE FROM army.submarines
WHERE army.submarines.id >= 1;

DELETE FROM army.uavs
WHERE army.uavs.id >= 1;

DELETE FROM army.warships
WHERE army.warships.id >= 1;

DELETE FROM army.jetties
WHERE army.jetties.id >= 1;

DELETE FROM army.hangars
WHERE army.hangars.id >= 1;

DELETE FROM army.mainarmy
WHERE army.mainarmy.id >= 1;


INSERT INTO mainarmy (name, rating)
VALUES ('Scotland_Army', 157), ('Poland_Army', 156), ('Ukraine_Army', 1), ('UK_Army', 2), ('USA_Army', 3);

INSERT INTO jetties (numberOfShips, Army_id)
VALUES (25, 1), (36, 2), (37, 4), (28, 3), (39, 5);

INSERT INTO hangars (numberOfMilitaryCraft, Army_id)
VALUES (14, 2), (16, 1), (18, 3), (19, 4), (20, 5);

INSERT INTO barracks (numberOfBeds, numberOfFloors, Army_id)
VALUES (15, 3, 1), (150, 5, 2), (200, 7, 3), (50, 2, 4), (90, 3, 5);

INSERT INTO aircrafts (name, releaseDate, numberOfFlights, strength, Hangars_id)
VALUES ('Avia BH-33', "1927-10-21 00:00:00", 15, 1, 1), ('Avia S-199', "1947-04-25 00:00:00", 9, 2, 1),
('Dewoitine D.1', "1922-11-18 00:00:00", 7, 1, 2), ('Dewoitine D.9', "1924-06-18 00:00:00", 21, 2, 2), ('Dewoitine D.37', "1931-10-01 00:00:00", 8, 1, 2),
('Fairey Flycatcher', "1922-11-01 00:00:00", 11, 1, 3), ('FMA I.Ae. 30', "1948-07-18 00:00:00", 2, 2, 3), ('Fokker D.XXIII', "1939-05-30 00:00:00", 6, 2, 3),
('Hawker Nimrod', "1931-10-14 00:00:00", 2, 1, 4),  ('IAR 15', "1934-01-01 00:00:00", 16, 5, 4),
('Mureaux 113', "1931-01-01 00:00:00", 17, 4, 5),  ('Potez 453', "1935-09-24 00:00:00", 5, 2, 5), ('Potez XI', "1922-12-11 00:00:00", 19, 3, 5);

INSERT INTO armoredpersonnelcarriers (name, releaseDate, numberOfGuns, strength, Hangars_id)
VALUES ('TP-4-1', "1988-01-01 00:00:00", 3, 1, 3), ('TB-26', "1995-01-01 00:00:00", 5, 2, 4), ('T-26', "1996-01-01 00:00:00", 6, 2, 4),
('TP-4', "2000-05-19 00:00:00", 2, 1, 2), ('TP-229', "2005-07-21 00:00:00", 11, 4, 5), ('TP-29', "2004-06-26 00:00:00", 7, 3, 5);

INSERT INTO beginners (name, surname, beginDate, endDate, strength, Barracks_id)
VALUES ('Ivan', 'Ivanov', "2017-05-20 18:00:00", "2019-05-20 18:00:00", 1, 1), ('Petro', 'Petrov', "2016-06-22 17:59:59", "2018-06-22 17:59:59", 1, 2), 
('Vasyl', 'Vasylkov', "2020-07-22 12:00:00", "2022-07-22 12:00:00", 1, 3), ('Artem', 'Artemov', "2022-07-26 00:00:00", "2024-07-26 00:00:00", 1, 4), 
('Denys', 'Denysov', "2023-09-26 13:44:26", "2025-09-26 13:44:26", 1, 5);

INSERT INTO boats (name, releaseDate, numberOfGuns, strength, Jetties_id)
VALUES ('Albion', "2003-01-01 00:00:00", 5, 2, 4), ('Bay', "2006-05-22 00:00:00", 6, 3, 4), ('Fudre', "1990-11-29 00:00:00", 3, 1, 4), 
('Enduras', "2000-01-29 00:00:00", 10, 4, 3), ('Osyma', "2015-01-30 00:00:00", 3, 2, 1); 

INSERT INTO commanders (army.commanders.name, surname, army.commanders.rank, strength, Barracks_id)
VALUES ('Dmytro', 'Dmytrienko', 'Lieutenant', 3, 2), ('Arkadiy', 'Dumchenko', 'Captain', 3, 1),
('Yury', 'Krovcov', 'General', 3, 3), ('Konstantin', 'Yaremov', 'Colonel', 3, 5), ('Petro', 'Poroshenko', 'Major', 3, 4);

INSERT INTO helicopters (name, releaseDate, numberOfFlights, strength, Hangars_id)
VALUES ('B-7', "1965-05-22 00:00:00", 4, 2, 1), ('Mi-8', "1974-06-11 00:00:00", 7, 2, 1), ('Mi-26', "2000-07-05 00:00:00", 3, 2, 5),
('Piaseki-20', "2007-01-01 00:00:00", 19, 5, 4);

INSERT INTO infantryfightingvehicles (name, releaseDate, numberOfGuns, strength, Hangars_id) 
VALUES ('BMP-66', "2001-05-06 13:14:55", 3, 2, 2), ('NI-11', "2005-08-14 00:00:00", 7, 4, 2), 
('BYY-77', "2001-05-06 00:00:00", 3, 2, 3), ('NI-11', "2005-08-14 00:00:00", 7, 4, 3);

INSERT INTO soldiers (army.soldiers.name, surname, army.soldiers.rank, strength, Barracks_id)
VALUES ('Denys', 'Novohatko', 'Corporal', 2, 2), ('Timofiy', 'Tonkovid', 'Sergeant', 2, 1),
('Nitika', 'Krovcov', 'Officer', 2, 3), ('David', 'Efremov', 'Master Sergeant', 2, 5), ('Nikolos', 'Valendovski', 'Private', 2, 4);

INSERT INTO submarines (name, releaseDate, numberOfBombs, numberOfEchoSounders, strength, Jetties_id)
VALUES ('Paltus', "2001-02-02 00:00:00", 6, 5, 5, 3), ('Lada', "2017-12-22 00:00:00", 20, 6, 7, 2), ('Varshavyanka', "2006-03-03 00:00:00", 9, 2, 1, 4), 
('Vanguard', "2020-07-26 00:00:00", 30, 8, 2, 3);

INSERT INTO tanks (name, releaseDate, numberOfGuns, centimetersOfArmor, strength, Hangars_id) 
VALUES ('T-52', "1922-01-01 00:00:00", 4, 15, 2, 1), ('T-63', "1924-12-15 19:00:00", 5, 18, 3, 2), ('MIS-77', "1985-04-13 00:00:00", 2, 11, 2, 3),
('IS-22', "1999-04-15 00:00:00", 7, 27, 5, 4),  ('TOBI-123', "2006-02-28 00:00:00", 9, 14, 3, 5);

INSERT INTO uavs (name, releaseDate, numberOfBombs, strength, Hangars_id)
VALUES ('Bayraktar', "2001-01-30 00:00:50", 2, 2, 3), ('Bayraktar-MV', "2006-05-18 00:00:00", 5, 5, 3);

INSERT INTO warships (name, releaseDate, numberOfGuns, numberOfBombs, strength, Jetties_id)
VALUES ('Rokford', "2015-04-04 00:00:00", 14, 25, 7, 3), ('Prikorda', "2012-03-06 00:00:47", 24, 15, 8, 2), ('Jokoma', "2014-06-03 00:12:00", 18, 29, 10, 5),
('Ekolda', "2008-01-01 00:00:00", 9, 9, 4, 1), ('Dalicio', "2018-11-03 09:47:11", 11, 35, 14, 4);



UPDATE warships SET strength = 10 WHERE id = 2;

UPDATE uavs SET numberOfBombs = 4 WHERE id = 2;

UPDATE boats SET strength = 7 WHERE id = 3;

UPDATE beginners SET army.beginners.name = 'Dryiver' WHERE id = 1;

UPDATE helicopters SET strength = 4 WHERE id = 1;



DELETE FROM aircrafts WHERE id = 1;

DELETE FROM helicopters WHERE id = 4;

DELETE FROM beginners WHERE id = 5;

DELETE FROM commanders WHERE id = 28;

DELETE FROM warships WHERE id = 7;



SELECT * FROM army.warships
WHERE id > 2 AND numberOfBombs > 20;

SELECT * FROM army.barracks
WHERE id IN (5, 6);

SELECT * FROM army.helicopters
WHERE name = 'B-7';

SELECT * FROM army.commanders
WHERE id BETWEEN 2 and 4;

SELECT * FROM army.commanders
WHERE id BETWEEN 2 and 4
ORDER BY name;

SELECT * FROM army.aircrafts
GROUP BY strength;




SELECT army.soldiers.name, army.soldiers.surname, army.barracks.id
FROM army.soldiers LEFT JOIN army.barracks
ON army.soldiers.Barracks_id = army.barracks.id
ORDER BY army.soldiers.name;

SELECT army.hangars.id, army.helicopters.name, army.helicopters.releaseDate
FROM army.hangars RIGHT JOIN army.helicopters
ON army.hangars.id = army.helicopters.Hangars_id
ORDER BY army.hangars.id;

SELECT army.submarines.name, army.submarines.releaseDate, army.warships.name, army.warships.releaseDate
FROM army.submarines INNER JOIN army.warships
ON army.submarines.Jetties_id = army.warships.Jetties_id
ORDER BY army.submarines.name;
