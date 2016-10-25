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



