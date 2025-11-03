package lotto;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    public List<Integer> getSortedNumbers(){
        return this.numbers
                .stream()
                .sorted()
                .toList();
    }

    public boolean isBonusMatched(WinningLotto winningLotto) {
        return this.numbers.contains(winningLotto.getBonusNumber());
    }

    public Integer getMatchedCount(Lotto winningLotto) {
        int matchCount = 0;
        for (Integer number : this.numbers) {
            if (winningLotto.getSortedNumbers().contains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }
}
