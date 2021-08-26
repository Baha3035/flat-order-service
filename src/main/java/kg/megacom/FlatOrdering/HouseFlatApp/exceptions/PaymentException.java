package kg.megacom.FlatOrdering.HouseFlatApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
public class PaymentException extends RuntimeException{
    public PaymentException(String message) {
        super(message);
    }
}
