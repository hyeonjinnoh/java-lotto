package lotto.domain;

import java.util.Arrays;
import lotto.domain.judgementway.BasicJudgementWay;
import lotto.domain.judgementway.BonusJudgementWay;
import lotto.domain.judgementway.JudgementWay;
import lotto.domain.judgementway.SecondJudgementWay;

public enum LottoRank {
    NONE(0, 0, BasicJudgementWay.instance()),
    FOURTH(3, 5_000, BasicJudgementWay.instance()),
    THIRD(4, 50_000, BasicJudgementWay.instance()),
    SECOND(5, 1_500_000, SecondJudgementWay.instance()),
    BONUS(5, 30_000_000, BonusJudgementWay.instance()),
    FIRST(6, 2_000_000_000, BasicJudgementWay.instance()),
    ;
    private final int correctCount;
    private final long winPrize;
    private final JudgementWay judgementWay;

    LottoRank(int correctNum, int winPrize, JudgementWay judgementWay) {
        this.correctCount = correctNum;
        this.winPrize = winPrize;
        this.judgementWay = judgementWay;
    }

    public static LottoRank findRank(WinningResult winningResult) {
        return Arrays.stream(LottoRank.values())
                .filter(lottoRank ->
                        lottoRank.judgementWay.apply(lottoRank.correctCount, winningResult)
                )
                .findFirst()
                .orElseGet(() -> NONE);
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public long getWinPrize() {
        return winPrize;
    }
}