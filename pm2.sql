# Create the schema if necessary.
CREATE SCHEMA IF NOT EXISTS PM2;
USE PM2;

DROP TABLE IF EXISTS AgeDistribution;
DROP TABLE IF EXISTS GenderDistribution;
DROP TABLE IF EXISTS AgeDistribution;
DROP TABLE IF EXISTS Review;
DROP TABLE IF EXISTS Hospital;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Environment;
DROP TABLE IF EXISTS Population;
DROP TABLE IF EXISTS Address;

CREATE TABLE Address (
  AddressId BIGINT,
  CONSTRAINT pk_Address_AddressId PRIMARY KEY (AddressId)
);


CREATE TABLE Population (
  populationId BIGINT AUTO_INCREMENT,
  addressId BIGINT,
  CONSTRAINT pk_Population_populationId PRIMARY KEY (populationId),
  CONSTRAINT fk_Population_addressId FOREIGN KEY (addressId)
    REFERENCES Address(addressId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE GenderDistribution (
  populationId BIGINT,
  Male INT,
  Femail INT,
  CONSTRAINT pk_GenderDistribution_populationId PRIMARY KEY (populationId),
  CONSTRAINT fk_GenderDistribution_populationId FOREIGN KEY (populationId)
    REFERENCES Population(populationId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE AgeDistribution (
  populationId BIGINT,
  LessThan5 INT,
  5To17 INT,
  18To24 INT,
  25To44 INT,
  45To64 INT,
  MoreThan65 INT,
  CONSTRAINT pk_AgeDistribution_populationId PRIMARY KEY (populationId),
  CONSTRAINT fk_AgeDistribution_populationId FOREIGN KEY (populationId)
    REFERENCES Population(populationId)
    ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Review (
  UserId INT,
  TractId INT,
  Rating INT,
  Content VARCHAR(255),
  AddressId BIGINT,
  CONSTRAINT pk_Review_AddressId PRIMARY KEY (AddressId),
  CONSTRAINT fk_Review_AddressId FOREIGN KEY (AddressId)
    REFERENCES Address(AddressId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Environment (
  AddressId BIGINT,
  CONSTRAINT pk_Environment_AddressId PRIMARY KEY (AddressId),
  CONSTRAINT fk_Environment_AddressId FOREIGN KEY (AddressId)
    REFERENCES Address(AddressId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE User (
  UserName VARCHAR(255),
  Password VARCHAR(255),
  Email VARCHAR(255),
  Phone INT,
  AddressId BIGINT,
  CONSTRAINT pk_User_AddressId PRIMARY KEY (AddressId),
  CONSTRAINT fk_User_AddressId FOREIGN KEY (AddressId)
    REFERENCES Address(AddressId)
    ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE Hospital (
  zipcode INT,
  hospital VARCHAR(255),
  AddressId BIGINT,
  CONSTRAINT pk_Hospital_AddressId PRIMARY KEY (AddressId),
  CONSTRAINT fk_Hospital_AddressId FOREIGN KEY (AddressId)
    REFERENCES Address(AddressId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

# Load the data.
LOAD DATA INFILE 'D:\\tmp\\HW2\\Address.csv' INTO TABLE Address
  FIELDS TERMINATED BY ','
  # Windows platforms may need '\r\n'.
  LINES TERMINATED BY '\r\n';

LOAD DATA INFILE 'D:\\tmp\\HW2\\population.csv' INTO TABLE Population
  FIELDS TERMINATED BY ','
   #Windows platforms may need '\r\n'.
  LINES TERMINATED BY '\r\n';
  
LOAD DATA INFILE 'D:\\tmp\\HW2\\GenderDistribution.csv' INTO TABLE GenderDistribution
  FIELDS TERMINATED BY ','
   #Windows platforms may need '\r\n'.
  LINES TERMINATED BY '\r\n';
  
LOAD DATA INFILE 'D:\\tmp\\HW2\\AgeDistribution.csv' INTO TABLE AgeDistribution
  FIELDS TERMINATED BY ','
   #Windows platforms may need '\r\n'.
  LINES TERMINATED BY '\r\n';
  