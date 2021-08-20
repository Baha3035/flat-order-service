package kg.megacom.FlatOrdering.HouseFlatApp.models.dto;

import kg.megacom.FlatOrdering.HouseFlatApp.enums.CodeStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CodeDto {
    private Long id;
    private Long code;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private CodeStatus codeStatus;
    private UserDto user;
}
