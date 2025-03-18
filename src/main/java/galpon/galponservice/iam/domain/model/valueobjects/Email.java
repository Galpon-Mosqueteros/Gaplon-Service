package galpon.galponservice.iam.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
public class Email {
    private String value;

    protected Email() {}

    public Email(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
