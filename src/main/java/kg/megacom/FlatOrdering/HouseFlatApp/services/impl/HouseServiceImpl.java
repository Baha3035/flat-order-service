package kg.megacom.FlatOrdering.HouseFlatApp.services.impl;

import kg.megacom.FlatOrdering.HouseFlatApp.dao.HouseRepo;
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
    public HouseDto save(HouseDto houseDto) {
        HouseMapper houseMapper = new HouseMapper();
        return houseMapper.toDto(houseRepo.save(houseMapper.toEntity(houseDto)));
    }

    @Override
    public HouseDto update(HouseDto houseDto) {
        return null;
    }

    @Override
    public HouseDto findById(Long id) {
        HouseMapper houseMapper = new HouseMapper();
        return houseMapper.toDto(houseRepo.findById(id).orElseThrow(()->new RuntimeException("House not found!!!")));
    }

    @Override
    public List<HouseDto> findAll() {
        return null;
    }
}
