package kg.megacom.FlatOrdering.HouseFlatApp.models.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String phone;
    private LocalDateTime blockDate;
}
