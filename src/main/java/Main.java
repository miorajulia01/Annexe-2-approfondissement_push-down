import DAO.DataRetriever;

public class Main {
    public static void main(String[] args) {
        DataRetriever dr = new DataRetriever();
        System.out.println("total_vote: " + dr.countAllVotes());

    }
}
