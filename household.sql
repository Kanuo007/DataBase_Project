# Create the schema if necessary.
CREATE SCHEMA IF NOT EXISTS GreatNeigHborhoods;
USE GreatNeigHborhoods;

DROP TABLE IF EXISTS HouseUnitDistribution;
DROP TABLE IF EXISTS OccupiedDistribution;
DROP TABLE IF EXISTS HouseHold;


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


LOAD DATA INFILE '/tmp/household.csv' INTO TABLE HouseHold
  # Fields are not quoted.
  FIELDS TERMINATED BY ','
  # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
  
  
LOAD DATA INFILE '/tmp/houseUnits.csv' INTO TABLE HouseUnitDistribution
  # Fields are not quoted.
  FIELDS TERMINATED BY ','
  # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
  
LOAD DATA INFILE '/tmp/houseOccpied.csv' INTO TABLE OccupiedDistribution
  # Fields are not quoted.
  FIELDS TERMINATED BY ','
  # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;