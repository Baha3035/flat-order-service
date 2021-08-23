package kg.megacom.FlatOrdering.HouseFlatApp.controllers;


import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.HouseDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputHouseData;
import kg.megacom.FlatOrdering.HouseFlatApp.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/house")
public class HouseController implements BaseCrudController<HouseDto, Long> {
    @Autowired
    private HouseService houseService;

    @PostMapping("/save-with-input")
    public ResponseEntity<HouseDto> save(@RequestBody InputHouseData inputHouseData){
        return new ResponseEntity<>(houseService.saveWithInput(inputHouseData), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HouseDto> save(HouseDto houseDto) {
        return null;
    }

    @Override
    public ResponseEntity<HouseDto> update(HouseDto houseDto) {
        return null;
    }

    @Override
    public ResponseEntity<HouseDto> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<HouseDto>> findAll() {
        return null;
    }
}
