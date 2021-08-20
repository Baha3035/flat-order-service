package kg.megacom.FlatOrdering.HouseFlatApp.mappers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.HouseDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.House;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;
import java.util.stream.Collectors;

public class HouseMapper implements BaseMapper<HouseDto, House>{
    private MapperFacade mapperFacade;

    public HouseMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory
                .Builder().build();

        mapperFactory.classMap(HouseDto.class, House.class)
                .byDefault();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public House toEntity(HouseDto dto) {
        return mapperFacade.map(dto, House.class);
    }

    @Override
    public HouseDto toDto(House entity) {
        return mapperFacade.map(entity, HouseDto.class);
    }

    @Override
    public List<House> toEntities(List<HouseDto> dtoList) {
        return dtoList.stream().map(x->mapperFacade.map(x, House.class)).collect(Collectors.toList());
    }

    @Override
    public List<HouseDto> toDtoList(List<House> entities) {
        return entities.stream().map(x->mapperFacade.map(x, HouseDto.class)).collect(Collectors.toList());
    }
}
