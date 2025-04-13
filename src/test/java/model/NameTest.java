package model;

import model.participant.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;


class NameTest {

    @ParameterizedTest
    @DisplayName("플레이어 이름에는 특수문자가 포함되지_않는다")
    @NullSource
    @ValueSource(strings = {"", " ", "   ", "john_doe", "hello!", "이름@", "abc#123"})
    void validateBanCharacter(String input) {
        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("플레이어 이름 길이를 검증한다")
    @ValueSource(strings = {"", "abcdefghijklmnop", "12345678901"})
    void validateLength(String input) {
        assertThrows(IllegalArgumentException.class, () -> new Name(input));
    }
}