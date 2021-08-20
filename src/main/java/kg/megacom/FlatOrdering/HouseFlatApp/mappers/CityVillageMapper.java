package kg.megacom.FlatOrdering.HouseFlatApp.mappers;


import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.CityVillageDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.CityVillage;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CityVillageMapper implements BaseMapper<CityVillageDto, CityVillage>{
    private MapperFacade mapperFacade;

    public CityVillageMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory
                .Builder().build();

        mapperFactory.classMap(CityVillageDto.class, CityVillage.class)
                .byDefault();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public CityVillage toEntity(CityVillageDto dto) {
        return mapperFacade.map(dto, CityVillage.class);
    }

    @Override
    public CityVillageDto toDto(CityVillage entity) {
        return mapperFacade.map(entity, CityVillageDto.class);
    }

    @Override
    public List<CityVillage> toEntities(List<CityVillageDto> dtoList) {
        return dtoList.stream().map(x -> mapperFacade.map(x, CityVillage.class)).collect(Collectors.toList());
    }

    @Override
    public List<CityVillageDto> toDtoList(List<CityVillage> entities) {
        return entities.stream().map(x -> mapperFacade.map(x, CityVillageDto.class)).collect(Collectors.toList());
    }

}
