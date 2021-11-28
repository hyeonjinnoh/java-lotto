package lotto.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTicketTest {

  @ParameterizedTest
  @MethodSource("makeLottoNumberList")
  @DisplayName("6개의 로또 번호를 이용해 Lotto ticket을 생성한다.")
  void create(List<LottoNumber> lottoNumbers, int size) {
    LottoTicket ticket = new LottoTicket(lottoNumbers);
    assertEquals(ticket.size(), size);
    assertArrayEquals(ticket.getNumbers().toArray(), lottoNumbers.toArray());
  }

  @ParameterizedTest
  @MethodSource("makeMoreThanBigSizeLottoNumberList")
  @DisplayName("6개 이상의 로또 번호를 이용해 Lotto ticket을 생성한다.")
  void createWrongSize(List<LottoNumber> lottoNumbers) {
    assertThrows(IllegalArgumentException.class, () -> new LottoTicket(lottoNumbers));
  }

  private static Stream<Arguments> makeLottoNumberList() {
    return Stream.of(
            Arguments.of(Arrays.asList(new LottoNumber(1), new LottoNumber(15), new LottoNumber(40),
            new LottoNumber(25), new LottoNumber(30), new LottoNumber(4)), 6),
            Arguments.of(Arrays.asList(new LottoNumber(4), new LottoNumber(10), new LottoNumber(26),
            new LottoNumber(35), new LottoNumber(45), new LottoNumber(12)), 6));
  }

  private static Stream<Arguments> makeMoreThanBigSizeLottoNumberList() {
    return Stream.of(
            Arguments.of(Arrays.asList(new LottoNumber(1), new LottoNumber(15), new LottoNumber(40),
                    new LottoNumber(25), new LottoNumber(15), new LottoNumber(4), new LottoNumber(20))),
            Arguments.of(Arrays.asList(new LottoNumber(4), new LottoNumber(10), new LottoNumber(26),
                    new LottoNumber(35), new LottoNumber(45), new LottoNumber(12), new LottoNumber(1),
                    new LottoNumber(7), new LottoNumber(19))));
  }

  @ParameterizedTest
  @MethodSource("makeLottoNumbers")
  @DisplayName("로또 티켓이 당첨 번호를 몇개 가지고 있는지 반환한다.")
  void getContainsNumberTest(LottoTicket lottoTicket, LottoTicket winning, int count) {
    assertEquals(lottoTicket.getMatchedCount(winning), count);
  }

  private static Stream<Arguments> makeLottoNumbers() {
    return Stream.of(
            Arguments.of(new LottoTicket(Arrays.asList(new LottoNumber(1), new LottoNumber(15), new LottoNumber(40),
                            new LottoNumber(25), new LottoNumber(30), new LottoNumber(4))),
                    new LottoTicket(Arrays.asList(new LottoNumber(15), new LottoNumber(40), new LottoNumber(30),
                            new LottoNumber(5), new LottoNumber(10), new LottoNumber(38))), 3),
            Arguments.of(new LottoTicket(Arrays.asList(new LottoNumber(4), new LottoNumber(10), new LottoNumber(26),
                    new LottoNumber(35), new LottoNumber(17), new LottoNumber(12))),
                    new LottoTicket(Arrays.asList(new LottoNumber(15), new LottoNumber(40), new LottoNumber(30),
                            new LottoNumber(5), new LottoNumber(10), new LottoNumber(29))), 1));
  }

  @ParameterizedTest
  @MethodSource("makeDuplicatedLottoNumberList")
  @DisplayName("6개의 로또번호를 입력했을 때 중복된 번호가 있다면 exception을 던진다.")
  void createDuplicatedNumbers(List<LottoNumber> lottoNumbers) {
    assertThrows(IllegalArgumentException.class, () -> new LottoTicket(lottoNumbers));
  }

  private static Stream<Arguments> makeDuplicatedLottoNumberList() {
    return Stream.of(
            Arguments.of(Arrays.asList(new LottoNumber(1), new LottoNumber(15), new LottoNumber(40),
                    new LottoNumber(25), new LottoNumber(15), new LottoNumber(4), new LottoNumber(20))),
            Arguments.of(Arrays.asList(new LottoNumber(4), new LottoNumber(10), new LottoNumber(26),
                    new LottoNumber(35), new LottoNumber(45), new LottoNumber(12), new LottoNumber(1),
                    new LottoNumber(7), new LottoNumber(19))));
  }
}
