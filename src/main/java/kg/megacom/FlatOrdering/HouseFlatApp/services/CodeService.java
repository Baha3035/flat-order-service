package kg.megacom.FlatOrdering.HouseFlatApp.services;

import kg.megacom.FlatOrdering.HouseFlatApp.enums.CodeStatus;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.CodeDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.UserDto;

public interface CodeService extends BaseCrudService<CodeDto, Long>{
    boolean sendCode(UserDto userDtoSaved);

    boolean putCode(long code, Long userId);

    CodeDto findByUserIdAndCodeStatusNot(Long id, CodeStatus codeStatus);

    boolean updateCode(Long user_id);
}
