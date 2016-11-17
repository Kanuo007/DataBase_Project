package blog.dal;

/**
 * @author LiYang
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import blog.model.EducationDistribution;
import blog.model.Population;

/**
 * @author LiYang
 */
public class EducationDistributionDao extends PopulationDao {
  private static EducationDistributionDao instance = null;

  protected EducationDistributionDao() {
    super();
  }

  public static EducationDistributionDao getInstance() {
    if (EducationDistributionDao.instance == null) {
      EducationDistributionDao.instance = new EducationDistributionDao();
    }
    return EducationDistributionDao.instance;
  }

  // create an education distribution
  public EducationDistribution create(EducationDistribution educationDistribution)
      throws SQLException {
    Population population = create(
        new Population(educationDistribution.getAddressId(), educationDistribution.getTotal()));

    String insertEducationDistribution =
        "INSERT INTO EducationDistribution(PopulationId,notHighSchool,College) VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = this.connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertEducationDistribution);
      insertStmt.setLong(1, population.getPopulationId());
      insertStmt.setInt(2, educationDistribution.getNotHighSchool());
      insertStmt.setInt(3, educationDistribution.getCollege());
      insertStmt.executeUpdate();
      educationDistribution.setPopulationId(population.getPopulationId());
      return educationDistribution;
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

  // delete an education distribution
  public EducationDistribution delete(EducationDistribution educationDistribution)
      throws SQLException {
    String deleteAddress = "DELETE FROM EducationDistribution WHERE PopulationId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = this.connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteAddress);
      deleteStmt.setLong(1, educationDistribution.getPopulationId());
      deleteStmt.executeUpdate();
      super.delete(educationDistribution);
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

  // update the number of people whose education background is not high school
  public Population updateNotHighSchool(EducationDistribution educationDistribution,
      int newNotHighSchool) throws SQLException {
    String updateEducationDistribution =
        "UPDATE EducationDistribution SET notHighSchool=? WHERE PopulationId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = this.connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateEducationDistribution);
      updateStmt.setInt(1, newNotHighSchool);
      updateStmt.setLong(2, educationDistribution.getPopulationId());
      updateStmt.executeUpdate();
      educationDistribution.setNotHighSchool(newNotHighSchool);
      return educationDistribution;
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

  // update the number of people whose education background is college
  public Population updateCollege(EducationDistribution educationDistribution, int newCollege)
      throws SQLException {
    String updateEducationDistribution =
        "UPDATE EducationDistribution SET College=? WHERE PopulationId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = this.connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateEducationDistribution);
      updateStmt.setInt(1, newCollege);
      updateStmt.setLong(2, educationDistribution.getPopulationId());
      updateStmt.executeUpdate();
      educationDistribution.setCollege(newCollege);
      return educationDistribution;
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
  public EducationDistribution getEducationDistributionFromPopulationId(long populationId)
      throws SQLException {
    String selectEducationDistribution =
        "SELECT Population.PopulationId AS PopulationId, AddressId, Total, NotHighSchool, College "
            + "FROM Population INNER JOIN EducationDistribution "
            + "  ON Population.PopulationId = EducationDistribution.PopulationId "
            + "WHERE Population.PopulationId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = this.connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectEducationDistribution);
      selectStmt.setLong(1, populationId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        long resultPopulationId = results.getLong("PopulationId");
        long addressId = results.getLong("AddressId");
        int total = results.getInt("Total");
        int notHighSchool = results.getInt("NotHighSchool");
        int college = results.getInt("College");
        EducationDistribution educationDistribution =
            new EducationDistribution(resultPopulationId, addressId, total, notHighSchool, college);
        return educationDistribution;
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
