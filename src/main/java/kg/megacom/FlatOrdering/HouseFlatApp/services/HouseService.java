package kg.megacom.FlatOrdering.HouseFlatApp.services;


import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.HouseDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputHouseData;

public interface HouseService extends BaseCrudService<HouseDto, Long>{

    HouseDto saveWithInput(InputHouseData inputHouseData);
}
