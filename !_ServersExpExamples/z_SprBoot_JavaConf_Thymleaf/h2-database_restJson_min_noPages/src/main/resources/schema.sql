DROP TABLE IF EXISTS TBL_STUDENT;
 
CREATE TABLE TBL_STUDENT (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  age INT NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);