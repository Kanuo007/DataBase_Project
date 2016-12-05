package blog.dal;
//Yudong Wang

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import blog.model.HouseUnitDistribution;


public class HouseUnitDistributionDao {
    protected ConnectionManager connectionManager;

    private static HouseUnitDistributionDao instance = null;

    protected HouseUnitDistributionDao() {
        connectionManager = new ConnectionManager();
    }

    public static HouseUnitDistributionDao getInstance() {
        if (instance == null) {
            instance = new HouseUnitDistributionDao();
        }
        return instance;
    }



    //HouseHoldId SingleUnit TwoToNineUnit TenMoreUnit MobileHome
    public HouseUnitDistribution getHouseUnitDistributionByHouseHoldId(int houseHoldId) throws SQLException {
        String selectHouseUnitDistribution = "SELECT * FROM HouseUnitDistribution WHERE HouseHoldId = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectHouseUnitDistribution);
            selectStmt.setInt(1, houseHoldId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int SingleUnit = results.getInt("SingleUnit");
                int TwoToNineUnit = results.getInt("TwoToNineUnit");
                int TenMoreUnit = results.getInt("TenMoreUnit");
                int MobileHome = results.getInt("MobileHome");
                HouseUnitDistribution houseUnitDistribution = new HouseUnitDistribution(houseHoldId
                    , SingleUnit,TwoToNineUnit,TenMoreUnit,MobileHome);
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
}
