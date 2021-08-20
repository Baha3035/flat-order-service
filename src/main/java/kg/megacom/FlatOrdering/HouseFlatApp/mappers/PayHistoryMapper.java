package kg.megacom.FlatOrdering.HouseFlatApp.mappers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.PayHistoryDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.PayHistory;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PayHistoryMapper  implements BaseMapper<PayHistoryDto, PayHistory>{
    private MapperFacade mapperFacade;

    public PayHistoryMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory
                .Builder().build();

        mapperFactory.classMap(PayHistoryDto.class, PayHistory.class)
                .byDefault();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public PayHistory toEntity(PayHistoryDto dto) {
        return mapperFacade.map(dto, PayHistory.class);
    }

    @Override
    public PayHistoryDto toDto(PayHistory entity) {
        return mapperFacade.map(entity, PayHistoryDto.class);
    }

    @Override
    public List<PayHistory> toEntities(List<PayHistoryDto> dtoList) {
        return dtoList.stream().map(x->mapperFacade.map(x, PayHistory.class)).collect(Collectors.toList());
    }

    @Override
    public List<PayHistoryDto> toDtoList(List<PayHistory> entities) {
        return entities.stream().map(x->mapperFacade.map(x, PayHistoryDto.class)).collect(Collectors.toList());
    }
}
