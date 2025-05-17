-- Create database and use it
CREATE DATABASE IF NOT EXISTS StellarOdyssey;
USE StellarOdyssey;

-- ASTRONAUTS
CREATE TABLE IF NOT EXISTS Astronauts (
astronaut_id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
birth_date DATE NOT NULL,
nationality VARCHAR(50),
missions_completed INT DEFAULT 0,
active BOOLEAN DEFAULT TRUE,
image VARCHAR(100)
);

-- PLANETS
CREATE TABLE IF NOT EXISTS Planets (
planet_id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL UNIQUE,
diameter INT,
discovery_date DATE,
has_atmosphere BOOLEAN DEFAULT FALSE,
description TEXT,
image VARCHAR(100)
);

-- MISSIONS (Astronauts-Planets relationship)
CREATE TABLE IF NOT EXISTS Missions (
mission_id INT AUTO_INCREMENT PRIMARY KEY,
astronaut_id INT NOT NULL,
planet_id INT NOT NULL,
mission_name VARCHAR(100) NOT NULL,
start_date DATE NOT NULL,
end_date DATE,
status VARCHAR(50) DEFAULT 'Planned',
DESCRIPTION TEXT,
FOREIGN KEY (astronaut_id) REFERENCES Astronauts(astronaut_id) ON DELETE CASCADE,
FOREIGN KEY (planet_id) REFERENCES Planets(planet_id) ON DELETE CASCADE
);

-- Data for astronauts
INSERT INTO Astronauts (name, birth_date, nationality, missions_completed, active, image) VALUES
('Nerea Tomas', '1997-10-31', 'Spain', 5, TRUE, 'nerea.png'),
('Miguel Chavarria', '1998-06-22', 'Spain', 3, TRUE, 'miguel.png'),
('Kenny Pineda', '1995-01-31', 'Spain', 2, TRUE, 'kenny.png'),
('Vanessa Lorente', '1995-01-31', 'Spain', 2, TRUE, 'vanessa.png'),
('Angel Ladrero', '1995-01-31', 'Spain', 2, TRUE, 'angel.png'),
('Santiago Faci', '1995-01-31', 'Spain', 2, TRUE, 'santiago.png');

-- Data for Planets
INSERT INTO Planets (name, diameter, discovery_date, has_atmosphere, description, image) VALUES
('Mars', 6779, '1659-07-13', FALSE, 'Fourth planet in the Solar System','mars.png'),
('Europa', 3122, '1610-01-08', FALSE, 'Jupiter moon with potential subsurface ocean','europa.png'),
('Titan', 5150, '1655-03-25', TRUE, 'Saturn moon with methane lakes','titan.png'),
('Kepler-186f', 11000, '2014-04-17', TRUE, 'Exoplanet located in the habitable zone of its star','Kepler-186f.png'),
('Proxima Centauri b', 12000, '2016-08-24', TRUE, 'Closest exoplanet to the Solar System','Proxima-Centauri-b.png');

-- Data for Missions
INSERT INTO Missions (astronaut_id, planet_id, mission_name, start_date, end_date, status, description) VALUES
(1, 3, 'Titan Exploration', '2036-04-12', '2036-07-20', 'Completed', 'Exploration of methane lakes on Titan'),
(2, 1, 'Olympus Base', '2035-06-15', '2035-09-28', 'Completed', 'Installation of temporary base on Mars'),
(3, 4, 'Kepler Discovery', '2037-02-10', '2037-05-05', 'Completed', 'Atmosphere research on Kepler-186f'),
(2, 5, 'Proxima Centauri', '2038-01-15', NULL, 'In Progress', 'Establishment of advanced base'),
(3, 2, 'Europa Ice', '2036-09-30', NULL, 'In Progress', 'Ice drilling and study of subsurface ocean'),
(1, 2, 'Olympus Base', '2035-09-29', NULL, 'In Progress', 'Repair incidences');
