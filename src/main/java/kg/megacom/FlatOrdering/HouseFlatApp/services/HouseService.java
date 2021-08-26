package kg.megacom.FlatOrdering.HouseFlatApp.services;


import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.HouseDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputHouseData;

import java.util.List;

public interface HouseService extends BaseCrudService<HouseDto, Long>{

    HouseDto saveWithInput(InputHouseData inputHouseData);

    List<HouseDto> findByDistrictId(Long id);
    List<HouseDto> findByCityId(Long id);
    List<HouseDto> findByTypeId(Long id);
    List<HouseDto> findByFloor(int floor);
    List<HouseDto> findByRoom(int room);
    List<HouseDto> findByInternet(boolean internet);
    List<HouseDto> findByFurniture(boolean furniture);
}
