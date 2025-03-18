package galpon.galponservice.iam.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
public class Password {
    private String value;

    protected Password() {}

    public Password(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
