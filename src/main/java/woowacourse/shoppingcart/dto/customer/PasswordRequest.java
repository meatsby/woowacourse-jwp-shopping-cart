package woowacourse.shoppingcart.dto.customer;

import javax.validation.constraints.NotBlank;
import woowacourse.shoppingcart.domain.customer.Password;

public class PasswordRequest {

    @NotBlank
    private String password;

    private PasswordRequest() {
    }

    public PasswordRequest(String password) {
        this.password = password;
    }

    public Password toPassword() {
        return new Password(password);
    }

    public String getPassword() {
        return password;
    }
}
