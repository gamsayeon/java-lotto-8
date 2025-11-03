package lotto;

public enum LottoResult {
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    FIVE_BONUS(5, 30000000),
    SIX(6, 2000000000);

    private final int matchCount;
    private final int prize;

    LottoResult(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static LottoResult valueOf(Integer currentMatchCount, Boolean bonusMatch) {
        if(currentMatchCount == 3)
            return THREE;
        if (currentMatchCount == 4)
            return FOUR;
        if (currentMatchCount == 5 && bonusMatch)
            return FIVE_BONUS;
        if (currentMatchCount == 5)
            return FIVE;
        if (currentMatchCount == 6)
            return SIX;
        throw new IllegalArgumentException();
    }

    public Integer getPrize(){
        return this.prize;
    }

    public Integer getMatchCount() {
        return this.matchCount;
    }
}
