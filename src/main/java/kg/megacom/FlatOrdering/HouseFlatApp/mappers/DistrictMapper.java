package kg.megacom.FlatOrdering.HouseFlatApp.mappers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.DistrictDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.District;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DistrictMapper implements BaseMapper<DistrictDto, District> {
    private MapperFacade mapperFacade;

    public DistrictMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory
                .Builder().build();

        mapperFactory.classMap(DistrictDto.class, District.class)
                .byDefault();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public District toEntity(DistrictDto dto) {
        return mapperFacade.map(dto, District.class);
    }

    @Override
    public DistrictDto toDto(District entity) {
        return mapperFacade.map(entity, DistrictDto.class);
    }

    @Override
    public List<District> toEntities(List<DistrictDto> dtoList) {
        return dtoList.stream().map(x->mapperFacade.map(x, District.class)).collect(Collectors.toList());
    }

    @Override
    public List<DistrictDto> toDtoList(List<District> entities) {
        return entities.stream().map(x->mapperFacade.map(x, DistrictDto.class)).collect(Collectors.toList());
    }
}
