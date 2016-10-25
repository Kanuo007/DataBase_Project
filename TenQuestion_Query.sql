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