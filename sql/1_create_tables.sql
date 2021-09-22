\c challenge;   

CREATE TABLE IF NOT EXISTS Routes (
  id INT NOT NULL,
  stringName varchar(250) NOT NULL,
  maxNumberCall integer,
  numberCall integer,
  page varchar(250),
  PRIMARY KEY (id)
);


