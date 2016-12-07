package blog.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import blog.model.Address;

public class AddressDao {
  protected ConnectionManager connectionManager;

  private static AddressDao instance = null;

  protected AddressDao() {
    this.connectionManager = new ConnectionManager();
  }

  public static AddressDao getInstance() {
    if (AddressDao.instance == null) {
      AddressDao.instance = new AddressDao();
    }
    return AddressDao.instance;
  }

  // Create an address
  public Address create(Address address) throws SQLException {
    String insertAddress =
        "INSERT INTO Address(AddressId,State,County,Tract,LandArea) VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = (Connection) this.connectionManager.getConnection();
      insertStmt = (PreparedStatement) connection.prepareStatement(insertAddress);
      insertStmt.setLong(1, address.getAddressId());
      insertStmt.setString(2, address.getState());
      insertStmt.setString(3, address.getCounty());
      insertStmt.setInt(4, address.getTract());
      insertStmt.setDouble(5, address.getLandArea());
      insertStmt.executeUpdate();
      return address;
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

  // delete an address
  public Address delete(Address address) throws SQLException {
    String deleteAddress = "DELETE FROM Address WHERE AddressId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = (Connection) this.connectionManager.getConnection();
      deleteStmt = (PreparedStatement) connection.prepareStatement(deleteAddress);
      deleteStmt.setLong(1, address.getAddressId());
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

  // update land area of a certain address
  public Address updateLandArea(Address address, Double newLandArea) throws SQLException {
    String updatePerson = "UPDATE Address SET LandArea=? WHERE AddressId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = (Connection) this.connectionManager.getConnection();
      updateStmt = (PreparedStatement) connection.prepareStatement(updatePerson);
      updateStmt.setDouble(1, newLandArea);
      updateStmt.setLong(2, address.getAddressId());
      updateStmt.executeUpdate();
      address.setLandArea(newLandArea);
      return address;
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

  // Get addresses from addressId
  public Address getAddressFromAddressId(long addressId) throws SQLException {
    String selectAddress =
        "SELECT AddressId,State,County,Tract,LandArea FROM Address WHERE AddressId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = (Connection) this.connectionManager.getConnection();
      selectStmt = (PreparedStatement) connection.prepareStatement(selectAddress);
      selectStmt.setLong(1, addressId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        long resultAddressId = results.getLong("AddressId");
        String state = results.getString("State");
        String county = results.getString("County");
        int tract = results.getInt("Tract");
        double landArea = results.getDouble("LandArea");
        Address address = new Address(resultAddressId, state, county, tract, landArea);
        return address;
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

  // Get addresses from state
  public List<Address> getAddressesFromState(String state) throws SQLException {
    List<Address> addresses = new ArrayList<>();
    String selectAddresses =
        "SELECT AddressId,State,County,Tract,LandArea FROM Address WHERE State=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = (Connection) this.connectionManager.getConnection();
      selectStmt = (PreparedStatement) connection.prepareStatement(selectAddresses);
      selectStmt.setString(1, state);
      results = selectStmt.executeQuery();
      while (results.next()) {
        long addressId = results.getLong("AddressId");
        String resultState = results.getString("State");
        String county = results.getString("County");
        int tract = results.getInt("Tract");
        double landArea = results.getDouble("LandArea");
        Address address = new Address(addressId, resultState, county, tract, landArea);
        addresses.add(address);
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
    return addresses;
  }

  // Get addresses from state and county
  public List<Address> getAddressesFromStateAndCounty(String state, String county)
      throws SQLException {
    List<Address> addresses = new ArrayList<>();
    String selectAddresses =
        "SELECT AddressId,State,County,Tract,LandArea FROM Address WHERE State=? AND County=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = (Connection) this.connectionManager.getConnection();
      selectStmt = (PreparedStatement) connection.prepareStatement(selectAddresses);
      selectStmt.setString(1, state);
      selectStmt.setString(2, county);
      results = selectStmt.executeQuery();
      while (results.next()) {
        long addressId = results.getLong("AddressId");
        String resultState = results.getString("State");
        String resultCounty = results.getString("County");
        int tract = results.getInt("Tract");
        double landArea = results.getDouble("LandArea");
        Address address = new Address(addressId, resultState, resultCounty, tract, landArea);
        addresses.add(address);
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
    return addresses;
  }

}
