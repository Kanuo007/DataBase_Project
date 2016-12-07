package blog.dal;

/**
 * @author LiYang
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import blog.model.Population;

public class PopulationDao {
  protected ConnectionManager connectionManager;

  private static PopulationDao instance = null;

  protected PopulationDao() {
    this.connectionManager = new ConnectionManager();
  }

  public static PopulationDao getInstance() {
    if (PopulationDao.instance == null) {
      PopulationDao.instance = new PopulationDao();
    }
    return PopulationDao.instance;
  }

  // create a population
  public Population create(Population population) throws SQLException {
    String insertPopulation = "INSERT INTO Population(AddressId,Total) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = this.connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertPopulation, Statement.RETURN_GENERATED_KEYS);
      insertStmt.setLong(1, population.getAddressId());
      insertStmt.setInt(2, population.getTotal());
      insertStmt.executeUpdate();
      resultKey = insertStmt.getGeneratedKeys();
      long populationId = -1;
      if (resultKey.next()) {
        populationId = resultKey.getLong(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      population.setPopulationId(populationId);
      System.out.println(populationId);
      return population;
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

  // delete a population
  public Population delete(Population population) throws SQLException {
    String deleteAddress = "DELETE FROM Population WHERE PopulationId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = this.connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteAddress);
      deleteStmt.setLong(1, population.getPopulationId());
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

  // update total
  public Population updateTotal(Population population, int newTotal) throws SQLException {
    String updatePerson = "UPDATE Population SET Total=? WHERE PopulationId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = this.connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updatePerson);
      updateStmt.setInt(1, newTotal);
      updateStmt.setLong(2, population.getPopulationId());
      updateStmt.executeUpdate();
      population.setTotal(newTotal);
      return population;
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
  public Population getPopulationFromPopulationId(long populationId) throws SQLException {
    String selectAddress =
        "SELECT PopulationId, AddressId, Total FROM Population WHERE PopulationId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = this.connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectAddress);
      selectStmt.setLong(1, populationId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        long resultPopulationId = results.getLong("PopulationId");
        long addressId = results.getLong("AddressId");
        int total = results.getInt("Total");
        Population population = new Population(resultPopulationId, addressId, total);
        return population;
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

  // get population from AddressId
  public Population getPopulationFromAddressId(long addressId) throws SQLException {
    String selectAddress =
        "SELECT PopulationId, AddressId, Total FROM Population WHERE AddressId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = this.connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectAddress);
      selectStmt.setLong(1, addressId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        long populationId = results.getLong("PopulationId");
        long resultAddressId = results.getLong("AddressId");
        int total = results.getInt("Total");
        Population population = new Population(populationId, resultAddressId, total);
        return population;
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
