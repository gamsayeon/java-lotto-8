package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Integer purchaseAmount = readPurchaseAmount();

        LottoAutomaticNumber lottoAutomaticNumber = new LottoAutomaticNumber();
        Integer lottoCount = lottoAutomaticNumber.calculateTicketCount(purchaseAmount);
        List<Lotto> purchasedLottos = lottoAutomaticNumber.generateMultiple(lottoCount);
        printLottos(purchasedLottos);

        WinningLotto winningLotto = readWinningLotto();
        System.out.println();

        LottoStatistics statistics = new LottoStatistics();
        for (Lotto lotto : purchasedLottos) {
            statistics.addLotto(lotto, winningLotto);
        }
        statistics.printStatistics(purchaseAmount);
    }

    public static Integer readPurchaseAmount(){
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(Console.readLine());
    }

    public static WinningLotto readWinningLotto() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        Lotto numbers = new Lotto(convertToNumbers(input));

        System.out.println("보너스 번호를 입력해 주세요.");
        Integer bonusLottoNumber = Integer.parseInt(Console.readLine());
        return new WinningLotto(numbers, bonusLottoNumber);
    }

    public static List<Integer> convertToNumbers(String input){
        String[] splitInput = input.split(",");
        return Arrays.stream(splitInput)
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
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
