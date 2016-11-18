package blog.dal;
/*
 * wen chen
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import blog.model.Diversity;



public class DiversityDao {
  protected ConnectionManager connectionManager;

  private static DiversityDao instance = null;
  protected DiversityDao() {
      connectionManager = new ConnectionManager();
  }
  public static DiversityDao getInstance() {
      if(instance == null) {
          instance = new DiversityDao();
      }
      return instance;
  }

  public Diversity create(Diversity diversity) throws SQLException {
      String insertDiversity =
          "INSERT INTO Diversity(PopulationId, hispanic, white, black, aian, asian, NHOPI, SOR) " +
          "VALUES(?,?,?,?,?,?,?,?);";
      Connection connection = null;
      PreparedStatement insertStmt = null;
      ResultSet resultKey = null;
      try {
          connection = connectionManager.getConnection();
          insertStmt = connection.prepareStatement(insertDiversity);
          insertStmt.setLong(1, diversity.getPopulationId());
          insertStmt.setInt(2, diversity.getHispanic());
          insertStmt.setInt(3, diversity.getWhite());
          insertStmt.setInt(4, diversity.getBlack());
          insertStmt.setInt(5, diversity.getAian());
          insertStmt.setInt(6, diversity.getAsian());
          insertStmt.setInt(7, diversity.getNHOPI());
          insertStmt.setInt(8, diversity.getSOR());
          insertStmt.executeUpdate();
          return diversity;
      } catch (SQLException e) {
          e.printStackTrace();
          throw e;
      } finally {
          if(connection != null) {
              connection.close();
          }
          if(insertStmt != null) {
              insertStmt.close();
          }
          if(resultKey != null) {
              resultKey.close();
          }
      }
  }







/**
 * Delete the AreaDistributionDao instance.
 * This runs a DELETE statement.
 */
public Diversity delete(Diversity diversity) throws SQLException {
  String deleteAreaDistribution = "DELETE FROM Diversity WHERE PopulationId=?;";
  Connection connection = null;
  PreparedStatement deleteStmt = null;   
  try {
    connection = connectionManager.getConnection();
    deleteStmt = connection.prepareStatement(deleteAreaDistribution);
    deleteStmt.setLong(1, diversity.getPopulationId());
    deleteStmt.executeUpdate();
    return null;
    } catch (SQLException e) {
    e.printStackTrace();
    throw e;
    } finally {
    if(connection != null) {
        connection.close();
    }
    if(deleteStmt != null) {
        deleteStmt.close();
    }
}
}


/**
 * GetDiversityByPopulationId
 * Return corresponding AreaDistribution
 */
public Diversity getDiversityByPopulationId(long populationId) throws SQLException {
    String selectDiversity =
        "SELECT PopulationId, hispanic, white, black, aian, asian, NHOPI, SOR FROM Diversity WHERE PopulationId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
        connection = connectionManager.getConnection();
        selectStmt = connection.prepareStatement(selectDiversity);
        selectStmt.setLong(1, populationId);
        results = selectStmt.executeQuery();
        if(results.next()) {
            int hispanic = results.getInt("hispanic");
            int white =  results.getInt("white");
            int black = results.getInt("black");
            int aian = results.getInt("aian");
            int asian = results.getInt("asian");
            int NHOPI = results.getInt("NHOPI");
            int SOR = results.getInt("SOR");
            Diversity diversity = new Diversity(populationId, hispanic, white, black, aian, asian, NHOPI, SOR);
            return diversity;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    } finally {
        if(connection != null) {
            connection.close();
        }
        if(selectStmt != null) {
            selectStmt.close();
        }
        if(results != null) {
            results.close();
        }
    }
    return null;
}

}
