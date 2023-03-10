package woowacourse.shoppingcart.ui;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import woowacourse.auth.support.AuthenticationPrincipal;
import woowacourse.shoppingcart.application.CustomerService;
import woowacourse.shoppingcart.domain.customer.Customer;
import woowacourse.shoppingcart.dto.customer.CustomerProfileRequest;
import woowacourse.shoppingcart.dto.customer.CustomerRequest;
import woowacourse.shoppingcart.dto.customer.CustomerResponse;
import woowacourse.shoppingcart.dto.customer.EmailUniqueCheckResponse;
import woowacourse.shoppingcart.dto.customer.PasswordCheckResponse;
import woowacourse.shoppingcart.dto.customer.PasswordRequest;

@Controller
@RequestMapping("/api/members")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/email-check")
    public ResponseEntity<EmailUniqueCheckResponse> checkDuplicateEmail(@RequestParam final String email) {
        final EmailUniqueCheckResponse emailDuplicateCheckResponse =
                new EmailUniqueCheckResponse(customerService.isDistinctEmail(email));
        return ResponseEntity.ok().body(emailDuplicateCheckResponse);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> signUp(@RequestBody @Valid final CustomerRequest customerRequest) {
        final Customer customer = customerService.signUp(customerRequest);
        final CustomerResponse customerResponse = new CustomerResponse(customer.getEmail(), customer.getNickname());
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
    }

    @PostMapping("/password-check")
    public ResponseEntity<PasswordCheckResponse> checkPassword(@AuthenticationPrincipal final String email,
                                              @RequestBody @Valid final PasswordRequest passwordRequest) {
        boolean checkPassword = customerService.checkPassword(email, passwordRequest);
        return ResponseEntity.ok().body(new PasswordCheckResponse(String.valueOf(checkPassword)));
    }

    @GetMapping("/me")
    public ResponseEntity<CustomerResponse> findProfile(@AuthenticationPrincipal final String email) {
        final Customer customer = customerService.findByEmail(email);
        final CustomerResponse customerResponse = new CustomerResponse(customer.getEmail(), customer.getNickname());
        return ResponseEntity.ok().body(customerResponse);
    }

    @PatchMapping("/me")
    public ResponseEntity<Void> updateProfile(@AuthenticationPrincipal final String email,
                                              @RequestBody @Valid final CustomerProfileRequest customerProfileRequest) {
        customerService.updateProfile(email, customerProfileRequest);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(@AuthenticationPrincipal final String email,
                                               @RequestBody @Valid final PasswordRequest passwordRequest) {
        customerService.updatePassword(email, passwordRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal final String email) {
        customerService.delete(email);
        return ResponseEntity.noContent().build();
    }
}
