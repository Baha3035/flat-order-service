package kg.megacom.FlatOrdering.HouseFlatApp.services;


import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.PayHistoryDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.PayHistory;

import java.util.List;

public interface PayHistoryService extends BaseCrudService<PayHistoryDto, Long>{
    List<PayHistoryDto> findByReserveHistoryId(Long reserve_id);
}
