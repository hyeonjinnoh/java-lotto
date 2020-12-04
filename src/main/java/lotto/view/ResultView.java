package lotto.view;

import lotto.context.Rank;
import lotto.domain.InputMoney;
import lotto.domain.Lotto;
import lotto.domain.Ticket;
import lotto.domain.WinningResult;

import java.util.*;
import java.util.stream.Collectors;

public class ResultView {
    private ResultView() {
    }

    public static void printLotto(List<Lotto> lottoList) {
        lottoList.forEach(System.out::println);
    }

    public static void printBuyLotto(Ticket ticket) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", ticket.getManualCount(), ticket.getAutoCount());
    }

    public static void printWinningStatistics(InputMoney inputMoney, List<Lotto> lottoList, WinningResult winningResult) {
        double totalWinningMoney = 0;
        System.out.println("당첨 통계");
        System.out.println("-------");
        List<Rank> ranks = Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        for (Rank rank : ranks) {
            int countByRank = winningResult.getCountByRank(lottoList, rank);
            System.out.printf("%s개 일치 (%s원) - %s개\n", rank.getCountOfMatch(),
                    rank.getWinningMoney(), countByRank);
            totalWinningMoney += countByRank * rank.getWinningMoney();
        }
        System.out.printf("총 수익률은 %.2f입니다.\n", calculationEarningsRate(inputMoney, totalWinningMoney));
    }

    private static double calculationEarningsRate(InputMoney inputMoney, double totalWinningMoney) {
        return Math.floor((double) totalWinningMoney / inputMoney.getMoney() * 100) / 100.0;
    }
}