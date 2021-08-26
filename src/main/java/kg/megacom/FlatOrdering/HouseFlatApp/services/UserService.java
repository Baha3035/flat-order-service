package kg.megacom.FlatOrdering.HouseFlatApp.services;


import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.UserDto;

public interface UserService extends BaseCrudService<UserDto, Long>{
    UserDto findByPhone(String phone);
    void blockTheUser(Long id);
    boolean userIsBlocked(Long id);
    void unblockTheUser(Long id);
}
