package lotto;

import java.util.EnumMap;
import java.util.Map;

public class LottoStatistics {
    private final Map<LottoResult, Integer> statistics = new EnumMap<>(LottoResult.class);

    public LottoStatistics() {
        for (LottoResult result : LottoResult.values()) {
            statistics.put(result, 0);
        }
    }

    public void addLotto(Lotto lotto, WinningLotto winningLotto) {
        int matchCount = lotto.getMatchedCount(winningLotto.getWinningLotto());
        boolean bonusMatch = lotto.isBonusMatched(winningLotto);

        if (matchCount >= 3) {
            LottoResult result = LottoResult.valueOf(matchCount, bonusMatch);
            statistics.put(result, statistics.get(result) + 1);
        }
    }

    public void printStatistics() {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (LottoResult result : LottoResult.values()) {
            int count = statistics.get(result);
            System.out.printf("%s개 일치%s (%s원) - %d개%n",
                    result.getMatchCount(),
                    result == LottoResult.FIVE_BONUS ? ", 보너스 볼 일치" : "",
                    String.format("%,d", result.getPrize()),
                    count);

        }
        System.out.println();
    }
}
