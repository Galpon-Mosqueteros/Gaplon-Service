package galpon.galponservice.iam.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class Email {
    private String value;

    protected Email() {}

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public Email(String value) {
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
