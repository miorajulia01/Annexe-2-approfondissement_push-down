package DAO;

import classe.VoteType;
import classe.VoteTypeCount;
import classe.Voter;
import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<VoteTypeCount> countVoteByTYpe(){
        List<VoteTypeCount> results = new ArrayList<VoteTypeCount>();
        String sql = "select  vote_type, count(id) as count from vote group by vote_type";

        try(Connection connection = dbConn.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                VoteTypeCount votes = new VoteTypeCount();
                votes.setCount(rs.getInt("count"));
                votes.setVoteType(VoteType.valueOf(rs.getString("vote_type")));
                results.add(votes);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

}
