package kg.megacom.FlatOrdering.HouseFlatApp.services;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.ReserveHistoryDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputReserveHistoryData;
import kg.megacom.FlatOrdering.HouseFlatApp.models.outputs.OutputReserveData;

public interface ReserveHistoryService extends BaseCrudService<ReserveHistoryDto, Long>{
    ReserveHistoryDto saveWithInput(InputReserveHistoryData inputReserveHistoryData);
    OutputReserveData pay(Long user_id, double cash);

}
