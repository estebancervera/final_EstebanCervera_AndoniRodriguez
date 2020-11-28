
CREATE USER 'dev'@'localhost' IDENTIFIED BY 'dev';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON *.* TO 'dev'@'localhost';

CREATE DATABASE IF NOT EXISTS alumno ;
USE alumno;

CREATE TABLE IF NOT EXISTS alumno (
  id INT  PRIMARY KEY,
  name VARCHAR(50) ,
  age INT ,
  grade INT,
  email VARCHAR(50)
);
