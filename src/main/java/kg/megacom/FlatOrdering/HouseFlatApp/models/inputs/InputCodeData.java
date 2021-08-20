package kg.megacom.FlatOrdering.HouseFlatApp.models.inputs;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.megacom.FlatOrdering.HouseFlatApp.enums.CodeStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InputCodeData {
    private Long code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate endDate;
    private CodeStatus codeStatus;
    private Long userId;
}
