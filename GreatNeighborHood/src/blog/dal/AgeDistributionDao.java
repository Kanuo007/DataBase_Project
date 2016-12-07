package blog.dal;
//Yudong Wang

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import blog.model.AgeDistribution;


public class AgeDistributionDao {
    protected ConnectionManager connectionManager;

    private static AgeDistributionDao instance = null;

    protected AgeDistributionDao() {
        connectionManager = new ConnectionManager();
    }

    public static AgeDistributionDao getInstance() {
        if (instance == null) {
            instance = new AgeDistributionDao();
        }
        return instance;
    }

    public AgeDistribution create(AgeDistribution ageDistribution) throws SQLException {
        String insertAgeDistribution =
            "INSERT INTO AgeDistribution(PopulationId,LessThan5,from5To17,from18To24,from25To44,from45To64,MoreThan65) " +
                "VALUES(?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertAgeDistribution);
            insertStmt.setLong(1, ageDistribution.getPopulationId());
            insertStmt.setInt(2, ageDistribution.getLessThan5());
            insertStmt.setInt(3, ageDistribution.getFrom5To17());
            insertStmt.setInt(4, ageDistribution.getFrom18To24());
            insertStmt.setInt(5, ageDistribution.getFrom25To44());
            insertStmt.setInt(6, ageDistribution.getFrom45To64());
            insertStmt.setInt(7, ageDistribution.getMoreThan65());
            insertStmt.executeUpdate();
            return ageDistribution;
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

    public AgeDistribution updateLessThan5(AgeDistribution ageDistribution, int newLessThan5) throws SQLException {
        String updateLessThan5 = "UPDATE AgeDistribution SET LessThan5=? WHERE PopulationId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateLessThan5);
            updateStmt.setLong(2, ageDistribution.getLessThan5());
            // Sets the Created timestamp to the current time.
            updateStmt.setInt(1, newLessThan5);
            updateStmt.executeUpdate();
            ageDistribution.setLessThan5(newLessThan5);
            return ageDistribution;
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

    public AgeDistribution delete(Long populationId) throws SQLException {

        String deleteCreditCards = "DELETE FROM AgeDistribution WHERE PopulationId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteCreditCards);
            deleteStmt.setLong(1, populationId);
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

    public AgeDistribution getAgeDistributionByPopulationId(long populationId) throws SQLException {

        String selectAgeDistribution = "SELECT *  FROM AgeDistribution WHERE PopulationId = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAgeDistribution);
            selectStmt.setLong(1, populationId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int LessThan5 = results.getInt("LessThan5");
                int from5To17 = results.getInt("from5To17");
                int from18To24 = results.getInt("from18To24");
                int from25To44 = results.getInt("from25To44");
                int from45To64 = results.getInt("from45To64");
                int MoreThan65 = results.getInt("MoreThan65");

                AgeDistribution ageDistribution = new AgeDistribution(populationId, LessThan5, from5To17, from18To24, from25To44, from45To64, MoreThan65);
                return ageDistribution;
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
