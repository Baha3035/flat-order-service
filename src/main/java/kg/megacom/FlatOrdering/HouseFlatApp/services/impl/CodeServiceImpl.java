package kg.megacom.FlatOrdering.HouseFlatApp.services.impl;

import kg.megacom.FlatOrdering.HouseFlatApp.dao.CodeRepo;
import kg.megacom.FlatOrdering.HouseFlatApp.enums.CodeStatus;
import kg.megacom.FlatOrdering.HouseFlatApp.mappers.CodeMapper;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.CodeDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.RequestDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.UserDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.Code;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputCodeData;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputUserData;
import kg.megacom.FlatOrdering.HouseFlatApp.services.CodeService;
import kg.megacom.FlatOrdering.HouseFlatApp.services.RequestService;
import kg.megacom.FlatOrdering.HouseFlatApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

//    @Override
//    public CodeDto saveCode(InputCodeData inputCodeData) {
//        CodeDto codeDto = new CodeDto();
//        codeDto.setCode((long) ((Math.random() * (9999 - 1000)) + 1000));
//        codeDto.setCodeStatus(CodeStatus.NEW);
//        codeDto.setStartDate(inputCodeData.getStartDate());
//        codeDto.setEndDate(inputCodeData.getStartDate());
//        codeDto.setUser(userService.findById(inputCodeData.getUserId()));
////        return codeMapper.toDto(codeRepo.save(codeMapper.toEntity(codeDto)));
//        return null;
//    }

    @Override
    public CodeDto findCodeById(Long id) {
        Code code = codeRepo.findById(id).orElseThrow(()-> new RuntimeException("Код по айди не найден!"));
//        return codeMapper.toDto(code);
        return null;
    }

    @Override
    public InputUserData chekCode(long code_id, long user_code) {
    if (user_code <=0){
        throw new RuntimeException("Введи номально!");
    }
        CodeDto codeDto = findCodeById(code_id);
    if (codeDto.getCode().equals(user_code)){
        codeDto.setCodeStatus(CodeStatus.APPROVED);
    }
    if (!codeDto.getCode().equals(user_code)){
        codeDto.setCodeStatus(CodeStatus.FAILED);
    }else codeDto.setCodeStatus(CodeStatus.CANCELED);

    codeDto = saveForCode(codeDto);
    InputUserData inputUserData = new InputUserData();
    inputUserData.setCode_id(code_id);
    inputUserData.setUserCode(user_code);

        return inputUserData;
    }

    @Override
    public CodeDto saveForCode(CodeDto codeDto) {
//        return codeMapper.toDto(codeRepo.save(codeMapper.toEntity(codeDto)));
        return null;
    }

    @Override
    public void sendCode(UserDto userDtoSaved) {
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
        save(codeDto);
    }

    @Override
    public boolean putCode(long code, Long userId) {
        CodeDto codeDto = findByUserId(userId);
        if(codeDto.getCodeStatus().equals(CodeStatus.CANCELED)){
            throw new RuntimeException("Your code is cancelled!!!");
        }
        long trueCode = codeDto.getCode();
        RequestDto requestDto = new RequestDto();
//        long countOfNotSuccessfulRequests = requestService.countAllByCodeIdAndSuccess(codeDto.getId(), false);
        Date todayDateToConvert = new Date();
        LocalDateTime todaysDate = todayDateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        long possibleSeconds = codeDto.getEndDate().until(todaysDate, ChronoUnit.SECONDS);
        if(possibleSeconds < 1) {
            if (code == trueCode) {
                codeDto.setCodeStatus(CodeStatus.APPROVED);
                requestDto.setSuccess(true);
            } else {
                codeDto.setCodeStatus(CodeStatus.FAILED);
                requestDto.setSuccess(false);
            }
            codeDto = save(codeDto);
            requestDto.setCode(codeDto);
            requestDto = requestService.save(requestDto);
            if(requestDto.isSuccess()){
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
//            Date todayDateToConvert = new Date();
//            LocalDateTime todaysDate = todayDateToConvert.toInstant()
//                    .atZone(ZoneId.systemDefault())
//                    .toLocalDateTime();
            UserDto userDto = userService.findById(userId);
            userDto.setBlockDate(todaysDate.plusHours(1));
            UserDto savedUser = userService.update(userDto);
//        codeDto.setUser(savedUser);
            save(codeDto);
        }
        return false;
    }

    @Override
    public CodeDto findByUserId(Long id) {
        CodeMapper codeMapper = new CodeMapper();
        return codeMapper.toDto(codeRepo.findByUserId(id));
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
        return null;
    }

    @Override
    public CodeDto findById(Long id) {
        return null;
    }

    @Override
    public List<CodeDto> findAll() {
        return null;
    }
}
