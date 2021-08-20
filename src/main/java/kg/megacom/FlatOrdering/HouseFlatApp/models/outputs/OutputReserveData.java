package kg.megacom.FlatOrdering.HouseFlatApp.models.outputs;


import kg.megacom.FlatOrdering.HouseFlatApp.enums.ReserveStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OutputReserveData {
    private Long houseId;
    private Long customerId;
    private ReserveStatus reserveStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;
    private double cash;
}
