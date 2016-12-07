package blog.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.model.Household;
import blog.model.OccupiedDistribution;


public class OccupiedDistributionDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static OccupiedDistributionDao instance = null;

  protected OccupiedDistributionDao() {
    this.connectionManager = new ConnectionManager();
  }

  public static OccupiedDistributionDao getInstance() {
    if (OccupiedDistributionDao.instance == null) {
      OccupiedDistributionDao.instance = new OccupiedDistributionDao();
    }
    return OccupiedDistributionDao.instance;
  }


  /**
   * Save the OccupiedDistribution instance by storing it in your MySQL instance. This runs a INSERT
   * statement.
   */
  public OccupiedDistribution create(OccupiedDistribution occupiedDistribution)
      throws SQLException {
    String insertOccupiedDistribution =
        "INSERT INTO OccupiedDistribution(HouseHoldId, TotalOccupiedUnit,"
            + "TotalVacantUnit, RenterOccupiedUnit, OwnerOccupiedUnit) VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = this.connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertOccupiedDistribution);
      insertStmt.setInt(1, occupiedDistribution.getHousedhold().getHouseholdId());
      insertStmt.setInt(2, occupiedDistribution.getTotalOccupiedUnit());
      insertStmt.setInt(3, occupiedDistribution.getTotalVacantUnit());
      insertStmt.setInt(4, occupiedDistribution.getRenterOccupiedUnit());
      insertStmt.setInt(5, occupiedDistribution.getOwnerOccupiedUnit());
      insertStmt.executeUpdate();
      return occupiedDistribution;
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
   * Delete the OccupiedDistribution instance. This runs a DELETE statement.
   */
  public OccupiedDistribution delete(OccupiedDistribution occupiedDistribution)
      throws SQLException {
    String deleteOccupiedDistribution = "DELETE FROM OccupiedDistribution WHERE HouseHoldId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = this.connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteOccupiedDistribution);
      deleteStmt.setInt(1, occupiedDistribution.getHousedhold().getHouseholdId());
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
   * Get the OccupiedDistribution record by fetching it from your MySQL instance. This runs a SELECT
   * statement and returns a single OccupiedDistribution instance.
   */
  public OccupiedDistribution getOccupiedDistributionFromHouseHoldId(int householdId)
      throws SQLException {
    String selectHouseUnitDistribution = "SELECT HouseHoldId, TotalOccupiedUnit, TotalVacantUnit,"
        + "RenterOccupiedUnit, OwnerOccupiedUnit FROM OccupiedDistribution WHERE HouseHoldId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    HouseholdDao householdDao = HouseholdDao.getInstance();
    try {
      connection = this.connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectHouseUnitDistribution);
      selectStmt.setInt(1, householdId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        int houseHoldId = results.getInt("HouseHoldId");
        int totalOccupiedUnit = results.getInt("TotalOccupiedUnit");
        int totalVacantUnit = results.getInt("TotalVacantUnit");
        int renterOccupiedUnit = results.getInt("renterOccupiedUnit");
        int ownerOccupiedUnit = results.getInt("OwnerOccupiedUnit");
        Household household = householdDao.getHouseholdFromHouseholdId(houseHoldId);

        OccupiedDistribution houseUnitDistribution = new OccupiedDistribution(totalOccupiedUnit,
            totalVacantUnit, renterOccupiedUnit, ownerOccupiedUnit, household);
        return houseUnitDistribution;
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
   * Get the OccupiedDistribution record by fetching it from your MySQL instance. This runs a SELECT
   * statement and returns a list of OccupiedDistribution instances.
   */
  public List<OccupiedDistribution> getOccupiedDistributionByTotalOccupiedUnit(
      int totalOccupiedUnitFrom, int totalOccupiedUnitTo) throws SQLException {
    List<OccupiedDistribution> occupiedDistributions = new ArrayList<>();
    String selectOccupiedDistribution;
    if ((totalOccupiedUnitFrom < 0) && (totalOccupiedUnitFrom < 0)) {
      return occupiedDistributions;
    } else if (totalOccupiedUnitFrom < 0) {
      totalOccupiedUnitFrom = 0;
    } else if (totalOccupiedUnitTo < 0) {
      totalOccupiedUnitTo = Integer.MAX_VALUE;
    }
    selectOccupiedDistribution =
        "SELECT HouseholdId, TotalOccupiedUnit,TotalVacantUnit,RenterOccupiedUnit,OwnerOccupiedUnit "
            + "FROM OccupiedDistribution WHERE TotalOccupiedUnit >=? AND TotalOccupiedUnit <= ? limit 50;";

    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = this.connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectOccupiedDistribution);
      selectStmt.setInt(1, totalOccupiedUnitFrom);
      selectStmt.setInt(2, totalOccupiedUnitTo);
      results = selectStmt.executeQuery();
      HouseholdDao householdDao = HouseholdDao.getInstance();
      while (results.next()) {
        int resultHouseholdId = results.getInt("HouseholdId");
        int totalOccupiedUnit = results.getInt("TotalOccupiedUnit");
        int totalVacantUnit = results.getInt("TotalVacantUnit");
        int renterOccupiedUnit = results.getInt("RenterOccupiedUnit");
        int ownerOccupiedUnit = results.getInt("OwnerOccupiedUnit");

        Household household = householdDao.getHouseholdFromHouseholdId(resultHouseholdId);
        OccupiedDistribution occupiedDistribution = new OccupiedDistribution(totalOccupiedUnit,
            totalVacantUnit, renterOccupiedUnit, ownerOccupiedUnit, household);
        occupiedDistributions.add(occupiedDistribution);
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
    return occupiedDistributions;
  }


  /**
   * Update the content of the OccupiedDistribution instance. This runs a UPDATE statement.
   */
  public OccupiedDistribution updateTotalOccupiedUnit(OccupiedDistribution occupiedDistribution,
      int newRenterOccupiedUnit) throws SQLException {
    String updateoccupiedDistribution =
        "UPDATE OccupiedDistribution SET RenterOccupiedUnit=?," + "TotalOccupiedUnit=?,"
            + "TotalVacantUnit=?," + "OwnerOccupiedUnit=? WHERE HouseholdId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    HouseholdDao householdDao = HouseholdDao.getInstance();

    try {
      connection = this.connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateoccupiedDistribution);
      updateStmt.setInt(1, newRenterOccupiedUnit);


      int renterOccupiedUnitUpdate =
          newRenterOccupiedUnit - occupiedDistribution.getRenterOccupiedUnit();
      int newtotalOccupiedUnit =
          occupiedDistribution.getTotalOccupiedUnit() + renterOccupiedUnitUpdate;
      updateStmt.setInt(2, newtotalOccupiedUnit);

      int householdId = occupiedDistribution.getHousedhold().getHouseholdId();
      Household household = householdDao.getHouseholdFromHouseholdId(householdId);

      int newTotalVacantUnit = household.getTotalNumOfHouseholds() - newtotalOccupiedUnit;
      updateStmt.setInt(3, newTotalVacantUnit);


      int newOwnerOccupiedUnit = newtotalOccupiedUnit - renterOccupiedUnitUpdate;
      updateStmt.setInt(4, newOwnerOccupiedUnit);
      updateStmt.setInt(5, occupiedDistribution.getHousedhold().getHouseholdId());

      updateStmt.executeUpdate();

      occupiedDistribution.setRenterOccupiedUnit(newRenterOccupiedUnit);
      occupiedDistribution.setTotalOccupiedUnit(newtotalOccupiedUnit);
      occupiedDistribution.setRenterOccupiedUnit(newTotalVacantUnit);
      occupiedDistribution.setRenterOccupiedUnit(newOwnerOccupiedUnit);

      return occupiedDistribution;
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
}
