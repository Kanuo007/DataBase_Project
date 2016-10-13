# Create the schema if necessary.
CREATE SCHEMA IF NOT EXISTS GreatNeigHborhoods;
USE GreatNeigHborhoods;

DROP TABLE IF EXISTS AgeDistribution;
DROP TABLE IF EXISTS GenderDistribution;
DROP TABLE IF EXISTS Review;
DROP TABLE IF EXISTS Hospital;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Environment;
DROP TABLE IF EXISTS AreaDistribution;
DROP TABLE IF EXISTS EducationDistribution;
DROP TABLE IF EXISTS InsuranceDistribution;
DROP TABLE IF EXISTS HouseUnitDistribution;
DROP TABLE IF EXISTS OccupiedDistribution;
DROP TABLE IF EXISTS HouseHold;
DROP TABLE IF EXISTS Diversity;
DROP TABLE IF EXISTS Population;
DROP TABLE IF EXISTS Education;
DROP TABLE IF EXISTS Address;

CREATE TABLE Address (
  AddressId BIGINT,
  State VARCHAR(255),
  County VARCHAR(255),
  Tract INT,
  CONSTRAINT pk_Address_AddressId PRIMARY KEY (AddressId)
);

CREATE TABLE Population (
  PopulationId BIGINT AUTO_INCREMENT,
  AddressId BIGINT,
  Total INT,
  CONSTRAINT pk_Population_PopulationId PRIMARY KEY (PopulationId),
  CONSTRAINT fk_Population_AddressId FOREIGN KEY (AddressId)
    REFERENCES Address(AddressId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Diversity (
  PopulationId BIGINT,
  hispanic INT,
  white INT,
  black INT,
  asian INT,
  NHOPI INT,
  SOR INT,
  CONSTRAINT pk_Diversity_PopulationId PRIMARY KEY (PopulationId),
  CONSTRAINT fk_Diversity_PopulationId FOREIGN KEY (PopulationId)
  REFERENCES Population(PopulationId)
  ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE GenderDistribution (
  PopulationId BIGINT,
  Male INT,
  Female INT,
  CONSTRAINT pk_GenderDistribution_PopulationId PRIMARY KEY (PopulationId),
  CONSTRAINT fk_GenderDistribution_PopulationId FOREIGN KEY (PopulationId)
    REFERENCES Population(PopulationId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE AgeDistribution (
  PopulationId BIGINT,
  LessThan5 INT,
  5To17 INT,
  18To24 INT,
  25To44 INT,
  45To64 INT,
  MoreThan65 INT,
  CONSTRAINT pk_AgeDistribution_PopulationId PRIMARY KEY (PopulationId),
  CONSTRAINT fk_AgeDistribution_PopulationId FOREIGN KEY (PopulationId)
    REFERENCES Population(PopulationId)
    ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE AreaDistribution (
    PopulationId BIGINT,
	Urban INT,
	Urbancluster INT,
	Rural INT,
	CONSTRAINT pk_AreaDistribution_PopulationId PRIMARY KEY (PopulationId),
	CONSTRAINT fk_AreaDistribution_PopulationId FOREIGN KEY (PopulationId)
    REFERENCES Population(PopulationId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE EducationDistribution (
	PopulationId BIGINT,
    NotHighSchool INT,
    College INT,
    CONSTRAINT pk_EducationDistribution_PopulationId PRIMARY KEY (PopulationId),
	CONSTRAINT fk_EducationDistribution_PopulationId FOREIGN KEY (PopulationId)
    REFERENCES Population(PopulationId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE InsuranceDistribution (	
	PopulationId BIGINT,
    OneInsurance INT,
    Morelnsurance INT,
    NoInsurance INT,
    CONSTRAINT pk_InsuranceDistribution_PopulationId PRIMARY KEY (PopulationId),
	CONSTRAINT fk_InsuranceDistribution_PopulationId FOREIGN KEY (PopulationId)
    REFERENCES Population(PopulationId)
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


CREATE TABLE HouseHold(
   HouseHoldId INT auto_increment,
   AddressId BIGINT NOT NULL,
   SumOfIncome BIGINT,
   AVGIncome INT,
   ChildUnderSix INT,
   OneOrMoreUnder18 INT,
   TotalPersons  INT,
   WithoutTeleService INT,
   LackFacilities INT,
   BuiltAfter2010 INT,
   MedianHouseValue INT,
   TotalValue  BIGINT,
   NumOfAssistance  Int,
   MovedInHouseHold INT,
   CONSTRAINT pk_HouseHold_HouseHoldId PRIMARY KEY (HouseHoldId),
   CONSTRAINT fk_HouseHold_AddressId FOREIGN KEY (AddressId)
   REFERENCES Address(AddressId)
   ON UPDATE CASCADE ON DELETE CASCADE
   );
   
CREATE TABLE HouseUnitDistribution (
    HouseHoldId INT NOT NULL,
    TotalHouseUnit INT,
    SingleUnit INT,
    TwoToNineUnit INT,
    TenMoreUnit INT,
	MobileHome INT,
    CONSTRAINT pk_HouseUnitDistribution_HouseHoldId PRIMARY KEY (HouseHoldId),
    CONSTRAINT fk_HouseUnitDistribution_HouseHoldId FOREIGN KEY (HouseHoldId)
	REFERENCES HouseHold(HouseHoldId)
    ON UPDATE CASCADE ON DELETE CASCADE
   );

CREATE TABLE OccupiedDistribution (
    HouseHoldId INT NOT NULL,
    TotalOccupiedUnit INT,
    TotalVacantUnit INT,
    RenterOccupiedUnit INT,
    OwnerOccupiedUnit INT,
    CONSTRAINT pk_OccupiedDistribution_HouseHoldId PRIMARY KEY (HouseHoldId),
    CONSTRAINT fk_OccupiedDistribution_HouseHoldId FOREIGN KEY (HouseHoldId)
	REFERENCES HouseHold(HouseHoldId)
    ON UPDATE CASCADE ON DELETE CASCADE
   );

CREATE TABLE Education (
  AddressId BIGINT,
  College VARCHAR(255),
  HighSchool VARCHAR(255),
  MiddleSchool VARCHAR(255),
  PrimarySchool VARCHAR(255),
  Kindergarden VARCHAR(255),
  CONSTRAINT pk_Education_AddressId PRIMARY KEY (AddressId),
  CONSTRAINT fk_Education_AddressId FOREIGN KEY (AddressId)
    REFERENCES Address(AddressId)
    ON UPDATE CASCADE ON DELETE CASCADE
);




# Load the data.
LOAD DATA INFILE '/tmp/address.csv' INTO TABLE Address
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;
  
LOAD DATA INFILE '/tmp/population.csv' INTO TABLE Population
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;
  
LOAD DATA INFILE '/tmp/GenderDistribution.csv' INTO TABLE GenderDistribution
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;
  
LOAD DATA INFILE '/tmp/AgeDistribution.csv' INTO TABLE AgeDistribution
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;





LOAD DATA INFILE '/tmp/pop-urban.csv' INTO TABLE AreaDistribution
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE '/tmp/pop-edu.csv' INTO TABLE EducationDistribution
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE '/tmp/pop-insurance.csv' INTO TABLE InsuranceDistribution
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;



LOAD DATA INFILE '/tmp/household.csv' INTO TABLE HouseHold
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
  
  
LOAD DATA INFILE '/tmp/houseUnits.csv' INTO TABLE HouseUnitDistribution
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
  
LOAD DATA INFILE '/tmp/houseOccpied.csv' INTO TABLE OccupiedDistribution
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;





