package kg.megacom.FlatOrdering.HouseFlatApp.mappers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.RegionDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.Region;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class RegionMapper implements BaseMapper<RegionDto, Region> {
    private MapperFacade mapperFacade;

    public RegionMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory
                .Builder().build();

        mapperFactory.classMap(RegionDto.class, Region.class)
                .byDefault();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public Region toEntity(RegionDto dto) {
        return mapperFacade.map(dto, Region.class);
    }

    @Override
    public RegionDto toDto(Region entity) {
        return mapperFacade.map(entity, RegionDto.class);
    }

    @Override
    public List<Region> toEntities(List<RegionDto> dtoList) {
        return dtoList.stream().map(x -> mapperFacade.map(x, Region.class)).collect(Collectors.toList());
    }

    @Override
    public List<RegionDto> toDtoList(List<Region> entities) {
        return entities.stream().map(x -> mapperFacade.map(x, RegionDto.class)).collect(Collectors.toList());
    }
}
