/*creacion de usuario para la base de datos*/
CREATE USER 'systec'@'localhost' IDENTIFIED BY 'adminSystec124@';
GRANT ALL PRIVILEGES ON systec.* TO 'systec'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;
