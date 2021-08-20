package kg.megacom.FlatOrdering.HouseFlatApp.services;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.RequestDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputRequestData;

public interface RequestService extends BaseCrudService<RequestDto, Long>{
    RequestDto saveWithInput(InputRequestData inputRequestData);

    long countAllByCodeIdAndSuccess(Long code_id, boolean success);

}
