package model.participant;

public class Name {
    private static final String INVALID_NAME_CHARACTERS_REGEX = ".*[^a-zA-Z0-9가-힣].*";
    private static final int MAX_LENGTH = 10;
    private static final int MIN_LENGTH = 1;

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateNotNullOrBlank(name);
        validateContainsOnlyAllowedCharacters(name);
        validateLength(name);
    }

    private void validateNotNullOrBlank(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("이름은 null이거나 빈 값일 수 없습니다.");
        }
    }

    private void validateContainsOnlyAllowedCharacters(String name) {
        if (name.matches(INVALID_NAME_CHARACTERS_REGEX)) {
            throw new IllegalArgumentException("이름에는 특수문자를 포함할 수 없습니다.");
        }
    }

    private void validateLength(String name) {
        if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("이름은 1자 이상 10자 이하여야 합니다.");
        }
    }

    public boolean isSame(String playerName) {
        return playerName.equals(this.name);

    }

    public String getName() {
        return name;
    }
}
