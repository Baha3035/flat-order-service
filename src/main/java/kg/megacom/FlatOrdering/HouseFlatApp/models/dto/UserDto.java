package kg.megacom.FlatOrdering.HouseFlatApp.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String phone;
    private LocalDateTime blockDate;
}
