package kg.megacom.FlatOrdering.HouseFlatApp.mappers;


import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.CodeDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.Code;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CodeMapper implements BaseMapper <CodeDto, Code>{
    private MapperFacade mapperFacade;

    public CodeMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory
                .Builder().build();

        mapperFactory.classMap(CodeDto.class, Code.class)
                .byDefault();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public Code toEntity(CodeDto dto) {
        return mapperFacade.map(dto, Code.class);
    }

    @Override
    public CodeDto toDto(Code entity) {
        return mapperFacade.map(entity, CodeDto.class);
    }

    @Override
    public List<Code> toEntities(List<CodeDto> dtoList) {
        return dtoList.stream().map(x -> mapperFacade.map(x, Code.class)).collect(Collectors.toList());
    }

    @Override
    public List<CodeDto> toDtoList(List<Code> entities) {
        return entities.stream().map(x-> mapperFacade.map(x, CodeDto.class)).collect(Collectors.toList());
    }
}
