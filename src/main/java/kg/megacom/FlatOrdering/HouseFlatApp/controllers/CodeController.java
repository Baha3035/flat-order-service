package kg.megacom.FlatOrdering.HouseFlatApp.controllers;


import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.CodeDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputCodeData;
import kg.megacom.FlatOrdering.HouseFlatApp.services.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/code")
public class CodeController implements BaseCrudController<CodeDto, Long> {
    @Autowired
    private CodeService codeService;

    @PostMapping("/put-code")
    public ResponseEntity<Boolean>  putCode(@RequestParam Long user_id, @RequestParam Long code){
        return new ResponseEntity<>(codeService.putCode(code, user_id), HttpStatus.CREATED);
    }

    @PostMapping("/update-code")
    public ResponseEntity<Boolean>  updateCode(@RequestParam Long user_id){
        return new ResponseEntity<>(codeService.updateCode(user_id), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CodeDto> save(CodeDto codeDto) {
        return null;
    }

    @Override
    public ResponseEntity<CodeDto> update(CodeDto codeDto) {
        return null;
    }

    @Override
    public ResponseEntity<CodeDto> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<CodeDto>> findAll() {
        return null;
    }
}
