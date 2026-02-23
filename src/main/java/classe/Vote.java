package classe;

import java.util.Objects;

public class Vote {
    private int id;
    private VoteType voteType;

    public Vote() {}

    public Vote(int id, VoteType voteType) {
        this.id = id;
        this.voteType = voteType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return id == vote.id && voteType == vote.voteType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, voteType);
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", voteType=" + voteType +
                '}';
    }
}
