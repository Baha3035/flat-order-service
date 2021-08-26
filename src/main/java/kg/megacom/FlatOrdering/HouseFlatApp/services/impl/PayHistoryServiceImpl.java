package kg.megacom.FlatOrdering.HouseFlatApp.services.impl;

import kg.megacom.FlatOrdering.HouseFlatApp.dao.PayHistoryRepo;
import kg.megacom.FlatOrdering.HouseFlatApp.exceptions.NotFoundByIdException;
import kg.megacom.FlatOrdering.HouseFlatApp.mappers.PayHistoryMapper;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.PayHistoryDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.PayHistory;
import kg.megacom.FlatOrdering.HouseFlatApp.services.PayHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayHistoryServiceImpl implements PayHistoryService {
    @Autowired
    private PayHistoryRepo payHistoryRepo;


    @Override
    public PayHistoryDto save(PayHistoryDto payHistoryDto) {
        PayHistoryMapper payHistoryMapper = new PayHistoryMapper();
        return payHistoryMapper.toDto(payHistoryRepo.save(payHistoryMapper.toEntity(payHistoryDto)));
    }

    @Override
    public PayHistoryDto update(PayHistoryDto payHistoryDto) {
        return null;
    }

    @Override
    public PayHistoryDto findById(Long id) {
        PayHistoryMapper payHistoryMapper = new PayHistoryMapper();
        PayHistory payHistory = payHistoryRepo.findById(id).orElseThrow(()-> new NotFoundByIdException("PayHistoryNotFound!!!"));
        return payHistoryMapper.toDto(payHistory);
    }

    @Override
    public List<PayHistoryDto> findAll() {
        return null;
    }

    @Override
    public List<PayHistoryDto> findByReserveHistoryId(Long reserve_id) {
        PayHistoryMapper payHistoryMapper = new PayHistoryMapper();
        return payHistoryMapper.toDtoList(payHistoryRepo.findByReserveHistoryId(reserve_id));
    }
}
