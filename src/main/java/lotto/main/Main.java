package lotto.main;

import lotto.domain.LottoGameService;

public class Main {
    public static void main(String[] args) {
        LottoGameService lottoGameService = LottoGameService.from();

        lottoGameService.buyTickets();
        lottoGameService.inputWinningNumber();
        lottoGameService.makeLotteryResult();
    }
}