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


INSERT INTO PLAYER (US_NAME, POINTS, PASS)VALUES ('Alain', 100, '123'),
('Mikel', 0, '321'),
('Ekain', 60, '213');

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

INSERT INTO PLAYS VALUES (2, 'AIM', '2024-12-12', 20),
(3, 'AIM', '2025-01-05', 30);


/*Delimiter //
create procedure MostrarUser()
begin
declare id_user int;
declare nombre varchar(20);
declare contraseña varchar(30);
declare points int;
declare fin boolean default 0;
declare c cursor for select Us_id, US_NAME, PASS, POINTS from PLAYER where POINTS >50;
declare continue handler for not found set fin = 1; 
open c;
fetch c into id_user, nombre, contraseña, points;
while fin=0 do
select concat('ID:', id_user ,' Name: ', nombre, ' Password: ', contraseña ,' Points: ', points)'User data';
fetch c into id_user, nombre, contraseña, points;
end while;
close c;
end //
Delimiter ;

call MostrarUser(); */

Delimiter //
create procedure InsertUsuario(us_name varchar(20),  points int, us_password varchar(20))
begin 
declare Id_user int;
if not exists(select * from Player where US_NAME=us_name and PASS=us_password) then 
select max(Us_Id) + 1 into Id_user from Player;
insert into Player values(Id_user, us_name, points, us_password);
select "Se ha creado correctamente";
else 
select "Ya existe";
end if;
end //
Delimiter ;

call InsertUsuario('xabi', 49, 'G2006');

Delimiter //
create procedure DeleteUser(id int)
Begin
if exists (select * from PLayer where Us_Id=id) then 
delete from PLayer where Us_Id=id;
select "Se ha eliminado correctamente" ;
else
select "No existe el Pedido";
end if;
end //
DELIMITER ;

call DeleteUser(4);



Delimiter // 
create procedure ModifyPrize(nameP varchar(30), Price int)
begin 
declare current_price INT;
select PRICE into current_price from TROPHYE where  T_NAME= nameP;
if current_price is not null then 
update TROPHYE set PRICE= Price where  T_NAME= nameP;
select "have been modificated";
else 
select "Doesn't exist that Prize";
end if;
end //
Delimiter ;
call ModifyPrize('EPIC TROPHYE', 900);


Delimiter //
create Function UserScoreGame(nom_us varchar(50), nom_ga varchar(50))
returns varchar(50) deterministic
begin 
declare pointsbygame int;
declare return_text varchar(50);
select SCORE into pointsbygame from PLAYS p join PLAYER pa on p.Us_Id=pa.Us_Id where pa.US_NAME=nom_us and p.G_NAME= nom_ga;
 select concat('This are the points that ', nom_us ,' have done in ', nom_ga , ': ', pointsbygame) into return_text;
 return return_text;
 end //
Delimiter ;

select UserScoreGame('Mikel', 'AIM');

Delimiter //
Create Function ReturnID( nom_us varchar(50), password_us varchar(50))
returns varchar(50) deterministic
begin
declare id_us int;
declare return_text varchar(50);
select Us_Id into id_us from PLAYER where US_NAME=nom_us and PASS=password_us;
select concat('The player ',nom_us , ' have this id: ',id_us) into return_text;
return return_text;
end //
Delimiter ;

select ReturnID('Mikel', '321');


Delimiter //
create function UserQuantity()
returns varchar(50) deterministic
begin
declare Quantity int;
declare return_text varchar(50);
select count(*) into Quantity from PLAYER;
select concat('This is the number of registered users ',Quantity) into return_text;
return return_text;
end //
Delimiter ;

select UserQuantity();


Delimiter //
create Function showUser(id_us int)
returns varchar(50) deterministic
begin
declare name_us, paswword_us, return_text varchar(50);
declare points_us int;
select US_NAME into name_us from player where Us_Id=id_us;
select PASS into paswword_us from player where Us_Id=id_us;
select POINTS into points_us from player where Us_Id=id_us;
select concat('ID: ',id_us,' Name: ',name_us,' Password: ',paswword_us,' Points: ',points_us) into return_text;
return return_text;
end //
Delimiter ;

select showUser(2);


Delimiter //
create function CheckUser(nom_us varchar(50), password_us varchar(50))
returns boolean deterministic
begin
declare exist boolean default 0;
if exists (select * from PLAYER where US_NAME=nom_us and PASS=password_us) then 
 set exist= 1;
 return exist;
 else 
set exist=0;
return exist;
end if;
end //
Delimiter ;

/*Delimiter //
create function CheckUser2(nom_us varchar(50), password_us varchar(50))
returns boolean deterministic
begin
declare exist boolean default 0;
if exists (select * from PLAYER where US_NAME=nom_us and PASS=password_us) then 
 set exist= 1;
 return exist;
 else 
set exist=0;
return exist;
end if;
end //
Delimiter ;*/

select CheckUser('Mikel','321');
-- drop database MINIGAMES;
-- drop procedure         ;
-- drop function        ;
