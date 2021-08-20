package kg.megacom.FlatOrdering.HouseFlatApp.controllers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.TypeDto;
import kg.megacom.FlatOrdering.HouseFlatApp.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/type")
public class TypeController implements BaseCrudController<TypeDto, Long>{
    @Autowired
    private TypeService typeService;

    @PostMapping("save-type")
    public ResponseEntity<TypeDto> save(@RequestBody TypeDto typeDto){
        return new ResponseEntity<>(typeService.save(typeDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TypeDto> update(TypeDto typeDto) {
        return null;
    }

    @Override
    public ResponseEntity<TypeDto> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<TypeDto>> findAll() {
        return null;
    }
}
