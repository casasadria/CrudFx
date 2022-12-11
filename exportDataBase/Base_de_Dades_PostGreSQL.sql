CREATE TABLE alumnes (
	id serial4 NOT NULL,
	nom varchar(50) NULL,
	cognoms varchar(50) NULL,
	data_naixement date NULL,
	curs_actual "curs" NULL,
	progenitors _varchar NULL,
	CONSTRAINT alumnes_pkey PRIMARY KEY (id)
);
INSERT INTO alumnes
(id, nom, cognoms, data_naixement, curs_actual, progenitors)
VALUES(17, 'Oriol', 'Torrent Roura', '2003-12-18', 'Cicles Formatius'::"curs", '{"Montse Roura","Carles Torrent"}');
INSERT INTO alumnes
(id, nom, cognoms, data_naixement, curs_actual, progenitors)
VALUES(29, 'Jaume', 'Joan', '1996-02-03', 'Cicles Formatius'::"curs", '{"Pare","Mare"}');
INSERT INTO alumnes
(id, nom, cognoms, data_naixement, curs_actual, progenitors)
VALUES(20, 'Eric', 'Morales', '2019-11-30', 'Cicles Formatius'::"curs", '{"Madre","Padre"}');
INSERT INTO alumnes
(id, nom, cognoms, data_naixement, curs_actual, progenitors)
VALUES(24, 'Nil', 'Sala', '2003-11-04', 'Cicles Formatius'::"curs", '{"Rudi Boy","Antonia Galafat"}');
INSERT INTO alumnes
(id, nom, cognoms, data_naixement, curs_actual, progenitors)
VALUES(25, 'Íngrid', 'Garcia', '2003-06-17', 'Batxillerat'::"curs", '{"Susana Torrent","Pare"}');
INSERT INTO alumnes
(id, nom, cognoms, data_naixement, curs_actual, progenitors)
VALUES(26, 'Josep', 'Torres', '2003-11-19', 'Cicles Formatius'::"curs", '{"Carme Aguilera","Josep Torres"}');
INSERT INTO alumnes
(id, nom, cognoms, data_naixement, curs_actual, progenitors)
VALUES(1, 'Adrià ', 'Casas', '2003-10-02', 'Cicles Formatius'::"curs", '{"Marc Casas","Carmeta Llorens"}');

CREATE TABLE useraccounts (
	iduseraccounts serial4 NOT NULL,
	firstname varchar(45) NOT NULL,
	lastname varchar(45) NOT NULL,
	username varchar(45) NOT NULL,
	"password" varchar(45) NOT NULL,
	CONSTRAINT useraccounts_pkey PRIMARY KEY (iduseraccounts),
	CONSTRAINT useraccounts_username_key UNIQUE (username)
);

INSERT INTO useraccounts
(iduseraccounts, firstname, lastname, username, "password")
VALUES(1, 'Adrià ', 'Casas', 'casasadria', 'adri');
INSERT INTO useraccounts
(iduseraccounts, firstname, lastname, username, "password")
VALUES(2, 'Eric', 'Morales', 'gitanoteton', 'pokemonballs123');
INSERT INTO useraccounts
(iduseraccounts, firstname, lastname, username, "password")
VALUES(3, 'Oriol', 'Torrent', 'orioltorrent', 'uriuleta');
INSERT INTO useraccounts
(iduseraccounts, firstname, lastname, username, "password")
VALUES(5, 'a', 'a', 'a', 'a');
