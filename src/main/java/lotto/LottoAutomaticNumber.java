package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class LottoAutomaticNumber {
    public Integer calculateTicketCount(Integer purchaseAmount){
        return purchaseAmount / 1000;
    }

    public Lotto generate(){
        return new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
    }

    public List<Lotto> generateMultiple(Integer lottoCount){
        List<Lotto> lottos = new ArrayList<>();
        for(int i =0; i< lottoCount; i++){
            lottos.add(generate());
        }
        return lottos;
    }
}
