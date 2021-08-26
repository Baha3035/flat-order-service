package kg.megacom.FlatOrdering.HouseFlatApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class CodeIsInConflictException extends RuntimeException{
    public CodeIsInConflictException(String message) {
        super(message);
    }
}
