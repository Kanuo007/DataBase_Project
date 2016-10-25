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
