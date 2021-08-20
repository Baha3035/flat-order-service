package kg.megacom.FlatOrdering.HouseFlatApp.models.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestDto {
    private Long id;
    private LocalDate addDate;
    private boolean success;
    private CodeDto code;
}
