package kg.megacom.FlatOrdering.HouseFlatApp.services.impl;

import kg.megacom.FlatOrdering.HouseFlatApp.dao.HouseRepo;
import kg.megacom.FlatOrdering.HouseFlatApp.exceptions.NotFoundByIdException;
import kg.megacom.FlatOrdering.HouseFlatApp.mappers.HouseMapper;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.HouseDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputHouseData;
import kg.megacom.FlatOrdering.HouseFlatApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseRepo houseRepo;
    @Autowired
    private TypeService typeService;
    @Autowired
    private UserService userService;
    @Autowired
    private CityVillageService cityVillageService;
    @Autowired
    private DistrictService districtService;

    @Override
    public HouseDto saveWithInput(InputHouseData inputHouseData) {
        HouseDto houseDto = new HouseDto();
        houseDto.setAddress(inputHouseData.getAddress());
        houseDto.setDescriptions(inputHouseData.getDescription());
        houseDto.setArea(inputHouseData.getArea());
        houseDto.setCityVillage(cityVillageService.findById(inputHouseData.getCityVillageId()));
        houseDto.setType(typeService.findById(inputHouseData.getTypeId()));
        houseDto.setUser(userService.findById(inputHouseData.getOwnerUserId()));
        houseDto.setDistrict(districtService.findById(inputHouseData.getDistrictId()));
        houseDto.setFloor(inputHouseData.getFloor());
        houseDto.setFurniture(inputHouseData.isFurniture());
        houseDto.setInternet(inputHouseData.isInternet());
        houseDto.setLat(inputHouseData.getLat());
        houseDto.setLon(inputHouseData.getLon());
        houseDto.setPrice(inputHouseData.getPrice());
        houseDto.setRooms(inputHouseData.getRoom());
        return save(houseDto);
    }

    @Override
    public List<HouseDto> findByDistrictId(Long id) {
        return map().toDtoList(houseRepo.findByDistrictId(id));
    }

    @Override
    public List<HouseDto> findByCityId(Long id) {
        return map().toDtoList(houseRepo.findByCityVillageId(id));
    }

    @Override
    public List<HouseDto> findByTypeId(Long id) {
        return map().toDtoList(houseRepo.findByTypeId(id));
    }

    @Override
    public List<HouseDto> findByFloor(int floor) {
        return map().toDtoList(houseRepo.findByFloor(floor));
    }

    @Override
    public List<HouseDto> findByRoom(int room) {
        return map().toDtoList(houseRepo.findByRooms(room));
    }

    @Override
    public List<HouseDto> findByInternet(boolean internet) {
        return map().toDtoList(houseRepo.findByInternet(internet));
    }

    @Override
    public List<HouseDto> findByFurniture(boolean furniture) {
        return map().toDtoList(houseRepo.findByFurniture(furniture));
    }

    @Override
    public HouseDto save(HouseDto houseDto) {
        return map().toDto(houseRepo.save(map().toEntity(houseDto)));
    }

    @Override
    public HouseDto update(HouseDto houseDto) {
        return null;
    }

    @Override
    public HouseDto findById(Long id) {
        return map().toDto(houseRepo.findById(id).orElseThrow(()->new NotFoundByIdException("House not found!!!")));
    }

    @Override
    public List<HouseDto> findAll() {
        return null;
    }

    public HouseMapper map() {
        HouseMapper houseMapper = new HouseMapper();
        return houseMapper;
    }
}
