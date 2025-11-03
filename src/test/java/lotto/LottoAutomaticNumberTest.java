package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoAutomaticNumberTest {
    private static LottoAutomaticNumber generator;

    @BeforeAll
    static void init() {
        generator = new LottoAutomaticNumber();
    }

    @Test
    @DisplayName("1000원단위의 구입금액에 대해 발행장수가 올바른지 체크한다")
    void 구입금액_발행장수_체크확인() {
        // When
        int count = generator.calculateTicketCount(8000);

        // Then
        assertThat(count).isEqualTo(8);
    }

    @Test
    @DisplayName("자동 생성된 단일 로또 번호는 6개로 구성되어야 한다")
    void 자동_생성된_로또_번호의_개수가_6개_체크() {
        // When
        Lotto lotto = generator.generate();

        // Then
        assertThat(lotto.getNumbers().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("자동 생성된 로또의 갯수는 구입금액에 맞게 생성되어야 한다")
    void 여러_장_로또_생성시_개수_체크() {
        // Given
        int purchaseAmount = 8000;
        int expectedCount = purchaseAmount / 1000;

        // When
        List<Lotto> tickets = generator.generateMultiple(expectedCount);

        // Then
        assertThat(tickets.size()).isEqualTo(expectedCount);
    }

}
