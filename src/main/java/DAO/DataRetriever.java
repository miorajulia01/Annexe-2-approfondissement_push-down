package DAO;

import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataRetriever {
    private DatabaseConnection dbConn;

    public DataRetriever() {
        this.dbConn = new DatabaseConnection();
    }


    public long countAllVotes() {
        String sql = "select count(id) as total_vote from vote";
        try(Connection conn = dbConn.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            if (rs.next()){
                return rs.getLong("total_vote");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}
