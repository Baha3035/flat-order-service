package kg.megacom.FlatOrdering.HouseFlatApp.models.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PayHistoryDto {
    private Long id;
    private ReserveHistoryDto reserveHistory;
    private double cash;
    private LocalDate addDate;
}
