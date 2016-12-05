# Create the schema if necessary.
CREATE SCHEMA IF NOT EXISTS GreatNeigHborhoods;
USE GreatNeigHborhoods;

DROP TABLE IF EXISTS AgeDistribution;
DROP TABLE IF EXISTS GenderDistribution;
DROP TABLE IF EXISTS AgeDistribution;
DROP TABLE IF EXISTS Review;
DROP TABLE IF EXISTS Hospitals;
DROP TABLE IF EXISTS Schools;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS AreaDistribution;
DROP TABLE IF EXISTS EducationDistribution;
DROP TABLE IF EXISTS InsuranceDistribution;
DROP TABLE IF EXISTS HouseUnitDistribution;
DROP TABLE IF EXISTS OccupiedDistribution;
DROP TABLE IF EXISTS HouseHold;
DROP TABLE IF EXISTS Diversity;
DROP TABLE IF EXISTS Population;
DROP TABLE IF EXISTS Address;

CREATE TABLE Address (
  AddressId BIGINT,
  State VARCHAR(255),
  County VARCHAR(255),
  Tract INT,
  LandArea DECIMAL(10, 3),
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


CREATE TABLE Diversity(
  PopulationId BIGINT,
  hispanic INT,
  white INT,
  black INT,
  aian INT,
  asian INT,
  NHOPI INT,
  SOR INT,
  CONSTRAINT pk_Diversity_PopulationId PRIMARY KEY (PopulationId),
  CONSTRAINT fk_Diversity_PopulationId FOREIGN KEY (PopulationId)
  REFERENCES Population(PopulationId)
  ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE GenderDistribution (
  PopulationId BIGINT ,
  Male INT,
  Female INT,
  CONSTRAINT pk_GenderDistribution_populationId PRIMARY KEY (PopulationId),
  CONSTRAINT fk_GenderDistribution_populationId FOREIGN KEY (PopulationId)
    REFERENCES Population(PopulationId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE AgeDistribution (
  PopulationId BIGINT,
  LessThan5 INT,
  From5To17 INT,
  From18To24 INT,
  From25To44 INT,
  From45To64 INT,
  MoreThan65 INT,
  CONSTRAINT pk_AgeDistribution_populationId PRIMARY KEY (PopulationId),
  CONSTRAINT fk_AgeDistribution_populationId FOREIGN KEY (PopulationId)
    REFERENCES Population(PopulationId)
    ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE AreaDistribution(
    PopulationId BIGINT NOT NULL,
	Urban INT,
	Urbancluster INT,
	Rural INT,
	CONSTRAINT pk_AreaDistribution_PopulationId PRIMARY KEY (PopulationId),
	CONSTRAINT fk_AreaDistribution_PopulationId FOREIGN KEY (PopulationId)
    REFERENCES Population(PopulationId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE EducationDistribution (
	PopulationId BIGINT NOT NULL,
    NotHighSchool INT,
    College INT,
    HighSchool INT,
    CONSTRAINT pk_EducationDistribution_PopulationId PRIMARY KEY (PopulationId),
	CONSTRAINT fk_EducationDistribution_PopulationId FOREIGN KEY (PopulationId)
    REFERENCES Population(PopulationId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE InsuranceDistribution(	
	PopulationID BIGINT NOT NULL,
    OneInsurance INT,
    Morelnsurance INT,
    NoInsurance INT,
    CONSTRAINT pk_InsuranceDistribution_PopulationId PRIMARY KEY (PopulationId),
	CONSTRAINT fk_InsuranceDistribution_PopulationId FOREIGN KEY (PopulationId)
    REFERENCES Population(PopulationId)
    ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE Schools(
	Schoold BIGINT,
	AddressId BIGINT,
	schoolName Char(255),
	schoolType ENUM('College', 'High-school','Middle-schoole','Primary-schoole','Kindergarden'),
    CONSTRAINT pk_EducationDistribution_Schoold PRIMARY KEY (Schoold),
	CONSTRAINT fk_EducationDistribution_AddressId FOREIGN KEY (AddressId)
    REFERENCES Population(PopulationId)
    ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE User (
  UserId INT,
  UserName VARCHAR(255),
  Password VARCHAR(255),
  Email VARCHAR(255),
  Phone INT,
  AddressId BIGINT,
  CONSTRAINT pk_User_UserId PRIMARY KEY (UserId)
  
);

CREATE TABLE Review (
  ReviewId INT,
  UserId INT,
  Rating Decimal,
  Content VARCHAR(255),
  AddressId BIGINT,
  CONSTRAINT pk_Review_ReviewId PRIMARY KEY (ReviewId),
  CONSTRAINT fk_Review_AddressId FOREIGN KEY (AddressId)
	REFERENCES Address(AddressId)
    ON UPDATE CASCADE ON DELETE CASCADE,

  CONSTRAINT fk_Review_UserId FOREIGN KEY (UserId)
	REFERENCES User(UserId)
    ON UPDATE CASCADE ON DELETE SET NULL
);




CREATE TABLE Hospitals (
  Hospitalld INT,
  HospitalName VARCHAR(255),
  AddressId BIGINT,
  CONSTRAINT pk_Hospitals_Hospitalld PRIMARY KEY (Hospitalld),
  CONSTRAINT fk_Hospitals_AddressId FOREIGN KEY (AddressId)
    REFERENCES Address(AddressId)
    ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Household (
   HouseholdId INT auto_increment,
   AddressId BIGINT NOT NULL,
   TotalIncomeOfHouseholds BIGINT,
   AVGIncomeOfHouseholds INT,
   NumOfFamilyWithChildUnderSix INT,
   NumOfFamilyWithOneOrMoreUnder18 INT,
   TotalPersonsInHouseholds  INT,
   NumOfHouseholdsWithoutTeleService INT,
   NumOfHouseholdsLackFacilities INT,
   NumOfHouseholdsBuiltAfter2010 INT,
   MedianHouseValue INT,
   TotalHouseValue  BIGINT,
   NumOfAssistanceFamilies  Int,
   TotalNumOfHouseholds INT,
   NumOfHouseholdsMovedInAfter2010 INT,
   CONSTRAINT pk_HouseHold_HouseHoldId PRIMARY KEY (HouseHoldId),
   CONSTRAINT fk_HouseHold_AddressId FOREIGN KEY (AddressId)
   REFERENCES Address(AddressId)
   ON UPDATE CASCADE ON DELETE CASCADE
   );

CREATE TABLE HouseUnitDistribution (
    HouseHoldId INT NOT NULL,
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

# Load the data.
LOAD DATA INFILE '/tmp/address.csv' INTO TABLE Address
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\r'
  IGNORE 1 LINES;
  
LOAD DATA INFILE '/tmp/population.csv' INTO TABLE Population
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\r'
  IGNORE 1 LINES;
 

LOAD DATA INFILE '/tmp/GenderDistribution.csv' INTO TABLE GenderDistribution
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\r'
  IGNORE 1 LINES;
  
  
LOAD DATA INFILE '/tmp/AgeDistribution.csv' INTO TABLE AgeDistribution
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\r'
  IGNORE 1 LINES;



LOAD DATA INFILE '/tmp/pop_area.csv' INTO TABLE AreaDistribution
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r'
IGNORE 1 LINES;

LOAD DATA INFILE '/tmp/pop_edu.csv' INTO TABLE EducationDistribution
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE '/tmp/pop_insurance.csv' INTO TABLE InsuranceDistribution
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r'
IGNORE 1 LINES;

LOAD DATA INFILE '/tmp/pop_diversity.csv' INTO TABLE Diversity
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\r'
  IGNORE 1 LINES;

LOAD DATA INFILE '/tmp/household.csv' INTO TABLE HouseHold
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\r'
  IGNORE 1 LINES;
  
  
LOAD DATA INFILE '/tmp/houseUnits.csv' INTO TABLE HouseUnitDistribution
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\r'
  IGNORE 1 LINES;
  
LOAD DATA INFILE '/tmp/houseOccpied.csv' INTO TABLE OccupiedDistribution
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\r'
  IGNORE 1 LINES;





