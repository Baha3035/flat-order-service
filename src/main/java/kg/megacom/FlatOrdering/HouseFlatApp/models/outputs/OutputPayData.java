package kg.megacom.FlatOrdering.HouseFlatApp.models.outputs;

import kg.megacom.FlatOrdering.HouseFlatApp.enums.ReserveStatus;
import lombok.Data;

@Data
public class OutputPayData {
    private double paidCash;
    private ReserveStatus reserveStatus;
}
