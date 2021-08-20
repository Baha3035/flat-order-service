package kg.megacom.FlatOrdering.HouseFlatApp.mappers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.ReserveHistoryDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.ReserveHistory;
import kg.megacom.FlatOrdering.HouseFlatApp.models.outputs.OutputReserveData;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;
import java.util.stream.Collectors;


public class ReserveHistoryMapper implements BaseMapper<ReserveHistoryDto, ReserveHistory> {
    private MapperFacade mapperFacade;

    public ReserveHistoryMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory
                .Builder().build();

        mapperFactory.classMap(ReserveHistoryDto.class, ReserveHistory.class)
                .byDefault();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public ReserveHistory toEntity(ReserveHistoryDto dto) {
        return mapperFacade.map(dto, ReserveHistory.class);
    }

    @Override
    public ReserveHistoryDto toDto(ReserveHistory entity) {
        return mapperFacade.map(entity, ReserveHistoryDto.class);
    }

    @Override
    public List<ReserveHistory> toEntities(List<ReserveHistoryDto> dtoList) {
        return dtoList.stream().map(x->mapperFacade.map(x, ReserveHistory.class)).collect(Collectors.toList());
    }

    @Override
    public List<ReserveHistoryDto> toDtoList(List<ReserveHistory> entities) {
        return entities.stream().map(x->mapperFacade.map(x, ReserveHistoryDto.class)).collect(Collectors.toList());
    }

    public OutputReserveData toOutputReserveHistory(ReserveHistoryDto reserveHistoryDto, double cash){
        OutputReserveData outputReserveData = new OutputReserveData();
        outputReserveData.setReserveStatus(reserveHistoryDto.getReserveStatus());
        outputReserveData.setCustomerId(reserveHistoryDto.getUser().getId());
        outputReserveData.setStartDate(reserveHistoryDto.getStartDate());
        outputReserveData.setEndDate(reserveHistoryDto.getEndDate());
        outputReserveData.setTotalPrice(reserveHistoryDto.getTotalPrice());
        outputReserveData.setCash(cash);
        outputReserveData.setHouseId(reserveHistoryDto.getHouse().getId());
        return outputReserveData;
    }
}
