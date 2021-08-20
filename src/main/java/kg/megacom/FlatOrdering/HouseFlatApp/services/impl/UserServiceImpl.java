package kg.megacom.FlatOrdering.HouseFlatApp.services.impl;

import kg.megacom.FlatOrdering.HouseFlatApp.dao.UserRepo;
import kg.megacom.FlatOrdering.HouseFlatApp.mappers.UserMapper;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.UserDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.User;
import kg.megacom.FlatOrdering.HouseFlatApp.services.CodeService;
import kg.megacom.FlatOrdering.HouseFlatApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        UserDto userDtoSaved = userMapper.toDto(userRepo.save(userMapper.toEntity(userDto)));
        codeService.sendCode(userDtoSaved);
        return userDtoSaved;
    }

    @Override
    public UserDto update(UserDto userDto) {
        UserMapper userMapper = new UserMapper();
        UserDto userDtoSaved = userMapper.toDto(userRepo.save(userMapper.toEntity(userDto)));
        return userDtoSaved;
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepo.findById(id).orElseThrow(()-> new RuntimeException("Пользователь не найден!"));
        UserMapper userMapper = new UserMapper();
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        return null;
    }
}
