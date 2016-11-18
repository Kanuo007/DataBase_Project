package blog.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import blog.model.GenderDistribution;
import blog.model.Population;

/**
 * 
 * @author AndrewSong
 *
 */
public class GenderDistributionDao extends PopulationDao {
  private static GenderDistributionDao instance = null;

  protected GenderDistributionDao() {
    super();
  }

  public static GenderDistributionDao getInstance() {
    if (GenderDistributionDao.instance == null) {
      GenderDistributionDao.instance = new GenderDistributionDao();
    }
    return GenderDistributionDao.instance;
  }

  // create an gender distribution
  public GenderDistribution create(GenderDistribution genderDistribution)
      throws SQLException {
    Population population = create(
        new Population(genderDistribution.getAddressId(), genderDistribution.getTotal()));

    String insertgenderDistribution =
        "INSERT INTO genderDistribution(PopulationId,Male,Female) VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = this.connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertgenderDistribution);
      insertStmt.setLong(1, population.getPopulationId());
      insertStmt.setInt(2, genderDistribution.getMale());
      insertStmt.setInt(3, genderDistribution.getFemale());
      insertStmt.executeUpdate();
      genderDistribution.setPopulationId(population.getPopulationId());
      return genderDistribution;
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

  // delete an gender distribution
  public GenderDistribution delete(GenderDistribution genderDistribution)
      throws SQLException {
    String deleteAddress = "DELETE FROM genderDistribution WHERE PopulationId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = this.connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteAddress);
      deleteStmt.setLong(1, genderDistribution.getPopulationId());
      deleteStmt.executeUpdate();
      super.delete(genderDistribution);
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

  // update the number of people whose gender background is not high school
  public Population updateMale(GenderDistribution genderDistribution,
      int newMale) throws SQLException {
    String updategenderDistribution =
        "UPDATE genderDistribution SET Male=? WHERE PopulationId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = this.connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updategenderDistribution);
      updateStmt.setInt(1, newMale);
      updateStmt.setLong(2, genderDistribution.getPopulationId());
      updateStmt.executeUpdate();
      genderDistribution.setMale(newMale);
      return genderDistribution;
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

  // update the number of people whose gender background is Female
  public Population updateFemale(GenderDistribution genderDistribution, int newFemale)
      throws SQLException {
    String updategenderDistribution =
        "UPDATE genderDistribution SET Female=? WHERE PopulationId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = this.connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updategenderDistribution);
      updateStmt.setInt(1, newFemale);
      updateStmt.setLong(2, genderDistribution.getPopulationId());
      updateStmt.executeUpdate();
      genderDistribution.setFemale(newFemale);
      return genderDistribution;
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

  // get population from populationId
  public GenderDistribution getGenderDistributionFromPopulationId(long populationId)
      throws SQLException {
    String selectgenderDistribution =
        "SELECT Population.PopulationId AS PopulationId, AddressId, Total, Male, Female "
            + "FROM Population INNER JOIN genderDistribution "
            + "  ON Population.PopulationId = genderDistribution.PopulationId "
            + "WHERE Population.PopulationId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = this.connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectgenderDistribution);
      selectStmt.setLong(1, populationId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        long resultPopulationId = results.getLong("PopulationId");
        long addressId = results.getLong("AddressId");
        int total = results.getInt("Total");
        int Male = results.getInt("Male");
        int Female = results.getInt("Female");
        GenderDistribution genderDistribution =
            new GenderDistribution(resultPopulationId, addressId, total, Male, Female);
        return genderDistribution;
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
}
