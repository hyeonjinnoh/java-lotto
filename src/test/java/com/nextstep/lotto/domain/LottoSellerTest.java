package com.nextstep.lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoSellerTest {
    @DisplayName("로또 개수 계산")
    @Test
    void count() {
        int count = LottoSeller.count(10000);
        assertThat(count).isEqualTo(10);
    }

    @DisplayName("로또 구입")
    @Test
    void buy() {
        List<Lotto> lottos = LottoSeller.buyAutoLottos(10);
        assertThat(lottos.size()).isEqualTo(10);
    }

    private static List<LottoNumber> toLottoNumbers(int ... numbers) {
        return IntStream.of(numbers)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
    }
}
