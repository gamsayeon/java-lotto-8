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


    public static Integer readPurchaseAmount() {
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                int amount = Integer.parseInt(Console.readLine());
                if (amount <= 0 || amount % 1000 != 0) {
                    throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위의 양수여야 합니다.");
                }
                return amount;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자만 입력 가능합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static WinningLotto readWinningLotto() {
        Lotto lottoNumbers;
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                String input = Console.readLine();
                lottoNumbers = new Lotto(convertToNumbers(input));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Integer bonusNumber;
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                bonusNumber = Integer.parseInt(Console.readLine());
                return new WinningLotto(lottoNumbers, bonusNumber);
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자만 입력 가능합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<Integer> convertToNumbers(String input){
        try {
            String[] splitInput = input.split(",");
            return Arrays.stream(splitInput)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
        }
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
