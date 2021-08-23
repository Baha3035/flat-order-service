package kg.megacom.FlatOrdering.HouseFlatApp.services.impl;

import kg.megacom.FlatOrdering.HouseFlatApp.dao.CodeRepo;
import kg.megacom.FlatOrdering.HouseFlatApp.enums.CodeStatus;
import kg.megacom.FlatOrdering.HouseFlatApp.mappers.CodeMapper;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.CodeDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.RequestDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.UserDto;
import kg.megacom.FlatOrdering.HouseFlatApp.services.CodeService;
import kg.megacom.FlatOrdering.HouseFlatApp.services.RequestService;
import kg.megacom.FlatOrdering.HouseFlatApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    private CodeRepo codeRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private RequestService requestService;

    @Override
    public boolean sendCode(UserDto userDtoSaved) {
        CodeDto codeDto = new CodeDto();
        codeDto.setCodeStatus(CodeStatus.NEW);
        Date todayDateToConvert = new Date();
        LocalDateTime todaysDate = todayDateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        codeDto.setStartDate(todaysDate);
        codeDto.setEndDate(codeDto.getStartDate().plusMinutes(3));
        codeDto.setCode(generateCode());
        codeDto.setUser(userDtoSaved);
        codeDto = save(codeDto);
        return requestService.sendRequest(codeDto, true);
    }

    @Override
    public boolean putCode(long code, Long userId) {
        CodeDto codeDto = findByUserIdAndCodeStatusNot(userId, CodeStatus.CANCELED);
        if(codeDto.getCodeStatus().equals(CodeStatus.APPROVED)){
            throw new RuntimeException("Your code is already approved");
        }
        long trueCode = codeDto.getCode();
//        RequestDto requestDto = new RequestDto();
        Date todayDateToConvert = new Date();
        LocalDateTime todaysDate = todayDateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        long possibleSeconds = todaysDate.until(codeDto.getEndDate(), ChronoUnit.SECONDS);
        System.out.println(possibleSeconds);
        if(possibleSeconds < 0) {
            boolean status;
            if (code == trueCode) {
                codeDto.setCodeStatus(CodeStatus.APPROVED);
                status = true;
            } else {
                codeDto.setCodeStatus(CodeStatus.FAILED);
                status = false;
            }
            codeDto = save(codeDto);
//            requestDto.setCode(codeDto);
//            requestDto = requestService.save(requestDto);
            boolean isSuccessful = requestService.sendRequest(codeDto, status);
            if(isSuccessful){
                return true;
            }
            long countOfNotSuccessfulRequests = requestService.countAllByCodeIdAndSuccess(codeDto.getId(), false);
            if(countOfNotSuccessfulRequests > 2){
                codeDto.setCodeStatus(CodeStatus.CANCELED);
                codeDto = save(codeDto);
                return false;
            }
        }else {
            codeDto.setCodeStatus(CodeStatus.CANCELED);
            UserDto userDto = userService.findById(userId);
            userDto.setBlockDate(todaysDate.plusHours(1));
            userService.update(userDto);
            save(codeDto);
        }
        return false;
    }

    public CodeDto findByUserIdAndCodeStatusNot(Long id, CodeStatus codeStatus) {
        CodeMapper codeMapper = new CodeMapper();
        CodeDto codeDto = codeMapper.toDto(codeRepo.findByUserIdAndCodeStatusNot(id, codeStatus));
        if(codeDto == null){
            throw new RuntimeException("Your code is cancelled!!!");
        }
        return codeMapper.toDto(codeRepo.findByUserIdAndCodeStatusNot(id, codeStatus));
    }

    private long generateCode(){
        return (long) ((Math.random() * (9999 - 1000)) + 1000);
    }


    @Override
    public CodeDto save(CodeDto codeDto) {
        CodeMapper codeMapper = new CodeMapper();
        return codeMapper.toDto(codeRepo.save(codeMapper.toEntity(codeDto)));
    }

    @Override
    public CodeDto update(CodeDto codeDto) {
        CodeMapper codeMapper = new CodeMapper();
        return codeMapper.toDto(codeRepo.save(codeMapper.toEntity(codeDto)));
    }

    @Override
    public CodeDto findById(Long id) {
        return null;
    }

    @Override
    public List<CodeDto> findAll() {
        return null;
    }

    @Override
    public boolean updateCode(Long user_id){
        CodeDto codeDtoPrevious = findByUserIdAndCodeStatusNot(user_id, CodeStatus.CANCELED);
        codeDtoPrevious.setCodeStatus(CodeStatus.CANCELED);
        update(codeDtoPrevious);
        UserDto userDto = userService.findById(user_id);
        return sendCode(userDto);
    }
}
