USE GreatNeigHborhoods;

# Calculate the population density for each tract in King County, Washington 
SELECT Address.AddressId, Address.State, Address.County, Address.Tract, FLOOR(Population.Total/Address.LandArea) AS PopulationDensity
FROM Address INNER JOIN Population
  ON Address.AddressId = Population.AddressId
WHERE Address.State = 'Washington' AND Address.County = 'King County'
GROUP BY Address.AddressId, Address.State, Address.County, Address.Tract, FLOOR(Population.Total/Address.LandArea)
ORDER BY PopulationDensity DESC;

# What is the percentage of households moving in after 2010 for each tract in King County, Washington
SELECT Address.State, Address.County, Address.Tract, 100 * Household.NumOfHouseholdsMovedInAfter2010 / Household.TotalNumOfHouseholds AS PERCENTAGE_OF_MOVING_IN_AFTER_2010
FROM Address INNER JOIN Household
  ON Address.AddressId = Household.AddressId
WHERE Address.State = 'Washington' AND Address.County = 'King County'
GROUP BY Address.State, Address.County, Address.Tract, 100 * Household.NumOfHouseholdsMovedInAfter2010 / Household.TotalNumOfHouseholds
ORDER BY Address.State, Address.County, PERCENTAGE_OF_MOVING_IN_AFTER_2010 DESC;




#how many tract in each county that the number of population with age more than 65 greater than the number of population with age 18 to 45
SELECT Address.State, Address.County, count(*) AS CNT
FROM Address INNER JOIN
    (SELECT Population.AddressId
     FROM AgeDistribution INNER JOIN Population
     WHERE AgeDistribution.PopulationId = Population.PopulationId AND
            AgeDistribution.MoreThan65 > (AgeDistribution.18To24 
                                        + AgeDistribution.25To44))
			AS Pop_Dis 
WHERE Address.AddressId = Pop_Dis.AddressId
GROUP BY Address.State, Address.County
ORDER BY Address.State, CNT DESC;

# households price distribution in each tract
SELECT Address.State, Address.County,
	IF(Household.MedianHouseValue <= 300000, '<300K', 
	IF(Household.MedianHouseValue > 300000 AND 
      Household.MedianHouseValue <= 400000, '300-400K',
    IF(Household.MedianHouseValue > 400000 AND 
      Household.MedianHouseValue <= 500000, '400-500K', '>500K'))) AS MedianHousePrice, Count(*) AS CNT
FROM Household INNER JOIN Address
WHERE Household.AddressId = Address.AddressId 
GROUP BY Address.State, Address.County,MedianHousePrice;



# List Population race distribution of New York state, Bronx county
SELECT Address.AddressId, state, county, hispanic, white, black,  asian, aian as American_Indian_And_Alaska_Native, NHOPI as Native_Hawaiian_and_Other_Pacific_Islanders
, SOR as others
FROM Diversity 
	LEFT OUTER JOIN Population
	ON Diversity.populationId = Population.PopulationId 
	LEFT OUTER JOIN Address
    ON Address.AddressId = Population.AddressId
WHERE state ='NEW YORK' AND county = "Bronx County"
GROUP BY Address.AddressId, state, county, hispanic, white, black, aian, asian, NHOPI, SOR;





#  List top three county with highest rate of health insurance in each state 
SELECT * FROM(
		SELECT State, County, AVG((1- NoInsurance/total)*100) AS County_AVE_RATE
			FROM InsuranceDistribution
			LEFT OUTER JOIN Population
			ON InsuranceDistribution.populationId = Population.PopulationId 
			LEFT OUTER JOIN Address
			ON Address.AddressId = Population.AddressId
		WHERE state ='Washington'
		GROUP BY State, County
		ORDER BY County_AVE_RATE DESC
		LIMIT 3) AS A
UNION
	SELECT * FROM(
		SELECT State, County, AVG((1- NoInsurance/total)*100) AS County_AVE_RATE
			FROM InsuranceDistribution
			LEFT OUTER JOIN Population
			ON InsuranceDistribution.populationId = Population.PopulationId 
			LEFT OUTER JOIN Address
			ON Address.AddressId = Population.AddressId
			WHERE state ='New York'
		GROUP BY State, County
		ORDER BY County_AVE_RATE DESC
		LIMIT 3) as B		
UNION
	SELECT * FROM(
		SELECT State, County, AVG((1- NoInsurance/total)*100) AS County_AVE_RATE
			FROM InsuranceDistribution
			LEFT OUTER JOIN Population
			ON InsuranceDistribution.populationId = Population.PopulationId 
			LEFT OUTER JOIN Address
			ON Address.AddressId = Population.AddressId
			WHERE state ='California'
		GROUP BY State, County
		ORDER BY County_AVE_RATE DESC
		LIMIT 3) as C
UNION
	SELECT * FROM(
		SELECT State, County, AVG((1- NoInsurance/total)*100) AS County_AVE_RATE
			FROM InsuranceDistribution
			LEFT OUTER JOIN Population
			ON InsuranceDistribution.populationId = Population.PopulationId 
			LEFT OUTER JOIN Address
			ON Address.AddressId = Population.AddressId
			WHERE state ='Texa'
		GROUP BY State, County
		ORDER BY County_AVE_RATE DESC
		LIMIT 3) AS D 



# What percentage of each HouseHold that does not have services, MedianHouseValue > 500000?
SELECT HouseholdId, (100.0 * NumOfHouseholdsWithoutTeleService / TotalNumOfHouseholds) ,MedianHouseValue
FROM household
WHERE MedianHouseValue > 500000;
  

# For each county how many household with more than 3000 person live inside?
SELECT County, NumHouseHold, TotalPersonsInHouseholds FROM
	(select Address.County , Household.TotalPersonsInHouseholds, COUNT(*)  AS NumHouseHold FROM
	Address INNER JOIN Household 
	on Address.AddressId = Household.AddressId
	Group  BY Address.County,Household.TotalPersonsInHouseholds
	Limit 10) As data
WHERE TotalPersonsInHouseholds > 3000;


-- Error Code: 1055. Expression #2 of SELECT list is not in GROUP BY clause and contains 
-- nonaggregated column 'greatneighborhoods.HouseHold.TotalPersonsInHouseholds' 
-- which is not functionally dependent on columns in GROUP BY clause; 
-- this is incompatible with sql_mode=only_full_group_by	0.00092 sec

# what areas that have Male/Female rate than 2?
SELECT Population.AddressId
FROM Population INNER JOIN GenderDistribution
ON Population.PopulationId = GenderDistribution.PopulationId
WHERE GenderDistribution.Male / GenderDistribution.Female > 2;


# what tracts have the Renter Unit rate less than 5% in each state? 
SELECT Address.State,
	Address.Tract,
	OccupiedDistribution.RenterOccupiedUnit/OccupiedDistribution.TotalOccupiedUnit*100 AS RenterPercentage
FROM Address CROSS JOIN HouseHold
ON Address.AddressId = HouseHold.AddressId
JOIN OccupiedDistribution
ON OccupiedDistribution.HouseHoldId = HouseHold.HouseHoldId
GROUP BY Address.State,Address.Tract,RenterPercentage
HAVING RenterPercentage < 5
ORDER BY Address.State DESC,RenterPercentage DESC;
