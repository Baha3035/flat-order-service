package kg.megacom.FlatOrdering.HouseFlatApp.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.megacom.FlatOrdering.HouseFlatApp.enums.ReserveStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReserveHistoryDto {
    private Long id;
    private HouseDto house;
    private UserDto user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate endDate;
    private ReserveStatus reserveStatus;
    private double totalPrice;
    private LocalDate addDate;
    private LocalDate editDate;
}
