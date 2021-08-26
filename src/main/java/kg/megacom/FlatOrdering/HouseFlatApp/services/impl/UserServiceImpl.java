package kg.megacom.FlatOrdering.HouseFlatApp.services.impl;

import kg.megacom.FlatOrdering.HouseFlatApp.dao.UserRepo;
import kg.megacom.FlatOrdering.HouseFlatApp.exceptions.NotFoundByIdException;
import kg.megacom.FlatOrdering.HouseFlatApp.exceptions.UserException;
import kg.megacom.FlatOrdering.HouseFlatApp.mappers.UserMapper;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.UserDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.User;
import kg.megacom.FlatOrdering.HouseFlatApp.services.CodeService;
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
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CodeService codeService;

    @Override
    public UserDto save(UserDto userDto) {
        UserMapper userMapper = new UserMapper();
        if(findByPhone(userDto.getPhone())==null) {
            UserDto userDtoSaved = userMapper.toDto(userRepo.save(userMapper.toEntity(userDto)));
            codeService.sendCode(userDtoSaved);
            return userDtoSaved;
        }else {
            throw new UserException("User with this phone already exists!!!");
        }
    }

    @Override
    public UserDto update(UserDto userDto) {
        UserMapper userMapper = new UserMapper();
        return userMapper.toDto(userRepo.save(userMapper.toEntity(userDto)));
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepo.findById(id).orElseThrow(()-> new NotFoundByIdException("User is not found!!!"));
        UserMapper userMapper = new UserMapper();
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public UserDto findByPhone(String phone) {
        UserMapper userMapper = new UserMapper();
        return userMapper.toDto(userRepo.findByPhone(phone));
    }
    @Override
    public void unblockTheUser(Long id){
       UserDto userDto = findById(id);
       userDto.setBlockDate(null);
       userDto = update(userDto);
       codeService.sendCode(userDto);
    }
    @Override
    public void blockTheUser(Long id){
        UserDto userDto = findById(id);
        Date todayDateToConvert = new Date();
        LocalDateTime todaysDate = todayDateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        userDto.setBlockDate(todaysDate.plusHours(1));
//        userDto.setBlockDate(todaysDate.minusHours(1));
        update(userDto);
    }

    @Override
    public boolean userIsBlocked(Long id){
        UserDto userDto = findById(id);
        System.out.println(userDto.getBlockDate());
        if(userDto.getBlockDate()==null){
            System.out.println("nullik");
            return false;
        }
        Date todayDateToConvert = new Date();
        LocalDateTime todaysDate = todayDateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        long range = ChronoUnit.DAYS.between(todaysDate, userDto.getBlockDate());

        if(range > 0){
            return false;
        }

        int nanos = Duration.between(userDto.getBlockDate() ,todaysDate).getNano();

//        long possibleSeconds = todaysDate.until(userDto.getBlockDate(), ChronoUnit.SECONDS);
        System.out.println("nanos " + nanos);
        if(nanos < 0){  //made more than 0
            System.out.println("Unblocked");
            unblockTheUser(id);
            return false;
        }
        return true;
    }
}
