package kg.megacom.FlatOrdering.HouseFlatApp.mappers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.RequestDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.Request;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;
import java.util.stream.Collectors;

public class RequestMapper implements BaseMapper<RequestDto, Request> {
    private MapperFacade mapperFacade;

    public RequestMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory
                .Builder().build();

        mapperFactory.classMap(RequestDto.class, Request.class)
                .byDefault();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public Request toEntity(RequestDto dto) {
        return mapperFacade.map(dto, Request.class);
    }

    @Override
    public RequestDto toDto(Request entity) {
        return mapperFacade.map(entity, RequestDto.class);
    }

    @Override
    public List<Request> toEntities(List<RequestDto> dtoList) {
        return dtoList.stream().map(x->mapperFacade.map(x, Request.class)).collect(Collectors.toList());
    }

    @Override
    public List<RequestDto> toDtoList(List<Request> entities) {
        return entities.stream().map(x->mapperFacade.map(x, RequestDto.class)).collect(Collectors.toList());
    }
}
