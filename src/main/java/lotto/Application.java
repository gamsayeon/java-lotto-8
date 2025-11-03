package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("구입금액을 입력해 주세요.");
        Integer purchaseAmount = Integer.parseInt(Console.readLine());

        LottoAutomaticNumber lottoAutomaticNumber = new LottoAutomaticNumber();
        Integer lottoCount = lottoAutomaticNumber.calculateTicketCount(purchaseAmount);
        printLottos(lottoAutomaticNumber.generateMultiple(lottoCount));
    }

    public static void printLottos(List<Lotto> lottos){
        StringBuilder printBuilder = new StringBuilder();
        printBuilder.append("\n")
                .append(lottos.size()).append("개를 구매했습니다.")
                .append("\n");
        for(Lotto lotto : lottos){
            printBuilder.append(lotto.getSortedNumbers())
                    .append("\n");
        }
        System.out.println(printBuilder);
    }
}
