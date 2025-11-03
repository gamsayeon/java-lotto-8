package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningLotto {
    private final Lotto lotto;
    private final Integer bonusNumber;

    public WinningLotto(Lotto lotto, Integer bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
        validate();
    }

    private void validate() {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException();
        }

        List<Integer> numbers = lotto.getSortedNumbers();
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }

        for (Integer num : numbers) {
            if (num < 1 || num > 45) {
                throw new IllegalArgumentException();
            }
        }

        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    public Lotto getWinningLotto() {
        return lotto;
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }
}
