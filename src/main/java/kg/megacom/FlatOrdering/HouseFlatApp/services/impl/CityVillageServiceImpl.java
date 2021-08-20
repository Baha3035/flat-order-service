package kg.megacom.FlatOrdering.HouseFlatApp.services.impl;

import kg.megacom.FlatOrdering.HouseFlatApp.dao.CityVillageRepo;
import kg.megacom.FlatOrdering.HouseFlatApp.mappers.CityVillageMapper;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.CityVillageDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.CityVillage;
import kg.megacom.FlatOrdering.HouseFlatApp.services.CityVillageService;
import kg.megacom.FlatOrdering.HouseFlatApp.services.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityVillageServiceImpl implements CityVillageService {
    private CityVillageRepo cityVillageRepo;
    private RegionService regionService;


    @Override
    public CityVillageDto save(CityVillageDto cityVillageDto) {
        cityVillageDto.setRegion(regionService.findById(cityVillageDto.getRegion().getId()));
        CityVillageMapper cityVillageMapper = new CityVillageMapper();
        return cityVillageMapper.toDto(cityVillageRepo.save(cityVillageMapper.toEntity(cityVillageDto)));
    }

    @Override
    public CityVillageDto update(CityVillageDto cityVillageDto) {
        return null;
    }

    @Override
    public CityVillageDto findById(Long id) {
        CityVillageMapper cityVillageMapper = new CityVillageMapper();
        CityVillage cityVillage = cityVillageRepo.findById(id).orElseThrow(()-> new RuntimeException("Айди города не найден!"));
        return cityVillageMapper.toDto(cityVillage);
    }

    @Override
    public List<CityVillageDto> findAll() {
        return null;
    }
}
