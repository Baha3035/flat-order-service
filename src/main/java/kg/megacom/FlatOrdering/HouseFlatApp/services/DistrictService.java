package kg.megacom.FlatOrdering.HouseFlatApp.services;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.DistrictDto;

public interface DistrictService extends BaseCrudService<DistrictDto, Long>{
    DistrictDto findByCityVillageID(Long id);
}
