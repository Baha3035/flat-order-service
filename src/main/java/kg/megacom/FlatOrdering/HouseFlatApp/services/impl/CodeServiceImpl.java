package kg.megacom.FlatOrdering.HouseFlatApp.services.impl;

import kg.megacom.FlatOrdering.HouseFlatApp.dao.CodeRepo;
import kg.megacom.FlatOrdering.HouseFlatApp.enums.CodeStatus;
import kg.megacom.FlatOrdering.HouseFlatApp.exceptions.CodeIsInConflictException;
import kg.megacom.FlatOrdering.HouseFlatApp.exceptions.UserException;
import kg.megacom.FlatOrdering.HouseFlatApp.mappers.CodeMapper;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.CodeDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.UserDto;
import kg.megacom.FlatOrdering.HouseFlatApp.services.CodeService;
import kg.megacom.FlatOrdering.HouseFlatApp.services.RequestService;
import kg.megacom.FlatOrdering.HouseFlatApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
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
            throw new CodeIsInConflictException("Your code is already approved");
        }
        if(userService.userIsBlocked(userId)){
            throw new UserException("User is blocked");
        }

//        if(codeDto.get)
        long trueCode = codeDto.getCode();
        Date todayDateToConvert = new Date();
        LocalDateTime todaysDate = todayDateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        long range = ChronoUnit.DAYS.between(codeDto.getEndDate(), todaysDate);
        UserDto userDto = userService.findById(userId);
        if (range > 0) {
            sendCode(userDto);
            throw new CodeIsInConflictException("Your code is out of time, so we send you new code");
        }

//        long possibleSeconds = todaysDate.until(codeDto.getEndDate(), ChronoUnit.SECONDS);
//        System.out.println(possibleSeconds);
        int nanos = Duration.between(codeDto.getEndDate() ,todaysDate).getNano();
//        System.out.println(nanos);
        if(nanos > 0) {
            System.out.println(nanos);
            boolean status;
            if (code == trueCode) {
                codeDto.setCodeStatus(CodeStatus.APPROVED);
                status = true;
            } else {
                codeDto.setCodeStatus(CodeStatus.FAILED);
                status = false;
            }
            codeDto = save(codeDto);
            boolean isSuccessful = requestService.sendRequest(codeDto, status);
            if(isSuccessful){
                return true;
            }
            long countOfNotSuccessfulRequests = requestService.countAllByCodeIdAndSuccess(codeDto.getId(), false);
            if(countOfNotSuccessfulRequests > 2){
                codeDto.setCodeStatus(CodeStatus.CANCELED);
                userService.blockTheUser(userId);
                save(codeDto);
//                sendCode(userDto);
//                return false;
            }
        }else {
            codeDto.setCodeStatus(CodeStatus.CANCELED);
            save(codeDto);
            sendCode(userDto);
            return false;
        }
        return false;
    }

    public CodeDto findByUserIdAndCodeStatusNot(Long id, CodeStatus codeStatus) {
        CodeMapper codeMapper = new CodeMapper();
        CodeDto codeDto = codeMapper.toDto(codeRepo.findByUserIdAndCodeStatusNot(id, codeStatus));
        if(codeDto == null){
            throw new CodeIsInConflictException("Your code is cancelled!!!");
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
