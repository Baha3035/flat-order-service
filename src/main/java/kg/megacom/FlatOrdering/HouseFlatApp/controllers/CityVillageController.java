package kg.megacom.FlatOrdering.HouseFlatApp.controllers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.CityVillageDto;
import kg.megacom.FlatOrdering.HouseFlatApp.services.CityVillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/cityVillage")
public class CityVillageController implements BaseCrudController<CityVillageDto, Long> {
    @Autowired
    private CityVillageService cityVillageService;

    @Override
    public ResponseEntity<CityVillageDto>  save(@RequestBody CityVillageDto cityVillageDto){
        return new ResponseEntity<>(cityVillageService.save(cityVillageDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CityVillageDto> update(CityVillageDto cityVillageDto) {
        return null;
    }

    @Override
    public ResponseEntity<CityVillageDto> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<CityVillageDto>> findAll() {
        return null;
    }
}
