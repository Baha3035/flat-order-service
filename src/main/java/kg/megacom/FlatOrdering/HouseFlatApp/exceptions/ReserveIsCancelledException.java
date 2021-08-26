package kg.megacom.FlatOrdering.HouseFlatApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
public class ReserveIsCancelledException extends RuntimeException{
    public ReserveIsCancelledException(String message) {
        super(message);
    }
}
