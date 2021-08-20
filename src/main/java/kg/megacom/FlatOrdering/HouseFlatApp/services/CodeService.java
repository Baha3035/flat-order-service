package kg.megacom.FlatOrdering.HouseFlatApp.services;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.CodeDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.UserDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.Code;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputCodeData;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputUserData;
public interface CodeService extends BaseCrudService<CodeDto, Long>{
    CodeDto findCodeById(Long id);
    InputUserData chekCode(long code_id, long user_code);
    CodeDto saveForCode(CodeDto codeDto);

    void sendCode(UserDto userDtoSaved);

    boolean putCode(long code, Long userId);

    CodeDto findByUserId(Long id);
}
