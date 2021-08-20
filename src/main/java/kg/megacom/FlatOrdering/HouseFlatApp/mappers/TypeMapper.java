package kg.megacom.FlatOrdering.HouseFlatApp.mappers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.TypeDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.Type;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;
import java.util.stream.Collectors;

public class TypeMapper implements BaseMapper<TypeDto, Type>{
    private MapperFacade mapperFacade;

    public TypeMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory
                .Builder().build();

        mapperFactory.classMap(TypeDto.class, Type.class)
                .byDefault();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public Type toEntity(TypeDto dto) {
        return mapperFacade.map(dto, Type.class);
    }

    @Override
    public TypeDto toDto(Type entity) {
        return mapperFacade.map(entity, TypeDto.class);
    }

    @Override
    public List<Type> toEntities(List<TypeDto> dtoList) {
        return dtoList.stream().map(x->mapperFacade.map(x, Type.class)).collect(Collectors.toList());
    }

    @Override
    public List<TypeDto> toDtoList(List<Type> entities) {
        return entities.stream().map(x->mapperFacade.map(x, TypeDto.class)).collect(Collectors.toList());
    }
}
