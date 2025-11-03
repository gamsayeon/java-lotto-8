package lotto;

import java.text.DecimalFormat;
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

    public void printStatistics(double purchaseAmount) {
        StringBuilder printBuilder = new StringBuilder();
        printBuilder.append("당첨 통계\n");
        printBuilder.append("---------\n");
        for (LottoResult result : LottoResult.values()) {
            int count = statistics.get(result);
            String line = String.format("%d개 일치%s (%s원) - %d개%n",
                    result.getMatchCount(),
                    result == LottoResult.FIVE_BONUS ? ", 보너스 볼 일치" : "",
                    String.format("%,d", result.getPrize()),
                    count);
            printBuilder.append(line);
        }
        double yield = calculateYield(purchaseAmount,
                statistics.entrySet().stream()
                        .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                        .sum()
        );
        DecimalFormat df = new DecimalFormat("#.##");
        String yieldStr = df.format(yield);

        printBuilder.append(String.format("총 수익률은 %s%%입니다.%n", yieldStr));

        System.out.println(printBuilder);
    }

    public double calculateYield(double purchaseAmount, double totalPurchaseAmount) {
        double totalYield = (totalPurchaseAmount / purchaseAmount) * 100;
        return Math.round(totalYield * 100) / 100.0;
    }
}
