package classe;

import java.util.Objects;

public class VoteSummary {
    private long validCount;
    private long blackCount;
    private long nullCount;

    public VoteSummary() {}
    public VoteSummary(long validCount, long blackCount, long nullCount) {
        this.validCount = validCount;
        this.blackCount = blackCount;
        this.nullCount = nullCount;
    }

    public long getValidCount() {
        return validCount;
    }

    public void setValidCount(long validCount) {
        this.validCount = validCount;
    }

    public long getBlackCount() {
        return blackCount;
    }

    public void setBlackCount(long blackCount) {
        this.blackCount = blackCount;
    }

    public long getNullCount() {
        return nullCount;
    }

    public void setNullCount(long nullCount) {
        this.nullCount = nullCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VoteSummary that = (VoteSummary) o;
        return validCount == that.validCount && blackCount == that.blackCount && nullCount == that.nullCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(validCount, blackCount, nullCount);
    }

    @Override
    public String toString() {
        return "VoteSummary{" +
                "validCount=" + validCount +
                ", blackCount=" + blackCount +
                ", nullCount=" + nullCount +
                '}';
    }
}
