package kg.megacom.FlatOrdering.HouseFlatApp.services;


import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.UserDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.User;

public interface UserService extends BaseCrudService<UserDto, Long>{
    UserDto findByPhone(String phone);
}
