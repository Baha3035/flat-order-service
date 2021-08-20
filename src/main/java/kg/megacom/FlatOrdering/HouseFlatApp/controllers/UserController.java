package kg.megacom.FlatOrdering.HouseFlatApp.controllers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.UserDto;
import kg.megacom.FlatOrdering.HouseFlatApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController implements BaseCrudController<UserDto, Long>{
    @Autowired
    private UserService userService;

    @PostMapping("/save-user")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto){
        return new  ResponseEntity<> (userService.save(userDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDto> update(UserDto userDto) {
        return null;
    }

    @Override
    public ResponseEntity<UserDto> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserDto>> findAll() {
        return null;
    }

}
