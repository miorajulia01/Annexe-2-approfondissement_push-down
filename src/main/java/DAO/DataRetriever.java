package DAO;

import classe.*;
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
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getLong("total_vote");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public List<VoteTypeCount> countVoteByType() {
        List<VoteTypeCount> results = new ArrayList<VoteTypeCount>();
        String sql = "select  vote_type, count(id) as count from vote group by vote_type";

        try (Connection connection = dbConn.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
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

    public List<CandidateVoteCount> countCandidateVoteByType() {
        List<CandidateVoteCount> results = new ArrayList<>();
        String sql = "select c.name,\n" +
                "       count(case when vote_type = 'VALID' then  1 end) as valid_vote\n" +
                "from candidate c\n" +
                "left join vote v\n" +
                "on c.id = v.candidate_id\n" +
                "group by c.name ";

        try (Connection connection = dbConn.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CandidateVoteCount votes = new CandidateVoteCount();
                votes.setCandidateName(rs.getString("name"));
                votes.setValidVoteCount(rs.getLong("valid_vote"));
                results.add(votes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    public VoteSummary computeVoteSummary() {
        VoteSummary summary = new VoteSummary();
        String sql = "select count(case when vote.vote_type = 'VALID' then 1 end) as valid_count,\n" +
                "        count(case when vote.vote_type = 'BLANK' then 1 end) as blank_count,\n" +
                "        count(case when vote.vote_type = 'NULL' then 1 end) as null_count\n" +
                "from vote";

        try (Connection connection = dbConn.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                summary.setValidCount(rs.getLong("valid_count"));
                summary.setBlackCount(rs.getLong("blank_count"));
                summary.setNullCount(rs.getLong("null_count"));
            }
            return summary;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double computeTurnoutRate() {
        Double results = 0.0;
        String sql = "select (count(voter_id) / count(vote.id)) * 100.0 as taux_participation from vote";

        try (Connection connection = dbConn.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                results = rs.getDouble("taux_participation");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return results;
    }

    public ElectionResult findWinner(){
        ElectionResult results = new ElectionResult();
        String sql = "select c.name,\n" +
                "       count(case when vote.vote_type = 'VALID' then 1 end) as valid_vote_count\n" +
                "from vote\n" +
                "left join candidate c\n" +
                "on c.id = vote.candidate_id\n" +
                "group by c.name\n" +
                "order by valid_vote_count desc\n" +
                "limit 1";

        try (Connection connection = dbConn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while (rs.next()) {
                results.setCandidateName(rs.getString("name"));
                results.setValidVoteCount(rs.getLong("valid_vote_count"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }
}