CREATE DATABASE MINIGAMES;
USE MINIGAMES;

CREATE TABLE PLAYER(
Us_Id int auto_increment not null primary key,
US_NAME VARCHAR(50),
POINTS int default 0,
PASS VARCHAR(50)
);

CREATE TABLE GAME(
G_NAME VARCHAR(50) PRIMARY KEY
);

CREATE TABLE TROPHYE(
T_NAME VARCHAR (30) PRIMARY KEY,
PRICE INT
);	

CREATE TABLE BUY(
Us_Id int,
T_NAME VARCHAR (30),
FOREIGN KEY (Us_Id) REFERENCES PLAYER (Us_Id)on update cascade,
FOREIGN KEY (T_NAME) REFERENCES TROPHYE (T_NAME)on update cascade,
PRIMARY KEY (Us_Id, T_NAME)
);

CREATE TABLE PLAYS(
Us_Id int,
G_NAME VARCHAR (30),
DATI DATE,
SCORE int DEFAULT 0,
FOREIGN KEY (Us_Id) REFERENCES PLAYER (Us_Id)on update cascade,
FOREIGN KEY (G_NAME) REFERENCES GAME (G_NAME)on update cascade,
PRIMARY KEY (Us_Id, G_NAME)
);


INSERT INTO PLAYER (US_NAME, POINTS, PASS)VALUES ('Alain', 0, '123'),
('Mikel', 0, '321'),
('Ekain', 0, '213');

INSERT INTO GAME VALUES('AIM'),
('ARITMETICS');

INSERT INTO TROPHYE VALUES ('STARTING TROPHYE', 100),
('RARE TROPHYE', 300),
('EPIC TROPHYE', 800),
('MITHYC TROPHYE', 1600),
('LEGENDARY TROPHY', 5000),
('DEFINITIVE TROPHY', 100000);

INSERT INTO BUY VALUES(1, 'DEFINITIVE TROPHY'),
(1, 'LEGENDARY TROPHY'),
(3, 'LEGENDARY TROPHY'),
(2, 'LEGENDARY TROPHY'),
(2, 'MITHYC TROPHYE');

INSERT INTO PLAYS VALUES (2, 'AIM', '2024-12-12', default),
(3, 'AIM', '2025-01-05', default);



Delimiter //
create procedure MostrarUsuario(Id int)
begin
declare nombre varchar(20);
declare contraseña varchar(30);
declare points int;
declare c cursor for select US_NAME, PASS, POINTS from Player where Us_Id=Id;
open c;
fetch c into nombre, contraseña, points;
select concat('Name: '+ nombre+ 'Password: '+ contraseña +'Points: '+ points)'User data';
close c;
end //
Delimiter ;

call MostrarUsuario(1);