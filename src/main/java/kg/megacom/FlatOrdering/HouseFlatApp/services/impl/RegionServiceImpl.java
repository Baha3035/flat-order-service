package kg.megacom.FlatOrdering.HouseFlatApp.services.impl;


import kg.megacom.FlatOrdering.HouseFlatApp.dao.RegionRepo;
import kg.megacom.FlatOrdering.HouseFlatApp.exceptions.NotFoundByIdException;
import kg.megacom.FlatOrdering.HouseFlatApp.mappers.RegionMapper;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.RegionDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.Region;
import kg.megacom.FlatOrdering.HouseFlatApp.services.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionRepo regionRepo;
//    private RegionMapper regionMapper = new RegionMapper();

    @Override
    public RegionDto save(RegionDto regionDto) {
        RegionMapper regionMapper = new RegionMapper();
        return regionMapper.toDto(regionRepo.save(regionMapper.toEntity(regionDto)));
    }

    @Override
    public RegionDto update(RegionDto regionDto) {
        return null;
    }

    @Override
    public RegionDto findById(Long id) {
        RegionMapper regionMapper = new RegionMapper();
        Region region = regionRepo.findById(id).orElseThrow(()->new NotFoundByIdException("Region is not found!!!"));
        return regionMapper.toDto(region);
    }

    @Override
    public List<RegionDto> findAll() {
        return null;
    }
}
