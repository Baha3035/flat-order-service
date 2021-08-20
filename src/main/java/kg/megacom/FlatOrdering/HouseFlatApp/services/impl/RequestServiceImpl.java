package kg.megacom.FlatOrdering.HouseFlatApp.services.impl;
import kg.megacom.FlatOrdering.HouseFlatApp.dao.RequestRepo;
import kg.megacom.FlatOrdering.HouseFlatApp.mappers.RequestMapper;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.RequestDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputRequestData;
import kg.megacom.FlatOrdering.HouseFlatApp.services.CodeService;
import kg.megacom.FlatOrdering.HouseFlatApp.services.RequestService;
import kg.megacom.FlatOrdering.HouseFlatApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepo requestRepo;
    @Autowired
    CodeService codeService;
    @Autowired
    UserService userService;
    @Override
    public RequestDto saveWithInput(InputRequestData inputRequestData) {
        RequestDto requestDto = new RequestDto();
        RequestMapper requestMapper = new RequestMapper();
        requestDto.setSuccess(true);
        requestDto.setCode(codeService.findCodeById(inputRequestData.getCodeId()));
        return requestMapper.toDto(requestRepo.save(requestMapper.toEntity(requestDto)));
    }

    @Override
    public long countAllByCodeIdAndSuccess(Long code_id, boolean success) {
        return requestRepo.countAllByCodeIdAndSuccess(code_id, success);
    }


    @Override
    public RequestDto save(RequestDto requestDto) {

        RequestMapper requestMapper = new RequestMapper();
        return requestMapper.toDto(requestRepo.save(requestMapper.toEntity(requestDto)));
    }

    @Override
    public RequestDto update(RequestDto requestDto) {
        return null;
    }

    @Override
    public RequestDto findById(Long id) {
        return null;
    }

    @Override
    public List<RequestDto> findAll() {
        return null;
    }

}
