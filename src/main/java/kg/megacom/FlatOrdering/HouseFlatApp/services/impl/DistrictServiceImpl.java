package kg.megacom.FlatOrdering.HouseFlatApp.services.impl;
import kg.megacom.FlatOrdering.HouseFlatApp.dao.DistrictRepo;
import kg.megacom.FlatOrdering.HouseFlatApp.mappers.DistrictMapper;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.DistrictDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.District;
import kg.megacom.FlatOrdering.HouseFlatApp.services.CityVillageService;
import kg.megacom.FlatOrdering.HouseFlatApp.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepo districtRepo;
    @Autowired
    private CityVillageService cityVillageService;

    @Override
    public DistrictDto findByCityVillageID(Long id) {
        return null;
    }

    @Override
    public DistrictDto save(DistrictDto districtDto) {
        districtDto.setCityVillage(cityVillageService.findById(districtDto.getCityVillage().getId()));
        DistrictMapper districtMapper = new DistrictMapper();
        return districtMapper.toDto(districtRepo.save(districtMapper.toEntity(districtDto)));
    }

    @Override
    public DistrictDto update(DistrictDto districtDto) {
        return null;
    }

    @Override
    public DistrictDto findById(Long id) {
        DistrictMapper districtMapper = new DistrictMapper();
        District district = districtRepo.findById(id).orElseThrow(()->new RuntimeException("Район по айди не найден!"));
        return districtMapper.toDto(district);
    }

    @Override
    public List<DistrictDto> findAll() {
        return null;
    }
}
