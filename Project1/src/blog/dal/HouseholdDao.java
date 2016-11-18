package blog.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import blog.model.Address;
import blog.model.Household;

public class HouseholdDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static HouseholdDao instance = null;

  protected HouseholdDao() {
    this.connectionManager = new ConnectionManager();
  }

  public static HouseholdDao getInstance() {
    if (HouseholdDao.instance == null) {
      HouseholdDao.instance = new HouseholdDao();
    }
    return HouseholdDao.instance;
  }

  /**
   * Save the household instance by storing it in your MySQL instance. This runs a INSERT statement.
   */
  public Household create(Household household) throws SQLException {
    String insertHousehold = "INSERT INTO Household(HouseholdId,"
        + "AddressId, TotalIncomeOfHouseholds, AVGIncomeOfHouseholds,"
        + "NumOfFamilyWithChildUnderSix, NumOfFamilyWithOneOrMoreUnder18,"
        + "TotalPersonsInHouseholds, NumOfHouseholdsWithoutTeleService,"
        + "NumOfHouseholdsLackFacilities,  NumOfHouseholdsBuiltAfter2010,"
        + "MedianHouseValue, totalHouseValue, NumOfAssistanceFamilies,TotalNumOfHouseholds,"
        + "NumOfHouseholdsMovedInAfter2010) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = this.connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertHousehold);
      insertStmt = connection.prepareStatement(insertHousehold, Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, household.getHouseholdId());
      insertStmt.setLong(2, household.getAddress().getAddressId());
      insertStmt.setLong(3, household.getTotalIncomeOfHouseholds());
      insertStmt.setInt(4, household.getAVGIncomeOfHouseholds());
      insertStmt.setInt(5, household.getNumOfFamilyWithChildUnderSix());
      insertStmt.setInt(6, household.getNumOfFamilyWithOneOrMoreUnder18());
      insertStmt.setInt(7, household.getTotalPersonsInHouseholds());
      insertStmt.setInt(8, household.getNumOfHouseholdsWithoutTeleService());
      insertStmt.setInt(9, household.getNumOfHouseholdsLackFacilities());
      insertStmt.setInt(10, household.getNumOfHouseholdsBuiltAfter2010());
      insertStmt.setInt(11, household.getMedianHouseValue());
      insertStmt.setLong(12, household.getTotalHouseValue());
      insertStmt.setLong(13, household.getNumOfAssistanceFamilies());
      insertStmt.setInt(14, household.getTotalNumOfHouseholds());
      insertStmt.setInt(15, household.getNumOfHouseholdsMovedInAfter2010());

      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int householdId = -1;
      if (resultKey.next()) {
        householdId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }

      household.setHouseholdId(householdId);
      return household;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStmt != null) {
        insertStmt.close();
      }
    }
  }


  /**
   * Update the medianHouseValue of the HouseHold instance. This runs a UPDATE statement.
   */
  public Household updateMedianHouseValue(Household household, int newMedianHouseValue)
      throws SQLException {
    String updateHouseHolds = "UPDATE Household SET MedianHouseValue=? WHERE HouseholdId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = this.connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateHouseHolds);
      updateStmt.setInt(1, newMedianHouseValue);
      updateStmt.setInt(2, household.getHouseholdId());
      updateStmt.executeUpdate();
      household.setMedianHouseValue(newMedianHouseValue);
      return household;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (updateStmt != null) {
        updateStmt.close();
      }
    }
  }


  /**
   * Delete the Household instance. This runs a DELETE statement.
   */
  public Household delete(Household household) throws SQLException {
    String deleteHousehold = "DELETE FROM household WHERE householdId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = this.connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteHousehold);
      deleteStmt.setInt(1, household.getHouseholdId());
      deleteStmt.executeUpdate();
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }


  /**
   * Get the Household record by fetching it from your MySQL instance. This runs a SELECT statement
   * and returns a single Housed instance.
   */
  public Household getHouseholdFromHouseholdId(int householdId) throws SQLException {
    String selectHousehold =
        "SELECT HouseholdId," + "AddressId, TotalIncomeOfHouseholds, AVGIncomeOfHouseholds,"
            + "NumOfFamilyWithChildUnderSix, NumOfFamilyWithOneOrMoreUnder18,"
            + "TotalPersonsInHouseholds, NumOfHouseholdsWithoutTeleService,"
            + "NumOfHouseholdsLackFacilities,  NumOfHouseholdsBuiltAfter2010,"
            + "MedianHouseValue, totalHouseValue, NumOfAssistanceFamilies,TotalNumOfHouseholds,"
            + "NumOfHouseholdsMovedInAfter2010 FROM Household WHERE HouseholdId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = this.connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectHousehold);
      selectStmt.setInt(1, householdId);
      results = selectStmt.executeQuery();
      AddressDao addressDao = AddressDao.getInstance();
      if (results.next()) {
        int resultHouseholdId = results.getInt("HouseholdId");
        Address address = addressDao.getAddressFromAddressId(results.getLong("addressId"));
        long totalIncomeOfHouseholds = results.getLong("TotalIncomeOfHouseholds");
        int AVGIncomeOfHouseholds = results.getInt("AVGIncomeOfHouseholds");
        int NumOfFamilyWithChildUnderSix = results.getInt("NumOfFamilyWithChildUnderSix");
        int NumOfFamilyWithOneOrMoreUnder18 = results.getInt("NumOfFamilyWithOneOrMoreUnder18");
        int TotalPersonsInHouseholds = results.getInt("TotalPersonsInHouseholds");
        int NumOfHouseholdsWithoutTeleService = results.getInt("NumOfHouseholdsWithoutTeleService");
        int NumOfHouseholdsLackFacilities = results.getInt("NumOfHouseholdsLackFacilities");
        int NumOfHouseholdsBuiltAfter2010 = results.getInt("NumOfHouseholdsBuiltAfter2010");
        int MedianHouseValue = results.getInt("MedianHouseValue");
        long TotalHouseValue = results.getLong("totalHouseValue");
        int NumOfAssistanceFamilies = results.getInt("NumOfAssistanceFamilies");
        int TotalNumOfHouseholds = results.getInt("TotalNumOfHouseholds");
        int NumOfHouseholdsMovedInAfter2010 = results.getInt("NumOfHouseholdsMovedInAfter2010");


        Household household = new Household(resultHouseholdId, address, totalIncomeOfHouseholds,
            AVGIncomeOfHouseholds, NumOfFamilyWithChildUnderSix, NumOfFamilyWithOneOrMoreUnder18,
            TotalPersonsInHouseholds, NumOfHouseholdsWithoutTeleService,
            NumOfHouseholdsLackFacilities, NumOfHouseholdsBuiltAfter2010, MedianHouseValue,
            TotalHouseValue, NumOfAssistanceFamilies, TotalNumOfHouseholds,
            NumOfHouseholdsMovedInAfter2010);

        return household;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return null;
  }


  /**
   * Get the Household record by fetching it from your MySQL instance. This runs a SELECT statement
   * and returns a single Housed instance.
   */
  public List<Household> getHouseholdByMedianHouseValue(int medianHouseValueFrom,
      int medianHouseValueTo) throws SQLException {
    List<Household> households = new ArrayList<>();
    String selectHousehold;
    if ((medianHouseValueFrom < 0) && (medianHouseValueTo < 0)) {
      return households;
    } else if (medianHouseValueFrom < 0) {
      medianHouseValueFrom = 0;
    } else if (medianHouseValueTo < 0) {
      medianHouseValueTo = Integer.MAX_VALUE;
    }
    selectHousehold =
        "SELECT HouseholdId,AddressId, TotalIncomeOfHouseholds, AVGIncomeOfHouseholds,"
            + "NumOfFamilyWithChildUnderSix, NumOfFamilyWithOneOrMoreUnder18,"
            + "TotalPersonsInHouseholds, NumOfHouseholdsWithoutTeleService,"
            + "NumOfHouseholdsLackFacilities,  NumOfHouseholdsBuiltAfter2010,"
            + "MedianHouseValue, totalHouseValue, NumOfAssistanceFamilies,TotalNumOfHouseholds,"
            + "NumOfHouseholdsMovedInAfter2010 FROM Household WHERE MedianHouseValue >=? AND "
            + "MedianHouseValue <= ? limit 50;";

    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = this.connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectHousehold);
      selectStmt.setInt(1, medianHouseValueFrom);
      selectStmt.setInt(2, medianHouseValueTo);
      results = selectStmt.executeQuery();
      AddressDao addressDao = AddressDao.getInstance();
      while (results.next()) {
        int resultHouseholdId = results.getInt("HouseholdId");
        Address address = addressDao.getAddressFromAddressId(results.getLong("addressId"));
        long totalIncomeOfHouseholds = results.getLong("TotalIncomeOfHouseholds");
        int AVGIncomeOfHouseholds = results.getInt("AVGIncomeOfHouseholds");
        int NumOfFamilyWithChildUnderSix = results.getInt("NumOfFamilyWithChildUnderSix");
        int NumOfFamilyWithOneOrMoreUnder18 = results.getInt("NumOfFamilyWithOneOrMoreUnder18");
        int TotalPersonsInHouseholds = results.getInt("TotalPersonsInHouseholds");
        int NumOfHouseholdsWithoutTeleService = results.getInt("NumOfHouseholdsWithoutTeleService");
        int NumOfHouseholdsLackFacilities = results.getInt("NumOfHouseholdsLackFacilities");
        int NumOfHouseholdsBuiltAfter2010 = results.getInt("NumOfHouseholdsBuiltAfter2010");
        int MedianHouseValue = results.getInt("MedianHouseValue");
        long TotalHouseValue = results.getLong("totalHouseValue");
        int NumOfAssistanceFamilies = results.getInt("NumOfAssistanceFamilies");
        int TotalNumOfHouseholds = results.getInt("TotalNumOfHouseholds");
        int NumOfHouseholdsMovedInAfter2010 = results.getInt("NumOfHouseholdsMovedInAfter2010");

        Household household = new Household(resultHouseholdId, address, totalIncomeOfHouseholds,
            AVGIncomeOfHouseholds, NumOfFamilyWithChildUnderSix, NumOfFamilyWithOneOrMoreUnder18,
            TotalPersonsInHouseholds, NumOfHouseholdsWithoutTeleService,
            NumOfHouseholdsLackFacilities, NumOfHouseholdsBuiltAfter2010, MedianHouseValue,
            TotalHouseValue, NumOfAssistanceFamilies, TotalNumOfHouseholds,
            NumOfHouseholdsMovedInAfter2010);
        households.add(household);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return households;
  }

}
