package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoStatisticsTest {
    @Test
    @DisplayName("로또 번호가 당첨 번호와 3개 일치하면 3등 상금 5,000원을 반환한다.")
    void 로또번호_3개_일치_당첨금_확인() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        // when
        int matchCount = lotto.getMatchedCount(winningLotto.getWinningLotto()); // 일치 개수 구함
        int prize = LottoResult.valueOf(matchCount, false).getPrize(); // 등수별 상금 반환

        // then
        assertThat(matchCount).isEqualTo(3);
        assertThat(prize).isEqualTo(5000);
    }

    @Test
    @DisplayName("로또 번호가 당첨 번호와 5개 일치하고 보너스 번호 일치시 2등 상금 30,000,000원을 반환한다.")
    void 로또번호_5개_일치_보너스번호_일치_당첨금_확인() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        // when
        int matchCount = lotto.getMatchedCount(winningLotto.getWinningLotto());
        Boolean bonusMatch = lotto.getSortedNumbers().contains(winningLotto.getBonusNumber());
        int prize = LottoResult.valueOf(matchCount, false).getPrize();

        // then
        assertThat(matchCount).isEqualTo(5);
        assertThat(bonusMatch).isEqualTo(true);
        assertThat(prize).isEqualTo(30000000);
    }

    @Test
    @DisplayName("로또 번호가 당첨 번호와 6개 일치하면 1등 상금 2,000,000,000원을 반환한다.")
    void 로또번호_6개_일치_당첨금_확인() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        // when
        int matchCount = lotto.getMatchedCount(winningLotto.getWinningLotto());
        int prize = LottoResult.valueOf(matchCount, false).getPrize();

        // then
        assertThat(matchCount).isEqualTo(6);
        assertThat(prize).isEqualTo(2000000000);
    }
}
