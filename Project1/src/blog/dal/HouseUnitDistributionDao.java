package restaurant.dal;
//Yudong Wang

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import restaurant.model.HouseUnitDistribution;


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


    public HouseUnitDistribution create(HouseUnitDistribution houseUnitDistribution) throws SQLException {
        String insertCreditCard =
            "INSERT INTO HouseUnitDistribution(HouseHoldId,TotalHouseUnit,SingleUnit,TwoToNineUnit,TenMoreUnit,MobileHome) " +
                "VALUES(?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCreditCard);
            insertStmt.setInt(1, houseUnitDistribution.getHouseHoldId());
            insertStmt.setInt(2, houseUnitDistribution.getTotalHouseUnit());
            insertStmt.setInt(3, houseUnitDistribution.getSingleUnit());
            insertStmt.setInt(4, houseUnitDistribution.getTwoToNineUnit());
            insertStmt.setInt(5, houseUnitDistribution.getTenMoreUnit());
            insertStmt.setInt(6, houseUnitDistribution.getMobileHome());
            insertStmt.executeUpdate();
            return houseUnitDistribution;
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

    public HouseUnitDistribution updateTotalHouseUnit(HouseUnitDistribution houseUnitDistribution, int totalHouseUnit) throws SQLException {
        String updateExpiration = "UPDATE HouseUnitDistribution SET TotalHouseUnit=? WHERE HouseHoldId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateExpiration);
            updateStmt.setInt(1, totalHouseUnit);
            updateStmt.setInt(2, houseUnitDistribution.getHouseHoldId());
            updateStmt.executeUpdate();
            houseUnitDistribution.setTotalHouseUnit(totalHouseUnit);
            return houseUnitDistribution;
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

    public HouseUnitDistribution delete(HouseUnitDistribution houseUnitDistribution) throws SQLException {

        String deleteHouseUnitDistribution = "DELETE FROM HouseUnitDistribution WHERE HouseHoldId = ?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteHouseUnitDistribution);
            deleteStmt.setInt(1, houseUnitDistribution.getHouseHoldId());
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
    //HouseHoldId TotalHouseUnit SingleUnit TwoToNineUnit TenMoreUnit MobileHome
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
                int TotalHouseUnit = results.getInt("TotalHouseUnit");
                int SingleUnit = results.getInt("TotalHouseUnit");
                int TwoToNineUnit = results.getInt("TotalHouseUnit");
                int TenMoreUnit = results.getInt("TotalHouseUnit");
                int MobileHome = results.getInt("TotalHouseUnit");
                HouseUnitDistribution houseUnitDistribution = new HouseUnitDistribution(houseHoldId
                    , TotalHouseUnit, SingleUnit,TwoToNineUnit,TenMoreUnit,MobileHome);
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
