package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoBall {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private static final Map<Integer, LottoBall> LOTTO_BALLS = new HashMap();
    static {
        for (int num = MIN_LOTTO_NUMBER; num <= MAX_LOTTO_NUMBER; num++) {
            LOTTO_BALLS.put(num, new LottoBall(num));
        }
    }

    private final int number;

    public static LottoBall from(int number) {
        validateLottoNumber(number);
        return LOTTO_BALLS.get(number);
    }

    private LottoBall(int number) {
        validateLottoNumber(number);
        this.number = number;
    }

    private static void validateLottoNumber(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(
                    "로또 숫자는 " +
                    MIN_LOTTO_NUMBER + "부터 " +
                    MAX_LOTTO_NUMBER + "까지의 숫자여야만 합니다.");
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoBall lottoBall = (LottoBall) o;
        return number == lottoBall.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}