package neighbor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import neighbor.model.AreaDistribution;



public class AreaDistributionDao {
    protected ConnectionManager connectionManager;

    private static AreaDistributionDao instance = null;
    protected AreaDistributionDao() {
        connectionManager = new ConnectionManager();
    }
    public static AreaDistributionDao getInstance() {
        if(instance == null) {
            instance = new AreaDistributionDao();
        }
        return instance;
    }

    public AreaDistribution create(AreaDistribution areaDistribution) throws SQLException {
      String insertAreaDistribution =
            "INSERT INTO AreaDistribution(populationId, Urban, Urbancluster, Rural) " +
            "VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertAreaDistribution);
            insertStmt.setLong(1, areaDistribution.getPopulationId());
            insertStmt.setInt(2, areaDistribution.getUrban());
            insertStmt.setInt(3, areaDistribution.getUrbancluster());
            insertStmt.setInt(4, areaDistribution.getRural());
            
            insertStmt.executeUpdate();
            return areaDistribution;
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
    public AreaDistribution delete(AreaDistribution areaDistribution) throws SQLException {
      String deleteAreaDistribution = "DELETE FROM AreaDistribution WHERE PopulationId=?;";
      Connection connection = null;
      PreparedStatement deleteStmt = null;   
      try {
        connection = connectionManager.getConnection();
        deleteStmt = connection.prepareStatement(deleteAreaDistribution);
        deleteStmt.setLong(1, areaDistribution.getPopulationId());
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
     * GetAreaDistributionDaoByPopulationId
     * Return corresponding AreaDistribution
     */
    public AreaDistribution getAreaDistributionByPopulationId(long populationId) throws SQLException {
      String selectAreaDistribution =
            "SELECT PopulationId, Urban, Urbancluster, Rural FROM AreaDistribution WHERE PopulationId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAreaDistribution);
            selectStmt.setLong(1, populationId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                int urban = results.getInt("Urban");
                int urbancluster =  results.getInt("Urbancluster");
                int rural = results.getInt("Rural");
                AreaDistribution areaDistribution = new AreaDistribution(populationId, urban, urbancluster, rural);
                return areaDistribution;
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
    
    
    
    
    public AreaDistribution updateUrbanAmount(AreaDistribution area, Integer UrbanAmount) throws SQLException {
      String updatePerson = "UPDATE AreaDistribution SET Urban=? WHERE PopulationId=?;";
      Connection connection = null;
      PreparedStatement updateStmt = null;
      try {
          connection = connectionManager.getConnection();
          updateStmt = connection.prepareStatement(updatePerson);
          updateStmt.setInt(1, UrbanAmount);
          updateStmt.setLong(2, area.getPopulationId());
          updateStmt.executeUpdate();
          
          // Update the person param before returning to the caller.
          area.setUrban(UrbanAmount);
          return area;
      } catch (SQLException e) {
          e.printStackTrace();
          throw e;
      } finally {
          if(connection != null) {
              connection.close();
          }
          if(updateStmt != null) {
              updateStmt.close();
          }
      }
  }

    
    
    
 
}
